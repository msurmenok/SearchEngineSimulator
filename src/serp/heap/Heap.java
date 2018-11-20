package serp.heap;

import java.util.ArrayList;

import serp.RankedElement;

/**
 * Represents a collection of static methods for Heap Sort algorithm.
 * 
 * @author msurmenok
 *
 */
public class Heap
{
    /**
     * Maintains the max-heap property.
     * 
     * @param elements
     *            array that represents heap.
     * @param i
     *            index of root of the subtree.
     * @param heapSize
     *            current size of heap.
     */
    public static <T extends RankedElement> void maxHeapify(ArrayList<T> elements, int i, int heapSize)
    {
	int left = 2 * i + 1;
	int right = 2 * i + 2;
	int largest;

	if (left < heapSize && elements.get(left).getScore() > elements.get(i).getScore())
	{
	    largest = left;
	} else
	{
	    largest = i;
	}

	if (right < heapSize && elements.get(right).getScore() > elements.get(largest).getScore())
	{
	    largest = right;
	}

	if (largest != i)
	{
	    // Swap A[i] with A[largest]
	    swapElements(elements, i, largest);
	    maxHeapify(elements, largest, heapSize);
	}
    }


    /**
     * Helper function to swap elements in heap and to reassign internal index
     * value.
     * 
     * @param elements
     *            array that represents heap.
     * @param index1
     *            index of the first element to be swapped.
     * @param index2
     *            index of the second element to be swapped.
     */
    public static <T extends RankedElement> void swapElements(ArrayList<T> elements, int index1, int index2)
    {
	// swap indices inside objects
	elements.get(index1).setIndex(index2);
	elements.get(index2).setIndex(index1);
	// swap objects in array
	T temp = elements.get(index1);
	elements.set(index1, elements.get(index2));
	elements.set(index2, temp);
    }


    /**
     * Build Max-Heap from an ArrayList
     * 
     * @param elements
     *            ArrayList to be rearranged.
     */
    public static <T extends RankedElement> void buildMaxHeap(ArrayList<T> elements)
    {
	int heapSize = elements.size();
	for (int i = (int) heapSize / 2; i >= 0; i--)
	{
	    maxHeapify(elements, i, heapSize);
	}
    }


    /**
     * Implementation of Heap Sorting algorithm.
     * 
     * @param elements
     *            ArrayList to be sorted.
     */
    public static <T extends RankedElement> void heapSort(ArrayList<T> elements)
    {
	buildMaxHeap(elements);
	int heapSize = elements.size();
	for (int i = elements.size() - 1; i > 0; i--)
	{
	    // exchange A[0] with A[i]
	    swapElements(elements, 0, i);
	    heapSize -= 1;
	    maxHeapify(elements, 0, heapSize);
	}
    }
}
