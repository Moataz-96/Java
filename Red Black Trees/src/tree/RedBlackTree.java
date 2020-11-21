package tree;

import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class RedBlackTree {
	Hashtable tableFile;
	RedBlackNode rootNode ;
	RedBlackNode insertedNode;
	RedBlackNode uncle = null;
	
	private boolean flag = false;

	public RedBlackTree() {
		
	}

	public RedBlackNode Search(int value) {
		
	     Queue<RedBlackNode> level  = new LinkedList<>();
	     level.add(rootNode);
	     if(rootNode == null)
	    	 return null;
	     while(!level.isEmpty()){
	    	 RedBlackNode node = level.poll();
	         if(value == node.getValue()) {
	        	 return node;
	         }
	         if(node.getLeftChild()!= null)
	         level.add(node.getLeftChild());
	         if(node.getRightChild()!= null)
	         level.add(node.getRightChild());}
	       //  System.out.println("\n");
    	 return null;
	}
	public RedBlackNode Search(String stringValue) {
		
	     Queue<RedBlackNode> level  = new LinkedList<>();
	     level.add(rootNode);
	     if(rootNode == null)
	    	 return null;
	     while(!level.isEmpty()){
	    	 RedBlackNode node = level.poll();
	         if(stringValue.compareTo(node.getstringValue()) == 0) {

	        	 return node;
	         }
	         if(node.getLeftChild()!= null)
	         level.add(node.getLeftChild());
	         if(node.getRightChild()!= null)
	         level.add(node.getRightChild());}
	       //  System.out.println("\n");
   
   	 return null;
	}


	public void BalanceInsert(int value) {
		RedBlackNode node = new RedBlackNode(value);
		i++;
		if(flag == false) {
			rootNode = node;
			rootNode.setRoot(true);
			rootNode.setColor("black");
			flag = true;
			insertedNode = rootNode;
			return ;
		}
		 insertedNode = Insert(rootNode, value);
		if(value < insertedNode.getValue() ) {
			insertedNode.setLeftChild(node);
		}
		else {
			insertedNode.setRightChild(node);
		}
		node.setParent(insertedNode);
		node.setColor("red");
		 uncle = null;
		if(node.getRoot()) {
			node.setColor("black");
		}
		while(node.getColor() == "red" && node.getParent().getColor() =="red") {
			getUncle(node);
			if(uncle == null) {
				uncle = new RedBlackNode(-1); 
				uncle.setColor("black");
			}
			 checkCase(node, uncle);
			 if(flipped) {
				 node = node.getParent().getParent();
				 flipped = false;
			 }
		}
		rootNode.setColor("black");
		rootNode.setParent(null);
	}
	
	public void BalanceInsert(String stringValue) {
		RedBlackNode node = new RedBlackNode(stringValue);
		i++;
		if(flag == false) {
			rootNode = node;
			rootNode.setRoot(true);
			rootNode.setColor("black");
			flag = true;
			insertedNode = rootNode;
			return ;
		}
		 insertedNode = Insert(rootNode, stringValue);
		if(stringValue.compareTo(insertedNode.getstringValue()) < 0 ) {
			insertedNode.setLeftChild(node);
		}
		else {
			insertedNode.setRightChild(node);
		}
		node.setParent(insertedNode);
		node.setColor("red");
		 uncle = null;
		if(node.getRoot()) {
			node.setColor("black");
		}
		while(node.getColor() == "red" && node.getParent().getColor() =="red") {
			getUncle(node);
			if(uncle == null) {
				uncle = new RedBlackNode("-1"); 
				uncle.setColor("black");
			}
			 checkCase(node, uncle);
			 if(flipped) {
				 node = node.getParent().getParent();
				 flipped = false;
			 }
		}
		rootNode.setColor("black");
		rootNode.setParent(null);
	}
	
	public RedBlackNode getRoot() {
		return this.rootNode;
	}
	private boolean flipped = false;
	public void checkCase(RedBlackNode insertedNode,RedBlackNode uncle) {
		if((insertedNode.getParent().getColor() == "red") && (!insertedNode.getRoot())) {
			if(uncle.getColor() == "red") {
				
			  ColorFlip(insertedNode).clone();
				flipped = true;
			}
			else if(uncle.getColor() == "black") {
				if((insertedNode.getParent().getParent().getLeftChild() == insertedNode.getParent())
						&& (insertedNode.getParent().getLeftChild() == insertedNode)) {
				rightRotate(insertedNode);
				}
				else if((insertedNode.getParent().getParent().getRightChild() == insertedNode.getParent())
						&& (insertedNode.getParent().getRightChild() == insertedNode)) {
				leftRotate(insertedNode);
				}
				else if((insertedNode.getParent().getParent().getLeftChild() == insertedNode.getParent())
						&& (insertedNode.getParent().getRightChild() == insertedNode)){
					leftRight(insertedNode);
				}
				else if((insertedNode.getParent().getParent().getRightChild() == insertedNode.getParent())
						&& (insertedNode.getParent().getLeftChild() == insertedNode)){
					rightLeft(insertedNode);
				}
				else {
					System.out.println("Error" + insertedNode.getValue());
				}
			}
		}
		
	}

	public void BalanceDeletion(int value) {
		RedBlackNode node = Search(value);
		RedBlackNode tempDelete;
		if(node != null)
			i--;
		RedBlackNode target = Delete(value);

		if(target == null) {
			return;}
		RedBlackNode sibling = null;

		if(target.getParent().getLeftChild() == target) {
			sibling = target.getParent().getRightChild();
		}else {
			sibling = target.getParent().getLeftChild();
		}
		
		if(sibling.getLeftChild() == null) {
			sibling.setLeftChild(new RedBlackNode(-1));
			sibling.getLeftChild().setColor("black");
		}
		if(sibling.getRightChild() == null) {
			sibling.setRightChild(new RedBlackNode(-1));
			sibling.getRightChild().setColor("black");
		}
		if((sibling.getColor() == "black") && 
				((sibling.getLeftChild().getColor() == "red") || (sibling.getRightChild().getColor() == "red"))){
			if((sibling.getParent().getRightChild() == sibling) &&
					(sibling.getRightChild().getColor() =="red") ){
						leftRotate(sibling.getRightChild());
					}
			else if((sibling.getParent().getLeftChild() == sibling) &&
					(sibling.getLeftChild().getColor() =="red") ){
						rightRotate(sibling.getLeftChild());
					}
			else if((sibling.getParent().getRightChild() == sibling) &&
					(sibling.getLeftChild().getColor() =="red") ){
						rightLeft(sibling.getLeftChild());
						sibling.getParent().getLeftChild().setColor("black");
					//	sibling.setRightChild(null);
					}
			else if((sibling.getParent().getLeftChild() == sibling) &&
					(sibling.getRightChild().getColor() =="red") ){
						leftRight(sibling.getRightChild());
						sibling.getParent().getRightChild().setColor("black");
					//	sibling.setLeftChild(null);
					}

			else {
				System.out.println("Erororo");
			}
		}else if((sibling.getColor() == "black") && (sibling.getLeftChild().getColor() == "black") && 
				(sibling.getRightChild().getColor() == "black")){
		
			sibling.setColor("red");
		
			sibling.getParent().setColor("black");
		}else if(sibling.getColor() == "red") {
			if(sibling.getParent().getLeftChild() == sibling) {
				rightRotate(sibling.getLeftChild());

			}else if(sibling.getParent().getRightChild() == sibling) {
				leftRotate(sibling.getRightChild());

			}
		}
		

		if(target.getLeftChild() == null || target.getLeftChild().getValue() == -1) {
			
			target.getParent().setRightChild(null);}
			else {
			
				target.getParent().setRightChild(target.getLeftChild());
				target.getLeftChild().setParent(target.getParent());
		
		}
		if(sibling.getLeftChild().getValue() == -1) {
		sibling.setLeftChild(null);}
		if(sibling.getRightChild().getValue() == -1)
		sibling.setRightChild(null);

	}
	public void BalanceDeletion(String stringValue) {
		RedBlackNode node = Search(stringValue);
		RedBlackNode tempDelete;
		if(node != null)
			i--;
		RedBlackNode target = Delete(stringValue);

		if(target == null) {
			return;}
		RedBlackNode sibling = null;
		if(target.getParent().getLeftChild() == target) {
			sibling = target.getParent().getRightChild();
		}else {
			sibling = target.getParent().getLeftChild();
		}
		
		if(sibling.getLeftChild() == null) {
			sibling.setLeftChild(new RedBlackNode("-1"));
			sibling.getLeftChild().setColor("black");
		}
		if(sibling.getRightChild() == null) {
			sibling.setRightChild(new RedBlackNode(-1));
			sibling.getRightChild().setColor("black");
		}
		if((sibling.getColor() == "black") && 
				((sibling.getLeftChild().getColor() == "red") || (sibling.getRightChild().getColor() == "red"))){
			if((sibling.getParent().getRightChild() == sibling) &&
					(sibling.getRightChild().getColor() =="red") ){
						leftRotate(sibling.getRightChild());
					}
			else if((sibling.getParent().getLeftChild() == sibling) &&
					(sibling.getLeftChild().getColor() =="red") ){
						rightRotate(sibling.getLeftChild());
					}
			else if((sibling.getParent().getRightChild() == sibling) &&
					(sibling.getLeftChild().getColor() =="red") ){
						rightLeft(sibling.getLeftChild());
						sibling.getParent().getLeftChild().setColor("black");
					//	sibling.setRightChild(null);
					}
			else if((sibling.getParent().getLeftChild() == sibling) &&
					(sibling.getRightChild().getColor() =="red") ){
						leftRight(sibling.getRightChild());
						sibling.getParent().getRightChild().setColor("black");
					//	sibling.setLeftChild(null);
					}

			else {
				System.out.println("Erororo");
			}
		}else if((sibling.getColor() == "black") && (sibling.getLeftChild().getColor() == "black") && 
				(sibling.getRightChild().getColor() == "black")){
			System.out.println("hereee");
			sibling.setColor("red");
		
			sibling.getParent().setColor("black");
		}else if(sibling.getColor() == "red") {
			if(sibling.getParent().getLeftChild() == sibling) {
				rightRotate(sibling.getLeftChild());

			}else if(sibling.getParent().getRightChild() == sibling) {
				leftRotate(sibling.getRightChild());

			}
		}

		if(target.getLeftChild() == null || target.getLeftChild().getstringValue() == "-1") {
			
			
			target.getParent().setRightChild(null);}
			else {
			
			
				target.getParent().setRightChild(target.getLeftChild());
				target.getLeftChild().setParent(target.getParent());
		
		}
		if(sibling.getLeftChild().getstringValue() == "-1") {
		sibling.setLeftChild(null);}
		if(sibling.getRightChild().getstringValue() == "-1")
		sibling.setRightChild(null);
	}
	public RedBlackNode Delete(int value) {
		RedBlackNode node = Search(value);
		if(node == null) {return null;}
		if(node.getLeftChild() == null && node.getRightChild() == null) {
			if(node.getParent().getLeftChild() == node) {
				node.getParent().setLeftChild(null);
			}else {
				node.getParent().setRightChild(null);
			}
		}
		else if(node.getLeftChild() == null && node.getRightChild() != null) {
			node.setValue(node.getRightChild().getValue());
			if(node.getColor() == "red" || node.getRightChild().getColor() == "red") {
				node.setColor("black");
				node.setRightChild(null);
				return null;
			}
			node.setRightChild(null);
		}else if(node.getRightChild() == null && node.getLeftChild() != null) {
			node.setValue(node.getLeftChild().getValue());
			if(node.getColor() == "red" || node.getLeftChild().getColor() == "red") {
				node.setColor("black");
				node.setLeftChild(null);
				return null;
			}
			node.setLeftChild(null);
		}else {
			RedBlackNode X = mostRight(node.getLeftChild());
			node.setValue(X.getValue());
			X.setValue(-1);

			if((node.getColor() =="red") || (X.getColor() == "red")) {
				X.setColor("black");
				if(X.getParent().getLeftChild() == X){
					X.getParent().setLeftChild(null);
				}else {
					X.getParent().setRightChild(null);
				}
				return null;
			}
			
			return X;
		}
		return null;
	}
	public RedBlackNode Delete(String stringValue) {
		RedBlackNode node = Search(stringValue);
		if(node == null) {return null;}
		if(node.getLeftChild() == null && node.getRightChild() == null) {
			if(node.getParent().getLeftChild() == node) {
				node.getParent().setLeftChild(null);
			}else {
				node.getParent().setRightChild(null);
			}
		}
		else if(node.getLeftChild() == null && node.getRightChild() != null) {
			node.setStringValue(node.getRightChild().getstringValue());
			if(node.getColor() == "red" || node.getRightChild().getColor() == "red") {
				node.setColor("black");
				node.setRightChild(null);
				return null;
			}
			node.setRightChild(null);
		}else if(node.getRightChild() == null && node.getLeftChild() != null) {
			node.setStringValue(node.getLeftChild().getstringValue());
			if(node.getColor() == "red" || node.getLeftChild().getColor() == "red") {
				node.setColor("black");
				node.setLeftChild(null);
				return null;
			}
			node.setLeftChild(null);
		}else {
			RedBlackNode X = mostRight(node.getLeftChild());
			node.setStringValue(X.getstringValue());
			X.setStringValue("-1");

			if((node.getColor() =="red") || (X.getColor() == "red")) {
				X.setColor("black");
				if(X.getParent().getLeftChild() == X){
					X.getParent().setLeftChild(null);
				}else {
					X.getParent().setRightChild(null);
				}
				return null;
			}
			
			return X;
		}
		return null;
	}
	public RedBlackNode mostRight(RedBlackNode node) {
		if(node.getRightChild() == null) {
			return node ;
		}
		return mostRight(node.getRightChild());
	}
	public int maxDepth(RedBlackNode node) 
    {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.getLeftChild());
            int rDepth = maxDepth(node.getRightChild());
  
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
             else
                return (rDepth + 1);
        }
    }
	public int Size(RedBlackNode node) {
		return this.i;
	}
	public int i =0;
	public void Display(RedBlackNode node) {
		
		if (node == null) {
			return ;
		}
		
		
		
		
		
		System.out.println("---------------------");	
		if(node.getValue() != -1) {
		System.out.println("Value = " + node.getValue() + "\t" + node.getColor());
		if(node.getParent() != null) {
		System.out.println("\nParent = " + node.getParent().getValue());}
		if(node.getLeftChild() != null) {
		System.out.println("\nLeftChild = " + node.getLeftChild().getValue());}
		if(node.getRightChild() != null) {
		System.out.println("\nRightChild = " + node.getRightChild().getValue());}
		}else{
			System.out.println("Value = " + node.getstringValue() + "\t" + node.getColor());
			if(node.getParent() != null) {
			System.out.println("\nParent = " + node.getParent().getstringValue());}
			if(node.getLeftChild() != null) {
			System.out.println("\nLeftChild = " + node.getLeftChild().getstringValue());}
			if(node.getRightChild() != null) {
			System.out.println("\nRightChild = " + node.getRightChild().getstringValue());}
		}
		
		Display(node.getLeftChild());
		Display(node.getRightChild());

		
	}
	public void initializeListString(RandomAccessFile file,RedBlackTree tree) {
		
		String word = null;
		try {
			while(((word = file.readLine()) != null)) {
			tree.BalanceInsert(word);
			}
		} catch (Exception e) {
			System.err.println("Error in file \nhint:Please write one string value in each line in file");
			System.exit(1);
		}
		
		 
	}
	public void initializeListIntegers(RandomAccessFile file,RedBlackTree tree) {
		
		String word = null;
		try {
			while(((word = file.readLine()) != null)) {
				tree.BalanceInsert(Integer.parseInt(word));
				}
			
		} catch (Exception e) {
			System.err.println("Error in file \nhint:Please write one Integer Value in each line in file");
			System.exit(1);
		}
		
		 
	}

	public void byLevel(RedBlackNode root){
	     Queue<RedBlackNode> level  = new LinkedList<>();
	     level.add(root);
	     while(!level.isEmpty()){
	    	 RedBlackNode node = level.poll();
	    	 if(node.getColor() == "red") {
	    		 System.err.print(node.getValue() +"\t ");
	    	 }else {
	    		 System.out.print(node.getValue() +"\t ");
	    	 }

	         if(node.getLeftChild()!= null)
	         level.add(node.getLeftChild());
	         if(node.getRightChild()!= null)
	         level.add(node.getRightChild());
	       //  System.out.println("\n");
	     }
	}
	public void byLevelString(RedBlackNode root){
	     Queue<RedBlackNode> level  = new LinkedList<>();
	     level.add(root);
	     while(!level.isEmpty()){
	    	 RedBlackNode node = level.poll();
	    	 if(node.getColor() == "red") {
	    		 System.err.print(node.getstringValue() +"\t ");
	    	 }else {
	    		 System.out.print(node.getstringValue() +"\t ");
	    	 }

	         if(node.getLeftChild()!= null)
	         level.add(node.getLeftChild());
	         if(node.getRightChild()!= null)
	         level.add(node.getRightChild());
	       //  System.out.println("\n");
	     }
	}
	
	public void displayTree(RedBlackNode root)
	{
		
		Stack globalStack = new Stack();
		globalStack.push(root);	
		int emptyLeaf = 32;
		boolean isRowEmpty = false;
		System.out.println("****......................................................****");
		while(isRowEmpty==false)
		{
			
			
			Stack localStack = new Stack();
			isRowEmpty = true;
			for(int j=0; j<emptyLeaf; j++)
				System.out.print(" ");
			while(globalStack.isEmpty()==false)
			{
				RedBlackNode temp = (RedBlackNode) globalStack.pop();
				if(temp != null)
				{	
					if(temp.getValue() != -1) {
					if(temp.getColor() == "red") {
						System.err.print(temp.getValue() +" ");
					}else {
					System.out.print(temp.getValue() +" ");}}
					else {
						if(temp.getColor() == "red") {
							System.err.print(temp.getstringValue() +" ");
						}else {
						System.out.print(temp.getstringValue() +" ");}
					}
				
					localStack.push(temp.getLeftChild());
					localStack.push(temp.getRightChild());
					if(temp.getLeftChild() != null ||temp.getRightChild() != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("  ");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<emptyLeaf*2-2; j++)
					System.out.print(" ");
			}
			System.out.println("\n\n");
			emptyLeaf /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		}
	System.out.println("****......................................................****");
	} 
	 

	public void rightRotate(RedBlackNode node) {

		RedBlackNode parent = node.getParent().clone();
		RedBlackNode GParent = node.getParent().getParent().clone();

		if(node.getParent().getParent().getRoot()) {
			
			node.getParent().getParent().setRoot(false);
			node.getParent().setRoot(true);
			rootNode = node.getParent();
			rootNode.setColor("black");
		}
		
		if (node.getParent().getRightChild() != null) {
			GParent.setLeftChild(node.getParent().getRightChild());
			node.getParent().getRightChild().setParent(GParent);
		}
		else {
			GParent.setLeftChild(null);
		}
		parent.setRightChild(GParent);
		
		if(node.getParent().getParent().getParent() !=null) {
		if(node.getParent().getParent().getParent().getLeftChild() == node.getParent().getParent()) {
			node.getParent().getParent().getParent().setLeftChild(node.getParent());
		}else {
			node.getParent().getParent().getParent().setRightChild(node.getParent());
		}
		node.getParent().setParent(node.getParent().getParent().getParent());
		}
		node.getParent().getRightChild().setParent(node.getParent());

		
		String color = node.getParent().getRightChild().getColor();
		node.getParent().getRightChild().setColor(node.getParent().getColor());
		node.getParent().setColor(color);
	}

	public void leftRotate(RedBlackNode node) {
		RedBlackNode parent = node.getParent().clone();
		RedBlackNode GParent = node.getParent().getParent().clone();

		if(node.getParent().getParent().getRoot()) {
			
			node.getParent().getParent().setRoot(false);
			node.getParent().setRoot(true);
			rootNode = node.getParent();
			rootNode.setColor("black");
		}
		if (node.getParent().getLeftChild() != null) {
			GParent.setRightChild(node.getParent().getLeftChild());
			node.getParent().getLeftChild().setParent(GParent);
		}else {
			GParent.setRightChild(null);
		}
		parent.setLeftChild(GParent);
		if(node.getParent().getParent().getParent() !=null) {
		if(node.getParent().getParent().getParent().getRightChild() == node.getParent().getParent()) {
			node.getParent().getParent().getParent().setRightChild(node.getParent());
		}else {
			node.getParent().getParent().getParent().setLeftChild(node.getParent());
		}
		node.getParent().setParent(node.getParent().getParent().getParent());
		}
		
	//	node.getParent().getLeftChild().setRightChild(null);
		node.getParent().getLeftChild().setParent(node.getParent());

		
		String color = node.getParent().getLeftChild().getColor();
		node.getParent().getLeftChild().setColor(node.getParent().getColor());
		node.getParent().setColor(color);
		
	}

	public void rightLeft(RedBlackNode node) {
		RedBlackNode Parent = node.getParent().clone();
		RedBlackNode Gparent = node.getParent().getParent().clone();
		RedBlackNode LeftChild = null;
		RedBlackNode RightChild = null;
		if(node.getLeftChild() != null)
		 LeftChild = node.getLeftChild().clone();
		if(node.getRightChild() != null)
		 RightChild = node.getRightChild().clone();
		if(node.getParent().getParent().getRoot()) {
			
			node.getParent().getParent().setRoot(false);
			node.setRoot(true);
			rootNode = node;
			rootNode.setColor("black");
		}
		if(node.getParent().getParent().getParent() !=null) {
		if(node.getParent().getParent().getParent().getLeftChild() == node.getParent().getParent()) {
			node.getParent().getParent().getParent().setLeftChild(node);
		}else {
			node.getParent().getParent().getParent().setRightChild(node);
		}
		node.setParent(node.getParent().getParent().getParent());
		}

		node.setRightChild(Parent);
		node.setLeftChild(Gparent);
		

		if(RightChild != null) {

			node.getRightChild().setLeftChild(RightChild);
		}
		else {
			node.getRightChild().setLeftChild(null);
		}
		if(LeftChild != null) {

			node.getLeftChild().setRightChild(LeftChild);
		}
		else {
			node.getLeftChild().setRightChild(null);
		}

		node.getLeftChild().setParent(node);
		node.getRightChild().setParent(node);
		
		String color = node.getLeftChild().getColor();
		node.getLeftChild().setColor(node.getColor());
		node.setColor(color);

	}

	public void leftRight(RedBlackNode node) {
		RedBlackNode Parent = node.getParent().clone();
		RedBlackNode Gparent = node.getParent().getParent().clone();
		RedBlackNode LeftChild = null;
		RedBlackNode RightChild = null;
		if(node.getLeftChild() != null)
		 LeftChild = node.getLeftChild().clone();
		if(node.getRightChild() != null)
		 RightChild = node.getRightChild().clone();
		
		if(node.getParent().getParent().getRoot()) {
			
			node.getParent().getParent().setRoot(false);
			node.setRoot(true);
			rootNode = node;
			rootNode.setColor("black");
		}
		if(node.getParent().getParent().getParent() !=null) {
		if(node.getParent().getParent().getParent().getLeftChild() == node.getParent().getParent()) {
			node.getParent().getParent().getParent().setLeftChild(node);
		}else {
			node.getParent().getParent().getParent().setRightChild(node);
		}
		
		node.setParent(node.getParent().getParent().getParent());
		}

		node.setLeftChild(Parent);
		node.setRightChild(Gparent);
		
	
		if(LeftChild != null) {

			node.getLeftChild().setRightChild(LeftChild);
		}
		else {
			node.getLeftChild().setRightChild(null);
		}
		if(RightChild != null) {

			node.getRightChild().setLeftChild(RightChild);
		}
		else {
			node.getRightChild().setLeftChild(null);
		}
	
		
		node.getLeftChild().setParent(node);
		node.getRightChild().setParent(node);
		
		String color = node.getRightChild().getColor();
		node.getRightChild().setColor(node.getColor());
		node.setColor(color);
	}

	public RedBlackNode Insert(RedBlackNode root, int Value) {
		if ((root.getLeftChild() == null) && (root.getRightChild() == null)) {
			return root;
		} else if ((root.getLeftChild() == null) && (Value < root.getRightChild().getValue())) {
			if(Value >= root.getValue()) {
				return root.getRightChild();
			}
			return root;
		} else if ((root.getRightChild() == null) && (Value > root.getLeftChild().getValue())) {
			if(Value <= root.getValue()) {
				return root.getLeftChild();
			}
			return root;
		} else if (Value < root.getValue()) {
			return Insert(root.getLeftChild(), Value);
		}
		else if (Value >= root.getValue()) {
			return Insert(root.getRightChild(), Value);}
		return root;
		
	}
	public RedBlackNode Insert(RedBlackNode root, String stringValue) {
		
		if ((root.getLeftChild() == null) && (root.getRightChild() == null)) {
			return root;
		} else if ((root.getLeftChild() == null) && (stringValue.compareTo(root.getRightChild().getstringValue()) <0)) {
			if(stringValue.compareTo(root.getstringValue()) > 0) {
				return root.getRightChild();
			}
			return root;
		} else if ((root.getRightChild() == null) &&(stringValue.compareTo(root.getLeftChild().getstringValue()) >0)) {
			if(stringValue.compareTo(root.getstringValue()) < 0) {
				return root.getLeftChild();
			}
			return root;
		} else if (stringValue.compareTo(root.getstringValue()) < 0)  {
			return Insert(root.getLeftChild(), stringValue);
		}
		else if (stringValue.compareTo(root.getstringValue()) > 0)  {
			return Insert(root.getRightChild(), stringValue);}
		return root;
		
	}


	public RedBlackNode ColorFlip(RedBlackNode node) {
		if (node.getParent().getParent() == null) {
			return node;
		}
		node.getParent().getParent().getLeftChild().setColor("black");
		node.getParent().getParent().getRightChild().setColor("black");
		node.getParent().setColor("black");
		node.getParent().getParent().setColor("red");
		node.getParent().getParent().setColor("red");		
		rootNode.setColor("black");
		return node.getParent().getParent();
	
	}

	public void getUncle(RedBlackNode node) {
		if((node.getParent().getParent() != null )) {
		if(node.getParent().getParent().getLeftChild() == node.getParent()) {
			 uncle = node.getParent().getParent().getRightChild();
		}else {
			 uncle = node.getParent().getParent().getLeftChild();
		}}
	}
	
}
