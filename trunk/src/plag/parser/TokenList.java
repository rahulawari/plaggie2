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
import java.util.*;

/**
 * Represents an ordered list of tokens. The tokens are ordered according to the
 * comparator class Token.OrderComparator.
 */
public class TokenList implements Serializable {

	/**
	 * Internal list of tokens stored in this token list.
	 */
	private List<Token> tokens = new ArrayList<Token>();

	/**
	 * Name associated with this token list. It identifies the token list with respect to the source of tokens (e.g. file path)
	 */
	private String name;
	
	private int[] values = null;

	public TokenList(String name) {
		this.name = name;
	}

	/**
	 * Adds a new token to this token list.
	 */
	public void addToken(Token t) {
		this.tokens.add(t);
	}

	/**
	 * Run in order to save memory when no new tokens are to be added.
	 */
	public void finalize() {
		//this.generateArrays();
	}

	/**
	 * Finalizes the token list. I.e. after this no new tokens are accepted to
	 * the list. This can be done for saving memory.
	 */

	/**
	 * Returns the token indicated by the given index.
	 */
	public Token getToken(int index) {
		return this.tokens.get(index);
	}

	/**
	 * Returns an iterator over all the Token objects stored in this list.
	 */
	public Iterator iterator() {
		return tokens.iterator();
	}

	/**
	 * Returns the number of tokens stored in this list.
	 */
	public int size() {
		return tokens.size();
	}

	/**
	 * Returns the values of the tokens in this list in an array.
	 */
	public int[] getValueArray() {
		return this.values;
	}

	public String toString() {
		return this.name;
	}
}
