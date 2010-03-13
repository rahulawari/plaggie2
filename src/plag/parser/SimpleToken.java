package plag.parser;

public class SimpleToken extends Token {

	private int value;
	
	public SimpleToken(int startLine, int endLine, int startChar, int endChar,
			String tokenCategory, int value) {
		super(startLine, endLine, startChar, endChar, tokenCategory);
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (!(obj instanceof SimpleToken))
			return false;
		
		return (((SimpleToken)obj).value == this.value);
	}
}
