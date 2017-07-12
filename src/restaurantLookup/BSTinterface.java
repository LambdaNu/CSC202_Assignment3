package restaurantLookup;

public interface BSTinterface<T extends Comparable<T>> {
	static final int preorder = -1;
	static final int inorder = 0; 
	static final int postorder = 1; 
	//public enum order{preorder, inorder, postorder};
	
	void add(T x);
	boolean remove(T x);
	boolean contains(T x);
	boolean isEmpty(); 
	int size();
	@Override
	String toString(); 
	T get(T x);
	/*void reset(order type); 
	T getNext(order type); */
	
	void reset(int type); 
	T getNext(int type);
}
