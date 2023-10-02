// Generated from C:/Users/alexc/IdeaProjects/autocomplete/src/main/kotlin\CommandsGrammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CommandsGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CommandsGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CommandsGrammarParser#classCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassCommand(CommandsGrammarParser.ClassCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsGrammarParser#classCommandSetterAndGetter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassCommandSetterAndGetter(CommandsGrammarParser.ClassCommandSetterAndGetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsGrammarParser#addParameterInMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddParameterInMethod(CommandsGrammarParser.AddParameterInMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsGrammarParser#navigationCommandSimple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigationCommandSimple(CommandsGrammarParser.NavigationCommandSimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsGrammarParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(CommandsGrammarParser.NameContext ctx);
}