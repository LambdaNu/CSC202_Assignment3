package restaurantLookup;

//import java.util.ArrayList;
import java.util.LinkedList; 

public class BST<T extends Comparable<T>> implements BSTinterface<T> {
	
	//Variables
	BSTNode<T> root = null; 
		//ArrayList<String> array  = new ArrayList<String>(); 
	int increment = 0; 
	boolean found = false; 
	private LinkedList<T> preOrderList;
	private LinkedList<T> inOrderList;
	private LinkedList<T> postOrderList; 
	private String str = ""; 

	//Constructors
	public BST(){root = null;};

	//Methods
	@Override
	/**Add an element to binary search tree. Balance Maintained.
	 * @param Element to be added to BST
	 */
	public void add(T x) {
		root = recursiveAdd(x,root);	
		increment++;
		
	}
		/**Recursively searches the tree to find the correct node in which to add the selected item.
		 * Accessed via Add.
		 * @param x data item to be added, retrieved from add.
		 * @param tree BSTNode currently being checked.
		 * @return BSTNode (generally the next one in the tree.
		 */
		private BSTNode<T> recursiveAdd(T x, BSTNode<T> tree){
			if(tree == null){
				tree = new BSTNode<T>(x);
			}else if(x.compareTo(tree.getData()) <= 0){
				tree.setLeft(recursiveAdd(x, tree.getLeft()));
			}else{
				tree.setRight(recursiveAdd(x,tree.getRight()));
			}
			return tree; 
		}
	
	/** Checks BST if tree contains element. Returns true if found, otherwise: returns false. 
	 * @param x Data element to be checked
	 */
	@Override
	public boolean contains(T x) {
		return (recursiveContains(x,root));
	}
		/**Recursively searches the tree to find the containing node.
		 * Accessed from Contains.
		 * @param x the object to be checked.
		 * @param tree the current node
		 * @return true if found, false if not.
		 */
		private boolean recursiveContains(T x, BSTNode<T> tree){
			if(tree==null){
				return false;
			}else if(x.compareTo(tree.getData()) > 0 ){
				return recursiveContains(x, tree.getRight());
			}else if(x.compareTo(tree.getData()) < 0 ){
				return recursiveContains(x, tree.getLeft());
			}else{
				return true;				
			}
		}	
		
		
	@Override
	/**
	 * @param the element to be removed
	 * return T the element removed
	 */
	public boolean remove(T x) {
		root = recursiveRemove(x, root);
		if(found == true){
			increment--;
		}
		return found;
	}
		/**Recursively searches and finds the removal node
		 * Accessed via Remove 
		 * @param x data element to be removed
		 * @param tree current node being tested
		 * @return node to be tested.
		 */
		private BSTNode<T> recursiveRemove(T x, BSTNode<T> tree){
			if(tree == null){
				found = false;
			}else if(x.compareTo(tree.getData()) > 0 ){
				tree.setRight(recursiveRemove(x, tree.getRight()));
			}else if(x.compareTo(tree.getData()) < 0 ){
				tree.setLeft(recursiveRemove(x, tree.getLeft()));
			}else{
				found = true;
				tree = removeNode(tree);
			
			}
			return tree; 
		}
		/** Removes a node and grabs the leaves where appropriate.
		 * Accessed via recursiveRemove. 
		 * @param tree BSTNode to be removed.
		 * @return BSTNode
		 */
		private BSTNode<T> removeNode(BSTNode<T> tree){
			T t; 
			if (tree.getLeft() == null)
	            return tree.getRight();
	        else if (tree.getRight() == null)
	            return tree.getLeft();
	        else {
	            t = getPrevious(tree.getLeft());
	            tree.setData(t);
	            tree.setLeft(recursiveRemove(t, tree.getLeft()));
	            return tree;
	        }
			
		}
		/** Returns the farthest item in the Tree
		 * return object
		 * @param BST Node, the item to be passed.
		 */
		private T getPrevious(BSTNode<T> tree){
			while (tree.getRight() != null)
	            tree = tree.getRight();
	        return tree.getData();
		}


	/** Returns false if Binary Search Tree is Empty. Returns true if this is not true.
	 * return boolean value indicating if Binary Search Tree is empty. 
	 */
	@Override
	public boolean isEmpty() {
		return(root == null);
	}

	/** Returns an integer indicating the size of the BST.
	 * return integer indicating the number of nodes in the Binary Search Tree
	 */
	@Override
	public int size() {	
		return increment;
	}

	/**Returns object from tree if it matches.
	 * @param x object to be matched against. 
	 * @return returns null if nothing found. Otherwise returns object. 
	 */
	@Override
	public T get(T x) {
		return recursiveGet(x,root);
	}
		/**Returns object from tree if it matches.
		 * If no match, is null.
		 * @param x object to be matched.
		 * @param tree current node.
		 * @return match If found. If not, null. 
		 */
		private T recursiveGet(T x, BSTNode<T> tree){
			if(tree==null){
				//tree = new BSTNode<T>(x);
				return null;
			}else if (x.compareTo(tree.getData()) < 0){
	            return recursiveGet(x, tree.getLeft());
	        } else if(x.compareTo(tree.getData()) > 0 ){
	            return recursiveGet(x, tree.getRight());
	        } else{
		        return tree.getData();
	        }
		}

	/**Grabs current position and sets it as the root for an iteration through the BST.
	 *  @param ordertype  accepted values are preorder, inorder, and postorder
	 */
	@Override
	public void reset(int ordertype) {
		if(ordertype == preorder){
			preOrderList = new LinkedList<T>();	
			preOrder(root);
		}else if(ordertype == inorder){
			inOrderList = new LinkedList<T>();	
			inOrder(root);
		}else if(ordertype == postorder){
			postOrderList = new LinkedList<T>();	
			postOrder(root);
		}
	}

			/** Sets tree to preorder-order. Accessed via Reset. 
			 * @param tree BSTNode, often the root is passed
			 */
			private void preOrder(BSTNode<T> tree) {
				if(tree != null){
		            preOrderList.add(tree.getData());
		            preOrder(tree.getLeft());
		            preOrder(tree.getRight());
				}
			}
			/** Sets tree to inorder-order. Accessed via Reset. 
			 * @param tree BSTNode, often the root is passed
			 */
			private void inOrder(BSTNode<T> tree) {
				if(tree != null){
		            inOrder(tree.getLeft());
		            inOrderList.add(tree.getData());
		            inOrder(tree.getRight());
				}
				
			}
			/** Sets tree to postorder-order. Accessed via Reset. 
			 * @param tree BSTNode, often the root is passed
			 */
			private void postOrder(BSTNode<T> tree) {
				if(tree != null){
		            postOrder(tree.getLeft());
		            postOrder(tree.getRight());
		            postOrderList.add(tree.getData());

				}
			}
	

	/** Returns the next element in the tree based on Ordertype: 
	 *  @param ordertype  accepted integers are -1 = preorder, 0 = inorder, 1 = postorder
	 *  
	 */
	@Override
	public T getNext(int ordertype) {
		if(ordertype == preorder){
			return this.preOrderList.remove();	
		}else if(ordertype == inorder){
			return this.inOrderList.remove();
		}else if(ordertype == postorder){
			return this.postOrderList.remove();
		}else{
			return null;
		}
	}
	
	/**Returns Tree Inorder.
	 * 
	 */
	@Override
	public String toString(){
		reset(inorder);
		return inOrderList.toString();
		/*if(root == null){
			return this.str;  
		}else{recursiveToString(root);}
		return this.str; */ 
	}
	/*private BSTNode recursiveToString(BSTNode<T> tree){
			/*if (root == null){return null;}
			this.str = str + tree.getData() + " ";
			if(tree.getLeft()!=null){
				recursiveToString(tree.getLeft());
			}
			if(tree.getRight()!= null){
				recursiveToString(tree.getRight());
			}
			
			return null; 
	        /*recursiveToString(tree.getLeft());
	        this.str = this.str + tree.getData() + " ";
	        recursiveToString(tree.getRight());
	        return tree; 
	} */
	
	public String toString(int ordertype){
		if(ordertype == preorder){
			this.reset(preorder);
			return preOrderList.toString();
		}else if(ordertype == inorder){
			this.reset(inorder);
			return inOrderList.toString();
		}else if(ordertype == postorder){
			this.reset(postorder);	
			return postOrderList.toString();
		}else{
			return null;
		}
	}

}
