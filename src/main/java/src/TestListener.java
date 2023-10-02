// Generated from C:/Users/alexc/IdeaProjects/autocomplete/src/main/kotlin\CommandsGrammar.g4 by ANTLR 4.12.0
package src;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestParser}.
 */
public interface TestListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestParser#classCommand}.
	 * @param ctx the parse tree
	 */
	void enterClassCommand(TestParser.ClassCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestParser#classCommand}.
	 * @param ctx the parse tree
	 */
	void exitClassCommand(TestParser.ClassCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestParser#classCommandSetterAndGetter}.
	 * @param ctx the parse tree
	 */
	void enterClassCommandSetterAndGetter(TestParser.ClassCommandSetterAndGetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestParser#classCommandSetterAndGetter}.
	 * @param ctx the parse tree
	 */
	void exitClassCommandSetterAndGetter(TestParser.ClassCommandSetterAndGetterContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestParser#addParameterInMethod}.
	 * @param ctx the parse tree
	 */
	void enterAddParameterInMethod(TestParser.AddParameterInMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestParser#addParameterInMethod}.
	 * @param ctx the parse tree
	 */
	void exitAddParameterInMethod(TestParser.AddParameterInMethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestParser#navigationCommandSimple}.
	 * @param ctx the parse tree
	 */
	void enterNavigationCommandSimple(TestParser.NavigationCommandSimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestParser#navigationCommandSimple}.
	 * @param ctx the parse tree
	 */
	void exitNavigationCommandSimple(TestParser.NavigationCommandSimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(TestParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(TestParser.NameContext ctx);
}