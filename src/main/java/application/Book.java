package application;

// Implements Comparable for sorting by published year
public class Book implements Comparable<Book> {

	// Attributes of the book
	private String bookId;
	private String title;
	private String author;
	private String category;
	private int publishedYear;
	private String isbn;

	// Constructor to initialize the book details
	public Book(String bookId, String title, String author, String category, int publishedYear, String isbn) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.publishedYear = publishedYear;
		this.isbn = isbn;
	}

// setters and getters to Attributes
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return bookId + ", " + title + ", " + author + ", " + category + ", " + publishedYear + ", " + isbn;
	}

	// compareTo method to compare books based on published year
	@Override
	public int compareTo(Book book) {
		return Integer.compare(this.publishedYear, book.publishedYear);
	}

}
