package sk.upjs.exper_vnorenie;
import java.util.ArrayList;
import java.util.List;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.expr.AssignExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

public class MyVisitor extends VoidVisitorAdapter<TokenList> {
	
	private MyTokens t;
	private List<MethodDeclaration> metody = new ArrayList<MethodDeclaration>();
	
	private int pocetVnoreni=0;
	
	private int maxVnorenie=0;
	
	private boolean vnarajDoMetod=false;
	
	
	public MyVisitor(MyTokens tokens){
		this.t = tokens;
	}
	
	public void visit(ForStmt n, TokenList arg) {
		System.out.println("FRiadok " + n.getBeginLine());
		System.out.println("FStlpec " + n.getBeginColumn());
		
		
		Token tok = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.FOR);
		arg.addToken(tok);		
		
		pocetVnoreni++;
		
		System.out.println("Vnorenie "+pocetVnoreni);
		super.visit(n, arg);
		
		if(pocetVnoreni>maxVnorenie)
			maxVnorenie = pocetVnoreni;
		pocetVnoreni--;
		
		Token tok2 = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.FOR_END);
		arg.addToken(tok2);	
	}
	
	
	public int getPocetVnoreni() {
		return pocetVnoreni;
	}
	
	public int getMaxVnorenie() {
		return maxVnorenie;
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
		
		pocetVnoreni = 0;
		
		maxVnorenie = 0;
		Token tok = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.METHOD_DECLARATION);
		arg.addToken(tok);
		
		System.out.println("MRiadok " + n.getBeginLine());
		System.out.println("MStlpec " + n.getBeginColumn());
		metody.add(n);
		
		if (vnarajDoMetod)
			super.visit(n, arg);
		
		Token tok2 = new Token(n.getBeginLine(), n.getEndLine(),
				t.lineStarts[n.getBeginLine() - 1] + n.getBeginColumn(),
				t.lineStarts[n.getEndLine() - 1] + n.getEndColumn(),
				MyTokens.METHOD_DECLARATION_END);
		arg.addToken(tok2);
	}

	
	public void setVnarajDoMetod(boolean vnarajDoMetod) {
		this.vnarajDoMetod = vnarajDoMetod;
	}

	public List<MethodDeclaration> getMetody() {
		return metody;
	}	

}



