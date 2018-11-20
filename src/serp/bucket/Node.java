package serp.bucket;

import serp.WebPage;

public class Node
{
	public WebPage data;
	public String key;
	Node next;
	Node prev;
	
	public Node(WebPage data)
	{
		this.data = data;
		this.key = data.getLink(); // TODO: clean from http, https, and www
		this.next = null;
		this.prev = null;
	}
}
