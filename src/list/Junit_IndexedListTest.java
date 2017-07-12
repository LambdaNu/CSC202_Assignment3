package list;

import static org.junit.Assert.*;

import org.junit.Test;

public class Junit_IndexedListTest {

	@Test
	public void Addtest() throws ADTExceptions {
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		//fail("Not yet implemented");
		String mystring = iL.toString();
		assertEquals("[Jordan][Cameron]", mystring);
	}
	
	@Test
	public void AddtestIndexAdd() throws ADTExceptions {
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		//fail("Not yet implemented");
		String mystring = iL.toString();
		assertEquals("[Jordan][Jameson][Cameron]", mystring);
	}
	@Test(expected=ADTExceptions.class)
	public void AddtestFailure() throws ADTExceptions {
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		iL.Add("Cameron");  //violates uniqueness.
		//fail("Not yet implemented");
		String mystring = iL.toString();
		//assertEquals("[Jordan][Jameson][Cameron]", mystring);
	}
	
	@Test
	public void cointainsTest() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);

		boolean b = iL.contains("Jordan");
		
		assertEquals(true, b);
	}
	
	@Test
	public void cointainsTestF() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);

		boolean b = iL.contains("Jorgen");
		
		assertEquals(false, b);
	}
	
	@Test
	public void isEmptyTest() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		boolean b = iL.isEmpty();
		
		assertEquals(true, b);
	}
	
	@Test
	public void isEmptyTestF() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);

		boolean b = iL.isEmpty();
		
		assertEquals(false, b);
	}
	
	@Test
	public void sizeTest0(){
		IndexedList iL = new IndexedList(); 
		int s = iL.size();
		assertEquals(0,s);
	}
	
	@Test
	public void sizeTest() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		int s = iL.size();
		assertEquals(3,s);
	}
	
	@Test
	public void indexOf() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		int s = iL.indexOf("Jameson");
		assertEquals(1, s);
	}
	@Test
	public void indexOfNegative() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		int s = iL.indexOf("James");
		assertEquals(-1, s);
	}
	
	@Test (expected=ADTExceptions.class)
	public void setTestF() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		
		iL.set("BLORGAH", 5); //because it's beyond where the set is, and would create gaps!
		//assertEquals(-1, s);
	}
	@Test
	public void setTest() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		iL.set("BLORGAH", 2);
		assertEquals("BLORGAH", iL.get(2));  //also secretly a test of get.
	}
	
	public void getNextTest() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Monsters United");
		//iL.getNext();
		assertEquals("Cameron",	iL.getNext());
	}
	
	@Test
	public void getTest() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		assertEquals("Cameron", iL.get(2));  
	}
	
	
	@Test
	public void removeTest() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		iL.Add("Tamara");
		boolean b = iL.remove("Tamara");
		
		assertEquals(true, b);  
	}
	
	@Test
	public void removeTestF() throws ADTExceptions{
		IndexedList iL = new IndexedList(); 
		iL.Add("Jordan");
		iL.Add("Cameron");
		iL.Add("Jameson",1);
		iL.Add("Tamara");
		boolean b = iL.remove("Tia");
		
		assertEquals(false, b);  
	}

}
