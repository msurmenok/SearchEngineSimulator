package serp.bucket;

/**
 * Class to implement standard insertion sort over list of Nodes
 * 
 * @author msurmenok
 *
 */
public class InsertionSort
{
	/**
	 * Sorts Nodes by their key value, which is the domain of Web Page
	 * 
	 * @param A list of Nodes
	 */
	public static void insertionSort(Node[] A)
	{
		for (int j = 1; j < A.length; j++)
		{
			Node node = A[j];
			String key = node.key;
			int i = j - 1;
			while (i >= 0 && A[i].key.compareTo(key) > 0)
			{
				A[i + 1] = A[i];
				i = i - 1;
			}
			A[i + 1] = node;
		}
	}
}
