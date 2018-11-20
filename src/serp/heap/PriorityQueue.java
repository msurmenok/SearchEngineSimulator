package serp.heap;

import java.util.ArrayList;

import serp.RankedElement;

/**
 * Represents priority queue data structure.
 * 
 * @author msurmenok
 *
 */
public class PriorityQueue<T extends RankedElement>
{
    ArrayList<T> elements = new ArrayList<>();
    int heapSize;


    /**
     * Initialize new Priority queue with a copy of ArrayList.
     * 
     * @param elements
     *            ArrayList without specific order.
     */
    public PriorityQueue(ArrayList<T> elements)
    {
	this.elements = (ArrayList<T>) elements.clone();
	Heap.buildMaxHeap(this.elements);
	this.heapSize = elements.size();
    }


    /**
     * Accessor to heap. 
     * 
     * @return a copy of field elements.
     */
    public ArrayList<T> getElements()
    {
	return (ArrayList<T>) this.elements.clone();
    }


    /**
     * Peek the max value.
     * 
     * @return the root of the heap.
     */
    public T heapMaximum()
    {
	return elements.get(0);
    }


    /**
     * Pop the maximum value and rebuild max-heap.
     * 
     * @return the root of the heap.
     */
    public T heapExtractMax()
    {
	T max = elements.get(0);
	elements.set(0, elements.get(this.heapSize - 1));
	this.heapSize -= 1;
	Heap.maxHeapify(this.elements, 0, this.heapSize);
	return max;
    }


    /**
     * Insert new element to the heap and rebuild Max-Heap.
     * 
     * @param element
     *            to be inserted.
     */
    public void maxHeapInsert(T element)
    {
	// Add new element to the end of heap
	this.heapSize += 1;
	element.setIndex(heapSize - 1);
	if (this.heapSize <= this.elements.size())
	{
	    this.elements.set(heapSize - 1, element);
	} else
	{
	    this.elements.add(element);
	}
	this.heapIncreaseKey(heapSize - 1);
    }


    /**
     * Rebuild Max-Heap when the score of specified element is increased.
     * 
     * @param i
     *            the index of element whose score has been increased.
     */
    public void heapIncreaseKey(int i)
    {
	int parentIndex = getParentIndex(i);

	while (i > 0 && this.elements.get(parentIndex).getScore() < this.elements.get(i).getScore())
	{
	    Heap.swapElements(this.elements, i, parentIndex);
	    i = parentIndex;
	    parentIndex = getParentIndex(i);
	}
    }


    /**
     * Return specified number of maximum elements and then rebuild the Heap, so no
     * data is lost.
     * 
     * @param numberOfElements
     *            number of elements to be popped from Heap.
     * @return ArrayList with greater elements of the heap in decreasing order.
     */
    public ArrayList<T> getTopElements(int numberOfElements)
    {
	ArrayList<T> topElements = new ArrayList<>();

	for (int i = 0; i < numberOfElements; i++)
	{
	    T maxElement = this.heapExtractMax();
	    topElements.add(maxElement);
	}
	// Append elements back to the heap.
	for (T element : topElements)
	{
	    this.maxHeapInsert(element);
	}
	return (ArrayList<T>)topElements.clone();
    }


    /**
     * Check that ArrayList maintains Max-Heap structure.
     * 
     * @throws HeapException
     */
    public void checkHeap() throws HeapException
    {
	for (int i = this.elements.size() - 1; i > 0; i--)
	{
	    int parentIndex = this.getParentIndex(i);
	    if (this.elements.get(i).getScore() > this.elements.get(parentIndex).getScore())
	    {
		throw new HeapException("Parent score is less than childs score");
	    }
	}
    }


    /**
     * Check that the field index is equal to the position of element in the
     * ArrayList.
     * 
     * @throws HeapException
     */
    public void checkIndexes() throws HeapException
    {
	for (int i = 0; i < this.elements.size(); i++)
	{
	    if (i != this.elements.get(i).getIndex())
	    {
		throw new HeapException("Real index of element is not equal the field index.");
	    }
	}
    }


    /**
     * Calculate index of parent node.
     * 
     * @param childIndex
     *            index of child node.
     * @return index of parent node.
     */
    private static int getParentIndex(int childIndex)
    {
	return (int) ((childIndex - 1) / 2);
    }
}
