package list;

import static org.junit.Assert.*;

import org.junit.Test;

public class Junit_StackTest {

	@Test
	public void emptySizeTest() {
		//test on empty.
		Stack s = new Stack();
		int result = s.size();
		assertEquals(0,result);
	}

	@Test
	public void hasValuesSizeTest() {
		//test with items. 
		Stack s = new Stack();
		try {
			s.push(Math.random());
			s.push(Math.random());
		} catch (ADTExceptions e) {
			e.printStackTrace();
		}
		//fail("Not yet implemented");
		int result = s.size();
		assertEquals(2,result);
	}
	
	@Test
	public void pushTest() throws ADTExceptions {
		//test success: push on a stack.
		//Also functionally a test on toString
		Stack s = new Stack();
		double m = Math.random(); 
		try {

			s.push(m);
			//s.push(m+1);
			//fail("Should not reach here.");
		}// catch (ADTExceptions e) {
			//Expected: Underflow Exception
			//e.printStackTrace();
		//}
		finally{
			String result = s.toString();
			assertEquals("[" + m + "],",result);
		}
	}
	
	@Test
	public void topTest() throws ADTExceptions {
		//test success: push on a stack.
		//Also functionally a test on toString
		Stack s = new Stack();
		double m = Math.random(); 
		try {

			s.push("Jenny");
			s.push("Johnny");
			String result = (String) s.top();
			System.out.println(result);
			assertEquals("Johnny",result);
			//fail("Should not reach here.");
		}// catch (ADTExceptions e) {
			//Expected: Underflow Exception
			//e.printStackTrace();
		//}
		finally{

		}
	}
	
	@Test
	public void isFull() throws ADTExceptions {
		//test success: push on a stack.
		//Also functionally a test on toString
		Stack s = new Stack();
		double m = Math.random(); 
		try {

			s.push(m);
			s.push(m+1);
			//fail("Should not reach here.");
		}// catch (ADTExceptions e) {
			//Expected: Underflow Exception
			//e.printStackTrace();
		//}
		finally{
			boolean result = s.isFull();
			assertEquals(false, result);
		}
	}
	
	@Test
	public void isEmpty() throws ADTExceptions {
		//test success: push on a stack.
		//Also functionally a test on toString
		Stack s = new Stack();
		double m = Math.random(); 
		try {

			s.push(m);
			s.push(m+1);
			//fail("Should not reach here.");
		}// catch (ADTExceptions e) {
			//Expected: Underflow Exception
			//e.printStackTrace();
		//}
		finally{
			boolean result = s.isEmpty();
			assertEquals(false, result);
		}
	}

	
	@Test(expected=ADTExceptions.class)
	public void popTest_anticipatedFailure() throws ADTExceptions {
		//test failure: pop on empty stack.
		Stack s = new Stack();
		try {
			s.pop(); 
			fail("Should not reach here.");
		}// catch (ADTExceptions e) {
			//Expected: Underflow Exception
			//e.printStackTrace();
		//}
		finally{
			//no catch!
		}
	}

	@Test
	public void popTest() throws ADTExceptions {
		//test success: pop on a stack with items within.
		//Also functionally a test on toString
		Stack s = new Stack();
		double m = Math.random(); 
		try {

			s.push(m);
			s.push(m+1);
			s.pop(); 
			//fail("Should not reach here.");
		}// catch (ADTExceptions e) {
			//Expected: Underflow Exception
			//e.printStackTrace();
		//}
		finally{
			String result = s.toString();
			assertEquals("[" + m + "],",result);
		}
	}
	
	@Test
	public void toStringEmpty() throws ADTExceptions {
		//test on empty stack: toString (we've had a test of it containing and handling items well.
		Stack s = new Stack();
		double m = Math.random(); 
		try {

			s.push(m);
			s.push(m+1);
			s.pop(); 
			s.pop();
			//fail("Should not reach here.");
		}// catch (ADTExceptions e) {
			//Expected: Underflow Exception
			//e.printStackTrace();
		//}
		finally{
			String result = s.toString();
			assertEquals("",result);
		}
	}
	
}
