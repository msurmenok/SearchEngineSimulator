package serp;

import java.util.ArrayList;

import crawler.Spider;
import serp.heap.HeapException;
import view.PageRankerView;

/**
 * Class for testing Quick sort algorithms with WebPage list. Also has helper
 * function to retrieve urls from web crawler.
 * 
 * @author msurmenok
 *
 */
public class PageRanker
{
	/**
	 * 
	 * @param args
	 *            - not used.
	 * @throws HeapException
	 */
	public static void main(String[] args)
	{
		System.out.println("STARTED");

		// Get links from web crawler for specified url and keyword.
		String searchWord = "business";
		WebPage[] webPages = getLinks(searchWord);

		// Initial input
		System.out.println("INITIAL INPUT");
		for (int i = 0; i < webPages.length; i++)
		{
			System.out.format("%-5d %-5d %-80s %5d\n", i, webPages[i].getIndex(), webPages[i].getLink(),
					webPages[i].getScore());
		}

		// After quick sort

		System.out.println("After Quick Sort");
		QuickSort.quickSort(webPages, 0, webPages.length - 1);
		WebPage[] rankedPages = reverse(webPages);

		for (int i = 0; i < rankedPages.length; i++)
		{
			System.out.format("%-5d %-5d %-80s %5d\n", i, rankedPages[i].getIndex(), rankedPages[i].getLink(),
					rankedPages[i].getScore());
		}

		// Call GUI
		PageRankerView view = new PageRankerView(rankedPages, searchWord);
		// view.run();
	}


	/**
	 * Call web crawler and generate 30 links for a particular keyword
	 * 
	 * @param searchWord
	 *            keyword to search in web pages
	 * @return list of 30 links with generated scores
	 */
	public static WebPage[] getLinks(String searchWord)
	{
		// Call crawler
		Spider spider = new Spider();
		String url = "https://www.zyxware.com/articles/4344/list-of-fortune-500-companies-and-their-websites";

		ArrayList<String> links = new ArrayList<>(spider.search(url, searchWord));

		// Create list of web pages
		WebPage[] webPages = new WebPage[links.size()];
		for (int i = 0; i < links.size(); i++)
		{
			int wordFrequency = generateRandom(100);
			int daysExisted = generateRandom(100);
			int numberOfLinks = generateRandom(100);
			int moneyPayed = generateRandom(100);
			String link = links.get(i);
			if (link.length() > 40)
			{
				link = link.substring(0, 40);
			}
			WebPage webPage = new WebPage(link, wordFrequency, daysExisted, numberOfLinks, moneyPayed, i);
			webPages[i] = webPage;

		}
		return webPages;
	}


	/**
	 * Reverse indices in a list of webpages
	 * 
	 * @param webPages
	 *            list to be reversed
	 * @return reversed list of web pages
	 */
	public static WebPage[] reverse(WebPage[] webPages)
	{
		WebPage[] reversedPages = new WebPage[webPages.length];
		int j = webPages.length - 1;
		for (int i = 0; i < reversedPages.length; i++)
		{
			reversedPages[i] = webPages[j];
			j--;
		}
		return reversedPages;
	}


	/**
	 * Generate random number.
	 * 
	 * @param maxValue
	 *            the maximum random number.
	 * @return random number from 0 to max value
	 */
	private static int generateRandom(int maxValue)
	{
		return 1 + (int) (Math.random() * (maxValue + 1));
	}
}
