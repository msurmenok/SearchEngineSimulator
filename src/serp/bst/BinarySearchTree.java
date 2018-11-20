package serp.bst;

import java.util.ArrayList;

import serp.WebPage;

/**
 * Implementation of Binary Search Tree data structure
 * 
 * @author msurmenok
 *
 */
public class BinarySearchTree
{
	public TreeNode root;
	private int size;


	public BinarySearchTree()
	{
		this.root = null;
		this.size = 0;
	}


	/**
	 * Remove all elements from BST
	 */
	public void clear()
	{
		this.root = null;
		this.size = 0;
	}


	/**
	 * Find web page with specific rank. Where rank = 1 has the biggest score
	 * 
	 * @param rank
	 *            page rank to find
	 * @return WebPage with specific rank
	 */
	public WebPage getDataByRank(int rank)
	{
		WebPage[] orderedPages = this.inOrderTreeWalk();
		int index = this.size - rank;

		if (index > 0 && rank > 0)
		{
			return orderedPages[this.size - rank];
		}
		return orderedPages[0];
	}


	/**
	 * Insert new node into tree T
	 * 
	 * @param T
	 *            reference to BST
	 * @param z
	 *            data to be inserted
	 */
	public static void treeInsert(BinarySearchTree T, WebPage page)
	{
		TreeNode z = new TreeNode(page); // Create a node for our webpage
		TreeNode y = null;
		TreeNode x = T.root;
		while (x != null)
		{
			y = x;
			if (z.key < x.key)
			{
				x = x.left;
			}
			else
			{
				x = x.right;
			}
		}
		z.p = y;
		if (y == null)
		{
			T.root = z; // tree T was empty
		}
		else if (z.key < y.key)
		{
			y.left = z;
		}
		else
		{
			y.right = z;
		}
		T.size++;
	}


	/**
	 * Walk to the left-child, root node, right child such that it finds elements of
	 * BST in ascending order.
	 * 
	 * @return elements of BST in ascending order
	 */
	public WebPage[] inOrderTreeWalk()
	{
		ArrayList<WebPage> pages = new ArrayList<>();
		WebPage[] sortedPages = new WebPage[this.size];
		inOrderTreeWalk(this.root, pages);
		for (int i = 0; i < pages.size(); i++)
		{
			sortedPages[i] = pages.get(i);
		}
		return sortedPages;
	}


	private void inOrderTreeWalk(TreeNode x, ArrayList<WebPage> pages)
	{
		if (x != null)
		{
			inOrderTreeWalk(x.left, pages);
			pages.add(x.data);
			inOrderTreeWalk(x.right, pages);
		}
	}


	/**
	 * Find a successor, node with minimum value that is greater than key of x node
	 * 
	 * @param x
	 *            node for which we need to find a successor
	 * @return successor node
	 */
	private TreeNode treeSuccessor(TreeNode x)
	{
		if (x.right != null)
		{
			return treeMinumum(x);
		}
		TreeNode y = x.p;
		while (y != null && x == y.right)
		{
			x = y;
			y = y.p;
		}
		return y;
	}


	/**
	 * Finds an element in a binary search tree whose key is a minimum by in the
	 * subtree rooted at a node x. We assume that x != null
	 * 
	 * @param x
	 *            root of the subtree
	 * @return the node with minimum key
	 */
	public TreeNode treeMinumum(TreeNode x)
	{
		while (x.left != null)
		{
			x = x.left;
		}
		return x;
	}


	/**
	 * Replaces one subtree as a child of its parent with another subtree
	 * 
	 * @param T
	 *            binary search tree that will be mutated
	 * @param u
	 *            the root of the subtree that will be replaced
	 * @param v
	 *            the root of the subtree that will replace u
	 */
	public static void transplant(BinarySearchTree T, TreeNode u, TreeNode v)
	{
		if (u.p == null)
		{
			T.root = v;
		}
		else if (u == u.p.left)
		{
			u.p.left = v;
		}
		else
		{
			u.p.right = v;
		}
		if (v != null)
		{
			v.p = u.p;
		}
	}


	/**
	 * Delete node from Binary Search Tree
	 * 
	 * @param T
	 *            bst from which node will be deleted
	 * @param z
	 *            node to delete
	 */
	public static void treeDelete(BinarySearchTree T, TreeNode z)
	{
		T.size--;
		if (z.left == null)
		{
			transplant(T, z, z.right);
		}
		else if (z.right == null)
		{
			transplant(T, z, z.left);
		}
		else
		{
			TreeNode y = T.treeMinumum(z.right);
			if (y.p != z)
			{
				transplant(T, y, y.right);
				y.right = z.right;
				y.right.p = y;
			}
			transplant(T, z, y);
			y.left = z.left;
			y.left.p = y;
		}
	}


	/**
	 * Finds a node with specific webpage score
	 * 
	 * @param k
	 *            score of the web page
	 * @return the first node with specified score, null if there is no such element
	 */
	public TreeNode treeSearch(int k)
	{
		return this.treeSearch(this.root, k);
	}


	/**
	 * Subroutine to find a node with specific webpage score
	 * 
	 * @param x
	 *            the root of the subtree where we are looking for a node
	 * @param k
	 *            score of the web page
	 * @return the first node with specified score, null if there is no such element
	 */
	private TreeNode treeSearch(TreeNode x, int k)
	{
		if (x == null || k == x.key)
		{
			return x;
		}
		if (k < x.key)
		{
			return treeSearch(x.left, k);
		}
		else
		{
			return treeSearch(x.right, k);
		}
	}
}
