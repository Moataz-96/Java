package tree;

public class Element {
	private int value;
	private String key;
	public Element(String key , int value) {
		this.value = value;
		this.key = key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
