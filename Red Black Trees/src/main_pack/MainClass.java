package main_pack;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import tree.RedBlackTree;

public class MainClass {
	private static RandomAccessFile file = null;
	private static String fileName = null;
	public static void main(String args[]) {
		RedBlackTree tree = new RedBlackTree();
		Scanner input = new Scanner(System.in);
		
		System.out.print("File? y:n :");
		String word = input.nextLine();
		if(word.equals("y")) {
			System.out.print("Enter File Name :");
			 fileName = input.nextLine();
			try {
				file = new RandomAccessFile(fileName,"r");
			} catch (FileNotFoundException e) {
				System.err.println("File Not Found !");
				System.exit(1);
		}}
		
			String choise;
			do {
		//		System.out.println("Please Choose :");
		//		System.out.println("1 - Integer Red Black Tree ");
		//		System.out.println("2 - String Red Black Tree ");
		//		System.out.print("Enter Value :");
		//		choise = input.nextLine();
				choise = "2";
			}while((Integer.parseInt(choise) < 1) && ( Integer.parseInt(choise) > 2));
			
			if(choise.equals("1")) {
				if(file != null)
					tree.initializeListIntegers(file, tree);
				int x = 0;
				System.out.println("--------------------------\n");
				while((x = Choose()) != 0) {
					try {
					switch(x) {
					case 1:
						tree.Display(tree.getRoot());
						//tree.byLevel(tree.getRoot());
						
						break;
					case 2:
						System.out.print("Inserting .. Enter Value :");
						tree.BalanceInsert(Integer.parseInt(input.nextLine()));	
						break;
					case 3:
						System.out.print("Deleting .. Enter Value :");
						tree.BalanceDeletion(Integer.parseInt(input.nextLine()));	
						break;
					case 4:
						System.out.print("Searching .. Enter Value :");
						if(tree.Search(Integer.parseInt(input.nextLine())) != null) {
							System.out.println("\nFOUND!!\n");
						}
						else {
							System.out.println("\nNOT FOUND!!\n");
						}
						break;
					case 5:
						System.out.print("\nMax Depth = "+tree.maxDepth(tree.getRoot())+"\n");
		
						break;
					case 6:
						System.out.print("\nSize = "+tree.Size(tree.getRoot())+"\n");
						break;
					case 7:
						System.err.print("Thank you");
						System.exit(1);
						break;
					
					}
					}catch(Exception io) {
						System.err.println("Error:: Cannot Accept String Values");
						System.exit(1);
					}
					
					
				}
			}else if(choise.equals("2")) {
				if(file != null)
					tree.initializeListString(file, tree);
				int x = 0;
				System.out.println("--------------------------\n");
				while((x = Choose()) != 0) {
					switch(x) {
					case 1:
						
						tree.Display(tree.getRoot());
						//tree.byLevelString(tree.getRoot());
						
						break;
					case 2:
						System.out.print("Inserting .. Enter Value :");
						String value = input.nextLine();		
						if(tree.Search(value) != null) {
							System.err.println("Error:: Word already in dictionary");	
						}else {
						tree.BalanceInsert(value);}
						break;
					case 3:
						System.out.print("Deleting .. Enter Value :");
						tree.BalanceDeletion(input.nextLine());	
						break;
					case 4:
						System.out.print("Searching .. Enter Value :");
						if(tree.Search(input.nextLine()) != null) {
							System.out.println("\nFOUND!!\n");
						}else {
							System.out.println("\nNOT FOUND!!\n");
						}
						
						break;
					case 5:
						System.out.print("\nMax Depth = "+tree.maxDepth(tree.getRoot())+"\n");
		
						break;
					case 6:
						System.out.print("\nSize = "+tree.Size(tree.getRoot())+"\n");
						break;
					case 7:
						System.err.print("Thank you");
						System.exit(1);
						break;
					}
			}

		}
	}
 	public static int Choose() {
		Scanner input = new Scanner(System.in);
		String value;
		do {
			System.out.println("-----------***----------------");
			System.out.println("1 - Display :");
			System.out.println("2 - Insert  :");
			System.out.println("3 - Delete  :");
			System.out.println("4 - Search  :");
			System.out.println("5 - depth  :");
			System.out.println("6 - size  :");
			System.out.println("7 - Exit    :");
			System.out.println("-----------***----------------");
			System.out.print("\nPlease Choose :");
			value = input.nextLine();
		}while(Integer.parseInt(value) > 7 && Integer.parseInt(value) < 0);
		return Integer.parseInt(value);
	}

}
