package plag.parser.java2;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.expr.SuperExpr;

import java.io.File;
import java.io.FileInputStream;

import plag.parser.CodeTokenizer;
import plag.parser.TokenList;
import plag.parser.plaggie.Configuration;
import plag.parser.plaggie.Plaggie;
import sk.upjs.experiment.MyVisitor;

public class JavaTokenizer implements CodeTokenizer {

	private TokenizationConfig config;
	
	public JavaTokenizer() {
		config = new TokenizationConfig();
	}
	
	@Override
	public void acceptSystemConfiguration(Configuration config) {
		// z configu sa vyberie cesta k submission dir, alebo sa tam prida dalsia property s nazvom konfiguracneho suboru
		if(config.submissionDirectory.equalsIgnoreCase("")) {
			this.config.loadFromFile(config.configFilePath+"\\config.xml");
		}
		else {
			this.config.loadFromFile(config.submissionDirectory+"\\config.xml");
		}		
	}
	
	@Override
	public TokenList tokenize(File file) throws Exception {
		FileInputStream in = new FileInputStream(file);
		CompilationUnit cu;
		try {
			cu = JavaParser.parse(in);
		} finally {
			in.close();		
		}
		
		TokenList tokens = new TokenList("tokens");
		JavaASTVisitor jvisit = new JavaASTVisitor(new SourceFileReader(file), config);
		jvisit.visit(cu, tokens);
		
		return tokens;
	}

	public final static int IMPORT_DECLARATION = 1;
	public final static int CLASS_DECLARATION = 2;
	public final static int INTERFACE_DECLARATION = 3;
	public final static int VARIABLE_DECLARATION = 4;
	public final static int METHOD_DECLARATION = 5;
	public final static int STATIC_INITIALIZATION = 6;
	public final static int CONSTRUCTOR_DECLARATION = 7;
	public final static int CONSTRUCTOR_INVOCATION_THIS = 8;
	public final static int CONSTRUCTOR_INVOCATION_SUPER = 9;
	public final static int CONSTANT_DECLARATION = 10;
	public final static int ABSTRACT_METHOD_DECLARATION = 11;
	public final static int SWITCH = 12;
	public final static int BREAK = 13;
	public final static int CONTINUE = 14;
	public final static int RETURN = 15;
	public final static int SYNCHRONIZED = 16;
	public final static int THROW = 17;
	public final static int TRY = 18;
	public final static int ASSERT = 19;
	public final static int ASSIGNMENT = 20;
	public final static int METHOD_INVOCATION = 21;
	public final static int NEW = 22;
	public final static int BLOCK = 23;
	public final static int CATCH = 24;
	public final static int FINALLY = 25;
	public final static int CASE = 26;
	public final static int ELSE = 27;
	public final static int IF = 28;
	public final static int DO = 29;
	public final static int INNER_CLASS_DECLARATION = 30;
	public final static int INNER_INTERFACE_DECLARATION = 31;
	public final static int INNER_CLASS_DECLARATION_END = 32;
	public final static int INNER_INTERFACE_DECLARATION_END = 33;
	public final static int CLASS_DECLARATION_END = 34;
	public final static int INTERFACE_DECLARATION_END = 35;
	public final static int METHOD_DECLARATION_END = 36;
	public final static int CONSTRUCTOR_DECLARATION_END = 37;
	public final static int SYNCHRONIZED_END = 38;
	public final static int DO_END = 39;
	public final static int WHILE_END = 40;
	public final static int SWITCH_END = 41;
	public final static int WHILE = 42;
	public final static int BLOCK_END = 43;
	public final static int FOR = 44;
	public final static int FOR_END = 45;

	public final static int CATCH_END = 46;
	public final static int FINALLY_END = 47;
	public final static int TRY_END = 48;

	public final static int PACKAGE_DECLARATION = 49;

	public final static int ANONYMOUS_INNER_CLASS = 50;
	public final static int ANONYMOUS_INNER_CLASS_END = 51;

	public final static int IF_END = 52;
	public final static int ELSE_END = 53;
	public final static int ENUM_DECLARATION = 54;
	public final static int ENUM_DECLARATION_END = 55;
	public final static int INNER_ENUM_DECLARATION = 56;
	public final static int INNER_ENUM_DECLARATION_END = 57;
}
