package tree;

public class Edge {
	private int weight;
	private Vertix distVertix;
	public Edge(Vertix distVertix ,int weight) {
		this.distVertix = distVertix;
		this.weight = weight;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Vertix getDistVertix() {
		return distVertix;
	}
	public void setDistVertix(Vertix distVertix) {
		this.distVertix = distVertix;
	}
	
	
}
