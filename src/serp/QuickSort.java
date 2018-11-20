package serp;

/**
 * Class that implements QuickSort algorithm
 * 
 * @author msurmenok
 *
 */
public class QuickSort
{
	/**
	 * Sort WebPage elements using by partitioning
	 * 
	 * @param A
	 * @param p
	 * @param r
	 */
	public static void quickSort(WebPage[] A, int p, int r)
	{
		// p - beginning
		// r - end
		// q - the index of previous pivot
		if (p < r)
		{
			int q = partition(A, p, r);
			quickSort(A, p, q - 1);
			quickSort(A, q + 1, r);

		}
	}


	/**
	 * Sort subarrays rotating elements according to their relationships with pivot
	 * 
	 * @param A
	 * @param p
	 * @param r
	 * @return
	 */
	static int partition(WebPage[] A, int p, int r)
	{
		int x = A[r].getScore();
		int i = p - 1;
		for (int j = p; j < r; j++)
		{
			if (A[j].getScore() <= x)
			{
				i += 1;
				WebPage temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		WebPage temp = A[i + 1];
		A[i + 1] = A[r];
		A[r] = temp;
		return i + 1;
	}
}
