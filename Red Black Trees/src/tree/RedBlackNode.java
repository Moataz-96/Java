package tree;

public class RedBlackNode {

	private int value = -1;
	private String stringValue;
	private String color;
	private RedBlackNode parent;
	private RedBlackNode leftChild;
	private RedBlackNode rightChild;
	private boolean root;
	
	public RedBlackNode(int value) {
		this.value = value;
		this.root = false;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}
	public RedBlackNode(String stringValue) {
		this.stringValue = stringValue;
		this.root = false;
		this.leftChild = null;
		this.rightChild = null;
		this.parent = null;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getColor() {
		return this.color;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public String getstringValue() {
		return this.stringValue;
	}
	public void setParent(RedBlackNode parent) {
		this.parent = parent;
	}
	public RedBlackNode getParent() {
		return this.parent;
	}
	public void setLeftChild(RedBlackNode child) {
		this.leftChild = child;
	}
	public RedBlackNode getLeftChild() {
		return this.leftChild;
	}
	public void setRightChild(RedBlackNode child) {
		this.rightChild = child;
	}
	public RedBlackNode getRightChild() {
		return this.rightChild;
	}
	public RedBlackNode clone() {
		RedBlackNode node = this;
		return node;
	}
	public void setRoot(boolean flag) {
		this.root = flag;
	}
	public boolean getRoot() {
		return this.root;
	}

	
	
}
