package lexical;

import java.util.Hashtable;


public class Keyword {
	private int TAG;
	private String lexema;
	public Keyword(int TAG , String lexema) {
		this.TAG = TAG;
		this.lexema = lexema;
	}
	public int getTAG() {
		return this.TAG;
	}
	public String getLexema() {
		return this.lexema;
	}
	public void setLexema(String Lexema) {
		this.lexema = Lexema;
	}
	
}
