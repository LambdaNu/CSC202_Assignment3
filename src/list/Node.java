package list;

public class Node <T>{
	private T data; 
	private Node<T> link; 
//	private Node<T> prev; 
	
	public Node(T data){
		this.data = data; 
		this.link = null; 
		//this.prev = null;
	}
	
	

	public Node(T data,Node<T> link){
		this.data = data; 
		this.link = link; 
		//this.prev = null; 
	}
	public Node(T data,Node<T> link, Node<T> prev){
		this.data = data; 
		this.link = link; 
		//this.prev = prev; 
	}
	
	public void setData(T data){this.data = data;}
	public T getData(){return data;}
	public void setLink(Node<T> link){this.link = link;}
	public Node<T> getLink(){return link;}
	
/*	public void setBefore(Node<T> prev){this.prev = prev;};
	public Node<T> getBefore(){return prev;}
	*/
	
}