package sk.upjs.exper_vnorenie;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.stmt.ForStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import sk.upjs.exper_vnorenie.MyVisitor;

public class Vnorenie {

	private int pocetVnoreni = 0;

	public static void main(String[] args) throws Exception {

		Vnorenie v = new Vnorenie();

		File f = new File("test.java");
		FileInputStream in = new FileInputStream(new File("test.java"));

		CompilationUnit cu;
		try {			
			cu = JavaParser.parse(in);
		} finally {
			in.close();
		}

		MyTokens t = new MyTokens();
		t.init("test.java");

		TokenList tl = new TokenList("tokens");

		MyVisitor visitor = new MyVisitor(t);
		visitor.setVnarajDoMetod(false);
		visitor.visit(cu, tl);

				
		int velkost = visitor.getMetody().size();
		visitor.setVnarajDoMetod(true);
		for(int i=0; i<velkost; i++) {
			System.out.println("Metoda " +visitor.getMetody().get(i).getName());
			visitor.visit(visitor.getMetody().get(i), tl);
			System.out.println("Maximalne vnorenie "+ visitor.getMaxVnorenie());
			System.out.println();
		}

	}

}

