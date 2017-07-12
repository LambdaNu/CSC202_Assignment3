package restaurantLookup;

import static org.junit.Assert.*;

import org.junit.Test;

public class BSTTest {
	BST<Integer> bst = new BST<Integer>(); 
	@Test
	public void addTest() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		
		assertEquals("[1, 13, 20, 27, 30]", bst.toString(0));
		//fail("Not yet implemented");
	}
	@Test
	public void containsTestTrue() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		
		assertEquals(true, bst.contains(20));
		//fail("Not yet implemented");
	}
	@Test
	public void containsTestFalse() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		
		assertEquals(false, bst.contains(15));
		//fail("Not yet implemented");
	}
	@Test
	public void removeTestTrue() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		bst.remove(20);
		//using contains to test the removal and then ensuring it's gone and that the methods are on the same page.
		assertEquals(false, bst.contains(20));
		assertEquals(false, bst.remove(20));

	}
	@Test
	public void removeTestTrue_remove() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		assertEquals(true, bst.remove(20));

	}
	@Test
	public void removeTestFalse() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		assertEquals(false, bst.remove(15));
	}
	@Test
	public void isEmptyTestTrue() {
		assertEquals(true, bst.isEmpty());
	}
	@Test
	public void isEmptyTestFalse() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		assertEquals(false, bst.isEmpty());
	}
	@Test
	public void SizeTest_Empty() {
		
		assertEquals(0, bst.size());
	}
	@Test
	public void SizeTest() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.remove(13);
		bst.remove(14);
		bst.add(20);
		bst.add(30);
		assertEquals(4, bst.size());
	}
	@Test
	public void GetTestNull() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.remove(13);
		bst.remove(14);
		bst.add(20);
		bst.add(30);
		assertEquals(null, bst.get(47));
	}
	@Test
	public void GetTestValue() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.remove(13);
		bst.remove(14);
		bst.add(20);
		bst.add(30);
		Integer a = 27;
		assertEquals(a, bst.get(27));
	}
	@Test
	public void toStringTestPreorder() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		assertEquals("[27, 13, 1, 20, 30]", bst.toString(-1));
	}
	@Test
	public void toStringTestPostorder() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		assertEquals("[1, 20, 13, 30, 27]", bst.toString(1));
	}
	@Test
	public void getNextResetTest() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		bst.reset(0);
		bst.getNext(0);
		Integer a = 13;
		assertEquals(a, bst.getNext(0));
	}
	@Test
	public void getNextResetTestpreorder() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		bst.reset(-1);
		bst.getNext(-1);
		Integer a = 13;
		assertEquals(a, bst.getNext(-1));
	}
	@Test
	public void getNextResetTestpostorder() {
		bst.add(27);
		bst.add(13);
		bst.add(1);
		bst.add(20);
		bst.add(30);
		bst.reset(1);
		bst.getNext(1);
		Integer a = 20;
		assertEquals(a, bst.getNext(1));
	}
	
	
}
