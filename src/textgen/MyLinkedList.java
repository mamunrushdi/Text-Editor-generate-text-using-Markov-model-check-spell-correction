package textgen;

import java.util.AbstractList;
  
/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
 		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		size = 0;
		
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null) throw new NullPointerException();
		//create new node with E
		LLNode<E> nNode = new LLNode<>(element);

		//connect newNode with the tail and previous node
		addNode(nNode, tail);

		if(tail.prev.data.equals(element))
			{
				size++;
				return true;
			}
		
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index < 0 || size == 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		int nIndex = 0;
		
		LLNode<E> tempNode = head.next;
		
		while(tempNode.data != null)
		{		
			if(nIndex == index) return tempNode.data;
			nIndex++;
			tempNode = tempNode.next;
		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{ 
		if(element == null) throw new NullPointerException();

		if(size > 0)
			if((index < 0 || index >= size)) throw new IndexOutOfBoundsException();
		
		if(size == 0 && index < 0)	throw new IndexOutOfBoundsException();

		LLNode<E> nNode = new LLNode<>(element);
				
		//if list is empty
		if(head.next == tail)
		{ 
			addNode(nNode, tail);				

		}
		else
		{
			LLNode<E> tempNode = head.next;
			int nIndex = 0;
	 		while(tempNode.data != null)
			{		
				if(nIndex == index)
				{
					addNode(nNode, tempNode);				
				}
				nIndex++;
				tempNode = tempNode.next;
			}
		}
		size++;
	}
	
	//helper method addNode
	private void addNode(LLNode<E> nNode, LLNode<E> tempNode)
	{
		//connect the new node to the list
		nNode.next = tempNode;
		nNode.prev = tempNode.prev;
		
		//update the list
		tempNode.prev.next = nNode;
		tempNode.prev = nNode;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index < 0 ||  index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		E element = null;
		
		LLNode<E> tempNode = head.next;
		
		int nIndex = 0;
		
		while(tempNode.data != null)
		{		
			if(nIndex == index)
				{
					element = tempNode.data;
					removeElement(index, tempNode);
				}
			nIndex++;
			tempNode = tempNode.next;
		}	
		 size--;
		 return element;	
	}
	
	//helper method remove an element
	private void removeElement(int index, LLNode<E> nodeToRemove)
	{
		//connect previous node to next node of nodeToRemove
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element == null) throw new NullPointerException();

		if(index < 0 ||  index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		E replaceElement = null;
		LLNode<E> tempNode = head.next;
		int nIndex = 0;
		
		while(tempNode.data != null)
		{		
			if(nIndex == index)
				{
					replaceElement = tempNode.data;	
					tempNode.data = element;
				}
			nIndex++;
			tempNode = tempNode.next;
		}
		size++;
		return replaceElement;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

 	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		
		this.prev = null;
		this.next = null;
	}
 
	//override the toString method
	@Override
	public String toString()
	{
		return "data is :" + data;
	}
}
