// Generated from Pascal.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PascalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PascalVisitor<T> extends PascalBaseVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PascalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(PascalParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#programHeader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramHeader(PascalParser.ProgramHeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#partBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartBlock(PascalParser.PartBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#varDecBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecBlock(PascalParser.VarDecBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(PascalParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(PascalParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#varSingleDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarSingleDec(PascalParser.VarSingleDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#varListDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarListDec(PascalParser.VarListDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#mainBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainBlock(PascalParser.MainBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(PascalParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(PascalParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(PascalParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(PascalParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(PascalParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(PascalParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#caseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseStatement(PascalParser.CaseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#whileDoLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileDoLoop(PascalParser.WhileDoLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#forDoLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForDoLoop(PascalParser.ForDoLoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#writeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteStatement(PascalParser.WriteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#writeParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteParameter(PascalParser.WriteParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PascalParser#readStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStatement(PascalParser.ReadStatementContext ctx);
}