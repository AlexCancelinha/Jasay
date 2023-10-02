import com.github.javaparser.*
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.Node
import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.*
import com.github.javaparser.ast.expr.BinaryExpr
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.VariableDeclarationExpr
import com.github.javaparser.ast.stmt.*
import com.github.javaparser.ast.type.Type
import java.lang.reflect.Field

import java.nio.file.Files
import kotlin.io.path.Path


// Primitive Types
val primitiveTypes = listOf("int", "boolean", "string", "char", "byte", "double", "float", "short")

val typesThatNeedParsing = listOf("string")

//  KEYWORDS
val indepententKeywords = listOf("if", "for", "while", "switch", "return", "try") + primitiveTypes

val controlStatements = listOf("if", "for", "while", "switch", "return", "try", "else")

val visibilityOperators = listOf("public", "private", "static")

// dependent on context:
val insideDependentKeywords = listOf("break", "continue")
val afterDependentKeywords = listOf("else", "catch", "finally")

// OPERATORS
val expressionOperators = listOf("+", "++", "-", "--", "*", "/", "%",
    "=", "==", "<", "<=", ">", ">=", "!=")

// dependent on context
val declarationOperators = listOf("=", "+=", "-=", "*=", "/=", "%=")

val listOfClassesOfProject = mutableListOf<ClassOrInterfaceDeclaration>()

val listOfFunctionsOfProject = mutableListOf<MethodDeclaration>()


fun allMethodPossibilities(stmt: Statement):List<String>{
    val listOfPossibilities = mutableListOf<String>() // talvez melhor separar as keywords
    val parent:Node
    val methodBlockStatement:Node
    var line = -1
    if(stmt.begin.isPresent) {
        line = stmt.begin.get().line
    }
    val method = getParentMethodDeclaration(stmt)
    methodBlockStatement = method.body.get()
    parent = getParentClassDeclaration(stmt)
    listOfPossibilities.addAll(getfieldDeclNames(parent))
    listOfPossibilities.addAll(getVariableDeclNames(parent, methodBlockStatement, line))
    listOfPossibilities.addAll(getParametersFromMethod(method))
    listOfPossibilities.addAll(getImportDeclNames(parent))
    listOfPossibilities.addAll(getClassDeclNames(parent))
    listOfPossibilities.addAll(getClassFunctionNames(parent, true))
    return listOfPossibilities
}

private fun getParametersFromMethod(method:MethodDeclaration): List<String> {
    val result = mutableListOf<String>()
    for(parameter in method.parameters){
        result.add(parameter.name.toString())
    }
    return result
}

private fun getClassFunctionNames(parentNode: Node, filter : Boolean):List<String>{
    val result = mutableListOf<String>()
    for(functions in (parentNode.parentNode.get() as CompilationUnit).findAll(MethodDeclaration::class.java)){
        if(filter) {
            result.add(functions.name.toString())
        }
    }
    return result
}

private fun getClassDeclNames(parentNode: Node): List<String> {
    val result = mutableListOf<String>()
    for(classDeclarationOrInterface in listOfClassesOfProject){
        result.add(classDeclarationOrInterface.name.toString())
    }
    return result
}

private fun getImportDeclNames(parentNode: Node): List<String>{
    val result = mutableListOf<String>()
    for(importDeclaration in (parentNode.parentNode.get() as CompilationUnit).imports){
        result.add(importDeclaration.name.asString().split(".").last())

    }
    return result
}

private fun getVariableDeclNames(parentNode: Node, methodBlockStmt: Node, line: Int): List<String> {
    val result = mutableListOf<String>()
    for (variableDeclaration in methodBlockStmt.findAll(VariableDeclarator::class.java)){
        var lineVariable = 0
        if(variableDeclaration.begin.isPresent) {
            lineVariable = variableDeclaration.begin.get().line
        }
        if(lineVariable <= line || line == -1)
            result.add(variableDeclaration.name.toString())
    }
    println("$result and line $line")
    return result
}

private fun getfieldDeclNames(parentNode: Node): List<String>{
    val result = mutableListOf<String>()
    for (fieldDeclaration in parentNode.findAll(FieldDeclaration::class.java)) {
        result.add(fieldDeclaration.variables[0].name.toString())
    }
    return result
}

fun getParentMethodDeclaration(statementNode:Node): MethodDeclaration {
    tailrec fun aux(node: Node): MethodDeclaration =
        if(node.hasParentNode()) {
            if (node.parentNode.get() is MethodDeclaration)
                node.parentNode.get() as MethodDeclaration
            else aux(node.parentNode.get())
        }else MethodDeclaration()
    return aux(statementNode)
}

fun getParentClassDeclaration(statementNode:Node): ClassOrInterfaceDeclaration {

    tailrec fun aux(node: Node): ClassOrInterfaceDeclaration =
        if(node.parentNode.get() is ClassOrInterfaceDeclaration)
            node.parentNode.get() as ClassOrInterfaceDeclaration
        else aux(node.parentNode.get())

    return aux(statementNode)
}

fun getParentsTillMethod(statementNode:Node, listOfParents:MutableList<Node>): MutableList<Node> {
    if(statementNode.hasParentNode()){
        if(statementNode.parentNode.get() is MethodDeclaration){
            return listOfParents
        }
        if(statementNode.parentNode.get() !is BlockStmt) {
            listOfParents.add(statementNode.parentNode.get())
        }
        getParentsTillMethod(statementNode.parentNode.get(), listOfParents)
    }
    return mutableListOf()
}

fun checkIfHasLoops(listOfParents: MutableList<Node>):List<String>{
    var foundLoop = false
    listOfParents.forEach {
        if(it is WhileStmt || it is ForStmt){
            foundLoop = true
        }
    }
    if(foundLoop){
        return insideDependentKeywords
    }
    return listOf()
}

fun getInputPossibilities(line: Statement, input: String): List<String> {
    val result = mutableListOf<String>()
    val possibilities = getKeywordsPossibilities(line) + getExpressionPossibilities(line)
    for (possibility in possibilities)
        if (possibility.startsWith(input))
            result.add(possibility)
    return result
}

fun getKeywordsPossibilities(line: Statement): List<String> =
    indepententKeywords +
            getAfterKeywordsPossibilities(line) +
            getParentKeywordsPossibilities(line)

fun getExpressionPossibilities(stmt: Statement): List<String> =
    allMethodPossibilities(stmt) + expressionOperators + declarationOperators

fun getParentKeywordsPossibilities(statement: Statement): List<String>{
    val listOfParents = mutableListOf<Node>()
    getParentsTillMethod(statement, listOfParents)
    return checkIfHasLoops(listOfParents)
}

fun getAfterKeywordsPossibilities(stmt: Statement): List<String> =
    when(stmt){
        is IfStmt -> listOf(afterDependentKeywords[afterDependentKeywords.indexOf("else")])
        is TryStmt -> listOf(
            afterDependentKeywords[afterDependentKeywords.indexOf("catch")],
            afterDependentKeywords[afterDependentKeywords.indexOf("finally")])
        else -> listOf()
    }

fun getExpressionPossibilities(expr: Expression): List<String> =
     when (expr) { // WARNING: expr.parentNode might return another Expression
        is BinaryExpr -> allMethodPossibilities(expr.parentNode.get() as Statement) +
                    expressionOperators
        is VariableDeclarationExpr -> allMethodPossibilities(expr.parentNode.get() as Statement) +
                expressionOperators + declarationOperators

        else -> listOf()
    }

fun getInputPossibilitiesForMethod(line: Node): List<String> =

    when(line){
        is Statement -> getKeywordsPossibilities(line) + getExpressionPossibilities(line)
        is Type -> getPossibilitiesToType(line)
        is Expression -> getPossibilitiesForExpression(line)
        else -> listOf()
    }

fun getPossibilitiesForExpression(expression: Expression): List<String> {
    println("Expressão selecionada: $expression")
    val listOfPossibilities = mutableListOf<String>()
    val parent: Node
    val methodBlockStatement: Node
    var line = -1
    if(expression.begin.isPresent) {
        line = expression.begin.get().line
    }
    val method = getParentMethodDeclaration(expression)
    methodBlockStatement = method.body.get()
    parent = getParentClassDeclaration(expression)
    val methodParameters = method.parameters.forEach { listOfPossibilities.add(it.name.toString()) }
    listOfPossibilities.addAll(getfieldDeclNames(parent))
    listOfPossibilities.addAll(getVariableDeclNames(parent, methodBlockStatement, line))
    for(functions in (parent.parentNode.get() as CompilationUnit).findAll(MethodDeclaration::class.java)){
        if(!functions.type.isVoidType) {
            listOfPossibilities.add(functions.name.toString())
        }
    }
    return listOfPossibilities + expressionOperators

}


fun getPossibilitiesToType(type: Type): List<String> {
    val listOfNamesOfClasses = mutableListOf<String>()
    for(classDeclarationOrInterface in listOfClassesOfProject){
        listOfNamesOfClasses.add(classDeclarationOrInterface.name.toString())
    }
    return primitiveTypes + listOfNamesOfClasses
}

fun getInitialInformationOfProject(directoryOfProject:String){
    val listOfCompilationUnits = mutableListOf<CompilationUnit>()
    Files.walk(Path(directoryOfProject))
        .filter { it.toFile().isFile && it.toFile().name.endsWith(".java") }
        .forEach {
            val compilationUnit = StaticJavaParser.parse(it)
            listOfCompilationUnits.add(compilationUnit)
        }
    listOfCompilationUnits.forEach { cu ->
        for (classOrInterfaceDeclaration in cu.findAll(ClassOrInterfaceDeclaration::class.java)) {
            listOfClassesOfProject.add(classOrInterfaceDeclaration)
        }
    }
    listOfCompilationUnits.forEach { cu ->
        for (classOrInterfaceDeclaration in cu.findAll(ClassOrInterfaceDeclaration::class.java)) {
            for(methodDeclaration in classOrInterfaceDeclaration.findAll(MethodDeclaration::class.java)){
                listOfFunctionsOfProject.add(methodDeclaration)
            }
        }
    }
}

fun discoverWhereTheStatementIs(statement:Node): Node? {
    if(statement.hasParentNode()){
        var parent = statement.parentNode.get()
        while (parent !is MethodDeclaration && parent !is ClassOrInterfaceDeclaration) {
            if(parent.hasParentNode()) {
                parent = parent.parentNode.get()
            }else{
                break
            }
        }
        return parent
    }
    return null
}

fun getPossibilities(statement: Node): List<String> {
    //discover where the statement is p.e. if is inside a method, or if is inside a class ...
    var parent = discoverWhereTheStatementIs(statement)
    //checking if is a class or a method
    return when(parent){
        is MethodDeclaration -> getInputPossibilitiesForMethod(statement)
        is ClassOrInterfaceDeclaration, is CompilationUnit -> allClassPossibilities(statement)
        else -> {
            listOf()
        }
    }
}

fun getFunctionListOfClass(classNode: ClassOrInterfaceDeclaration): List<Pair<String, Int>> {
    val listOfFunctions = mutableListOf<Pair<String, Int>>()
    classNode.findAll(MethodDeclaration::class.java).forEach { method ->
        val pair = Pair(method.name.toString(), method.parameters.size)
        listOfFunctions.add(pair)
    }
    return listOfFunctions
}

//TODO
fun allClassPossibilities(statement: Node): List<String> {
    return when(statement){
        is ClassOrInterfaceDeclaration, is FieldDeclaration -> visibilityOperators + primitiveTypes + listOf("void") + listOfClassesOfProject.map { it.name.toString() }
        is Type -> getPossibilitiesToType(statement)
        is Expression -> getPossibilitiesForExpression(statement)
        is Modifier -> visibilityOperators
        else -> listOf()
    }
}

