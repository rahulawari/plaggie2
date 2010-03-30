/* 
 *  Copyright (C) 2006 Aleksi Ahtiainen, Mikko Rahikainen.
 * 
 *  This file is part of Plaggie.
 *
 *  Plaggie is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; either version 2 of the License,
 *  or (at your option) any later version.
 *
 *  Plaggie is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaggie; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *  02110-1301  USA
 */
package plag.parser;

import java.io.Serializable;

/**
 * Represents a token within source code. A token can be for example the start
 * of a declaration, a return call, start of a for loop etc.
 * <p>
 * Tokens are comparable according to their value.
 */
public abstract class Token implements Serializable {

	/** The line from which this token starts in the source code */
	private int startLine;
	/** The line at which this token ends in the source code */
	private int endLine;
	/** The character from which this token starts in the source code */
	private int startChar;
	/** The character at which this token ends in the source code */
	private int endChar;

	/** The name of the token category for reporting, e.g. assigment statetment, method call, etc.*/
	private String tokenCategory;
	
	/** The number used as a token marker for distinguishing tokens of different code parts. 0 means no marker used. */
	protected int markerNumber = 0;
		
	/**
	 * Creates a new token (old)
	 * 
	 * @param startLine
	 *            The line from which this token starts in the source cod
	 * @param endLine
	 *            The line at which this token ends in the source code
	 * @param startChar
	 *            The character from which this token starts in the source code
	 * @param endChar
	 *            The character at which this token ends in the source code
	 * @param tokenCategory
	 * 			  The category of the token that can be shown in reporting
	 */
	public Token(int startLine, int endLine, int startChar, int endChar, String tokenCategory) {
		this.startLine = startLine;
		this.endLine = endLine;
		this.startChar = startChar;
		this.endChar = endChar;
		this.tokenCategory = tokenCategory;
	}

	/**
	 * Returns the start line of this token.
	 */
	public int getStartLine() {
		return this.startLine;
	}

	/**
	 * Returns the end line of this token.
	 */
	public int getEndLine() {
		return this.endLine;
	}

	/**
	 * Returns the start character of this token.
	 */
	public int getStartChar() {
		return this.startChar;
	}

	/**
	 * Returns the end character of this token.
	 */
	public int getEndChar() {
		return this.endChar;
	}

	/**
	 * Returns the number of lines spanned by this token.
	 */
	public int getLineCount() {
		return endLine - startLine + 1;
	}

	/**
	 * Returns the number of characters spanned by this token.
	 */
	public int getCharCount() {
		return endChar - startChar + 1;
	}

	/**
	 * Returns the string representation of the token type/category
	 */
	public String getTokenCategory() {
		return this.tokenCategory;
	}


	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
