package application;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.stage.FileChooser;

public class LoadButton {
	Alerts a = new Alerts();

	// Method to display the file chooser and load books from the selected file
	public boolean Display() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Select Books File");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
		fc.setInitialDirectory(initialDirectory());
		File f = fc.showOpenDialog(null);
		if (f == null) {
			return false;
		}

		try {
			List<String> lines = Files.readAllLines(f.toPath(), StandardCharsets.UTF_8);
			List<Book> loadedBooks = new ArrayList<>();
			Set<String> bookIds = new HashSet<>();
			for (int lineNumber = 1; lineNumber <= lines.size(); lineNumber++) {
				String line = lines.get(lineNumber - 1).trim();
				if (line.isEmpty()) {
					continue;
				}
				String[] data = line.split(",", -1);
				if (data.length != 6) {
					throw new IllegalArgumentException("Line " + lineNumber + " must contain exactly six values.");
				}
				String bookId = data[0].trim();
				String title = data[1].trim();
				String author = data[2].trim();
				String category = data[3].trim();
				String isbn = data[5].trim();
				if (bookId.isEmpty() || title.isEmpty() || author.isEmpty() || category.isEmpty() || isbn.isEmpty()) {
					throw new IllegalArgumentException("Line " + lineNumber + " contains an empty value.");
				}
				if (!bookIds.add(bookId)) {
					throw new IllegalArgumentException("Line " + lineNumber + " contains duplicate book ID " + bookId + ".");
				}
				int publishedYear;
				try {
					publishedYear = Integer.parseInt(data[4].trim());
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Line " + lineNumber + " has an invalid publication year.");
				}
				if (publishedYear < 1900 || publishedYear > Year.now().getValue()) {
					throw new IllegalArgumentException("Line " + lineNumber + " has a publication year outside 1900-"
							+ Year.now().getValue() + ".");
				}
				if (!isbn.matches("^\\d{3}-\\d{10}$")) {
					throw new IllegalArgumentException("Line " + lineNumber + " has an invalid ISBN.");
				}
				loadedBooks.add(new Book(bookId, title, author, category, publishedYear, isbn));
			}
			BookData.bookList.setAll(loadedBooks);
			a.infoAlert("Success", loadedBooks.size() + " books loaded successfully.");
			return true;
		} catch (IllegalArgumentException e) {
			a.errorAlert("Invalid Books File", e.getMessage());
		} catch (IOException e) {
			a.errorAlert("Error", "The selected file could not be read.");
		}
		return false;
	}

	private File initialDirectory() {
		Path dataDirectory = Path.of("data").toAbsolutePath().normalize();
		if (Files.isDirectory(dataDirectory)) {
			return dataDirectory.toFile();
		}
		return Path.of(System.getProperty("user.home")).toFile();
	}
}
