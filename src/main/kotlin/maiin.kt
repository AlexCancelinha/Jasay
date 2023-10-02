
import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.stmt.Statement
import javax0.levenshtein.Levenshtein
import org.eclipse.swt.widgets.Display
import pl.allegro.finance.tradukisto.ValueConverters
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.typeOf


fun getName(name: String):String{
    val stringSplitted = name.split(" ")
    var returnString = stringSplitted[0]
    var index = 1
    while(index!=stringSplitted.size){
        returnString += stringSplitted[index].replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        index++
    }
    return returnString
}

fun extractName(command:String): String {
    val stringSplitted = command.split(" ")
    val indexOfInt = stringSplitted.indexOf("int")
    val indexOfEquals = stringSplitted.indexOf("equals")
    return getName(stringSplitted.subList(indexOfInt+1, indexOfEquals).joinToString(" "))
}

//fun main(){
//    val mapOfSymbols:Map<String, String> = mapOf(
//        "greater than" to ">",
//        "greater or equal than" to ">=",
//        "lower than" to "<",
//        "lower or equal than" to "<=",
//        "equals" to "==",
//        "plus" to "+",
//        "minus" to "-",
//        "divided" to "/",
//        "multiply" to "*",
//        "different" to "!="
//    )
//    val str = getName("fact names")
//    val listOfPossibilities = listOf("if", "for", "while", "switch", "return", "try", "int", "boolean", "string"
//        , "char", "byte", "double", "float", "short", "number", "name", "x", "z", "getNumber", "getName", "factNames", "+",
//        "++", "-", "--", "*", "/",
//    "%", "=", "==", "<", "<=", ">", ">=", "!=", "=", "+=", "-=", "*=", "/=", "%=")
//
//    val booleanA = checkIfStringIsInListOfPossibilities(str,listOfPossibilities) || str.contains("[0-9]".toRegex())
//            || mapOfSymbols.keys.stream().anyMatch(str::contains)
//
//    println(booleanA)
//
//    val newInt = "int get gender equals 2."//"int getGender = 2;"
//    val name = "get name" // getName
//    println(getName(name))
//    println(extractName(newInt))
//
//
//}

//fun main(){
//    val src = """
//public class Student {
//	private int number;
//	private String name;
//
//	public Student(int number, String name) {
//		this.number = number;
//		this.name = name;
//        if(true) {
//            i = 1;
//            while(true) {
//                j= 0;
//                }
//        }
//	}
//	public int getNumber() {
//		return number;
//	}
//	public String getName() {
//		return name;
//	}
//    public int fact(int n) {
//        int x = 2;
//        int z = 3;
//        if(n == 1 == 3){
//            ;
//            return 1;
//        }else{
//            return n * fact(n-1);
//        }
//    }
//
//}
//
//""".trimIndent()
//    val compilationUnit = loadCompilationUnit(src)
//    val clazz = compilationUnit.findMainClass()!!
//    val mds = clazz.findAll(MethodDeclaration::class.java)
//    mds[0].addParameter("Int", "value")
//    // while 7 > 8 -> while( 7 > 8 ){}
//    // if 7 > 8 -> if (7 > 8){}
//    val parsedNode: Node = StaticJavaParser.parseStatement("int \$Hole;")
//    println(parsedNode)
//}

//private fun transformIntoSimilar(str: String, listOfPossibilities: List<String>, splitBool:Boolean): Pair<String,Boolean> {
//    var bool = false
//    var returnString = str
//    if(splitBool) {
//        for (string in str.replace(Regex("[^\\w\\s]"), "").split(" ")) {
//            val pair = levenshteinAndPhoneticDistance(string, listOfPossibilities)
//            returnString = returnString.replace(string,pair.first)
//            if(pair.second) {
//                bool = pair.second
//            }
//        }
//    }else{
//        val pair = levenshteinAndPhoneticDistance(str, listOfPossibilities)
//        returnString = pair.first
//        bool=pair.second
//    }
//    return Pair(returnString, bool)
//}
//
//private fun levenshteinAndPhoneticDistance(str:String, listOfPossibilities: List<String>):Pair<String,Boolean>{
//    var bool = false
//    var returnString = str
//    var threshold: Int
//    val doubleMetaphone = DoubleMetaphone()
//    for (stringToCompare in listOfPossibilities) {
//        threshold = stringToCompare.length / 3
//        doubleMetaphone.maxCodeLen = 100
//        val distance = Levenshtein.distance(str, stringToCompare)
//        if (distance <= threshold || doubleMetaphone.isDoubleMetaphoneEqual(str, stringToCompare)) {
//            println("A string $returnString encontrou match com $stringToCompare")
//            returnString = returnString.replace(str, stringToCompare)
//            bool = true
//
//        }
//    }
//    return Pair(returnString, bool)
//}

//fun joinStringInList(stringSplitted: List<String>, indexOf: Int): List<String> {
//    var newList:List<String>
//    if(indexOf == stringSplitted.size-1){
//        return stringSplitted
//    }
//    val stringJoin = getName(stringSplitted[indexOf]+" "+stringSplitted[indexOf+1])
//    newList = stringSplitted.subList(0,indexOf) + listOf(stringJoin) + stringSplitted.subList(indexOf+2, stringSplitted.size)
//    return newList
//}
//
//

//private fun matchListOfPossibilities(str: String, listOfPossibilities: List<String>, splitBool: Boolean): Pair<String, Boolean> {
//    var bool = false
//    if(splitBool) {
//        var stringSplitted = str.split(" ")
//        for (index in 0..stringSplitted.size) {
//            if (index < stringSplitted.size) {
//                val triple = findTheMatch(index, listOfPossibilities, stringSplitted)
//                stringSplitted = triple.second
//                if (triple.third) {
//                    bool = true
//                }
//            }
//        }
//        return Pair(stringSplitted.joinToString(" "), bool)
//    }else{
//        val result = matchFullStringInPossibilities(str, listOfPossibilities)
//        return Pair(result.first, result.second)
//    }
//}
//
//private fun matchFullStringInPossibilities(str: String, listOfPossibilities: List<String>): Pair<String, Boolean> {
//    var bool = false
//    var returnString = str
//    val threshold:Int
//    if(returnString.length > 2){
//        threshold = 1
//    }else{
//        threshold = 0
//    }
//    for(string in listOfPossibilities){
//        val distance = Levenshtein.distance(returnString, string)
//        if(distance <= threshold){
//            returnString = string
//            bool=true
//            break;
//        }
//    }
//    return Pair(returnString, bool)
//}
//
//fun findTheMatch(indexOfString:Int, listOfPossibilities: List<String>, stringList:List<String>): Triple<String, List<String>, Boolean> {
//    var returnString = stringList[indexOfString]
//    var stringListReturn = stringList.toMutableList()
//    var bool = false
//    val threshold:Int
//    if(returnString.length > 2){
//        threshold = 1
//    }else{
//        threshold = 0
//    }
//    for(string in listOfPossibilities){
//        val distance = Levenshtein.distance(returnString, string)
//        if(distance <= threshold){
//            returnString = string
//            stringListReturn[indexOfString] = string
//            bool=true
//            break;
//        }else{
//            if(string.startsWith(returnString)){
//                if(indexOfString != stringListReturn.size-1) {
//                    val newString = getName(returnString + " " + stringListReturn[indexOfString + 1])
//                    val newStringList = stringListReturn.subList(0, indexOfString) + listOf(newString) + stringListReturn.subList(indexOfString+2, stringListReturn.size)
//                    val triple = findTheMatch(indexOfString, listOfPossibilities, newStringList)
//                    returnString = triple.first
//                    stringListReturn = triple.second.toMutableList()
//                    if(triple.third) {
//                        bool = triple.third
//                    }
//                }
//            }
//        }
//    }
//    return Triple(returnString, stringListReturn, bool)
//}

val phoneticInformationMap = HashMap<List<String>, List<String>>()

private fun matchListOfPossibilities(str: String, listOfPossibilities: List<String>, splitBool: Boolean): Pair<String, Boolean> {
    var bool = false
    val listOfPossibilitiesAsPhonemes:List<String> = if(phoneticInformationMap.containsKey(listOfPossibilities)){
        phoneticInformationMap[listOfPossibilities]!!
    }else{
        phoneticDistance(listOfPossibilities, "", true)
    }
    if(splitBool) {
        var stringSplitted = "[^\\w\\s]".toRegex().replace(str, "").split(" ")
        for (index in 0..stringSplitted.size) {
            if (index < stringSplitted.size) {
                val triple = findTheMatch(index, listOfPossibilities, listOfPossibilitiesAsPhonemes, stringSplitted)
                stringSplitted = triple.second
                if (triple.third) {
                    bool = true
                }
            }
        }
        return Pair(stringSplitted.joinToString(" "), bool)
    }else{
        val result = matchFullStringInPossibilities(str, listOfPossibilities, listOfPossibilitiesAsPhonemes)
        return Pair(result.first, result.second)
    }
}

private fun matchFullStringInPossibilities(str: String, listOfPossibilities: List<String>, listOfPossibilitiesAsPhonemes: List<String>): Pair<String, Boolean> {
    var bool = false
    var returnString = str
    val threshold:Int
    val phoneticOfStringToBeCompared: String = if(phoneticInformationMap.containsKey(listOf(str))){
        phoneticInformationMap[listOf(str)]!![0]
    }else {
        phoneticDistance(listOf(), str, false)[0]
    }
    if(returnString.length > 2){
        threshold = 1
    }else{
        threshold = 0
    }
    for(i in listOfPossibilities.indices){
        val string = listOfPossibilities[i]
        val phoneticString = listOfPossibilitiesAsPhonemes[i]
        val distance = Levenshtein.distance(returnString, string)
        if(distance <= threshold || (phoneticOfStringToBeCompared == phoneticString && phoneticString != "")){
            println("match entre $returnString e $string")
            returnString = string
            bool=true
            break;
        }
    }
    return Pair(returnString, bool)
}

fun findTheMatch(indexOfString:Int, listOfPossibilities: List<String>, listOfPossibilitiesAsPhonemes: List<String>, stringList:List<String>): Triple<String, List<String>, Boolean> {
    var returnString = stringList[indexOfString]
    val returnStringPhonetic = if(phoneticInformationMap.containsKey(listOf(returnString))){
        phoneticInformationMap[listOf(returnString)]!![0]
    }else{
        phoneticDistance(listOf(),returnString, false)[0]
    }
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
        val stringPhonetic = listOfPossibilitiesAsPhonemes[i]
        val distance = Levenshtein.distance(returnString, string)
        if(distance <= threshold || (stringPhonetic == returnStringPhonetic && stringPhonetic != "" && returnString!="")){
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
                    val triple = findTheMatch(indexOfString, listOfPossibilities, listOfPossibilitiesAsPhonemes, newStringList)
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

// listOfPossibilities + string
// string = get nu equals 2
// get
// verifica se a palavra está na lista
// se está pronto true
// se nao verifica se a palavra está no inicio de alguma das da lista de possibilidades.
// e é concatenada com a proxima string -> getNumber e sempre assim recursividade

fun isNumeric(input: String): Boolean {
    return input.toLongOrNull() != null
}

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

    fun phoneticDistance(listOfPossibilities: List<String>, commandString: String, matchList :Boolean): List<String> {
        val client = Socket("localhost",8090)
        try{
            val dataInputStream = DataInputStream(client.getInputStream())
            val stringToSend = "[${listOfPossibilities.joinToString(",")}];${commandString};${matchList}"
            val dataOutputStream = DataOutputStream(client.getOutputStream())
            dataOutputStream.writeUTF(stringToSend)
            val phonemeResponse = dataInputStream.readAllBytes().decodeToString()
            val phonemeResponseCleaned = phonemeResponse.substring(1, phonemeResponse.length-1)
            println("Recebi: $phonemeResponse")
            client.close()
            return if(matchList) {
                val phonemeList = phonemeResponseCleaned.split(",")
                println("Lista de fonemas = $phonemeList")
                if(!phoneticInformationMap.contains(listOfPossibilities)){
                    phoneticInformationMap[listOfPossibilities] = phonemeList
                }
                phonemeList
            }else{
                listOf(phonemeResponseCleaned)
            }

        }catch (e:Exception){
            client.close()
        }
        return listOf()
    }

fun testing(text:String): String {
    var returnString= text
    val intConverter = ValueConverters.ENGLISH_INTEGER
    if(text.toIntOrNull() != null){
        val valueAsWords = intConverter.asWords(text.toInt())
        returnString = valueAsWords
    }else{
        if(text.toDoubleOrNull() != null){
            val stringSplitted = text.split(".")
            val firstPart = intConverter.asWords(stringSplitted[0].toInt())
            val secondPart = intConverter.asWords(stringSplitted[1].toInt())
            returnString = "$firstPart point $secondPart"
        }
    }
    return returnString
}

fun main(){


    val listOfPossibilities = listOf("if", "for", "while", "switch", "return", "try", "int", "boolean", "string",
        "char", "byte", "double", "float", "short", "number", "name", "x", "z", "getNumber", "getName", "parkingLot", "factNames"

    , "+",
            "++", "-", "--", "*", "/",
        "%", "=", "==", "<", "<=", ">", ">=", "!=", "=", "+=", "-=", "*=", "/=", "%=")
    val mapOfSymbols:Map<String, String> = mapOf(
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

    val goodCommand = "greatfnnner than"
    val badCommand = "get number equals 2" //getNumber equals 2
    val command = "inc new variable equals 2" // ParkingLot lot equals new ParkingLot
    val commandGreaterThan = "greater than"
    val co = "get number"
    //println(transformIntoSimilar(goodCommand, mapOfSymbols.keys.toList(), false))
    //println(transformIntoSimilar(badCommand, listOfPossibilities,true))
//    println(joinStringInList(badCommand.split(" "), 2))
//    println(findTheMatch(0, listOfPossibilities, command.split(" ")))
    println(matchListOfPossibilities(command, listOfPossibilities, true))
    println(matchListOfPossibilities(commandGreaterThan, mapOfSymbols.keys.toList(), false))
    println(matchListOfPossibilities(co, listOfPossibilities, true))
//    println(isNumeric("35"))
//    println(isNumeric("inc 35"))
//    println(isNumeric(" 7 < 3"))
//
//    val string = "methodCall(\$hole)"
//    val parsed: Expression = StaticJavaParser.parseExpression(string)
//    val parsed2: Statement = StaticJavaParser.parseStatement("getNumber();")
//    println(parsed)
////    println(parsed2)
//    println(listOfPossibilities.toString())
//    executePythonPhoneticTransformation(listOfPossibilities,"", true)
//    testing("two for two point five in 12")
//    val number = "12345.23"
//    println(testing(number))
//
//    val testingParse: VariableDeclarationExpr? = StaticJavaParser.parseVariableDeclarationExpr("int i = 0")
//    println(testingParse!!::class.java)
//    println("$testingParse e ${testingParse::class.java}")
    //phoneticDistance(listOfPossibilities, "nice", true)
}