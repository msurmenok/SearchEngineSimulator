package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import serp.WebPage;
import serp.bucket.BucketSort;
import serp.bucket.LinkedList;
import serp.Keyword;
import serp.PageRanker;

/**
 * GUI representation to the third part of Programming Assignment. Use Bucket
 * Sort to sort and handle list of Web Pages
 * 
 * @author msurmenok
 *
 */
public class KeywordSearchView
{
	JTable topLinksTable;
	String[] columnNames = { "Order", "Original Index", "Score", "Link" };
	JComboBox keywordList;
	Map<String, BucketSort> hashedWebPages;


	public KeywordSearchView()
	{
		hashedWebPages = new HashMap<>();

		ArrayList<Keyword> keywords = Keyword.getTop10Keywords();
		JLabel information = new JLabel(
				"Run search to populate the table. First time search will take approximately 40 seconds");

		JPanel searchPanel = new JPanel();
		keywordList = new JComboBox(keywords.toArray());
		JButton searchButton = new JButton("Search");

		// LISTENER TO SEARCH LINK FOR SPECIFIED KEYWORD
		searchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String searchWord = keywordList.getSelectedItem().toString();
				WebPage[] urls;
				// If we already hashed web pages for this keyword, retrive them from HashMap
				// Otherwise get web pages from crawler and put into HashMap for future use
				if (hashedWebPages.containsKey(searchWord))
				{
					LinkedList[] list = hashedWebPages.get(searchWord).B;
					urls = BucketSort.concatenate(list);
				}
				else
				{
					WebPage[] pages = PageRanker.getLinks(searchWord);
					BucketSort bs = new BucketSort();
					urls = bs.bucketSort(pages);
					hashedWebPages.put(searchWord, bs);
				}

				String[][] urlData = WebPage.prepareTableValues(urls);
				DefaultTableModel model = new DefaultTableModel(urlData, columnNames);
				topLinksTable.setModel(model);
				topLinksTable.getColumnModel().getColumn(3).setMinWidth(300);

			}
		});

		searchPanel.add(keywordList);
		searchPanel.add(searchButton);

		String[][] initial = { { "", "", "", "", "" } };
		this.topLinksTable = new JTable(initial, columnNames);
		JScrollPane tableScroller = new JScrollPane(this.topLinksTable);
		tableScroller.setPreferredSize(new Dimension(600, 600));

		JFrame frame = new JFrame("Search for top 10 popular keywords");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(information, BorderLayout.NORTH);
		frame.add(searchPanel, BorderLayout.CENTER);
		frame.add(tableScroller, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}


	public void run()
	{

	}

}
