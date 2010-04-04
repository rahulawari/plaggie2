package plag.parser.java2.tokens;

import plag.parser.Token;

public class MethodInvocationToken extends Token {

	String name;
	
	
	public MethodInvocationToken(int startLine, int endLine, int startChar,
			int endChar, String tokenCategory, String name) {
		super(startLine, endLine, startChar, endChar, tokenCategory);
		this.name = name;
	}
	
	public MethodInvocationToken(int startLine, int endLine, int startChar,
			int endChar, String tokenCategory, String name, int marker) {
		super(startLine, endLine, startChar, endChar, tokenCategory);
		this.name = name;
		this.markerNumber = marker;
	} 
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (!(obj instanceof MethodInvocationToken))
			return false;
		
		MethodInvocationToken mtObj = ((MethodInvocationToken)obj);
		
		if ((mtObj.markerNumber != 0) && (this.markerNumber != 0) && (mtObj.markerNumber != this.markerNumber))
			return false;
		
		return (mtObj.name.equals(this.name));
	}
}
