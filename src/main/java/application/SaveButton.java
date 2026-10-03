package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;

public class SaveButton {
	ObservableList<Book> bookList; // List of books to be saved
	Alerts a = new Alerts();

	// Constructor to initialize the book list
	public SaveButton(ObservableList<Book> bookList) {
		this.bookList = bookList;
	}

	// Method to save book data to a file
	public void Display() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Books File");
		fileChooser.setInitialFileName("UpdatedBooks.txt");
		fileChooser.setInitialDirectory(initialDirectory());
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
		File file = fileChooser.showSaveDialog(null);
		if (file == null) {
			return;
		}
		try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
			for (Book book : bookList) {
				writer.write(buildBookData(book));
				writer.newLine();
			}
			a.infoAlert("Success", "Books data saved successfully.");
		} catch (IOException e) {
			a.errorAlert("Error", "Books data could not be saved to the selected file.");
		}
	}

	private File initialDirectory() {
		Path documents = Path.of(System.getProperty("user.home"), "Documents");
		return Files.isDirectory(documents) ? documents.toFile() : Path.of(System.getProperty("user.home")).toFile();
	}

	// method to return book information as a string
	private String buildBookData(Book book) {
		return book.getBookId() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getCategory() + ", "
				+ book.getPublishedYear() + ", " + book.getIsbn();
	}
}
