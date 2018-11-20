package serp;

import java.util.ArrayList;

import serp.heap.PriorityQueue;

/**
 * Class Keyword represents popular web search request.
 * 
 * @author msurmenok
 *
 */
public class Keyword implements RankedElement
{
	private String keyword;
	private int score;
	private int index;

	public static final Keyword[] KEYWORDS = { new Keyword("aol", 10840000), new Keyword("youtube", 191400000),
			new Keyword("nfl", 7640000), new Keyword("weather", 50970000), new Keyword("netflix", 38350000),
			new Keyword("google", 83260000), new Keyword("google translate", 35710000), new Keyword("cnn", 24160000),
			new Keyword("news", 241050000), new Keyword("hotmail", 23620000), new Keyword("calculator", 20870000),
			new Keyword("finance", 9020000), new Keyword("amazon", 99050000), new Keyword("translate", 24820000),
			new Keyword("gmail", 89360000), new Keyword("sports", 12450000), new Keyword("facebook", 236100000),
			new Keyword("company", 236009000) };


	/**
	 * Finds top 10 popular keywords using PriorityQueue
	 * 
	 * @return list of the top 10 popular keywords
	 */
	public static ArrayList<Keyword> getTop10Keywords()
	{
		ArrayList<Keyword> keywords = new ArrayList<>();
		for (int i = 0; i < Keyword.KEYWORDS.length; i++)
		{
			Keyword.KEYWORDS[i].setIndex(i);
			keywords.add(Keyword.KEYWORDS[i]);
		}
		PriorityQueue<Keyword> queue = new PriorityQueue<>(keywords);
		// getTopElements
		ArrayList<Keyword> top10Keywords = queue.getTopElements(10);
		return top10Keywords;
	}


	/**
	 * Initialize keyword object.
	 * 
	 * @param keyword
	 *            is the name of popular search request.
	 * @param score
	 *            number of requests.
	 */
	public Keyword(String keyword, int score)
	{
		this.keyword = keyword;
		this.score = score;
	}


	/**
	 * Accessor to field keyword
	 * 
	 * @return the popular search word.
	 */
	public String getKeyword()
	{
		return this.keyword;
	}


	/**
	 * Assigns new score if it is greater than previous one.
	 * 
	 * @param score
	 *            number of searches for this keyword.
	 */
	public void setScore(int score)
	{
		if (score > this.score)
		{
			this.score = score;
		}
	}


	/**
	 * Accessor to field score.
	 * 
	 * @return number of searches for this keyword.
	 */
	@Override
	public int getScore()
	{
		return this.score;
	}


	/**
	 * Assigns new index to keyword when it changes it's position in priority queue.
	 */
	@Override
	public void setIndex(int index)
	{
		this.index = index;
	}


	/**
	 * Accessor to index field.
	 * 
	 * @return current position of object in priority queue.
	 */
	@Override
	public int getIndex()
	{
		return this.index;
	}


	@Override
	public String toString()
	{
		return this.getKeyword();
	}

}
