import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ForStmt
import com.github.javaparser.ast.stmt.IfStmt
import com.github.javaparser.ast.stmt.TryStmt
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.FileInputStream

internal class FunctionsKtTest {

    @Test
    fun getInputPossibilities() {
        val cu = StaticJavaParser.parse(FileInputStream("src/test/java/ParkingLot.java"))
        val inMethod = cu.findAll(MethodDeclaration::class.java)[1].body.get()
        val line = inMethod.statements[0]

        val currList = getInputPossibilities(line, "curr")
        assertEquals(1, currList.size)
        assertEquals("currentSize", currList[0])
        assertFalse(currList.contains("char"))

        val fList = getInputPossibilities(line ,"f")
        assertEquals(2, fList.size)
        assertTrue(fList.contains("for"))
        assertTrue(fList.contains("float"))
        assertFalse(currList.contains("maxSize"))

        val xList = getInputPossibilities(line ,"x")
        assertEquals(1, xList.size)
        assertTrue(xList.contains("x"))
        assertFalse(xList.contains("x1"))
        assertFalse(currList.contains("String"))

    }

    @Test
    fun getExpressionPossibilities() {
        val cu = StaticJavaParser.parse(FileInputStream("src/test/java/ParkingLot.java"))
        val inMethod = cu.findAll(MethodDeclaration::class.java)[1].body.get()

        val exprLine = inMethod.statements[0]
        val exprList = getExpressionPossibilities(exprLine)
        assertTrue(exprList.contains("currentSize"))
        assertTrue(exprList.contains("x"))
        assertTrue(exprList.contains("!="))
        assertTrue(exprList.contains("*="))
        assertFalse(exprList.contains("string"))
        assertFalse(exprList.contains("double"))

        val condLine = inMethod.statements[2]
        val condList = getExpressionPossibilities(condLine)
        assertTrue(condList.contains("maxSize"))
        assertTrue(condList.contains("x1"))
        assertTrue(condList.contains(">="))
        assertFalse(condList.contains("+="))
        assertFalse(exprList.contains("float"))
        assertFalse(exprList.contains("char"))
    }

    @Test
    fun getAfterKeywordsPossibilities(){
        // Variable Declarations
        val cu = StaticJavaParser.parse(FileInputStream("src/test/java/HorseThread.java"))
        val clazz = cu.findAll(ClassOrInterfaceDeclaration::class.java)[1]
        val inMethod = clazz.findAll(MethodDeclaration::class.java)[1].body.get()

        // Test Statements
        val tryStmt = inMethod.statements[0] as TryStmt
        val ifStmt = tryStmt.tryBlock.statements[0]
        val whileStmt = tryStmt.tryBlock.statements[1]

        val tryList = getAfterKeywordsPossibilities(tryStmt)
        assertEquals(2, tryList.size)
        assertFalse(tryList.contains("else"))
        assertTrue(tryList.contains("catch"))
        assertTrue(tryList.contains("finally"))

        val ifList = getAfterKeywordsPossibilities(ifStmt)
        assertEquals(1, ifList.size)
        assertTrue(ifList.contains("else"))
        assertFalse(ifList.contains("catch"))
        assertFalse(ifList.contains("finally"))

        val whileList = getAfterKeywordsPossibilities(whileStmt)
        assertEquals(0, whileList.size)
        assertFalse(whileList.contains("else"))
        assertFalse(whileList.contains("catch"))
        assertFalse(whileList.contains("finally"))
    }

    @Test
    fun getParentKeywordsPossibilities(){
        val cu = StaticJavaParser.parse(FileInputStream("src/test/java/ParkingLot.java"))
        val inMethod = cu.findAll(MethodDeclaration::class.java)[1].body.get()
        val ifMethod = inMethod.findAll(IfStmt::class.java)[0] as IfStmt
        val forMethod = (ifMethod.thenStmt as BlockStmt).findAll(ForStmt::class.java)[0] as ForStmt
        val statement = (forMethod.body as BlockStmt).getStatement(0)
        val list = getParentKeywordsPossibilities(statement)
        assertTrue( list.contains("break"))
        assertTrue(list.contains("continue"))
    }


    @Test
    fun getPossibilitiesForExpressions(){
        getInitialInformationOfProject("src/test/java")
        val cu = StaticJavaParser.parse(FileInputStream("src/test/java/ParkingLot.java"))
        val blockStmt = cu.findAll(MethodDeclaration::class.java)[1].body.get()
        val variableDeclaratorValue = blockStmt.statements[1].asExpressionStmt().expression.asVariableDeclarationExpr().getVariable(0).initializer.get()
        val listOfPossibilitiesExpression = getPossibilities(variableDeclaratorValue)
        assertTrue( listOfPossibilitiesExpression.contains("maxSize"))
        assertFalse(listOfPossibilitiesExpression.contains("full"))
        assertTrue(listOfPossibilitiesExpression.contains("HorseThread.testFD"))
        assertTrue(listOfPossibilitiesExpression.contains("teste(int x)"))
        assertTrue(listOfPossibilitiesExpression.contains("HorseThread.test(int x2)"))
    }
}