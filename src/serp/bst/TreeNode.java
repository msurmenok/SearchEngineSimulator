package serp.bst;

import serp.WebPage;

/**
 * Simple element of BST. Instance variable key is web page score.
 * 
 * @author msurmenok
 *
 */
public class TreeNode
{
	public int key;
	public WebPage data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode p;


	public TreeNode(WebPage data)
	{
		this.key = data.getScore();
		this.data = data;
	}
}
