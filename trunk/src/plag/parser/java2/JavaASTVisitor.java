package plag.parser.java2;

import plag.parser.*;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class JavaASTVisitor extends VoidVisitorAdapter<TokenList> {

	private SourceFileReader sfr;
	private TokenizationConfig config;

	public JavaASTVisitor(SourceFileReader sfr, TokenizationConfig config) {
		this.sfr = sfr;
		this.config = config;
	}

	public void visit(ForStmt n, TokenList arg) {

		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
				sfr.getCharPos(n.getBeginLine() - 1, n.getBeginColumn() - 1), 
				sfr.getCharPos(n.getEndLine() - 1, n.getEndColumn() - 1), "FOR",
				10);
		arg.addToken(tok);

		super.visit(n, arg);

		/*
		 * Token tok2 = new SimpleToken(n.getBeginLine(), n.getEndLine(),
		 * t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn()-1,
		 * t.lineStarts[n.getEndLine() - 1] + n.getEndColumn()-1, "FOR_END",
		 * MyTokens.FOR_END); arg.addToken(tok2);
		 */
	}

	public void visit(BlockStmt n, TokenList arg) {

		/*
		 * Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(),
		 * t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn()-1,
		 * t.lineStarts[n.getEndLine() - 1] + n.getEndColumn()-1,"BLOCK",
		 * MyTokens.BLOCK); arg.addToken(tok);
		 * 
		 * super.visit(n, arg);
		 * 
		 * Token tok2 = new SimpleToken(n.getBeginLine(), n.getEndLine(),
		 * t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn()-1,
		 * t.lineStarts[n.getEndLine() - 1] + n.getEndColumn()-1, "BLOCK_END",
		 * MyTokens.BLOCK_END); arg.addToken(tok2);
		 */

	}

	public void visit(VariableDeclarationExpr n, TokenList arg) {

		/*
		 * Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(),
		 * t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn()-1,
		 * t.lineStarts[n.getEndLine() - 1] +
		 * n.getEndColumn()-1,"VARIABLE_DECLARATION",
		 * MyTokens.VARIABLE_DECLARATION); arg.addToken(tok);
		 * 
		 * super.visit(n, arg); }
		 * 
		 * public void visit(AssignExpr n, TokenList arg) {
		 * 
		 * Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine()-1,
		 * t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn()-1,
		 * t.lineStarts[n.getEndLine() - 1] + n.getEndColumn()-1,"ASSIGNMENT",
		 * MyTokens.ASSIGNMENT); arg.addToken(tok);
		 * 
		 * super.visit(n, arg);
		 */
	}

	public void visit(MethodDeclaration n, TokenList arg) {
		/*
		 * Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(),
		 * t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn()-1,
		 * t.lineStarts[n.getEndLine() - 1] + n.getEndColumn()-1,
		 * "METHOD_DECLARATION", MyTokens.METHOD_DECLARATION);
		 * arg.addToken(tok);
		 * 
		 * super.visit(n, arg);
		 * 
		 * Token tok2 = new SimpleToken(n.getBeginLine(), n.getEndLine(),
		 * t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn()-1,
		 * t.lineStarts[n.getEndLine() - 1] + n.getEndColumn()-1,
		 * "METHOD_DECLARATION_END", MyTokens.METHOD_DECLARATION_END);
		 * arg.addToken(tok2);
		 */
	}

	public void visit(MethodCallExpr n, TokenList arg) {
		/*
		 * Token tok = new MethodInvocationToken(n.getBeginLine(),
		 * n.getEndLine(), t.lineStarts[n.getBeginLine() - 1] +
		 * n.getBeginColumn()-1, t.lineStarts[n.getEndLine() - 1] +
		 * n.getEndColumn()-1, "METHOD_INVOCATION", MyTokens.METHOD_INVOCATION);
		 * arg.addToken(tok);
		 */
	}

}
