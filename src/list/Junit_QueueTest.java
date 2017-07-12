package list;

import static org.junit.Assert.*;

import org.junit.Test;

public class Junit_QueueTest {

	@Test
	public void isEmptyTestT() {
		//testing if true.
		Queue q = new Queue(); 
		boolean b = q.isEmpty();
		assertEquals(true, b);
		//fail("Not yet implemented");
	}
	@Test
	public void isEmptyTestF() throws ADTExceptions {
		//testing if false
		Queue q = new Queue(); 
		q.enqueue(Math.random());
		boolean b = q.isEmpty();
		assertEquals(false, b);
		//fail("Not yet implemented");
	}
	
	
	
	@Test
	public void SizeTest() throws ADTExceptions {
		//testing for sizing.
		Queue q = new Queue(); 
		q.enqueue(Math.random()); //a lil dirty but meh.
		q.enqueue(Math.random());
		q.enqueue(Math.random());
		int s = q.size();
		assertEquals(3, s);
		//fail("Not yet implemented");
	}
	
	@Test
	public void toStringTest() throws ADTExceptions {
		Queue q = new Queue(); 
		q.enqueue(5); //a lil dirty but meh.
		q.enqueue(7);
		q.enqueue(3.46);
		String s = q.toString();
		assertEquals("[5][7][3.46]", s);
		//fail("Not yet implemented");
	}
	
	@Test(expected = ADTExceptions.class)
	public void dequeueTestF() throws ADTExceptions {
		//testing for underflow/empty queue.
		Queue q = new Queue(); 
		q.dequeue();
		String s = q.toString();
	}
	@Test
	public void dequeueTestT() throws ADTExceptions {
		//testing for underflow/empty queue.
		Queue q = new Queue(); 
		q.enqueue("Jenny");
		q.dequeue();
		String s = q.toString();
		assertEquals("", s);
	}
	
	@Test
	public void enqueueTest() throws ADTExceptions {
		//testing for underflow/empty queue.
		Queue q = new Queue(); 
		q.enqueue("Jenny");
		//q.dequeue();
		String s = q.toString();
		assertEquals("[Jenny]", s);
	}

}
