package serp.bucket;

import java.util.ArrayList;

import serp.WebPage;

/**
 * Implements double linked list data structure for using in Bucket Sort
 * 
 * @author msurmenok
 *
 */
public class LinkedList
{
	Node head;
	Node tail;


	public LinkedList()
	{
		this.head = null;
		this.tail = null;
	}


	/**
	 * Insert a node into the beginning of linked list
	 * 
	 * @param L
	 *            linked list where we insert our node
	 * @param x
	 *            node to be inserted
	 */
	public static void listInsert(LinkedList L, Node x)
	{
		x.next = L.head;
		if (L.head != null)
		{
			L.head.prev = x;
		}
		L.head = x;
		x.prev = null;
	}


	/**
	 * Convert LinkedList into array of Nodes
	 * 
	 * @param L
	 *            linked list
	 * @return list of nodes
	 */
	public static Node[] getData(LinkedList L)
	{
		ArrayList<Node> pages = new ArrayList<>();
		Node current = L.head;
		while (current != null)
		{
			Node nextNode = current.next;
			current.prev = null;
			current.next = null;
			pages.add(current);
			current = nextNode;
		}
		Node[] nodes = new Node[pages.size()];
		return pages.toArray(nodes);
	}


	/**
	 * Sort double linked list with insertion sort
	 * 
	 * @param L
	 *            list to be sorted
	 */
	public static void listSort(LinkedList L)
	{
		// Convert linked list into array of nodes
		Node[] data = LinkedList.getData(L);
		L.head = null;
		L.tail = null;

		InsertionSort.insertionSort(data);
		for (int i = data.length - 1; i >= 0; i--)
		{
			LinkedList.listInsert(L, data[i]);
		}
	}
}
