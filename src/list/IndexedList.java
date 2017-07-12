package list;

import java.io.Serializable;
import java.util.Arrays;

public class IndexedList<T extends Comparable> implements DataStructure<T>, Serializable {
	private T[] indexedList; 
	private int ind; 
	private int pointer; 

	private static final long serialVersionUID = 1L;

	

	public IndexedList(){
		this.ind = 0;
		this.pointer = 0;
		this.indexedList = (T[]) new Comparable[10]; //preset it with ten items, and then grow as needed with enlarge. 
	}
	
/*	public IndexedList(int ListSize){
		this.ind = 0; 
		this.IndexedList = (T[]) new Object[ListSize];
		
	}  */ //more hassle than it's worth. 
	
	/**Add an item to the next available slot on the list.
	 * @exception Must be unique to list. Exception thrown when duplicate.
	 * @param element adds an item to the end of the IndexedList
	 */
	public void Add(T x) throws ADTExceptions{
		T[] testList = Arrays.copyOf(this.indexedList, size()+1);
		testList[ind] = x;
		if(!unique(testList)){
			throw new ADTExceptions("Uniqueness Exception: the element you have attempted to queue is not unique to the list.");
		} else{
			if(ind == this.indexedList.length){enlargeList();}
			this.indexedList[ind] = x; 
			ind++;
		}
		//this.indexedList[ind] = element; 
		
	}
	
	/** Enlarges the array by ten.  
	 */
	private void enlargeList(){
		//this.stack = Arrays.copyOf(this.stack, this.stack.length+1);
		this.indexedList = Arrays.copyOf(this.indexedList, this.indexedList.length+10);
		}
	
	/**Adds an element to the specified position in the list.
	 * 
	 * @param x element to be added
	 * @param index position in which it is to be added.
	 * @throws ADTExceptions If an invalid index is provided, an exception will be thrown. Invalid indexes include negative integers.
	 */
	public void Add(T x, int index) throws ADTExceptions{
			//System.out.println(index + " " + ind);
		if(index < 0 || index > ind){ //if it refers to an index which cannot be or is not the terminal item...
			throw new ADTExceptions("Invalid Index Provided. Index must be a positive integer and refer to an existing slot on the list.");
		}else if(contains(x)){
			throw new ADTExceptions("Element Violates Uniqueness: " + x);
		}else if(index >= ind){ //if it refers to a new element...
			if(ind == this.indexedList.length){enlargeList();}
			//System.out.println("Hit second condition");
			ind++;
			this.indexedList[index] = x; 
		}else{ //if it refers to a point it's overwriting...
			if(ind == this.indexedList.length){enlargeList();}
			//System.out.println("hit third condition");
			T temp = this.indexedList[index];
			if(index == 0){
				
				for( int i = size(); i > 0 ; i--){ //move to right of zero.
					this.indexedList[i+1] = this.indexedList[i];
				}
				this.indexedList[1] = temp;
				this.indexedList[index] = x;
				this.ind++;
			}else{
				for(int i = index-1; i <= size(); i++){ //move over, roxanne.
					this.indexedList[i+1] = this.indexedList[i];
				}
				this.indexedList[index] = x; 
				this.indexedList[index+1] = temp;
				this.ind++;
			}

		}
		
	}
	
	
	/**Passed an argument and, if an equivalent element exists on the list, removes one instance of that element. 
	 * Returns a boolean value indicating whether an element was actually removed.
	 * 
	 * @param x argument to be passed
	 * @return boolean value indicating if successfully removed.
	 * @throws ADTExceptions one cannot remove from an empty list.
	 */
	public boolean remove(T x)throws ADTExceptions{
		if(this.ind == 0){throw new ADTExceptions("Cannot remove from an empty list.");
		}else if(!contains(x)){return false;
		}else{
			for(int i = 0; i < ind; i++){ //find element
				if(indexedList[i].compareTo(x) == 0){
					for(int j = i; j < ind-1; j++){
						indexedList[j] = indexedList[j+1];
					}
					indexedList[ind] = null;
					ind--;					
					/*int myInd = i; 
					//now remove element
					indexedList[i] = null;
					do{
						indexedList[i] = indexedList[i+1];
					}while(i > myInd); */
				}
			}
			return true; 
		}
	}
	
	/**Passed an argument and returns a boolean indicating whether the list contains an equivalent element.
	 * 
	 * @param x element to be passed
	 * @return boolean if true, the element exists in the list. If false, it does not.
	 */
	public boolean contains(T x){
		//CONTAINS SHOULD NOT THROW AN EXCEPTION: IN THE DOCUMENATION IT RETURNS FALSE OR TRUE. THIS IMP IS FINE.
		for(int i = 0; i < ind; i++){
			if(indexedList[i].compareTo(x) == 0){
				return true;
			}
		}
			return false; 
	}
	
	
	/**Passed an element, determines uniqueness of the elements within the list.
	 * 
	 * @param s element to be passed
	 * @return boolean if false, it is not unique to the list. If true, it is unique to the list.
	 */
	private boolean unique(T[] s){
		//brute force check...thank goodness we dont have tons of users
		//Arrays.sort(s);
		//System.out.println(s.toString());
		if(this.ind > 0 ){

		for(int i = 0; i < ind;i++){
			for( int j = 0; j <=  ind; j++){
				//System.out.println("Hit unique: " + i + " " + j);
				if(s[i].compareTo(s[j]) == 0 && i != j){
					return false; 
				}
			}
		}}
		return true; 
	
	}
	
	/**Passed an element it returns the index of the first such matching element (or -1)
	 * In this case, as uniqueness is enforced, it will return the only matching element in the list.
	 * @param x element to be retrieved.
	 * @return index of the retrievable element. Or -1 if element not found.
	 */
	public int indexOf(T x){
		//SHOULD NOT THROW AN EXCEPTION: IN THE DOCUMENTATION IF THE ITEM IS NOT FOUND, IT RETURNS -1;
		 int  v = -1;
	/*	if(!contains(x)){
			throw new ADTExceptions("Invalid entry: Element does not exist in list.");
		} */
		for(int i = 0; i < ind; i++){
			if(x == indexedList[i]){ v = i; return v; }
		}
		return v; 
	}
	
	/**Overwrites the element at the specified index with the element provided in x.
	 * 
	 * @param x the element to overwrite the original element.
	 * @param index the index at which the original element resides. 
	 * @throws ADTExceptions  If an invalid index is provided, an exception will be thrown. Invalid indexes include negative integers.
	 */
	public void set(T x, int index) throws ADTExceptions{
		if(index < 0 || index > ind){
			throw new ADTExceptions("Invalid passed index. Must be a viable item in list.");
		}else if(ind == 0){
			throw new ADTExceptions("Empty List: no item retrievable.");
		}else{
			indexedList[index] = x; 
			
		}
	}
	
	/**Passed an integer index it returns the element from the list at that position
	 * @param index the index from which you are attempting to retrieve a value
	 * @return value at specified index.
	 * @throws ADTExceptions  If an invalid index is provided, an exception will be thrown. Invalid indexes include negative integers.
	 */
	public T get(int index) throws ADTExceptions{
		if(index < 0 || index > ind){
			throw new ADTExceptions("Invalid passed index. Must be a viable item in list.");
		}else if(ind == 0){
			throw new ADTExceptions("Empty List: no item retrievable.");
		}else{
			return indexedList[index];	
		}
	}
	
	
	@Override
	/**Returns a boolean determining if the structure is empty of all elements or not.
	 * 
	 * @return Boolean will be true if empty of all elements. 
	 */
	public Boolean isEmpty() {
		return (ind == 0);
	}

	@Override
	/**Size: Returns an integer indicating the size of the list.
	 * @return int indicating the size. Cannot be under zero.
	 */
	public int size() {
		return ind;
	}
	
	
	/**Returns a formatted string containing the list elements. 
	 * returns String formatted string containing the list elements. 
	 */
	@Override
	public String toString(){
		String outString = "";
		for(int i = 0; i < ind;i++){
			outString = outString + "[" + this.indexedList[i].toString() + "]";
		}
		return outString;
	}
	/**Resets your point to the front of the list.
	 * To be used with getNext() for iteration purposes.
	 */
	public void reset(){
		this.pointer = 0;
	}
	
	/** Returns the next element, and updates the current position. To be paired with reset().
	 * 
	 * @return element from the next item.
	 * @throws ADTExceptions if no item is found at the next point, an exception is thrown. 
	 */
	public T getNext() throws ADTExceptions{
		int testpoint = this.pointer + 1; 
		if(indexedList[testpoint] == null){
			throw new ADTExceptions("Null Pointer Exception: no next item found");
		}else{
			this.pointer++; 
			return indexedList[pointer];
		}

	}

}
