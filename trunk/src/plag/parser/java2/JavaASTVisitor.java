package plag.parser.java2;

import plag.parser.*;
import japa.parser.ast.*;
import japa.parser.ast.body.*;
import japa.parser.ast.expr.*;
import japa.parser.ast.stmt.*;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import plag.parser.java2.*;
import plag.parser.java2.tokens.*;

public class JavaASTVisitor extends VoidVisitorAdapter<TokenList> {

	private SourceFileReader sfr;
	private TokenizationConfig config;
	private int tokenMarker;
	private boolean voidMethod;
	
	public JavaASTVisitor(SourceFileReader sfr, TokenizationConfig config) {
		this.sfr = sfr;
		this.config = config;
	}
	
	public void visit(PackageDeclaration n, TokenList arg) {
		
		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()),
				"PACKAGE_DECLARATION", JavaTokenizer.PACKAGE_DECLARATION,
				tokenMarker);
		arg.addToken(tok);
	}
	
	public void visit(ImportDeclaration n, TokenList arg) {
		
		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()),
				"IMPORT_DECLARATION", JavaTokenizer.IMPORT_DECLARATION,
				tokenMarker);
		arg.addToken(tok);
	}

	public void visit(ClassOrInterfaceDeclaration n, TokenList arg) {
		
		if(n.getImplements()!=null){
			Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(), sfr
					.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
					.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length()+n.getBeginColumn()),
					"INTERFACE_DECLARATION", JavaTokenizer.INTERFACE_DECLARATION,
					tokenMarker);
			arg.addToken(tok);

			super.visit(n, arg);

			Token tok2 = new SimpleToken(n.getEndLine(), n.getEndLine(), sfr
					.getCharPos(n.getEndLine(), n.getEndColumn()), sfr.getCharPos(n
					.getEndLine(), n.getEndColumn()), "INTERFACE_DECLARATION_END",
					JavaTokenizer.INTERFACE_DECLARATION_END, tokenMarker);
			arg.addToken(tok2);
		}		
		else {
			Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(), sfr
					.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
					.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length()+n.getEndColumn()),
					"CLASS_DECLARATION", JavaTokenizer.CLASS_DECLARATION,
					tokenMarker);
			arg.addToken(tok);

			super.visit(n, arg);

			Token tok2 = new SimpleToken(n.getEndLine(), n.getEndLine(), sfr
					.getCharPos(n.getEndLine(), n.getEndColumn()), sfr.getCharPos(n
					.getEndLine(), n.getEndColumn()), "CLASS_DECLARATION_END",
					JavaTokenizer.CLASS_DECLARATION_END, tokenMarker);
			arg.addToken(tok2);
		}
		

	}
	
	public void visit(MethodDeclaration n, TokenList arg) {

		if(n.getType().toString().equals("void"))
			voidMethod = true;
			
		if (config.methodPairing) 
			if (config.methodList.contains(n.getName())) 
				for (int i = 0; i < config.methodList.size(); i++) 
					if (config.methodList.get(i).equals(n.getName())) {
						tokenMarker = i + 1;
						break;
					}

		Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length() + n.getBeginColumn()),
				"METHOD_DECLARATION", JavaTokenizer.METHOD_DECLARATION,
				tokenMarker);
		arg.addToken(tok);

		super.visit(n, arg);

		if(voidMethod && arg.getToken(arg.size()-1).getTokenCategory().equals("RETURN")) {
			arg.removeToken(arg.size()-1);	
		}
		Token tok2 = new SimpleToken(n.getEndLine(), n.getEndLine(), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()), sfr.getCharPos(n
				.getEndLine(), n.getEndColumn()), "METHOD_DECLARATION_END",
				JavaTokenizer.METHOD_DECLARATION_END, tokenMarker);
		arg.addToken(tok2);

		tokenMarker = 0;
		voidMethod = false;
	}

	public void visit(BlockStmt n, TokenList arg) {

		if (!arg.getToken(arg.size() - 1).getTokenCategory().equals("METHOD_DECLARATION")) {
			if (config.plagiarismProtection && n.getStmts().size() > 1) {
				Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(),
						sfr.getCharPos(n.getBeginLine(), n.getBeginColumn()),
						sfr.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length() + n.getBeginColumn()),
						"BLOCK", JavaTokenizer.BLOCK, tokenMarker);
				arg.addToken(tok);

				super.visit(n, arg);

				Token tok2 = new SimpleToken(n.getEndLine(), n.getEndLine(),
						sfr.getCharPos(n.getEndLine(), n.getEndColumn()), sfr
								.getCharPos(n.getEndLine(), n.getEndColumn()),
						"BLOCK_END", JavaTokenizer.BLOCK_END, tokenMarker);
				arg.addToken(tok2);
			}
		}

		super.visit(n, arg);
	}

	public void visit(ForStmt n, TokenList arg) {

		if(!config.plagiarismProtection){
			Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length()+n.getBeginColumn()), "FOR",
				JavaTokenizer.FOR, tokenMarker);
			arg.addToken(tok);

			super.visit(n, arg);

			Token tok2 = new SimpleToken(n.getEndLine(), n.getEndLine(), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()), sfr.getCharPos(n
				.getEndLine(), n.getEndColumn()), "FOR_END",
				JavaTokenizer.FOR_END, tokenMarker);
			arg.addToken(tok2);
			}
		
		else {
			if (n.getInit() != null) {
	            for (Expression e : n.getInit()) {
	                e.accept(this, arg);
	            }
	        }
			
			Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(), sfr
					.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
					.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length()+n.getBeginColumn()), "WHILE",
					JavaTokenizer.WHILE, tokenMarker);
			arg.addToken(tok);

			if (n.getCompare() != null) {
	            n.getCompare().accept(this, arg);
	        }
			
			n.getBody().accept(this, arg);			
			
			if (n.getUpdate() != null) {
	            for (Expression e : n.getUpdate()) {
	                e.accept(this, arg);
	            }
	        }
			
			Token tok2 = new SimpleToken(n.getEndLine(), n.getEndLine(), sfr
					.getCharPos(n.getEndLine(), n.getEndColumn()), sfr.getCharPos(n
					.getEndLine(), n.getEndColumn()), "WHILE_END",
					JavaTokenizer.WHILE_END, tokenMarker);
			arg.addToken(tok2);
			
		}
	}
	
	public void visit(WhileStmt n, TokenList arg) {
		
		Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length()+n.getBeginColumn()), "WHILE",
				JavaTokenizer.WHILE, tokenMarker);
		arg.addToken(tok);

		super.visit(n, arg);

		Token tok2 = new SimpleToken(n.getEndLine(), n.getEndLine(), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()), sfr.getCharPos(n
				.getEndLine(), n.getEndColumn()), "WHILE_END",
				JavaTokenizer.WHILE_END, tokenMarker);
		arg.addToken(tok2);
	
	}
	
	public void visit(DoStmt n, TokenList arg) {
		
		Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length()+n.getBeginColumn()), "DO",
				JavaTokenizer.DO, tokenMarker);
		arg.addToken(tok);

		super.visit(n, arg);

		Token tok2 = new SimpleToken(n.getEndLine(), n.getEndLine(), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()), sfr.getCharPos(n
				.getEndLine(), n.getEndColumn()), "DO_END",
				JavaTokenizer.DO_END, tokenMarker);
		arg.addToken(tok2);
	}
	
	public void visit(UnaryExpr n, TokenList arg) {
		
		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()), "ASSIGNMENT",
				JavaTokenizer.ASSIGNMENT, tokenMarker);
		arg.addToken(tok);
	}

	public void visit(MethodCallExpr n, TokenList arg) {

		if (config.methodInvocationName) {
			Token tok = new MethodInvocationToken(n.getBeginLine(), n
					.getEndLine(), sfr.getCharPos(n.getBeginLine(), n
					.getBeginColumn()), sfr.getCharPos(n.getEndLine(), n
					.getEndColumn()), "METHOD_INVOCATION", n.getName(),
					tokenMarker);
			arg.addToken(tok);
		} else {
			Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
					.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
					.getCharPos(n.getEndLine(), n.getEndColumn()),
					"METHOD_INVOCATION", JavaTokenizer.METHOD_INVOCATION,
					tokenMarker);
			arg.addToken(tok);
		}
	}

	
	public void visit(IfStmt n, TokenList arg) {
		  
		Token tok = new SimpleToken(n.getBeginLine(), n.getBeginLine(), sfr
					.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
					.getCharPos(n.getBeginLine(), sfr.getLine(n.getBeginLine()).length() + n.getBeginColumn()),
					"IF", JavaTokenizer.IF,
					tokenMarker);
		arg.addToken(tok);
		n.getCondition().accept(this, arg);
	    n.getThenStmt().accept(this, arg);
	    Token tok2 = new SimpleToken(n.getThenStmt().getEndLine(), n.getThenStmt().getEndLine(), sfr
				.getCharPos(n.getThenStmt().getEndLine(), n.getThenStmt().getEndColumn()), sfr
				.getCharPos(n.getThenStmt().getEndLine(), n.getThenStmt().getEndColumn()),
				"IF_END", JavaTokenizer.IF_END,
				tokenMarker);
	    arg.addToken(tok2);
	    
	    
	    if (n.getElseStmt() != null) {
	    	Token tok3 = new SimpleToken(n.getElseStmt().getBeginLine(), n.getElseStmt().getBeginLine(), sfr
					.getCharPos(n.getElseStmt().getBeginLine(), sfr.getLine(n.getElseStmt().getBeginLine()).indexOf('e')), sfr
					.getCharPos(n.getElseStmt().getBeginLine(), sfr.getLine(n.getElseStmt().getBeginLine()).indexOf('e')+3),
					"ELSE", JavaTokenizer.ELSE,
					tokenMarker);
	    	arg.addToken(tok3);
	    	
	        n.getElseStmt().accept(this, arg);
	        
	        Token tok4 = new SimpleToken(n.getEndLine(), n.getEndLine(), sfr
					.getCharPos(n.getElseStmt().getEndLine(), n.getElseStmt().getEndColumn()), sfr
					.getCharPos(n.getElseStmt().getEndLine(), n.getElseStmt().getEndColumn()),
					"ELSE_END", JavaTokenizer.ELSE_END,
					tokenMarker);
	    	arg.addToken(tok4);
	        }
		   
	  }
	 

	public void visit(VariableDeclarationExpr n, TokenList arg) {

		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()),
				"VARIABLE_DECLARATION", JavaTokenizer.VARIABLE_DECLARATION,
				tokenMarker);
		arg.addToken(tok);

		super.visit(n, arg);
	}

	public void visit(AssignExpr n, TokenList arg) {

		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()), "ASSIGNMENT",
				JavaTokenizer.ASSIGNMENT, tokenMarker);
		arg.addToken(tok);

		super.visit(n, arg);
	}
	
	public void visit(ReturnStmt n, TokenList arg) {
		
		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()), "RETURN",
				JavaTokenizer.RETURN, tokenMarker);
		arg.addToken(tok);

		super.visit(n, arg);
	}
	
	public void visit(BreakStmt n, TokenList arg) {
		
		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()), "BREAK",
				JavaTokenizer.BREAK, tokenMarker);
		arg.addToken(tok);

		super.visit(n, arg);
    }
	
	public void visit(FieldDeclaration n, TokenList arg) {
		Token tok = new SimpleToken(n.getBeginLine(), n.getEndLine(), sfr
				.getCharPos(n.getBeginLine(), n.getBeginColumn()), sfr
				.getCharPos(n.getEndLine(), n.getEndColumn()),
				"VARIABLE_DECLARATION", JavaTokenizer.VARIABLE_DECLARATION,
				tokenMarker);
		arg.addToken(tok);

		super.visit(n, arg);
		
	}
}
