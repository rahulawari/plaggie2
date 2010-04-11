package plag.parser.java2;

import plag.parser.*;
import japa.parser.ast.*;
import japa.parser.ast.body.*;
import japa.parser.ast.expr.*;
import japa.parser.ast.stmt.*;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import plag.parser.java2.*;
import plag.parser.java2.tokens.*;

public class JavaASTVisitor extends VoidVisitorAdapter<TokenList> {

	private SourceFileReader sfr;
	private TokenizationConfig config;
	private int tokenMarker;

	public JavaASTVisitor(SourceFileReader sfr, TokenizationConfig config) {
		this.sfr = sfr;
		this.config = config;
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
		}
			
		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(),
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()),
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
			"METHOD_DECLARATION", JavaTokenizer.METHOD_DECLARATION, tokenMarker);
		arg.addToken(tok);
		
			
		super.visit(n, arg);
		
		Token tok2 = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
					"METHOD_DECLARATION_END", JavaTokenizer.METHOD_DECLARATION_END,
					tokenMarker);
		arg.addToken(tok2);
				
		tokenMarker = 0;
	}
	
	public void visit(BlockStmt n, TokenList arg) {

		if(n.getStmts().size()>1){
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()), "BLOCK",
					JavaTokenizer.BLOCK, tokenMarker);
			arg.addToken(tok);
			
			super.visit(n, arg);
			
			Token tok2 = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()), "BLOCK_END",
					JavaTokenizer.BLOCK_END, tokenMarker);
			arg.addToken(tok2);			
		}
		
		else super.visit(n, arg);
	}

	public void visit(ForStmt n, TokenList arg) {

		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()), "FOR",
					JavaTokenizer.FOR, tokenMarker);
		arg.addToken(tok);			
				
		super.visit(n, arg);
		
		Token tok2 = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
					sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
					sfr.getCharPos(n.getEndLine(), n.getEndColumn()), "FOR_END",
					JavaTokenizer.FOR_END, tokenMarker);
		arg.addToken(tok2);		
	}
	
	public void visit(MethodCallExpr n, TokenList arg) {
		
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
	
	/*public void visit(IfStmt n, TokenList arg) {
		
		super.visit(n,arg);
	}*/

	

	public void visit(VariableDeclarationExpr n, TokenList arg) {

		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
				sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
				sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
				"VARIABLE_DECLARATION", JavaTokenizer.VARIABLE_DECLARATION, tokenMarker);
		arg.addToken(tok);
		 
		 super.visit(n, arg); 
	}
	
		 
	public void visit(AssignExpr n, TokenList arg) {
		  
		 Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), 
						sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()), 
						sfr.getCharPos(n.getEndLine(), n.getEndColumn()),
						"ASSIGNMENT", JavaTokenizer.ASSIGNMENT, tokenMarker);
		arg.addToken(tok);
		  
		super.visit(n, arg);		 
	}	
}
