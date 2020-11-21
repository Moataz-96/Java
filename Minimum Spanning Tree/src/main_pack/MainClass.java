package main_pack;

import java.util.ArrayList;
import java.util.Scanner;

import tree.SpanningTree;

public class MainClass {
	public static void main(String args[]) {
		SpanningTree sTree = new SpanningTree();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number of vertices :");
		int x = Integer.parseInt(input.nextLine());
		if (x == 0) {
			return;
		}
		System.out.println("Enter weights and 0 if no connection **");
		sTree.addVertix("0", null, 0);
		for (int i = 0; i < x - 1; i++)
			for (int j = i + 1; j < x; j++) {
				// System.out.print("Enter ( "+((char)(i+65)) +" - "+ ((char)(j+65)) + " ) : "
				// );
				// sTree.addVertix(String.valueOf(((char)(i+65))),
				// String.valueOf(((char)(j+65))), Integer.parseInt(input.nextLine()));
				System.out.print("Enter ( " + i + " - " + j + " ) : ");
				sTree.addVertix(String.valueOf(i), String.valueOf(j), Integer.parseInt(input.nextLine()));

			}
		System.out.println("\n**********************************\n");
		sTree.getMinumumSpanningTree();
		System.out.println("\n**********************************\n");

		// Start Short Path from first index which it's name is "0"
		sTree.ShortestPath("0");

		/* Example 1:
		 * 
		 * sTree.addVertix("a",null,0); 
		 * sTree.addVertix("a","b",3);
		 * sTree.addVertix("b","c",4); 
		 * sTree.addVertix("c","d",5);
		 * sTree.addVertix("a","d",7); 
		 * sTree.addVertix("b","d",2);
		 * 
		 * sTree.getMinumumSpanningTree(); 
		 * sTree.ShortestPath("a");
		 */

		/*Example 2:
		 * 
		 * sTree.addVertix("a",null,0); 
		 * sTree.addVertix("a","b",3);
		 * sTree.addVertix("a","c",5); 
		 * sTree.addVertix("a","d",6);
		 * sTree.addVertix("c","e",6); 
		 * sTree.addVertix("e","f",5);
		 * sTree.addVertix("f","g",1); 
		 * sTree.addVertix("b","d",2);
		 * sTree.addVertix("c","d",2); 
		 * sTree.addVertix("c","f",3);
		 * sTree.addVertix("c","g",7); 
		 * sTree.addVertix("d","f",9);
		 * sTree.addVertix("e","g",2);
		 * 
		 * 
		 * sTree.getMinumumSpanningTree(); 
		 * sTree.ShortestPath("a");
		 */
		
		/* Example 3:
		 * 
		 * sTree.addVertix("A",null,0); 
		 * sTree.addVertix("A","B",5);
		 * sTree.addVertix("B","C",4); 
		 * sTree.addVertix("C","D",5);
		 * sTree.addVertix("A","C",10); 
		 * sTree.addVertix("B","D",11);
		 * 
		 * sTree.getMinumumSpanningTree(); 
		 * sTree.ShortestPath("A");
		 * 
		 */

		/* Example 4:
		 * 
		 * sTree.addVertix("a",null,0); 
		 * sTree.addVertix("a","b",4);
		 * sTree.addVertix("b","c",8); 
		 * sTree.addVertix("c","d",7);
		 * sTree.addVertix("d","e",9); 
		 * sTree.addVertix("e","f",10);
		 * sTree.addVertix("f","g",2); 
		 * sTree.addVertix("g","h",1);
		 * sTree.addVertix("h","i",7); 
		 * sTree.addVertix("a","h",8);
		 * sTree.addVertix("b","h",11); 
		 * sTree.addVertix("c","i",2);
		 * sTree.addVertix("c","f",4); 
		 * sTree.addVertix("d","f",14);
		 * sTree.addVertix("g","i",6);
		 * 
		 * sTree.getMinumumSpanningTree(); 
		 * sTree.ShortestPath("a");
		 * 
		 * 
		 */

		/* Example 5:
		 * 
		 * sTree.addVertix("A",null,0); 
		 * sTree.addVertix("A","B",4);
		 * sTree.addVertix("A","C",8); 
		 * sTree.addVertix("B","C",9);
		 * sTree.addVertix("B","E",10); 
		 * sTree.addVertix("B","D",8);
		 * sTree.addVertix("C","D",2); 
		 * sTree.addVertix("C","F",1);
		 * sTree.addVertix("D","E",7); 
		 * sTree.addVertix("D","F",9);
		 * sTree.addVertix("E","F",5); 
		 * sTree.addVertix("E","G",6);
		 * sTree.addVertix("F","G",2);
		 * 
		 * sTree.getMinumumSpanningTree(); 
		 * sTree.ShortestPath("A");
		 */

		
		// System.out.println(sTree.getSize());
		// sTree.printVertices();
		// sTree.displayGraph();

		// System.out.print("Please Enter Label of Starting short path Ex: A :");
		// String startingAddress = input.next();
		// sTree.ShortestPath(startingAddress);

	}
}
