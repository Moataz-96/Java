package tree;

import java.util.ArrayList;
import java.util.LinkedList;

public class SpanningTree {
	private int V = 5;
	private LinkedList<Vertix> Vertices;
	
	private ArrayList<ArrayList<Integer>> SP_List;

	public SpanningTree() {
		Vertices = new LinkedList<Vertix>();
	
	}

	public void addVertix(String currentLabel, String newLabel, int weight) {
		if (!checkInput(currentLabel, newLabel)) {

			return;
		}
		int indexCurrentLabel = -1;
		int indexNewLabel = -1;
		if (Vertices.size() == 0) {
			Vertices.add(new Vertix(currentLabel));
			return;
		}
		for (int i = 0; i < Vertices.size(); i++) {

			if (Vertices.get(i).getLabel().equals(currentLabel)) {
				indexCurrentLabel = i;
				break;
			}
		}
		if (indexCurrentLabel == -1) {
			System.err.println("Error " + currentLabel + " not found");
			return;
		}
		for (int i = 0; i < Vertices.size(); i++) {

			if (Vertices.get(i).getLabel().equals(newLabel)) {
				indexNewLabel = i;
				break;
			}
		}
		Vertix newVertix = new Vertix(newLabel);
		if (indexNewLabel == -1) {
			Vertices.get(indexCurrentLabel).addEdgeVertix(new Edge(newVertix, weight));
			Vertices.add(newVertix);
		} else {
			Vertices.get(indexCurrentLabel).addEdgeVertix(new Edge(Vertices.get(indexNewLabel), weight));

		}

	}

	public boolean checkInput(String currentLabel, String newLabel) {
		if (currentLabel == newLabel) {
			// System.out.println("Loop " + currentLabel + " with " + newLabel+" its
			// handled");
			return false;
		}
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < Vertices.get(i).getEdgeVertix().size(); j++) {
				if ((Vertices.get(i).getLabel() == newLabel)
						&& (Vertices.get(i).getEdgeVertix().get(j).getDistVertix().getLabel() == currentLabel))
					// System.out.println("Duplicated " + currentLabel + " with "+ newLabel +" its
					// handled");
					return false;
			}
		}
		return true;
	}

	public int getSize() {
		return this.Vertices.size();
	}

	public void displayFullGraph(Vertix rootVertix) {

		// System.out.print(rootVertix.getLabel() + " - " +
		// rootVertix.getEdgeVertix().getWeight() + " - ");
		// displayFullGraph(rootVertix.getEdgeVertix().getDistVertix());
	}

	public void displayGraph() {
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < Vertices.get(i).getEdgeVertix().size(); j++) {
				System.out
						.println(Vertices.get(i).getLabel() + " - " + Vertices.get(i).getEdgeVertix().get(j).getWeight()
								+ " " + Vertices.get(i).getEdgeVertix().get(j).getDistVertix().getLabel() + " ");
			}
		}
	}

	public void ShortestPath(String startingLabel) {
		int index=0,seqIterator, firstIndex = 1, minValue;
		boolean checkFlag = false;
		for(int count = 0 ; count < Vertices.size() ; count ++) {
			if(Vertices.get(count).getLabel() == startingLabel) {
				index = count+1;
				checkFlag = true;
				break;
			}
		}
		if(checkFlag == false) {
			System.out.println("Vertix not found");
			return;
		}
		 ArrayList<ArrayList<Integer>> MST_List= new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();

		for (int i = 0; i < this.getSize(); i++) {
			row.clear();
			for (int k = 0; k < this.getSize(); k++) {
				row.add(0);
			}
			for (int j = 0; j < Vertices.get(i).getEdgeVertix().size(); j++) {

				row.set(Vertices.indexOf(Vertices.get(i).getEdgeVertix().get(j).getDistVertix()),
						Vertices.get(i).getEdgeVertix().get(j).getWeight());
			}
			MST_List.add((ArrayList<Integer>) row.clone());
		}
		// System.out.println(MST_List);
		for (int i = 0; i < MST_List.size(); i++) {
			for (int j = 0; j < MST_List.get(i).size(); j++) {
				// System.out.print(MST_List.get(i).get(j) + " ");
				if (MST_List.get(j).get(i) == 0) {
					MST_List.get(j).set(i, MST_List.get(i).get(j));
				} else {
					MST_List.get(i).set(j, MST_List.get(j).get(i));
				}
			}
		}
		for (int i = 0; i < MST_List.size(); i++) {
			for (int j = 0; j < MST_List.get(i).size(); j++) {
				// System.out.print(MST_List.get(i).get(j) + " ");
				if (MST_List.get(j).get(i) == 0) {
					MST_List.get(j).set(i, 999);
				}
			}
		}
		for (int i = 0; i < MST_List.size(); i++) {
			for (int j = 0; j < MST_List.get(i).size(); j++) {
				if (i == j) {
					MST_List.get(i).set(j, 0);
				}
			}
		}
		ArrayList<String> temp = new ArrayList<String>();
		System.out.println("Shortest Path \n");
		int[][] Graph = this.convertIntoGraph(MST_List);		
		int rowArr[] = new int[Graph.length + 1];		
		int Length = Graph.length - 1;
		int marked[] = new int[Graph.length];
		for (seqIterator = 1; seqIterator <= Length; seqIterator++) {
			marked[seqIterator] = 0;
			rowArr[seqIterator] = Graph[index][seqIterator];
			//System.out.println(Vertices.get(seqIterator-1).getLabel());
			//temp.add(Vertices.get(seqIterator-1).getLabel());
		}
		int tempValue = 0;
		for(int counter = 2 ; counter <= Length ; counter++) {
			minValue = 999;
			for (int i = 1; i <= Length; i++) {
				if (rowArr[i] < minValue && marked[i] != 1) {
					minValue = rowArr[seqIterator];
					firstIndex = i;
					
				}
			}
			marked[firstIndex] = 1; 
			for (int i = 1; i <= Length; i++) {
				if (rowArr[firstIndex] + Graph[firstIndex][i] < rowArr[i] && marked[i] != 1) {
					
					rowArr[i] = rowArr[firstIndex] + Graph[firstIndex][i];
				
					//temp.set(i-1, Vertices.get(firstIndex-1).getLabel());
					//System.out.println(Vertices.get(firstIndex-1).getLabel());
					
					}
			}
			
		}
		System.out.println("--------------------");
		for (int count = 1; count <= Length; count++)
			if(count != index) {
				System.out.println( startingLabel + " to " + Vertices.get(count-1).getLabel() + " is " + rowArr[count]);
				
			}
	}

	public void getMinumumSpanningTree() {
		 ArrayList<ArrayList<Integer>> MST_List= new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		for (int i = 0; i < this.getSize(); i++) {
			row.clear();
			for (int k = 0; k < this.getSize(); k++) {
				row.add(0);
			}
			for (int j = 0; j < Vertices.get(i).getEdgeVertix().size(); j++) {

				row.set(Vertices.indexOf(Vertices.get(i).getEdgeVertix().get(j).getDistVertix()),
						Vertices.get(i).getEdgeVertix().get(j).getWeight());
			}
			MST_List.add((ArrayList<Integer>) row.clone());
		}
		// System.out.println(MST_List);
		for (int i = 0; i < MST_List.size(); i++) {
			for (int j = 0; j < MST_List.get(i).size(); j++) {
				// System.out.print(MST_List.get(i).get(j) + " ");
				if (MST_List.get(j).get(i) == 0) {
					MST_List.get(j).set(i, MST_List.get(i).get(j));
				} else {
					MST_List.get(i).set(j, MST_List.get(j).get(i));
				}
			}
		}
		ArrayList<ArrayList<Integer>> visited = new ArrayList<ArrayList<Integer>>();
		ArrayList<String> mstLabel = new ArrayList<String>();
		ArrayList<String> markedNode = new ArrayList<String>();
		int x = 0, y = 0;
		int weight = 0;
		boolean flag = true;
		visited.add(MST_List.get(0));

		for (int i = 0; i < MST_List.size() - 1; i++) {

			int min = 0;
			x = 0;
			y = 0;
			for (int z = 0; z < visited.size(); z++) {
				for (int k = 0; k < visited.get(z).size(); k++) {
					if (visited.get(z).get(k) > 0) {

						min = visited.get(z).get(k);
						x = MST_List.indexOf(visited.get(z));
						y = k;
						flag = false;
						break;
					}
				}
				if (flag == false) {
					flag = true;
					break;
				}
			}
			for (int z = 0; z < visited.size(); z++) {
				for (int j = 0; j < visited.get(z).size(); j++) {
					if ((visited.get(z).get(j) != 0) && (visited.get(z).get(j) < min)) {
						min = visited.get(z).get(j);
						x = MST_List.indexOf(visited.get(z));
						y = j;
					}
				}
			}

			if (!markedNode.contains(Vertices.get(y).getLabel())) {
				mstLabel.add(Vertices.get(x).getLabel());
				mstLabel.add(String.valueOf(min));
				mstLabel.add(Vertices.get(y).getLabel());
				markedNode.add(Vertices.get(y).getLabel());
				if (!visited.contains(MST_List.get(y)))
					visited.add(MST_List.get(y));
				else {
					visited.add(MST_List.get(i));
				}
				weight += min;
			} else {
				i--;
			}

			MST_List.get(y).set(x, 0);
			MST_List.get(x).set(y, 0);

		}

		System.out.println("Minumum Spanning Tree \n");
		for (int i = 0; i < mstLabel.size(); i += 3) {
			System.out.print(mstLabel.get(i) + " - " + mstLabel.get(i + 2) + "\n");
		//	System.out.print(mstLabel.get(i + 1) + " 1- " + mstLabel.get(i + 2) + "\n");
		}
		System.out.println("\nWeight = " + weight);

	}

	public Vertix getRoot() {
		return this.Vertices.get(0);
	}

	public void printVertices() {
		System.out.println("Vertices = " + Vertices);
	}

	public int[][] convertIntoGraph(ArrayList<ArrayList<Integer>> MST_List) {
		int[][] Graph = new int[MST_List.size() + 1][];
		for (int i = 1; i <= MST_List.size(); i++) {
			ArrayList<Integer> row_f = MST_List.get(i - 1);
			Graph[i] = new int[row_f.size() + 1];
			for (int x = 1; x <= Graph.length - 1; x++) {
				Graph[i][x] = row_f.get(x - 1);
			}
		}

		return Graph;
	}

}
