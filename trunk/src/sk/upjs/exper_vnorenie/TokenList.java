package sk.upjs.exper_vnorenie;

import java.io.Serializable;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Collection;


/**
 * Represents an ordered list of tokens. The tokens are ordered according to the
 * comparator class Token.OrderComparator.
 */
public class TokenList implements Serializable {

	/**
	 * The sorted set, which stores the tokens temporarily
	 */
	private TreeSet tokens = new TreeSet(new Token.OrderComparator());

	private int[] values = null;
	private int[] startLines = null;
	private int[] endLines = null;
	private int[] startChars = null;
	private int[] endChars = null;

	private void generateArrays() {
		Collection col = tokens;

		this.values = new int[col.size()];
		this.startLines = new int[col.size()];
		this.endLines = new int[col.size()];
		this.startChars = new int[col.size()];
		this.endChars = new int[col.size()];

		Iterator i = col.iterator();
		int c = 0;
		while (i.hasNext()) {
			Token t = (Token) i.next();
			this.values[c] = t.getValue();
			this.startLines[c] = t.getStartLine();
			this.endLines[c] = t.getEndLine();
			this.startChars[c] = t.getStartChar();
			this.endChars[c] = t.getEndChar();
			c++;
		}
		this.tokens = null;
	}

	private void generateSet() {
		tokens = new TreeSet(new Token.OrderComparator());
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				tokens.add(new Token(this.startLines[i], this.endLines[i],
						this.startChars[i], this.endChars[i], this.values[i]));
			}
		}
		this.values = null;
		this.startLines = null;
		this.endLines = null;
		this.startChars = null;
		this.endChars = null;
	}

	private String name;

	public TokenList(String name) {
		this.name = name;
	}
	
	public TokenList() {
		
	}

	/**
	 * Adds a new token to this token list.
	 */
	public void addToken(Token t) {
		if (this.tokens != null) {
			this.tokens.add(t);
		} else if (this.values != null) {
			this.generateSet();
			this.tokens.add(t);
		}
	}

	/**
	 * Run in order to save memory when no new tokens are to be added.
	 */
	public void finalize() {
		this.generateArrays();
	}

	/**
	 * Finalizes the token list. I.e. after this no new tokens are accepted to
	 * the list. This can be done for saving memory.
	 */

	/**
	 * Returns the token indicated by the given index.
	 */
	public Token getToken(int index) {
		if (this.values == null) {
			this.generateArrays();
		}
		return new Token(this.startLines[index], this.endLines[index],
				this.startChars[index], this.endChars[index],
				this.values[index]);
	}

	/**
	 * Returns an iterator over all the Token objects stored in this list.
	 */
	public Iterator iterator() {
		if (this.tokens == null) {
			this.generateSet();
		}
		return tokens.iterator();
	}

	/**
	 * Returns the number of tokens stored in this list.
	 */
	public int size() {
		if (this.values == null)
			return tokens.size();
		else
			return this.values.length;
	}

	/**
	 * Returns the values of the tokens in this list in an array.
	 */
	public int[] getValueArray() {
		if (this.values == null) {
			generateArrays();
		}
		return this.values;
	}

	public String toString() {
		return this.name;
	}

}
