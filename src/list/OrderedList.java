package list;



public class OrderedList<T extends Comparable> implements DataStructure<T> {
	int length = 0;
	Node<T> first = null;
	Node<T> current = null; 
	//Node<T> last = null;
	
	public OrderedList(){
		
		first = null; 
		length = 0; 
		current = first; 
	}
	
	/** Retrieves the data from the next node in list
	 * 
	 * @return returns the data from the next node in list. 
	 */
	public T getNext(){
		T element = null; 
		if(current.getLink() == null){
			return element; 
		}else{
			current = current.getLink();
			return current.getData(); 
		}
	}
	

	/**Returns a boolean determining if the structure is empty of all elements or not.
	 * 
	 * @return Boolean will be true if empty of all elements. 
	 */
	@Override
	public Boolean isEmpty() {
		if(length == 0 ){
			return true; 
		}else{return false;}
	}

	
	
	@Override
	/**Size: Returns an integer indicating the size of the list.
	 * @return int indicating the size. Cannot be under zero.
	 */
	public int size() {
		return length; 
	}
	
	//@SuppressWarnings("unchecked")
	/**Adds an element to the orderedlist.
	 * First determines if the list is empty.
	 * Then determines if it's a duplicate. 
	 * From there, it determines if the element is to go at the front of the list. 
	 * After that it determines where the element should go within the list.
	 * 
	 * @param x the element the user attempts to add.
	 * @throws ADTExceptions upon attempted addition of an element which would violate uniqueness.
	 */
	public void add(T x) throws ADTExceptions{
		Node<T> n = new Node(x);
		reset();
		Node<T> next = new Node(null);
		//if empty list 
		if(first == null){
			//System.out.println("hit first condition)");
			first = n;
			//current = first; 
			//length++;
			length++;
		}else if(contains(x)){	//if there's nothing you can't check for uniqueness, if there's something you can...
			throw new ADTExceptions("Uniqueness Violation: Duplicate Entry."); 
		}else if(x.compareTo(first.getData()) < 0){ //add at beginning (before first entry) 
			//if these are not the same, for one, also lesser
			current = n; 
			n.setLink(first);
			first = n;
			length++;
		}else{
			boolean b = true;
			while(b  == true){
				next = current.getLink();
				if(current.getLink() ==null){ 
					current.setLink(n); 
					length++;  
					b = false;}
				if(current.getLink().getData().compareTo(x) > 0){
					current.setLink(n); 
					n.setLink(next); 
					length++;
					b = false;}
				
				current = current.getLink(); 
				}
			}
			
			
			
			/*
			reset(); 

			for(int i = 0; i < length; i++){
			//while(current.getLink() != null){
			next = current.getLink();
			if(current.getLink() == null){//if at end of rope
				current.setLink(n);
				length++;
			}else if(x.compareTo(current.getData()) > 0 && x.compareTo(next.getData()) < 0){ //if the current item is greater than the last...
					System.out.println("Hit last condition 1");
					current.setLink(n);
					n.setLink(next);
					length++;
			}else if( (x.compareTo(current.getData()) > 0)){
					System.out.println("Hit last condition 3");
					System.out.println(x.compareTo(current.getData()) + " " + x);
					//current.setLink(n);
					//length++;
					//break; 
				} //current.getLink(); 

				current = current.getLink(); 
			} */
			
			
		}
		//regardless, increment length (length++)
	
	
	/** Removes the specified data from the list.
	 * 
	 * @param x sets the data-item to be removed from this list.
	 * @return T returns the data from the item in question.
	 * @throws ADTExceptions if one attempts to remove from an empty list, or if the element does not exist. 
	 */
	public T remove(T x) throws ADTExceptions{
		T tmp = null;
		Node<T> n = first; 
		reset(); 
		//throw exception if !contains
		if(first == null){
			throw new ADTExceptions("Cannot remove from empty list. ");
		}
		else if(!contains(x)){
			throw new ADTExceptions("Element not found.");
		}else{
			for(int i = 0; i < length; i++){
				n = current.getLink(); 
				if(x.compareTo(n.getData()) == 0){
					current.setLink(n.getLink());
					length--; 
					return n.getData(); 
				}
				current = current.getLink(); 
				
			}
			//current.getLink().getData() == find(element))
			//Node(Node(data))) = Node(Data)
			
			current = find(x);
			tmp = current.getData(); 
			
			//length--;
			return tmp;}
		}

	
	/** A boolean which returns true if the item has been found in the list. 
	 *  It returns false if it hasnt
	 * @param x the item which is being tested. 
	 * @return boolean returns true if item is found. 
	 */
	public boolean contains(T x){
		Boolean contains = false; 
		Node n = first; 
		for(int i = 0; i < length; i++){
				
				if(x.compareTo(n.getData()) == 0){
					contains = true; 
					return contains; 
				}
			n = n.getLink();}
		
		
		/*while (n.getLink() != null){
			if(x.compareTo(n.getData()) == 0){
				System.out.println("hit contains: true");
				contains = true; 
				return contains; 
			} 
			n.getLink(); 
		} */
		return contains;
	}
	
	/** Returns the node which has the specific data.
	 * 
	 * @param x data/element in question
	 * @return node
	 * @throws ADTExceptions if empty list or if element does not exist.
	 */
	public Node<T> find(T x) throws ADTExceptions{
	//can't binary search because linkedlist so... here we go.
		boolean found = true; 
		reset(); //start at first
		
		if(first == null){ //because someone is going to try to break this on an empty list.
			throw new ADTExceptions("Cannot find element on Empty List");
		}
		
		for(int i = 0; i < length; i++){
			if(x.compareTo(current.getData()) == 0){ //if match return current.
				return current; 
			}
			current = current.getLink();
		}
		throw new ADTExceptions("Element does not exist.");  //if it failed to find the item... 
	}
	
	public void reset(){
		current = first; 
	}
	
/**Returns a formatted string containing the list elements. 
 * returns String formatted string containing the list elements. 
 */
	@Override
	public String toString(){
		//System.out.println("Hit toString");
		Node n = first; 
		String outString = "";
		if(first == null){ //because someone is going to try to break this on an empty list.
			return outString; 
		}
		if(first.getLink() == null){ //if there is only one item in the list.
			outString = outString + "[" + n.getData() + "]";
		}else{
			for(int i = 0; i < length; i++){
				//System.out.println(outString);
				outString = outString + "[" + n.getData() + "]"; 		
				n = n.getLink();
			}
			
			/*while(n.getLink() != null){
				outString = outString + "[" + n.getData() + "]" + ","; 
			} */
		}
		return outString; 
	}
}