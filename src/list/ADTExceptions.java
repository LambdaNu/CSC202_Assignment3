package list;

public class ADTExceptions extends Exception {

	private static final long serialVersionUID = 1L;
	private String message = null; 
	public ADTExceptions(){super();}
	public ADTExceptions(String message) {
		super(message);
	}
	public String StackUnderflowException(String message){
		//super(message);
		return this.message;
	}
	
	
	
}
