package list;
//import java.util.*;

public class Queue<T extends Comparable> implements DataStructure<T> { 
	private Node<T> first;
	//private Node<T> current; 
	private Node<T> last; 
	int count = 0; 
	
	public Queue(){
		/**
		 * 
		 */
		first = null; last = null; 
	}


	/**Returns a string of all elements within the stack, delmited by brackets and commas.
	 * @return	String 	returns a formatted string of all elements in queue.
	 */
@Override
public String toString(){
	String mystring = "";
	/*for(Node n = first; n.getLink() != null; n = n.getLink()){
		mystring = mystring + "[" + n.getData() + "]";
	} */
	Node<T> n = first; 
	int i = 0; 
	while(i < count){
		mystring = mystring + "[" + n.getData() + "]";
		n = n.getLink();
		i++;
		
	}
	/*	Node n = first; 
	for(int i = 0; i <= count; i++){
		mystring = mystring + "[" + n.getData() + "]";
		n = n.getLink();
	} */
	
	return mystring;
}

/**Returns true if the queue is empty of all values. Returns false if there is data within the array.
 * @return	boolean value representing whether or not the queue is empty.
 */
@Override
public Boolean isEmpty() {
	if(first == null && last == null){ //by all logic, one should only require first to be null...but eh.
		return true;
	}else{
		return false; 
	}

}

/**Adds an item to the queue.
 * 
 * @param element the item to be added to the queue. 
 * @throws ADTExceptions upon the addition of a duplicate element. 
 */

public void enqueue(T element) throws ADTExceptions{
	Node<T> newNode = new Node<T>(element);
	
	if(isEmpty()){
		first = newNode;
	}else if(!unique(element)){
		throw new ADTExceptions("Uniqueness Exception: the element you have attempted to queue is not unique to the queue.");
	}
	else{
		last.setLink(newNode);
	}
	last = newNode; 
	count++;
}

private boolean unique(T element){
	Node<T> n = first; 
	int i = 0; 
	
	while(i < count){
		if(element == n.getData()){
			return false; 
		}
		n = n.getLink();
		i++;
		
	}
	return true; 
}

/**Removes an element from the front of the queue
 * 
 * @return returns removed element
 * @throws ADTExceptions upon the attempt to remove from empty queue.
 */
public T dequeue() throws ADTExceptions {
	T element = null; 
	if(isEmpty()){
		throw new ADTExceptions("Underflow Exception: Cannot Dequeue on empty Queue.");
	}else{
		element = first.getData();
		if(first == last){
			last = null; //handling if this brings the queue down to one. 
		}
		first = first.getLink();
		count--;
	}
	return element; 
}


/**Returns an integer representing the number of elements in the queue.
 * @return int value representing the number of elements in the queue
 */
@Override
public int size() {
	return count;
}
}
  