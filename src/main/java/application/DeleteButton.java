package application;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DeleteButton {
	Stage stage = new Stage();
	Alerts a = new Alerts();
	int index; // Index of the selected book in the list
	boolean deleted;
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

	// Method to load the selected book details into the form fields
	public void loadDetails(CustomTextField t1, CustomTextField t2, CustomTextField t3, CustomTextField t4,
			CustomTextField t5, CustomTextField t6) {

		if (bookList == null || bookList.isEmpty() || index < 0 || index >= bookList.size()) {
			a.errorAlert("Error", "No books are available to delete.");
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

	// Method to delete the selected book from the list
	public void deleteB() {
		if (bookList == null || index < 0 || index >= bookList.size()) {
			a.errorAlert("Error", "Please select a book to delete.");
			return;
		}
		Book selectedBook = bookList.get(index);
		boolean confirmation = a.confirmationAlert("Confirmation", "Are you sure you want to delete this book?");
		if (!confirmation) {
			return;
		}
		// Remove book from list
		bookList.remove(selectedBook);
		deleted = true;

		t1.clear();
		t2.clear();
		t3.clear();
		t4.clear();
		t5.clear();
		t6.clear();

		a.infoAlert("Success", "The book was deleted successfully.");
		stage.close();
	}

	// Constructor to initialize the selected book index and book list
	public DeleteButton(int index, ObservableList<Book> bookList) {
		this.index = index;
		this.bookList = bookList;
	}

	public void Style1(TextField t) {
		t.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95);" + "-fx-border-color: black;"
				+ "-fx-border-width: 2px;" + "-fx-border-radius: 8px;" + "-fx-font-size: 18px;"
				+ "-fx-font-weight: bold;" + "-fx-text-fill: black;" + "-fx-padding: 6px;" + "-fx-opacity: 1;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 0, 3);");
	}

	public void Style2(Label l) {
		l.setStyle("-fx-text-fill: white; " + "-fx-font-size: 25px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 10, 0.5, 0, 2);");
	}

	public void Display() {
		MenuBar menuBar = Main.createmenuBar(stage);
		m.setbackGround(bp);
		// Check if the selected book is valid
		if (bookList == null || bookList.isEmpty() || index < 0 || index >= bookList.size()) {
			a.errorAlert("Error", "No book is available to delete.");
			return;
		}
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(15));
		gp.setHgap(50);
		gp.setVgap(35);

		l1 = new CustomLabel("Book ID :");
		t1 = new CustomTextField();
		Style1(t1);
		Style2(l1);
		t1.setDisable(true);
		gp.add(l1, 0, 0);
		gp.add(t1, 1, 0);

		CustomLabel l2 = new CustomLabel("Title :");
		t2 = new CustomTextField();
		Style1(t2);
		Style2(l2);
		t2.setDisable(true);
		gp.add(l2, 0, 1);
		gp.add(t2, 1, 1);

		CustomLabel l3 = new CustomLabel("Author :");
		t3 = new CustomTextField();
		Style1(t3);
		Style2(l3);
		t3.setDisable(true);
		gp.add(l3, 0, 2);
		gp.add(t3, 1, 2);

		CustomLabel l4 = new CustomLabel("Category :");
		t4 = new CustomTextField();
		Style1(t4);
		Style2(l4);
		t4.setDisable(true);
		gp.add(l4, 0, 3);
		gp.add(t4, 1, 3);

		CustomLabel l5 = new CustomLabel("Published Year :");
		t5 = new CustomTextField();
		Style1(t5);
		Style2(l5);
		t5.setDisable(true);
		gp.add(l5, 0, 4);
		gp.add(t5, 1, 4);

		CustomLabel l6 = new CustomLabel("ISBN :");
		t6 = new CustomTextField();
		Style1(t6);
		Style2(l6);
		t6.setDisable(true);
		gp.add(l6, 0, 5);
		gp.add(t6, 1, 5);

		loadDetails(t1, t2, t3, t4, t5, t6);

		HBox hb1 = new HBox(80);
		IconButton back = new IconButton("Back", "/application/icons8-back-50.png");
		back.setOnAction(x -> {
			stage.close();
		});
		IconButton delete = new IconButton("Delete", "/application/icons8-delete-30.png");
		delete.setOnAction(x -> {

			deleteB();
		});
		hb1.getChildren().addAll(back, delete);
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
