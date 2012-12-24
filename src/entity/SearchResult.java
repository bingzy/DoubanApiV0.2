package entity;

import java.util.List;

public class SearchResult {
	private int start;
	private int count;
	private int total;
	private List<SimpleBook> books;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<SimpleBook> getBooks() {
		return books;
	}
	public void setBooks(List<SimpleBook> books) {
		this.books = books;
	}

}
