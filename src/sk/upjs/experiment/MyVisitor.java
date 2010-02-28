package sk.upjs.experiment;

import plag.parser.Token;
import plag.parser.TokenList;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class MyVisitor extends VoidVisitorAdapter<TokenList> {
	
	private MyTokens t;
	
	public MyVisitor(MyTokens tokens){
		this.t = tokens;
	}
	
	public void visit(ForStmt n, TokenList arg) {

		Token tok = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.FOR);
		arg.addToken(tok);		
		
		super.visit(n, arg);
		
		Token tok2 = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.FOR_END);
		arg.addToken(tok2);	
	}
	
	
	public void visit(BlockStmt n, TokenList arg) {
		
		Token tok = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.BLOCK);
		arg.addToken(tok);
		
		super.visit(n, arg);
		
		Token tok2 = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.BLOCK_END);
		arg.addToken(tok2);
		
	}
	
	public void visit(VariableDeclarationExpr n, TokenList arg){
		
		Token tok = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.VARIABLE_DECLARATION);
		arg.addToken(tok);
		
		super.visit(n, arg);
	}
	
	public void visit(AssignExpr n, TokenList arg) {
		
		Token tok = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.ASSIGNMENT);
		arg.addToken(tok);
		
		super.visit(n, arg);
	}

	public void visit(MethodDeclaration n, TokenList arg) {
		
		Token tok = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.METHOD_DECLARATION);
		arg.addToken(tok);
		
		super.visit(n, arg);
		
		Token tok2 = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.METHOD_DECLARATION_END);
		arg.addToken(tok2);
	}

}



