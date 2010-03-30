package plag.parser;

public class SimpleToken extends Token {

	private int value;
	
	public SimpleToken(int startLine, int endLine, int startChar, int endChar,
			String tokenCategory, int value) {
		super(startLine, endLine, startChar, endChar, tokenCategory);
		this.value = value;
	}
	
	public SimpleToken(int startLine, int endLine, int startChar, int endChar,
			String tokenCategory, int value, int marker) {
		this(startLine, endLine, startChar, endChar, tokenCategory, value);
		this.markerNumber = marker;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (!(obj instanceof SimpleToken))
			return false;
		
		SimpleToken stObj = ((SimpleToken)obj); 
		
		// if both tokens are marked with non-zero markers, they can be equal only when they have the same marker number  
		if ((stObj.markerNumber != 0) && (this.markerNumber != 0) && (stObj.markerNumber != this.markerNumber))
			return false;
		
		return (stObj.value == this.value);
	}
}
