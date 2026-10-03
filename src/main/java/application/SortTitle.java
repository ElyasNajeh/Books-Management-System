package application;

import java.util.Comparator;

public class SortTitle implements Comparator<Book> {

	// This method compares two books based on the Titles name form a-z

	@Override
	public int compare(Book o1, Book o2) {
		return o1.getTitle().compareToIgnoreCase(o2.getTitle());
	}

}
