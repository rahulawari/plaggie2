
package sk.upjs.experiment;

import plag.parser.CodeTokenizer;
import plag.parser.TokenList;
import plag.parser.plaggie.Configuration;

import sk.upjs.experiment.MyVisitor;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.*;

/**
 * A tokenizer for Java program code files.
 *
 */
public class MyFirstTokenizer
    implements CodeTokenizer
{

	@Override
	public void acceptSystemConfiguration(Configuration config) {
			
	}
	
    public MyFirstTokenizer()
    {
    }

    
    public TokenList tokenize(File file)
	throws Exception
    {

	
	MyTokens t = new MyTokens();
	t.init(file.getPath());	
	
	FileInputStream in = new FileInputStream(file);

	CompilationUnit cu;
	try {
		cu = JavaParser.parse(in);
	} finally {
		in.close();		
	}
	
	TokenList tokens = new TokenList("tokens");
	new MyVisitor(t).visit(cu, tokens);
	
	return tokens;
    }

    /**
     * Returns the descriptive name of a token with the given value.
     */
    public String getValueString(int value)
    {
	return MyTokens.getValueString(value);
    }


}



