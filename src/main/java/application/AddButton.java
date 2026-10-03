package application;

import java.util.Calendar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddButton {
	Stage stage = new Stage();
	Alerts a = new Alerts();
	CustomTextField t1;
	CustomTextField t2;
	CustomTextField t3;
	CustomTextField t4;
	CustomTextField t5;
	CustomTextField t6;
	BorderPane bp = new BorderPane();
	Main m = new Main();

	// Method to validate if input fields are empty
	public boolean validInput1(String bookId, String title, String author, String category, int publishedYear,
			String isbn) {
		if (bookId == null || bookId.isEmpty()) {
			a.errorAlert("Error", "Book ID cannot be empty.");
			return false;
		}
		if (title == null || title.isEmpty()) {
			a.errorAlert("Error", "Title cannot be empty.");
			return false;
		}
		if (author == null || author.isEmpty()) {
			a.errorAlert("Error", "Author cannot be empty.");
			return false;
		}
		if (category == null || category.isEmpty()) {
			a.errorAlert("Error", "Category cannot be empty.");
			return false;
		}
		if (publishedYear == 0) {
			a.errorAlert("Error", "Published year cannot be empty or zero.");
			return false;
		}
		if (isbn == null || isbn.isEmpty()) {
			a.errorAlert("Error", "ISBN cannot be empty.");
			return false;
		}
		return true;
	}

	// Method to validate input formats
	public boolean validInput2(String bookId, String title, String author, String category, int publishedYear,
			String isbn) {
		if (!bookId.matches("\\d+")) {
			a.errorAlert("Error", "Book ID can contain only numbers.");
			return false;
		}
		if (!author.matches("^[\\p{L} .'-]+$")) {
			a.errorAlert("Error", "Author contains invalid characters.");
			return false;
		}
		if (!category.matches("^[\\p{L} &'/-]+$")) {
			a.errorAlert("Error", "Category contains invalid characters.");
			return false;
		}
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		if (publishedYear < 1900 || publishedYear > currentYear) {
			a.errorAlert("Error", "Published year must be between 1900 and the current year.");
			return false;
		}
		if (!isbn.matches("^\\d{3}-\\d{10}$")) {
			a.errorAlert("Error", "ISBN must use the format 123-1234567890.");
			return false;
		}

		return true;
	}

	// Method to clear input fields
	public void clear() {
		t1.clear();
		t2.clear();
		t3.clear();
		t4.clear();
		t5.clear();
		t6.clear();
	}

	// Method to check if a book ID already exists
	public Boolean CheckIdEnter(String bookId) {
		for (Book book : BookData.bookList) {
			if (book.getBookId().equals(bookId)) {
				a.errorAlert("Error", "A book with this ID already exists. Please try again.");
				return false;
			}
		}
		return true;
	}

	public void Display() {
		MenuBar menuBar = Main.createmenuBar(stage);
		m.setbackGround(bp);
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(15));
		gp.setHgap(50);
		gp.setVgap(35);

		CustomLabel l1 = new CustomLabel("Book ID :");
		l1.setStyle("-fx-text-fill: white; " + "-fx-font-size: 25px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 2);");
		t1 = new CustomTextField();
		gp.add(l1, 0, 0);
		gp.add(t1, 1, 0);

		CustomLabel l2 = new CustomLabel("Title :");
		l2.setStyle("-fx-text-fill: white; " + "-fx-font-size: 25px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 2);");
		t2 = new CustomTextField();
		gp.add(l2, 0, 1);
		gp.add(t2, 1, 1);

		CustomLabel l3 = new CustomLabel("Author :");
		l3.setStyle("-fx-text-fill: white; " + "-fx-font-size: 25px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 2);");
		t3 = new CustomTextField();
		gp.add(l3, 0, 2);
		gp.add(t3, 1, 2);

		CustomLabel l4 = new CustomLabel("Category :");
		l4.setStyle("-fx-text-fill: white; " + "-fx-font-size: 25px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 2);");
		t4 = new CustomTextField();
		gp.add(l4, 0, 3);
		gp.add(t4, 1, 3);

		CustomLabel l5 = new CustomLabel("Published Year :");
		l5.setStyle("-fx-text-fill: white; " + "-fx-font-size: 25px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 2);");
		t5 = new CustomTextField();
		gp.add(l5, 0, 4);
		gp.add(t5, 1, 4);

		CustomLabel l6 = new CustomLabel("ISBN :");
		l6.setStyle("-fx-text-fill: white; " + "-fx-font-size: 25px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 2);");
		t6 = new CustomTextField();
		gp.add(l6, 0, 5);
		gp.add(t6, 1, 5);

		HBox hb = new HBox(50);
		hb.setPadding(new Insets(20, 80, 20, 80));
		IconButton b1 = new IconButton("Clear", "/application/icons8-clear-24.png");
		b1.setOnAction(x -> {
			clear();
		});
		IconButton b2 = new IconButton("Add Book", "/application/icons8-add-50.png");
		b2.setOnAction(X -> {
			// To assign information to fields
			String bookId = t1.getText().trim();
			String title = t2.getText().trim();
			String author = t3.getText().trim();
			String category = t4.getText().trim();
			String publishedYearText = t5.getText().trim();
			String isbn = t6.getText().trim();
			if (publishedYearText.isEmpty()) {
				a.errorAlert("Error", "Published year cannot be empty.");
				return;
			}
			int publishedYear;
			try {
				publishedYear = Integer.parseInt(publishedYearText);
			} catch (NumberFormatException e) {
				a.errorAlert("Error", "Published year must be a whole number.");
				return;
			}

			// Validate inputs
			boolean isValid1 = validInput1(bookId, title, author, category, publishedYear, isbn);
			if (!isValid1) {
				return;
			}
			boolean isValid2 = validInput2(bookId, title, author, category, publishedYear, isbn);
			if (!isValid2) {
				return;
			}
			boolean isValid3 = CheckIdEnter(bookId);
			if (!isValid3) {
				return;
			}
			boolean confirmed = a.confirmationAlert("Confirmation",
					"Are you sure you want to add this book to the list?");
			if (!confirmed) {
				return;
			}
			// Add the book
			Book b = new Book(bookId, title, author, category, publishedYear, isbn);
			BookData.bookList.add(b);
			a.infoAlert("Success", "Book added successfully.");
			clear();
		});
		IconButton b3 = new IconButton("Back", "/application/icons8-back-50.png");
		b3.setOnAction(x -> {
			stage.close();
		});
		hb.getChildren().addAll(b3, b2, b1);
		hb.setAlignment(Pos.CENTER);

		bp.setTop(menuBar);
		bp.setCenter(gp);
		bp.setBottom(hb);

		Scene scene = new Scene(bp, 400, 400);
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.setTitle("Library Management System");
		stage.getIcons().add(new Image("/application/icons8-library-24.png"));
		stage.show();
	}
}
