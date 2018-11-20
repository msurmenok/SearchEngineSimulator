package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import serp.PageRanker;
import serp.QuickSort;
import serp.WebPage;
import serp.QuickSort;

/**
 * GUI representation for the first part of the Programming Assignment.
 * @author msurmenok
 *
 */
public class PageRankerView
{
	JTable topThirtyTable;
	JPanel topThirtyPanel;
	JLabel title;
	WebPage[] urls;


	/**
	 * Create new Swing app to represent sorted url link by their rank in a table.
	 * @param urls links sorted by quicksort
	 * @param defaultKeyword initial keyword to obtain the first 30 links
	 */
	public PageRankerView(WebPage[] urls, String defaultKeyword)
	{
		this.urls = urls;
		////////// SEARCHPANEL
		// Panel to search with custom keyword
		// Has two elements: textbox for keyword and button to initiate new search.
		JPanel searchPanel = new JPanel();

		searchPanel.add(new JLabel("Run a new search:"));
		JTextField keywordField = new JTextField(15);
		keywordField.setText(defaultKeyword);
		JButton searchButton = new JButton("Search");
		// Handle the new search
		searchButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String newKeyword = keywordField.getText();
				WebPage[] urls = PageRanker.getLinks(newKeyword);
				QuickSort.quickSort(urls, 0, urls.length - 1);
				urls = PageRanker.reverse(urls);
				String[][] urlData = WebPage.prepareTableValues(urls);
				title.setText("Sorted urls for keyword \"" + newKeyword + "\"");
				for (int i = 0; i < urlData.length; i++)
				{
					for (int j = 0; j < urlData[i].length; j++)
					{
						topThirtyTable.getModel().setValueAt(urlData[i][j], i, j);
					}
				}

			}
		});
		searchPanel.add(keywordField);
		searchPanel.add(searchButton);

		//////// TOPTHIRTYPANEL
		// Panel to show 30 sorted urls as a table
		topThirtyPanel = new JPanel();
		topThirtyPanel.setLayout(new BorderLayout());
		title = new JLabel("Sorted urls for keyword \"" + defaultKeyword + "\"");
		topThirtyPanel.add(title, BorderLayout.CENTER);

		// String representation of sorted urls
		String[][] topThirtyToPrint = WebPage.prepareTableValues(urls);

		// Create column names
		String[] columnNames = { "Page Rank", "Original Index", "Score", "Link" };

		// Populate the table with urls' values and column names
		this.topThirtyTable = new JTable(topThirtyToPrint, columnNames);

		// Create scroll bar for the table
		JScrollPane tableScroller = new JScrollPane(this.topThirtyTable);
		topThirtyTable.getColumnModel().getColumn(3).setMinWidth(300);	
		tableScroller.setPreferredSize(new Dimension(600, 600));
		topThirtyPanel.add(tableScroller, BorderLayout.SOUTH);

		// Create JFrame and add panel with table into it
		JFrame frame = new JFrame("Page Ranker using Quicksort");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(searchPanel, BorderLayout.NORTH);
		frame.add(topThirtyPanel, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}


	public void run()
	{
	}
}
