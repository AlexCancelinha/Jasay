import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.ConstructorDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.VariableDeclarator
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.ast.expr.SimpleName
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import com.github.javaparser.ast.stmt.*
import com.github.javaparser.ast.type.PrimitiveType
import com.github.javaparser.ast.type.Type
import com.github.javaparser.ast.type.VoidType
import javax0.levenshtein.Levenshtein
import org.antlr.v4.runtime.BailErrorStrategy
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.TerminalNode
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Event
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Text
import pt.iscte.javardise.external.*
import pt.iscte.javardise.widgets.members.ClassWidget
import src.TestLexer
import src.TestParser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.util.*

/*
Opens two views for the editing same method of the model.
Any edits on either window will be reflected on the other.
 */
const val debug = true
const val port = 8080
const val portFeedback = 8081
const val socketOpened = true
lateinit var textFieldToFeedback :Text
lateinit var textFieldToFeedbackMessageRecieved :Text

class FeedBackThread():Thread(){
    private val serverSocket: ServerSocket = ServerSocket(portFeedback)
    override fun run(){
        println("Waiting for connection in the 8081 port")
        while(true) {
            try{
                val client = serverSocket.accept()
                println("Recebi ligacao")
                val bufferedReader = BufferedReader(InputStreamReader(client.getInputStream()))
                val printWriter = PrintWriter(client.getOutputStream(), true)
                val feedback = bufferedReader.readLine()
                println("Recebi: $feedback")
                if(feedback != null ) {
                    Display.getDefault().syncExec {
                        textFieldToFeedback.text = feedback.replace("\"", "")
                    }
                }
            }catch (e:Exception){
                println(e.stackTrace)
                serverSocket.close()
            }
        }
    }
}
class ServerThread : Thread() {

    private val serverSocket: ServerSocket = ServerSocket(port)
    private lateinit var nodeToChange: Node
    private var classNode: ClassOrInterfaceDeclaration? = null
    private var listOfPossibilities: List<String> = listOf()
    private val listOfSymbols:List<String> = listOf(">",">=","<","<=","+","-","/","*","!=","==")
    private val mapOfSymbols:Map<String, String> = mapOf(
        "greater than" to ">",
        "greater or equal than" to ">=",
        "lower than" to "<",
        "lower or equal than" to "<=",
        "equals" to "==",
        "plus" to "+",
        "minus" to "-",
        "divided" to "/",
        "multiply" to "*",
        "different" to "!="
    )
    private var listOfFunctions:MutableList<Pair<String, Int>> = mutableListOf()
    private var listOfVariables:List<String> = listOf("int", "boolean", "string", "char", "byte", "double", "float", "short")
    private var integersAsString = false
    private val listOfModifications = listOf("delete", "delete line", "set method public", "public", "private", "set method private","add parameter", "add new parameter", "numbers as text", "text to numbers")
    private val mapOfModificationCommands:Map<List<String>,String> = mapOf(
        listOf("delete", "delete line") to "delete",
        listOf("public", "set method public") to "public",
        listOf("private", "set method private") to "private",
        listOf("add parameter", "add new parameter") to "add parameter",
        listOf("numbers as text", "text to numbers") to "change numeric"
        )
    private val listOfGetterAndSetterCommands = listOf("setter", "getter", "constructor")


    fun changeNode(node: Node){
        Display.getDefault().syncExec {
        this.nodeToChange = node
        println(node::class.java)
        listOfPossibilities = getPossibilities(node)
        println("Lista das Possibilidades $listOfPossibilities")
        try {
            //TODO try manhoso para nao dar erro por causa do no ficar sem pais
        val parentClass = getParentClassDeclaration(nodeToChange)
        if(classNode == null){
            classNode = parentClass
            listOfFunctions = getFunctionListOfClass(classNode!!).toMutableList()
        }else{
            if(classNode != parentClass){
                classNode = parentClass
                listOfFunctions = getFunctionListOfClass(classNode!!).toMutableList()
            }
        }

        }catch (_:Exception){
            println("erro para quando nao tem pai")
        }
        }
    }

    private fun changeClass(command: String) {
        changeNode(nodeToChange)
        //confirmar se alguma palavra que vem do cliente reconhecedor de voz está na lista das possibilidades
        // de alteração da linha de codigo
        val commandLower = convertTextualNumbersInDocument(command.lowercase().replace("\"", ""))
        val commandWithNoSymbols = "[^\\w\\s]".toRegex().replace(commandLower, "")
        println("Comando lowercase e sem o espaco de inicio: $commandLower") //"+"
        // Verificar se estamos numa declaracao de variavel
            val isNavigationCommand = navigationCommandCheck(commandLower)
            if (!isNavigationCommand) {
                if (nodeToChange is VariableDeclarator) {
                    changeName(commandWithNoSymbols)
                } else {
                val isCreateMethodOrFieldCommand = createMethodOrFieldGrammarCheck(commandWithNoSymbols)
                if(!isCreateMethodOrFieldCommand){
                    val isCreateGetterSetterConstructorCommand = createGetterSetterConstructorGrammarCheck(commandWithNoSymbols)
                    if(!isCreateGetterSetterConstructorCommand){
                        // Verificar se está na lista das possibilidades
                        val commandTransformed = matchListOfPossibilities(commandWithNoSymbols, listOfPossibilities, true)
                        val commandCheckingGettersAndSetters =
                            matchListOfPossibilities(commandTransformed.first, listOfGetterAndSetterCommands, true)
                        if (commandTransformed.second || commandCheckingGettersAndSetters.second || commandTransformed.first.toLongOrNull() != null
                            || mapOfSymbols.keys.toList().any {
                                it.contains(commandWithNoSymbols.replace(Regex("\\d+"), "").trim())
                            }
                        ) {
                            when (nodeToChange) {
                                is ClassOrInterfaceDeclaration -> {
                                    println("Chegou ao change class or interface o comando: $command")
                                    val commandWithNoSymbols = command.replace(".", "")
                                    val commandWithNoSymbolsSplitted = commandWithNoSymbols.split(" ")
                                    handleCommandInClassWithEquals(commandWithNoSymbolsSplitted)
                                }
                                is Expression -> changeExpression(
                                    nodeToChange as Expression,
                                    commandCheckingGettersAndSetters.first
                                )
                                else -> println(nodeToChange::class.java)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun navigationCommandCheck(command:String): Boolean {
        val commandWithNoSymbols = command.replace(".", "")
        val charStreams = CharStreams.fromString(commandWithNoSymbols)
        val lexer = TestLexer(charStreams)
        val parser = TestParser(CommonTokenStream(lexer))
        parser.errorHandler = BailErrorStrategy()
        var isNavigationCommand: Boolean
        try {
            val navigationCommandParsedInGrammar = parser.navigationCommandSimple()
            val navigationType = navigationCommandParsedInGrammar.NavigationCommandKeyword()
            val numberOfInstructions = navigationCommandParsedInGrammar.Numbers()
            val numberOfRepetitions = numberOfInstructions?.toString()?.toInt() ?: 1
            executeNavigationCommand(navigationType.toString(), numberOfRepetitions)
            isNavigationCommand = true
        } catch (_: Exception) {
            isNavigationCommand = false
        }
        return isNavigationCommand
    }

    private fun createGetterSetterConstructorGrammarCheck(commandWithNoSymbols: String):Boolean{
        val charStreams = CharStreams.fromString(commandWithNoSymbols)
        val lexer = TestLexer(charStreams)
        val parser = TestParser(CommonTokenStream(lexer))
        parser.errorHandler = BailErrorStrategy()
        val classNode:ClassOrInterfaceDeclaration
        if(nodeToChange is ClassOrInterfaceDeclaration){
            classNode = nodeToChange as ClassOrInterfaceDeclaration
        }else {
            classNode = getParentClassDeclaration(nodeToChange)
        }
        var createGetterSetterConstructorGrammar:Boolean
        try {
            val setterGetterCommandParsedInGrammar = parser.classCommandSetterAndGetter()
            val setterOrGetter = setterGetterCommandParsedInGrammar.TypeToCreate()
            val nameOfGetterOrSetter = setterGetterCommandParsedInGrammar.name().Text()
            Display.getDefault().syncExec{
                try {
                    when (setterOrGetter.toString()) {
                        "getter", "gather" -> classNode.getFieldByName(getName(nameOfGetterOrSetter.joinToString(" "))).get()
                            .createGetter()

                        "setter" -> classNode.getFieldByName(getName(nameOfGetterOrSetter.joinToString(" "))).get()
                            .createSetter()

                        "constructor" -> classNode.addConstructor()
                    }
                }catch (_:Exception){

                }
            }
            createGetterSetterConstructorGrammar = true
        } catch (_: Exception) {
            createGetterSetterConstructorGrammar = false
        }
        return createGetterSetterConstructorGrammar
    }

    private fun handleCommandInClassWithEquals(commandWithNoSymbolsSplitted:List<String>){
        try {
            val commandJoin = "${commandWithNoSymbolsSplitted.joinToString(" ").replaceFirst("equals", "=")};"
            val finalCommandSplitted = commandJoin.split(" ")
            val privacyTermListAsModifier = getModifiers(finalCommandSplitted)
            val fieldDeclarationType = finalCommandSplitted[privacyTermListAsModifier.second]
            val indexOfEquals = finalCommandSplitted.indexOf("=")
            val fieldDeclarationName = getName(
                finalCommandSplitted.subList(privacyTermListAsModifier.second + 1, indexOfEquals).joinToString(" ")
            )
            var value = "\$HOLE"
            if (commandWithNoSymbolsSplitted.size > indexOfEquals) {
                value = commandWithNoSymbolsSplitted.subList(
                    indexOfEquals + 1,
                    commandWithNoSymbolsSplitted.size
                ).joinToString(" ")
                value = parseSymbolsAndFunctions(value)
            }
            Display.getDefault().syncExec {
                while (nodeToChange !is ClassOrInterfaceDeclaration) {
                    nodeToChange = nodeToChange.parentNode.get()
                }
                val expressionParsed: Expression = StaticJavaParser.parseExpression(value)
                val classNode = (nodeToChange as ClassOrInterfaceDeclaration)
                classNode.addFieldWithInitializer(fieldDeclarationType, fieldDeclarationName, expressionParsed)
                val fieldAdded = classNode.getFieldByName(fieldDeclarationName).get()
                for (privacyT in privacyTermListAsModifier.first) {
                    fieldAdded.addModifier(privacyT)
                }
                println(classNode)
            }
        }catch (_:Exception){

        }
    }

    private fun createMethodOrFieldGrammarCheck(commandWithNoSymbols: String): Boolean {
        val charStreams = CharStreams.fromString(commandWithNoSymbols)
        val lexer = TestLexer(charStreams)
        val parser = TestParser(CommonTokenStream(lexer))
        parser.errorHandler = BailErrorStrategy()
        var isCreateMethodOrFieldGrammar:Boolean
        try {
            val commandParsedInGrammar = parser.classCommand()
            val typeOfCommand = commandParsedInGrammar.ClassCommandKeyword()
            val privacyTerm = commandParsedInGrammar.PrivacyKeyword()
            val staticTerm = commandParsedInGrammar.StaticKeyword()
            val finalTerm = commandParsedInGrammar.FinalKeyword()
            val typeTerm = commandParsedInGrammar.TypeKeyword()
            val name = getName(commandParsedInGrammar.name().Text().joinToString(" "))
            executeMethodOrFieldCreation(typeOfCommand, privacyTerm, staticTerm, finalTerm,typeTerm, name)
            isCreateMethodOrFieldGrammar = true
        }catch (_:Exception){
            isCreateMethodOrFieldGrammar = false
        }
        return isCreateMethodOrFieldGrammar
    }

    private fun executeMethodOrFieldCreation(typeOfCommand: TerminalNode?, privacyTerm: TerminalNode?, staticTerm: TerminalNode?, finalTerm: TerminalNode?, typeTerm: TerminalNode?, name: String) {
        val classNode = (nodeToChange as ClassOrInterfaceDeclaration)
        if(typeOfCommand.toString() == "method") {
            Display.getDefault().syncExec {
                val methodDeclaration = MethodDeclaration()
                methodDeclaration.name = SimpleName(name)
                methodDeclaration.type = typeParser(typeTerm.toString())
                if (staticTerm != null) {
                    methodDeclaration.isStatic = true
                }
                if (privacyTerm != null) {
                    if(privacyTerm.toString() == "private")
                        methodDeclaration.isPrivate = true
                }
                classNode.members.add(methodDeclaration)
                println("Node : $classNode")
                listOfFunctions.add(Pair(name,0))
            }
        }else{
            Display.getDefault().syncExec {
                val field = classNode.addField(typeTerm.toString(), name)
                if(finalTerm != null) {
                    field.isFinal = true
                }
                if(privacyTerm !=null){
                    field.isPrivate = true
                }
            }
        }
    }


    private fun typeParser(type:String):Type{
        return when(type){
            "int" -> PrimitiveType.intType()
            "double" -> PrimitiveType.doubleType()
            "float" -> PrimitiveType.floatType()
            "char" -> PrimitiveType.charType()
            "byte" -> PrimitiveType.byteType()
            "boolean" -> PrimitiveType.booleanType()
            "long" -> PrimitiveType.longType()
            "short" -> PrimitiveType.shortType()
            else -> VoidType()
        }
    }

    private fun getModifiers(command:List<String>): Pair<MutableList<Modifier.Keyword>, Int> {
        val privacyTermList = mutableListOf<String>()
        var typeIndex = 0
        for(privacyTerm in command){
            if(privacyTerm in visibilityOperators){
                privacyTermList.add(privacyTerm)
                typeIndex+=1
            }else{
                break
            }
        }
        return Pair(modifierParser(privacyTermList),typeIndex)
    }

    private fun modifierParser(listOfModifiersAsString:List<String>):MutableList<Modifier.Keyword>{
        val listOfModifiers = mutableListOf<Modifier.Keyword>()
        for(modifier in listOfModifiersAsString){
            when(modifier){
                "public" -> listOfModifiers.add( Modifier.Keyword.PUBLIC)
                "private" -> listOfModifiers.add(Modifier.Keyword.PRIVATE)
                "static" -> listOfModifiers.add(Modifier.Keyword.STATIC)
                "final" -> listOfModifiers.add(Modifier.Keyword.FINAL)
            }
        }
        return listOfModifiers
    }

    private fun changeMethod(command:String){
        changeNode(nodeToChange)
        val commandLower = convertTextualNumbersInDocument(command.lowercase().replace("\"", ""))
        val commandWithNoSymbols = "[^\\w\\s]".toRegex().replace(commandLower, "")
        println("Comando lowercase e sem o espaco de inicio: $commandLower")
            val isNavigationCommand = navigationCommandCheck(commandLower)
            if(!isNavigationCommand) {
                if(nodeToChange is VariableDeclarator) {
                    changeName(commandWithNoSymbols)
                }else{
                val isAddParameterCommand = addParameterCommandGrammar(commandWithNoSymbols)
                if (!isAddParameterCommand) {
                    val commandToModifications =
                        matchListOfPossibilities(commandWithNoSymbols, listOfModifications, false)
                    println("Comando tratado para mapa dos comandos: $commandToModifications")
                    if (commandToModifications.second) {
                        modifyNode(commandToModifications.first)
                    } else {
                        // Verificar se está na lista das possibilidades
                        val commandTransformed =
                            matchListOfPossibilities(commandWithNoSymbols, listOfPossibilities, true)
                        val commandToSymbols =
                            matchListOfPossibilities(commandWithNoSymbols, mapOfSymbols.keys.toList(), false)
                        val commandWithNormalization = normalizeNumbers(commandTransformed.first)
                        println("Comando tratado para lista das possibilidades: $commandTransformed")
                        println("Comando tratado para mapa dos simbolos: $commandToSymbols")
                        println("commando sem numeros ${commandWithNoSymbols.replace(Regex("\\d+"), "").trim()}")
                        if (commandTransformed.second || commandTransformed.first.toLongOrNull() != null
                            || mapOfSymbols.keys.toList().any {
                                it.contains(commandWithNoSymbols.replace(Regex("\\d+"), "").trim())
                            }
                        ) {
                            println("Encontrou algo na lista de possibilidades")
                            when (nodeToChange) {
                                is Statement -> changeStatement(nodeToChange as Statement, commandWithNormalization)
                                is Expression -> changeExpression(nodeToChange as Expression, commandWithNormalization)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addParameterCommandGrammar(commandWithNoSymbols: String):Boolean{
        val charStreams = CharStreams.fromString(commandWithNoSymbols)
        val lexer = TestLexer(charStreams)
        val parser = TestParser(CommonTokenStream(lexer))
        parser.errorHandler = BailErrorStrategy()
        var isParameterCommandGrammar:Boolean
        try {
            val commandParsedInGrammar = parser.addParameterInMethod()
            val type = commandParsedInGrammar.TypeKeyword()
            val name = getName(commandParsedInGrammar.name().Text().joinToString(" "))
            addParameter(type.toString(), name)
            println("added a parameter $type and $name")

            isParameterCommandGrammar = true
        }catch (_:Exception){
            isParameterCommandGrammar = false
        }
        return isParameterCommandGrammar
    }

    private fun normalizeNumbers(command: String): String {
        var commandLower = command
        val string = "[^\\w\\s]".toRegex().replace(commandLower, "")
        println(string)
        val commandLowerNormalized :String
        if(integersAsString){
            val stringSplitted = commandLower.split(" ")
            for(i in stringSplitted.indices){
                if(stringSplitted[i].toIntOrNull() != null){
                    val numberAsText = english_number(Integer.parseInt(stringSplitted[i]))
                    commandLower = commandLower.replace(stringSplitted[i], numberAsText)
                }else{
                    if(stringSplitted[i].toDoubleOrNull() != null){
                        val firstNumber =  english_number(Integer.parseInt(stringSplitted[i].split(".")[0]))
                        val secondNumber = english_number(Integer.parseInt(stringSplitted[i].split(".")[1]))
                        commandLower=commandLower.replace(stringSplitted[i],"$firstNumber point $secondNumber")
                    }
                }
            }
            commandLowerNormalized = commandLower
        }else{
            commandLowerNormalized = convertTextualNumbersInDocument(commandLower)
        }
        println("Comando normalizado : $commandLowerNormalized")
        return commandLowerNormalized
    }

    private fun modifyNode(modificationString: String) {
        var commandString = ""
        for (entry in mapOfModificationCommands.entries.iterator()) {
            if (entry.key.contains(modificationString)) {
                commandString = entry.value
                break
            }
        }
        println(commandString)
        when(commandString){
            "delete" -> deleteSelectedNode()
            "private", "public" -> changeVisibilityOfMethod(commandString)
            "add parameter" -> addParameter()
            "change numeric" -> {
                integersAsString = !integersAsString
                println("Mudou para $integersAsString")
            }
        }

    }

    private fun addParameter(type:String = "\$HOLE", name:String = "\$HOLE") {
        val methodParent = getParentMethodDeclaration(nodeToChange)
        Display.getDefault().syncExec {
            methodParent.addParameter(type,name)
        }
        for(function in listOfFunctions){
            if(function.first == methodParent.name.toString()){
                listOfFunctions.remove(function)
                listOfFunctions.add(Pair(function.first,function.second+1))
            }
        }
    }

    private fun deleteSelectedNode(){
        Display.getDefault().syncExec {
            when (nodeToChange) {
                is Statement -> {
                    //TODO Rever se esta parte tem que haver confirmação de ser um blockstatement e isso
                    nodeToChange.parentNode.get().remove(nodeToChange)
                }

                is Expression -> {
                    println("entrou na expressão")
                    (nodeToChange as Expression).replace(NameExpr("\$HOLE"))
                }
            }
        }
    }

    private fun changeVisibilityOfMethod(commandString:String){

        Display.getDefault().syncExec {
        val methodParent = getParentMethodDeclaration(nodeToChange)
            when (commandString) {
                "public" -> {
                    methodParent.removeModifier(Modifier.Keyword.PRIVATE)
                    methodParent.addModifier(Modifier.Keyword.PUBLIC)
                }
                "private" -> {
                    methodParent.removeModifier(Modifier.Keyword.PUBLIC)
                    methodParent.addModifier(Modifier.Keyword.PRIVATE)
                }
            }
            println(methodParent)
        }

    }

    private fun matchListOfPossibilities(str: String, listOfPossibilities: List<String>, splitBool: Boolean): Pair<String, Boolean> {
        var bool = false

        if(splitBool) {
            var stringSplitted = "[^\\w\\s]".toRegex().replace(str, "").split(" ")
            for (index in 0..stringSplitted.size) {
                if (index < stringSplitted.size) {
                    val triple = findTheMatch(index, listOfPossibilities, stringSplitted)
                    stringSplitted = triple.second
                    if (triple.third) {
                        bool = true
                    }
                }
            }
            return Pair(stringSplitted.joinToString(" "), bool)
        }else{
            val result = matchFullStringInPossibilities(str, listOfPossibilities)
            return Pair(result.first, result.second)
        }
    }

    private fun matchFullStringInPossibilities(str: String, listOfPossibilities: List<String>): Pair<String, Boolean> {
        var bool = false
        var returnString = str
        val threshold:Int
        if(returnString.length > 2){
            threshold = 1
        }else{
            threshold = 0
        }
        for(i in listOfPossibilities.indices){
            val string = listOfPossibilities[i]
            val distance = Levenshtein.distance(returnString, string)
            if(distance <= threshold ){
                println("match entre $returnString e $string")
                returnString = string
                bool=true
                break;
            }
        }
        return Pair(returnString, bool)
    }

    private fun findTheMatch(indexOfString:Int, listOfPossibilities: List<String>, stringList:List<String>): Triple<String, List<String>, Boolean> {
        var returnString = stringList[indexOfString]
        var stringListReturn = stringList.toMutableList()
        var bool = false
        val threshold:Int
        if(returnString.length > 2){
            threshold = 1
        }else{
            threshold = 0
        }
        for(i in listOfPossibilities.indices){
            val string = listOfPossibilities[i]
            val distance = Levenshtein.distance(returnString, string)
            if(distance <= threshold ){
                println("match entre $returnString e $string")
                returnString = string
                stringListReturn[indexOfString] = string
                bool=true
                break;
            }else{
                if(string.startsWith(returnString)){
                    println(indexOfString)
                    if(indexOfString != stringListReturn.size-1) {
                        val newString = getName(returnString + " " + stringListReturn[indexOfString + 1])
                        val newStringList = stringListReturn.subList(0, indexOfString) + listOf(newString) + stringListReturn.subList(indexOfString+2, stringListReturn.size)
                        val triple = findTheMatch(indexOfString, listOfPossibilities,newStringList)
                        returnString = triple.first
                        stringListReturn = triple.second.toMutableList()
                        if(triple.third) {
                            bool = triple.third
                        }
                    }
                }
            }
        }
        return Triple(returnString, stringListReturn, bool)
    }

    private fun changeName(name:String) {
        Display.getDefault().syncExec {
            val listString = name.split(" ")
            var index = 1
            var stringFinal = listString[0]
            while(index != listString.size){
                stringFinal += listString[index].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                index++
            }
            println("Nome da variavel: $stringFinal")
            (nodeToChange as VariableDeclarator).setName(stringFinal)
        }
    }

    private fun executeNavigationCommand(commandLower: String, numberOfRepetitions: Int) {
        println("Commando recebido para o execute navigation command $commandLower e o numero de repeticoes $numberOfRepetitions")
            for(i in 0 until numberOfRepetitions) {
            val e = Event()
            e.type = SWT.KeyDown
            when (commandLower) {
                "down" -> e.keyCode = SWT.ARROW_DOWN
                "up" -> e.keyCode = SWT.ARROW_UP
                "right" -> e.keyCode = SWT.ARROW_RIGHT
                "left" -> e.keyCode = SWT.ARROW_LEFT
                "enter" -> e.character =  SWT.CR
                else -> e.keyCode = SWT.SHIFT
            }
            if (e.keyCode != SWT.SHIFT) {
                Display.getDefault().post(e)
                sleep(50)
                }
        }
    }

    private fun getName(name: String):String{
        val stringSplitted = name.split(" ")
        var returnString = stringSplitted[0]
        var index = 1
        while(index!=stringSplitted.size){
            returnString += stringSplitted[index].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            index++
        }
        return returnString
    }

    private fun changeExpression(expression: Expression, expressionString: String) {
        var commandString = "[^\\w\\s]".toRegex().replace(expressionString, "")
        commandString = parseSymbolsAndFunctions(commandString)
        if(commandString in listOfSymbols) {
            Display.getDefault().syncExec {
                val parsedNode: Node
                val stringToParse = "$expression $commandString \$HOLE"
                println(stringToParse)
                parsedNode = StaticJavaParser.parseExpression(stringToParse)
                println("Node convertido: $parsedNode")
                expression.replace(parsedNode)
            }
        }else {
                if (commandString[0].toString() in listOfSymbols) {
                    Display.getDefault().syncExec {
                        val parsedNode: Node
                        val stringToParse = "$expression$commandString"
                        println(stringToParse)
                            parsedNode = StaticJavaParser.parseExpression(stringToParse)
                            println("Node convertido: $parsedNode")
                            expression.replace(parsedNode)
                    }
                } else {
                    Display.getDefault().syncExec {
                    val parsedNode: Node
                    try {
                        println(commandString)
                        parsedNode = StaticJavaParser.parseExpression(commandString.replace(";", ""))
                        println("Node convertido: $parsedNode")
                        expression.replace(parsedNode)
                    }catch (_:Exception){
                    }
                }
            }

        }

    }

    private fun parseSymbolsAndFunctions(inputString: String): String {
        var commandString = inputString
        for (entry in mapOfSymbols.entries.iterator()) {
            commandString= commandString.replace(entry.key, entry.value)
        }
        for(pair in listOfFunctions){
            println(pair)
            var stringOfFunction = "${pair.first}("
            for(index in 1..pair.second){
                stringOfFunction+="\$HOLE"

                if(index!=pair.second){
                    stringOfFunction+=","
                }
            }
            stringOfFunction += ")"
            commandString = commandString.replace(pair.first, stringOfFunction)
        }
        println(commandString)
        return commandString
    }

    private fun changeStatement(nodeToChange: Statement, commandWithSymbols:String){
        println("Chegou ao changeStatement o $commandWithSymbols")
        val commandWithNoSymbolsSplitted = commandWithSymbols.replace(".", "").split(" ")

        if(commandWithNoSymbolsSplitted.size == 1){
            //Comandos apenas com uma palavra
            if(commandWithSymbols.replace(".", "") in primitiveTypes){
                //comandos que sejam dos tipos primitivos
                createPrimitiveTypesForStatementChange(nodeToChange, commandWithSymbols)

        }else {
                if(commandWithSymbols.replace(".", "") in controlStatements){
                    //comandos que sejam de palavras reservadas
                    createKeyWordFunctions(nodeToChange, commandWithSymbols)
                }else {
                    handleUniqueFunctionCalls(commandWithSymbols)
                }
            }
        }else {
            if (commandWithNoSymbolsSplitted[0] in controlStatements) {
                var expressionOfKeyword = ""
                if(commandWithNoSymbolsSplitted[0] == "for"){
                    val restOfCommand = commandWithNoSymbolsSplitted.subList(1, commandWithNoSymbolsSplitted.size)
                    val commandToParse = restOfCommand.joinToString(" ").replace("equals", "=")
                    try{
                        println("comando $commandToParse")
                        val variableDeclarationExpr: VariableDeclarationExpr? = StaticJavaParser.parseVariableDeclarationExpr(commandToParse)
                        expressionOfKeyword = variableDeclarationExpr!!.toString()
                    }catch(_ : Exception){

                    }
                }else{
                                expressionOfKeyword = parseSymbolsAndFunctions(commandWithNoSymbolsSplitted.subList(1,commandWithNoSymbolsSplitted.size).joinToString(" "))
                            }
                                    createKeyWordFunctions(nodeToChange, commandWithNoSymbolsSplitted[0], expressionOfKeyword)
                        }else {
                handleNormalStatements(commandWithNoSymbolsSplitted, commandWithSymbols)
            }
        }
    }

    private fun createPrimitiveTypesForStatementChange(nodeToChange: Statement, commandWithSymbols: String) {
        Display.getDefault().syncExec {
            var parsedNode = VariableDeclarationExpr()
            when (commandWithSymbols.replace(".", "")) {
                "int" -> parsedNode = VariableDeclarationExpr(PrimitiveType.intType(),"\$HOLE")
                "double" -> parsedNode = VariableDeclarationExpr(PrimitiveType.doubleType(),"\$HOLE")
                "float" -> parsedNode = VariableDeclarationExpr(PrimitiveType.floatType(),"\$HOLE")
                "char" -> parsedNode = VariableDeclarationExpr(PrimitiveType.charType(),"\$HOLE")
                "boolean" -> parsedNode = VariableDeclarationExpr(PrimitiveType.booleanType(),"\$HOLE")
                "byte" -> parsedNode = VariableDeclarationExpr(PrimitiveType.byteType(), "\$HOLE")
            }
            println(parsedNode)
            val expressionStatement = ExpressionStmt(parsedNode)
            nodeToChange.replace(expressionStatement)
        }
    }

    private fun createKeyWordFunctions(nodeToChange: Statement, commandWithSymbols: String,
                firstArgument:String = "\$HOLE", secondArgument:String = "\$HOLE", thirdArgument:String = "\$HOLE") {
        try {
        if (commandWithSymbols == "else") {
            handleElseCommand()
        } else {
            val firstExpression: Expression
            if (commandWithSymbols == "for") {
                firstExpression = StaticJavaParser.parseVariableDeclarationExpr(firstArgument)
            } else {
                firstExpression = StaticJavaParser.parseExpression(firstArgument)
            }
            val secondExpression: Expression = StaticJavaParser.parseExpression(secondArgument)
            val thirdExpression: Expression = StaticJavaParser.parseExpression(thirdArgument)
            Display.getDefault().syncExec {
                try {
                    val parsedNode: Node = when (commandWithSymbols.replace(".", "")) {
                        "if" -> IfStmt(firstExpression, BlockStmt(), null as Statement?)
                        "for" -> ForStmt(
                            NodeList.nodeList(firstExpression),
                            secondExpression, NodeList.nodeList(thirdExpression), BlockStmt()
                        )

                        "while" -> WhileStmt(firstExpression, BlockStmt())
                        "return" -> ReturnStmt(firstExpression)
                        else -> WhileStmt()
                    }
                    println(nodeToChange)
                    println(nodeToChange.parentNode.get())
                    println(parsedNode)
                    nodeToChange.replace(parsedNode)
                }catch(_:Exception){

                }
            }
        }
        } catch (_: Exception) {
        }
    }

    private fun handleElseCommand() {
        Display.getDefault().syncExec {
            var ifStatement = nodeToChange
            while (ifStatement !is IfStmt) {
                ifStatement = ifStatement.parentNode.get()
            }
            ifStatement.setElseStmt(BlockStmt(NodeList()))
        }
    }

    private fun handleUniqueFunctionCalls(commandWithSymbols:String){
        Display.getDefault().syncExec {
            var stringToParse = commandWithSymbols
            for (pair in listOfFunctions) {
                var stringOfFunction = "${pair.first}("
                for (index in 1..pair.second) {
                    stringOfFunction += "\$HOLE"

                    if (index != pair.second) {
                        stringOfFunction += ","
                    }
                }
                stringOfFunction += ");"
                stringToParse = stringToParse.replace(pair.first, stringOfFunction)

            }
            println(stringToParse)
            try {
                val parsedNode =
                    StaticJavaParser.parseStatement(stringToParse)
                nodeToChange.replace(parsedNode)
            } catch (_: Exception) {
            }
        }
    }

    private fun handleNormalStatements(commandWithNoSymbolsSplitted: List<String>, commandWithSymbols: String){
        Display.getDefault().syncExec {
            var type = commandWithNoSymbolsSplitted[0]
            if (type in listOfVariables) {
                val commandString: String
                val name: String
                var value = "\$HOLE"
                if ("equals" in commandWithNoSymbolsSplitted) {
                    commandString = handleNormalStatementsWithEquals(commandWithNoSymbolsSplitted)
                } else {
                    if(commandWithNoSymbolsSplitted[1] == "array" && commandWithNoSymbolsSplitted.size >= 3){
                        type = "$type []"
                        name = getName(
                            commandWithNoSymbolsSplitted.subList(2, commandWithNoSymbolsSplitted.size)
                                .joinToString(" "))

                    }else {
                        name = getName(
                            commandWithNoSymbolsSplitted.subList(1, commandWithNoSymbolsSplitted.size)
                                .joinToString(" "))
                    }
                    if(type in typesThatNeedParsing){
                        if(type.contains("string")){
                            type= type.substring(0, 1).uppercase() + type.substring(1)
                        }
                    }
                    commandString = "$type $name;"
                }
                println(commandString)
                try {
                    val parsedNode =
                        StaticJavaParser.parseStatement(commandString)
                    nodeToChange.replace(parsedNode)
                } catch (_: Exception) {

                }
            }else{
                handleNormalAssignmentStatements(commandWithNoSymbolsSplitted)
            }
        }
    }

    private fun handleNormalStatementsWithEquals(commandWithNoSymbolsSplitted: List<String>): String {
        var value = "\$HOLE"
        var type = commandWithNoSymbolsSplitted[0]
        val indexOfEquals = commandWithNoSymbolsSplitted.indexOf("equals")
        val name = getName(commandWithNoSymbolsSplitted.subList(1, indexOfEquals).joinToString(" "))
        if (commandWithNoSymbolsSplitted.size > indexOfEquals) {
            value = commandWithNoSymbolsSplitted.subList(
                indexOfEquals + 1,
                commandWithNoSymbolsSplitted.size
            ).joinToString(" ")
            value = parseSymbolsAndFunctions(value)
        }

        println("Comando para ser parsed : $value")
        return "$type $name = $value;"
    }

    private fun handleNormalAssignmentStatements(commandWithNoSymbolsSplitted: List<String>){
        try {
        val indexOfEquals = commandWithNoSymbolsSplitted.indexOf("equals")
        val name = getName(commandWithNoSymbolsSplitted.subList(0, indexOfEquals).joinToString(" "))
        var value = "\$HOLE"
        if (commandWithNoSymbolsSplitted.size > indexOfEquals) {
            value = commandWithNoSymbolsSplitted.subList(
                indexOfEquals + 1,
                commandWithNoSymbolsSplitted.size
            ).joinToString(" ")
            value = parseSymbolsAndFunctions(value)
        }
        val commandParsed = "$name = $value;"
        Display.getDefault().syncExec {

                val parsedNode =
                    StaticJavaParser.parseStatement(commandParsed)
                nodeToChange.replace(parsedNode)
        }
        } catch (_: Exception) {

        }
    }

    override fun run(){
        println("Waiting for connection in the 8080 port")
        try{
        while(true) {

                val client = serverSocket.accept()
                println("recebi ligacao")
                val bufferedReader = BufferedReader(InputStreamReader(client.getInputStream()))
                val printWriter = PrintWriter(client.getOutputStream(), true)
                val fromClient = bufferedReader.readLine()
                println("Recebeu do Reconhecedor: $fromClient")
                Display.getDefault().syncExec{
                    if(fromClient != null)
                        textFieldToFeedbackMessageRecieved.text = fromClient.lowercase().replace("\"", "")
                }
                when(discoverWhereTheStatementIs(nodeToChange)){
                    is MethodDeclaration, is ConstructorDeclaration -> changeMethod(fromClient)
                    is ClassOrInterfaceDeclaration, is CompilationUnit -> changeClass(fromClient)
                }

            }
        }catch (e:Exception){
            serverSocket.close()
            e.printStackTrace()
        }
    }



}

fun main() {
    /*val src = """
public class Student {
	private int number;
	private String name;
	
	public Student(int number, String name) {
		this.number = number;
		this.name = name;
        if(true) {
            i = 1;
            while(true) {
                j= 0;
                }
        }
	}
	public int getNumber() {
		return number;
	}
    public int fact(int n) {
        int x = 2;
        int z = 3;
        if(n == 1 == 3){
            ;
            return 1;
        }else{
            return n * fact(n-1);
        }
    }
    
}
""".trimIndent()*/
    val src = """
public class TestingJasay {
	public String name;
    private int age;

    public int test(){
    }
    
}
""".trimIndent()

    val compilationUnit = loadCompilationUnit(src)
    val clazz = compilationUnit.findMainClass()!!
    val display = Display()
    val server = ServerThread()
    val feedbackThread = FeedBackThread().start()
    if (socketOpened){
        server.start()
    }
    val shell1 = createShell(display, clazz, true, debug, svThread = server)
    shell1.open()
    while (!shell1.isDisposed) {
        if (!display.readAndDispatch()) display.sleep()
    }
    display.dispose()
}

private fun createShell(display: Display, model: ClassOrInterfaceDeclaration, editable: Boolean, debuggerMode:Boolean ,svThread: ServerThread? = null): Shell {
    val shell = Shell(display)
    shell.layout = FillLayout()
    val methodWidget = shell.column {
        layout = FillLayout()
        val classWidget = scrollable {
            ClassWidget(it, model)
        }
        classWidget.enabled = editable
        grid(2) {
            label("Recognizer Feedback")
            textFieldToFeedback = text("No Recognizer found"){
                enabled = false
                fillGridHorizontal()
            }
            label("You said")
            textFieldToFeedbackMessageRecieved = text("No Recognizer found"){
                enabled = false
                fillGridHorizontal()
            }
            if(debuggerMode){
                label("member")
                val memberText = text("") {
                    enabled = false
                    fillGridHorizontal()
                }

                label("node")
            val nodeText = text("") {
                enabled = false
                fillGridHorizontal()
            }

            label("detail")
            val detailText = text("") {
                enabled = false
                fillGridHorizontal()
            }
            classWidget.addFocusObserver { member, node, data ->
                memberText.text = member?.let { member::class.simpleName } ?: ""
                nodeText.text = node?.let { node::class.simpleName } ?: ""
                //println(nodeText.parent)
                detailText.text = data?.let { data::class.java.simpleName } ?: ""
                if(data != null) {
                    svThread?.changeNode(data)
                }
            }
            val textToSend = text(""){
                fillGridHorizontal()
            }
            button("Execute command") {
                sendMessage(textToSend.text)
            }
            }
        }
    }
    // add dispose listener
    methodWidget.shell.pack()
    return shell
}