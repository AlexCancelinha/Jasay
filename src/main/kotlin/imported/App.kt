package imported

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import java.io.File

fun main() {
    val file = File("src/test/java/POO_Heranca/Rectangle.java")
    val compiler = StaticJavaParser.parse(file)
    val clazz = compiler.findFirst(
        ClassOrInterfaceDeclaration::class.java
    ).orElseThrow { NoSuchElementException() }

    val method = clazz.findAll(MethodDeclaration::class.java)[0]
    val stmt = method.body.get().statements[3]
    Temp(clazz).getAllMethodPossibilities(stmt).entries.forEach { println("${it.key.nameAsString}:\n${it.value.printWords()}") }
}