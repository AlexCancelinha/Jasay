import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import java.io.FileInputStream
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Tests {

    @Test
    fun test() {
        val listOfCompilationUnits = mutableListOf<CompilationUnit>()
        val directory = "src/test/java"
        Files.walk(Path(directory))
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
        val cu = StaticJavaParser.parse(FileInputStream("src/test/java/ParkingLot.java"))
        val inMethod = cu.findAll(MethodDeclaration::class.java)[0].body.get()
        val line = inMethod.statements[0]
        val list = allMethodPossibilities(line)
        assertEquals(11, list.size)
        assertTrue(list.contains("maxSize"))
        assertTrue(list.contains("currentSize"))
        assertTrue(list.contains("File"))
        assertTrue(list.contains("Cavalo"))

    }
}