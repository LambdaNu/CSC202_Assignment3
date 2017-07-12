package list;

import static org.junit.Assert.*;

import org.junit.Test;

public class Junit_OrderedlistTest {

	@Test
	public void toStringtest() {
		//test on null
		Ordered_List o = new Ordered_List();
		
		String str = (o.toString());
		assertEquals("", str);
		//fail("Not yet implemented");
	}
	
	@Test
	public void getNextTest() throws ADTExceptions {
		Ordered_List o = new Ordered_List();
		o.add(5);
		o.add(4);
		o.add(6);
		o.reset();

		//String str = (o.toString());
		assertEquals(5,	o.getNext());
		//fail("Not yet implemented");
	}
	@Test
	public void isEmptyTest() throws ADTExceptions {
		Ordered_List o = new Ordered_List();
		assertEquals(true,	o.isEmpty());
		
		o.add(5);
		o.add(4);
		o.add(6);
		o.reset();
		
		//String str = (o.toString());
		assertEquals(false,	o.isEmpty());
		
		//fail("Not yet implemented");
	}
	@Test
	public void sizeTest() throws ADTExceptions {
		Ordered_List o = new Ordered_List();
		o.add(5);
		o.add(4);
		o.add(6);
		assertEquals(3,	o.size());

	}
	
	@Test
	public void addTest() throws ADTExceptions {
		Ordered_List o = new Ordered_List();
		o.add(5);
		o.add(4); //before current position
		o.add(6); //after current
		o.add(1); //at front again.
		o.add(3); //between existing elements.
		String s = o.toString();
		assertEquals("[1][3][4][5][6]",s);
	}
	@Test(expected=ADTExceptions.class)
	public void addTestF() throws ADTExceptions {
		Ordered_List o = new Ordered_List();
		o.add(5);
		o.add(4); //before current position
		o.add(6); //after current
		o.add(1); //at front again.
		o.add(3); //between existing elements.
		o.add(4); //duplicate.
 		/*String s = o.toString();
		assertEquals("[1][3][4][5][6]",s); */
	}
	@Test(expected=ADTExceptions.class)
	public void removeTestF() throws ADTExceptions {
		//test removal of nonexistent item.
		Ordered_List o = new Ordered_List();
		o.add(5);
		o.add(6); 
		o.add(1); 
		o.add(3);
		o.remove(4); //remove nonexistent item. 
 		/*String s = o.toString();
		assertEquals("[1][3][4][5][6]",s); */
	}
	@Test
	public void removeTest() throws ADTExceptions {
		//test removal of existent item.
		Ordered_List o = new Ordered_List();
		o.add(5);
		o.add(6); 
		o.add(1); 
		o.add(4);
		o.add(3);
		o.remove(4); //remove existent item
 		String s = o.toString();
		assertEquals("[1][3][5][6]",s); 
	}
	@Test
	public void containsTest() throws ADTExceptions {
		Ordered_List o = new Ordered_List();
		int b = 4; 
		assertEquals(false,o.contains(b));
		o.add(5);
		o.add(4);
		o.add(6);
		assertEquals(true,o.contains(b));
		
		//fail("Not yet implemented");
	}
	@Test
	public void resetTest() throws ADTExceptions {
		Ordered_List o = new Ordered_List();
		o.add(5);
		o.add(4);
		o.add(6);
		o.add((12));
		o.add(36);
		o.reset();  //if reset works, the next item is the second element in list.
		assertEquals(5,	o.getNext());
		
		//fail("Not yet implemented");
	}
	@Test
	public void findTest() throws ADTExceptions {
		Ordered_List o = new Ordered_List();
		o.add(5);
		o.add(4);
		o.add(6);
		o.add((12));
		o.add(36);
		o.find(6);  //if find works, the next item is the next highest item in the list.
		assertEquals(12,o.getNext());
	}
	
	
}
