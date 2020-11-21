package tree;

import java.util.LinkedList;

public class Vertix {
	private String Label;
	private int value;
	private LinkedList<Edge> edgeVertix;
	
	public Vertix(String Label) {
		this.Label = Label;
		edgeVertix = new LinkedList<Edge>();
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LinkedList<Edge> getEdgeVertix() {
		return edgeVertix;
	}

	public void addEdgeVertix(Edge edgeVertix) {
		this.edgeVertix.add(edgeVertix);
	}
	
}
