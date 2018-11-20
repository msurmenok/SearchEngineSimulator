package serp;

import serp.bst.BinarySearchTree;
import view.DynamicPageRankerView;

/**
 * Class for testing BST dynamic data structure that handles WebPage list.
 * 
 * @author msurmenok
 *
 */
public class DynamicPageRanker
{

	public static void main(String[] args)
	{
		System.out.println("STARTED");
		// Get links from web crawler for specified url and keyword.
		String searchWord = "business";
		WebPage[] webPages = PageRanker.getLinks(searchWord);

		// Initial input
		System.out.println("INITIAL INPUT");
		for (int i = 0; i < webPages.length; i++)
		{
			System.out.format("%-5d %-5d %-80s %5d\n", i, webPages[i].getIndex(), webPages[i].getLink(),
					webPages[i].getScore());
		}

		// Create BST
		BinarySearchTree bst = new BinarySearchTree();
		for (WebPage page : webPages)
		{
			BinarySearchTree.treeInsert(bst, page);
		}

		// Sort using in-order traverse
		WebPage[] sortedPages = bst.inOrderTreeWalk();
		sortedPages = PageRanker.reverse(sortedPages);

		// Print sorted elements
		System.out.println("\nSored using BST");
		for (int i = 0; i < sortedPages.length; i++)
		{
			System.out.format("%-5d %-5d %-80s %5d\n", i, sortedPages[i].getIndex(), sortedPages[i].getLink(),
					sortedPages[i].getScore());
		}

		// Call GUI
		DynamicPageRankerView view = new DynamicPageRankerView(sortedPages, searchWord, bst);
	}

}
