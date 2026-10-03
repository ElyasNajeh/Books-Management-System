package application;

import java.util.Comparator;

public class SortAuthor implements Comparator<Book> {

	// This method compares two books based on the authors name form a-z
	@Override
	public int compare(Book o1, Book o2) {
		return o1.getAuthor().compareToIgnoreCase(o2.getAuthor());
	}

}
