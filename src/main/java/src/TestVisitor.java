// Generated from C:/Users/alexc/IdeaProjects/autocomplete/src/main/kotlin\CommandsGrammar.g4 by ANTLR 4.12.0
package src;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TestParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TestVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TestParser#classCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassCommand(TestParser.ClassCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestParser#classCommandSetterAndGetter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassCommandSetterAndGetter(TestParser.ClassCommandSetterAndGetterContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestParser#addParameterInMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddParameterInMethod(TestParser.AddParameterInMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestParser#navigationCommandSimple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNavigationCommandSimple(TestParser.NavigationCommandSimpleContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(TestParser.NameContext ctx);
}