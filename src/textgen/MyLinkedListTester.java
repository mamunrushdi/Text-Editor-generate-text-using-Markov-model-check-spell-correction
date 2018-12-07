/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds0");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds-1");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds2");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds-1-1");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds lls");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		try {
			longerList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.remove(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
 	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		String s = "hi";
		
		boolean result = true;
		assertTrue("check the add method,", result == shortList.add(s));
		
		shortList.add(s);
		assertTrue("Check the add method, ", s.equals(shortList.tail.prev.data));	
		
		try
		{
			shortList.add(null);
			fail("check the add end method");
		}
		catch(NullPointerException e)
		{
			
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		int oldSize = shortList.size();
		
		shortList.add("hello");
		
		int newSize = shortList.size();
		
		assertTrue("check the size method", oldSize + 1 == newSize);
		
		assertTrue("check the size method in case of emptry list", emptyList.size() == 0);
		
		oldSize = shortList.size();
		shortList.remove(0);
		newSize = shortList.size();
		assertTrue("check the size method", oldSize == newSize + 1);
		
		shortList.add("hi");
		
		oldSize = shortList.size;
		shortList.remove(0);
		newSize = shortList.size;
		assertTrue("check the size method", oldSize == newSize + 1);
		

	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try
		{
			shortList.add(null);
			fail("check the add end method");
		}
		catch(NullPointerException e)
		{
			
		}
		try {
			longerList.add(-1, 3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.add(longerList.size +1 , 4);
			fail("Check out of bounds of the list.");
		}
		catch (IndexOutOfBoundsException e) {
		}
//		
		int oldSize = longerList.size();
		int index = 0;
		int element = 5;
		
		longerList.add(0, element);
		
		int nSize = longerList.size();

		assertTrue("check add with position method", oldSize + 1 == nSize);
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			longerList.set(-1, 3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.set(LONG_LIST_LENGTH, 4);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		int index = 0;
		int element = longerList.get(index);
		
		assertTrue("check add with position method", element == longerList.set(index, 5));
		
		try
		{
			shortList.set(index, null);
			fail("check the set method");
		}
		catch(NullPointerException e)
		{
			
		}
	}
}
