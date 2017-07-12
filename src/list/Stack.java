package list;

import java.util.Arrays;

public class Stack<T> implements DataStructure {
//	@SuppressWarnings("rawtypes")
	private T[] stack;
	private int ind;
	
	
	@SuppressWarnings("unchecked")
	public Stack(){
		this.ind = 0; 
		this.stack = (T[]) new Object[ind]; //because you can't really make real generic arrays and work with them.
	}
	
	
	
	/**Returns an integer representing the comparison of the items. 
	 * @param	x	any element can be passed.
	 * @return 	int	a comparison of the two items. 
	 */
	public int compareTo(T x) {
		//It's a generic...I guess the question is, does it return the same 'memory location' or not. 
		//for all primitives it will and for int/double it'll actually compare everything involved. For anything else, it's gonna fail.
		if(this.stack.equals(x)){
			return 0; 
		}else{return -1; }
	}

	@Override
	/**Returns true if the array is empty of all values. Returns false if there is data within the array.
	 * @return	boolean value representing whether or not the stack is empty.
	 */
		public Boolean isEmpty() {
			if(ind == 0){
				return true; 
			}else{ return false;}
		}
	/**Returns true if the stack is full. Returns false if there are open items within the array.
	 * @return	boolean value representing whether or not the stack is full.
	 */
		public Boolean isFull() { //yes this means an empty array would be full....
			if(ind == stack.length){
				return true; 
			}else{ return false;}
		}

	@Override
	/**Returns true if the array is empty of all values. Returns false if there is data within the array.
	 * @return	boolean value representing whether or not the stack is empty.
	 */
		public int size() {
			//return stack.length;
			return ind;
		}
	
	/**Adds an item to the stack. 
	 * @param	x adds element to the top of the stack.
	 */
	public void push(T x) throws ADTExceptions{
		T[] testStack = Arrays.copyOf(this.stack, size()+1);
		testStack[ind] = x;
		
		if(!unique(testStack)){
			throw new ADTExceptions("Uniqueness Exception: the element you have attempted to queue is not unique to the stack.");
			//System.out.println("Failure to add element " + x + ". Violates uniqueness.");
		}else{
			if(ind == this.stack.length){ enlargeStack(); }	
			this.stack[ind] = x;
			this.ind++;}		
	}
	
	/**Removes an item from the stack. 
	 * @return	x removes element to the top of the stack.
	 */
	public T pop() throws ADTExceptions{
		if(ind == 0){
			throw new ADTExceptions("Underflow Exception: cannot pop on empty stack.");
		}
		T x = stack[ind];
		stack[ind] = null; 
		ind--; 
		//stack(ind) in the middle of writing pop
		return x; 
	}
	
	/**Returns an item from the top of stack. 
	 * @return	T returns element sitting at the top of the stack.
	 */
	public T top() throws ADTExceptions{
		if(ind == 0){
			throw new ADTExceptions("There are no items in the Stack.");
		}
		T x = stack[ind-1];
		return x; 
	}
	
	
	/**Returns a string of all elements within the stack, delmited by brackets and commas.
	 * @return	String 	returns a formatted string of all elements in stack.
	 */
	@Override
	public String toString(){
	 String outString = "";
		//for(int i = 0; i < this.stack.length;i++){
		for(int i = 0; i < ind;i++){
			//outString = outString + "[" + stack[i].getClass().getSimpleName() + "],";
			outString = outString + "[" + this.stack[i].toString() + "],";
		}
		return outString; 
	}
	
	/**Enlarges stack by 10, in order to maintain an "unbounded" array."
	 * 
	 */
	private void enlargeStack(){
		//this.stack = Arrays.copyOf(this.stack, this.stack.length+1);
		this.stack = Arrays.copyOf(this.stack, this.stack.length+10);
		}
	
	/** Checks for uniqueness of the introduced element via a brute force check.
	 * This does not depend on any kind of sort, but is consequently very slow and inefficient.
	 * 
	 * @param s	The introduced element
	 * @return boolean true if the item is unique to the array/stack. returns false if match is found.
	 */
	private boolean unique(T[] s){
		//brute force check...thank goodness we dont have tons of users
		//T[] copy = Arrays.copyOf(this.stack,  this.stack.length);
		//Arrays.sort(copy);
		if(this.ind > 0 ){

		for(int i = 0; i < ind;i++){
			for( int j = i+1; j <  ind; j++){
				if(s[i].equals(s[j]) && i != j){
					return false; 
				}
			}
		}}
		return true; 
	
	}

}
