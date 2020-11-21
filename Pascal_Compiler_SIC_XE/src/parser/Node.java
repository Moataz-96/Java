package parser;

import java.util.LinkedList;

public class Node {
	
	private String lexema;
	private Node child ;;
	public Node(String lexema) {
		this.lexema = lexema;
	}
	public String getLexema() {
		return this.lexema;
	}
	public void addChild(Node child) {
		this.child = child;
	}
	public Node getChild() {
		return this.child;
	}
	
}
