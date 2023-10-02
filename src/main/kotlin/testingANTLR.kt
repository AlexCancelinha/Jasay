import org.antlr.v4.runtime.BailErrorStrategy
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import src.TestLexer
import src.TestParser

fun main(){
    val charStreams = CharStreams.fromString("create method long factorial")
    val lexer = TestLexer(charStreams)
    val parser = TestParser(CommonTokenStream(lexer))
    parser.errorHandler = BailErrorStrategy()
    val test = parser.classCommand()
    println(test.name().Text())

    val charStreams2 = CharStreams.fromString("add parameter long nice")
    val lexer2 = TestLexer(charStreams2)
    val parser2 = TestParser(CommonTokenStream(lexer2))
    parser2.errorHandler = BailErrorStrategy()
    val test2 = parser2.addParameterInMethod()
    println(test2.name().Text())
}