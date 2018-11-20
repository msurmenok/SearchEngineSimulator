package serp.bucket;

import java.util.ArrayList;

import serp.WebPage;

/**
 * Implementation of bucket sort algorithm
 * 
 * @author msurmenok
 *
 */
public class BucketSort
{
	public LinkedList[] B;


	/**
	 * Sort Web Pages by their domain names using buckets
	 * 
	 * @param A
	 *            list of web pages
	 * @return list of alphabetically sorted web pages
	 */
	public WebPage[] bucketSort(WebPage[] A)
	{
		// We encode each letter
		// a = 0, b = 1, c = 2, ... z = 25
		B = new LinkedList[26];

		// Create a bucket for each letter
		for (int i = 0; i < 26; i++)
		{
			B[i] = new LinkedList();
		}

		// Put each element of the A according to its first letter
		// to a specific bucket.
		// For instance:
		// ---------- amazon.com will go to the first bucket
		// ---------- zillow.com will go to the 25th bucket
		for (int i = 0; i < A.length; i++)
		{
			// remove http, https, and www. using regex
			String domain = A[i].getLink();
			String pattern = "((http://)|(https://))*(www.)*";
			domain = domain.replaceAll(pattern, "");

			// get the first char and convert it to number
			int bucketNumber = domain.charAt(0) - 'a';

			// Add url to a bucket
			LinkedList.listInsert(B[bucketNumber], new Node(A[i]));
		}

		// Sort each linked list with insertion sort
		for (int i = 0; i < B.length; i++)
		{
			LinkedList.listSort(B[i]);
		}
		// Concatenate linked lists
		return concatenate(B);
	}


	/**
	 * Put all linked lists into one list of web pages
	 * 
	 * @param B
	 *            array containing sorted linked lists
	 * @return web pages sorted by their domains
	 */
	public static WebPage[] concatenate(LinkedList[] B)
	{
		ArrayList<WebPage> sortedUrls = new ArrayList<>();
		for (int i = 0; i < B.length; i++)
		{
			// Traverse through linked list
			Node y = B[i].head;
			while (y != null)
			{
				sortedUrls.add(y.data);
				y = y.next;
			}
		}
		WebPage[] urls = new WebPage[sortedUrls.size()];
		return sortedUrls.toArray(urls);
	}
}
