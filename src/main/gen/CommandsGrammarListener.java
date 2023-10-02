// Generated from C:/Users/alexc/IdeaProjects/autocomplete/src/main/kotlin\CommandsGrammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CommandsGrammarParser}.
 */
public interface CommandsGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CommandsGrammarParser#classCommand}.
	 * @param ctx the parse tree
	 */
	void enterClassCommand(CommandsGrammarParser.ClassCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsGrammarParser#classCommand}.
	 * @param ctx the parse tree
	 */
	void exitClassCommand(CommandsGrammarParser.ClassCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsGrammarParser#classCommandSetterAndGetter}.
	 * @param ctx the parse tree
	 */
	void enterClassCommandSetterAndGetter(CommandsGrammarParser.ClassCommandSetterAndGetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsGrammarParser#classCommandSetterAndGetter}.
	 * @param ctx the parse tree
	 */
	void exitClassCommandSetterAndGetter(CommandsGrammarParser.ClassCommandSetterAndGetterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsGrammarParser#addParameterInMethod}.
	 * @param ctx the parse tree
	 */
	void enterAddParameterInMethod(CommandsGrammarParser.AddParameterInMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsGrammarParser#addParameterInMethod}.
	 * @param ctx the parse tree
	 */
	void exitAddParameterInMethod(CommandsGrammarParser.AddParameterInMethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsGrammarParser#navigationCommandSimple}.
	 * @param ctx the parse tree
	 */
	void enterNavigationCommandSimple(CommandsGrammarParser.NavigationCommandSimpleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsGrammarParser#navigationCommandSimple}.
	 * @param ctx the parse tree
	 */
	void exitNavigationCommandSimple(CommandsGrammarParser.NavigationCommandSimpleContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsGrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(CommandsGrammarParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsGrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(CommandsGrammarParser.NameContext ctx);
}