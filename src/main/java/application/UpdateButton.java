package application;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UpdateButton {
	Stage stage = new Stage();
	Alerts a = new Alerts();
	int index; // Index of the selected book
	boolean updated;
	ObservableList<Book> bookList;

	CustomLabel l1;
	CustomTextField t1;
	CustomTextField t2;
	CustomTextField t3;
	CustomTextField t4;
	CustomTextField t5;
	CustomTextField t6;
	BorderPane bp = new BorderPane();
	Main m = new Main();
	AddButton ad = new AddButton(); // to get Validation method

	// Method to load book details into input fields
	public void loadDetails(CustomTextField t1, CustomTextField t2, CustomTextField t3, CustomTextField t4,
			CustomTextField t5, CustomTextField t6) {

		if (bookList == null || bookList.isEmpty() || index < 0 || index >= bookList.size()) {
			a.errorAlert("Error", "No books are available to update.");
			return;
		}
		Book book = bookList.get(index);
		t1.setText(book.getBookId());
		t2.setText(book.getTitle());
		t3.setText(book.getAuthor());
		t4.setText(book.getCategory());
		t5.setText(book.getPublishedYear() + "");
		t6.setText(book.getIsbn());
	}

	// Method to update the selected book details
	public void updateB() {
		if (bookList == null || index < 0 || index >= bookList.size()) {
			a.errorAlert("Error", "Please select a book to update.");
			return;
		}
		Book selectedBook = bookList.get(index);

		String newBookId = t1.getText().trim();
		String newTitle = t2.getText().trim();
		String newAuthor = t3.getText().trim();
		String newCategory = t4.getText().trim();
		String publishedYearText = t5.getText().trim();
		String newIsbn = t6.getText().trim();
		if (publishedYearText.isEmpty()) {
			a.errorAlert("Error", "Published year cannot be empty.");
			return;
		}
		int newPublishedYear;
		try {
			newPublishedYear = Integer.parseInt(publishedYearText);
		} catch (NumberFormatException e) {
			a.errorAlert("Error", "Published year must be a whole number.");
			return;
		}

		boolean isValid1 = ad.validInput1(newBookId, newTitle, newAuthor, newCategory, newPublishedYear, newIsbn);
		if (!isValid1) {
			return;
		}
		boolean isValid2 = ad.validInput2(newBookId, newTitle, newAuthor, newCategory, newPublishedYear, newIsbn);
		if (!isValid2) {
			return;
		}

		boolean confirmation = a.confirmationAlert("Confirmation",
				"Are you sure you want to update this book?");
		if (!confirmation) {
			return;
		}
		selectedBook.setBookId(newBookId);
		selectedBook.setTitle(newTitle);
		selectedBook.setAuthor(newAuthor);
		selectedBook.setCategory(newCategory);
		selectedBook.setPublishedYear(newPublishedYear);
		selectedBook.setIsbn(newIsbn);

		// Update book in the list
		int idx = BookData.bookList.indexOf(selectedBook);
		if (idx != -1) {
			BookData.bookList.set(idx, selectedBook);
		}
		updated = true;

		a.infoAlert("Success", "The book was updated successfully.");
		stage.close();
	}

	// Constructor to initialize selected book index and book list
	public UpdateButton(int index, ObservableList<Book> bookList) {
		this.index = index;
		this.bookList = bookList;
	}

	public void Display() {
		MenuBar menuBar = Main.createmenuBar(stage);
		m.setbackGround(bp);
		// Validate if the book list is empty or invalid index
		if (bookList == null || bookList.isEmpty() || index < 0 || index >= bookList.size()) {
			a.errorAlert("Error", "No book is available to update.");
			return;
		}
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(15));
		gp.setHgap(50);
		gp.setVgap(35);

		l1 = new CustomLabel("Book ID :");
		l1.setStyle("-fx-text-fill: white; " + "-fx-font-size: 25px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 2);");
		t1 = new CustomTextField();
		t1.setDisable(true);
		t1.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95);" + "-fx-border-color: black;"
				+ "-fx-border-width: 2px;" + "-fx-border-radius: 8px;" + "-fx-font-size: 18px;"
				+ "-fx-font-weight: bold;" + "-fx-text-fill: black;" + "-fx-padding: 6px;" + "-fx-opacity: 1;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 0, 3);");
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

		loadDetails(t1, t2, t3, t4, t5, t6);

		HBox hb1 = new HBox(80);
		IconButton back = new IconButton("Back", "/application/icons8-back-50.png");
		back.setOnAction(x -> {
			stage.close();
		});
		IconButton update = new IconButton("Update", "/application/icons8-update-50.png");
		update.setOnAction(x -> {

			updateB();
		});
		hb1.getChildren().addAll(back, update);
		hb1.setAlignment(Pos.CENTER);

		bp.setTop(menuBar);
		bp.setCenter(gp);
		bp.setBottom(hb1);

		Scene scene = new Scene(bp, 400, 400);
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.setTitle("Library Management System");
		stage.getIcons().add(new Image("/application/icons8-library-24.png"));
		stage.show();
	}
}
