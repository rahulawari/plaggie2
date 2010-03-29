package plag.parser;

public class MethodInvocationToken extends SimpleToken {

	String name;
	int value;
	
	public MethodInvocationToken(int startLine, int endLine, int startChar,
			int endChar, String tokenCategory, int value) {
		super(startLine, endLine, startChar, endChar, tokenCategory, value);
		//this.name = name;
		this.value = value;
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (!(obj instanceof SimpleToken))
			return false;
		
		return (((MethodInvocationToken)obj).value == this.value);
	}

	

}
