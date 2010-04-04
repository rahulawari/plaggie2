package plag.parser.java2;

import plag.parser.*;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import plag.parser.java2.*;
import plag.parser.java2.tokens.MethodInvocationToken;

public class JavaASTVisitor extends VoidVisitorAdapter<TokenList> {

	private SourceFileReader sfr;
	private TokenizationConfig config;
	private int tokenMarker;

	public JavaASTVisitor(SourceFileReader sfr, TokenizationConfig config) {
		this.sfr = sfr;
		this.config = config;
	}

	public void visit(ForStmt n, TokenList arg) {

		if(config.methodPairing){
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()), "FOR",
					JavaTokenizer.FOR, tokenMarker);
			arg.addToken(tok);			
		}
		else {
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()), "FOR",
					JavaTokenizer.FOR);
			arg.addToken(tok);
		}
		
		super.visit(n, arg);
		
		if(config.methodPairing){
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()), "FOR_END",
					JavaTokenizer.FOR_END, tokenMarker);
			arg.addToken(tok);			
		}
		else {
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()), "FOR_END",
					JavaTokenizer.FOR_END);
			arg.addToken(tok);
		}
	}

	public void visit(BlockStmt n, TokenList arg) {

		/*
		 * Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(),
		 * t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn()-1,
		 * t.lineStarts[n.getEndLine() - 1] + n.getEndColumn()-1,"BLOCK",
		 * MyTokens.BLOCK); arg.addToken(tok);
		 */ 
		 super.visit(n, arg);
		 /* 
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
		
		if(config.methodPairing) {
			if(config.methodList.contains(n.getName())){
				for (int i = 0; i < config.methodList.size(); i++) {
					if(config.methodList.get(i).equals(n.getName())){
						tokenMarker = i+1;
						break;
					}
				}
			}
			
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(),
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()),
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
			"METHOD_DECLARATION", JavaTokenizer.METHOD_DECLARATION, tokenMarker);
			arg.addToken(tok);
		}
		else {
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(),
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()),
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
			"METHOD_DECLARATION", JavaTokenizer.METHOD_DECLARATION);
			arg.addToken(tok);
		}
		
		super.visit(n, arg);
		
		if (config.methodPairing) {
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
					"METHOD_DECLARATION_END", JavaTokenizer.METHOD_DECLARATION_END,
					tokenMarker);
			arg.addToken(tok);
		} else {
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
					"METHOD_DECLARATION_END", JavaTokenizer.METHOD_DECLARATION_END);
			arg.addToken(tok);
		}
		
		tokenMarker = 0;
	}

	public void visit(MethodCallExpr n, TokenList arg) {
		
		if(config.methodPairing){
			if(config.methodInvocationName){
				Token tok = new MethodInvocationToken(n.getBeginLine(), n.getEndLine(), 
						sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
						sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
						"METHOD_INVOCATION", n.getName(), tokenMarker);
				arg.addToken(tok);
			}
			else{
				Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
						sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
						sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
						"METHOD_INVOCATION", JavaTokenizer.METHOD_INVOCATION, tokenMarker);
				arg.addToken(tok);
			}
		}
		else {
			if(config.methodInvocationName){
				Token tok = new MethodInvocationToken(n.getBeginLine(), n.getEndLine(), 
						sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
						sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
						"METHOD_INVOCATION", n.getName());
				arg.addToken(tok);
			}
			else{
				Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
						sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
						sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
						"METHOD_INVOCATION", JavaTokenizer.METHOD_INVOCATION);
				arg.addToken(tok);
			}
		}
	}
}
