package application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Sort {
	Alerts a = new Alerts();
	TableView<Book> tableView;
	ObservableList<Book> sortedList;

	// Constructor to initialize the table and data
	public Sort() {
		this.tableView = new TableView<>();
		this.sortedList = FXCollections.observableArrayList(BookData.bookList); // To take a copy of the original table

		TableColumn<Book, String> idCol = new TableColumn<>("BookID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));

		TableColumn<Book, String> tiCol = new TableColumn<>("Title");
		tiCol.setCellValueFactory(new PropertyValueFactory<>("title"));

		TableColumn<Book, String> auCol = new TableColumn<>("Author");
		auCol.setCellValueFactory(new PropertyValueFactory<>("author"));

		TableColumn<Book, String> caCol = new TableColumn<>("Category");
		caCol.setCellValueFactory(new PropertyValueFactory<>("category"));

		TableColumn<Book, Integer> yeCol = new TableColumn<>("Published Year");
		yeCol.setCellValueFactory(new PropertyValueFactory<>("publishedYear"));

		TableColumn<Book, String> isCol = new TableColumn<>("ISBN");
		isCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));

		tableView.getColumns().add(idCol);
		tableView.getColumns().add(tiCol);
		tableView.getColumns().add(auCol);
		tableView.getColumns().add(caCol);
		tableView.getColumns().add(yeCol);
		tableView.getColumns().add(isCol);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		tableView.setStyle("-fx-border-color: #C6E2FF; -fx-border-width: 1;");
		this.tableView.setItems((sortedList));
	}

	// Method to sort books by title
	public void sortbyTitle() {
		List<Book> book = new ArrayList<>(sortedList);
		SortTitle s1 = new SortTitle();
		book.sort(s1);
		sortedList.setAll(book);
		tableView.setItems(sortedList);
		tableView.refresh();
	}

	// Method to sort books by author
	public void sortbyAuthor() {
		List<Book> book = new ArrayList<>(sortedList);
		SortAuthor s2 = new SortAuthor();
		book.sort(s2);
		sortedList.setAll(book);
		tableView.setItems(sortedList);
		tableView.refresh();
	}

	// Method to sort books by published year
	public void sortbyPublishedyear() {
		List<Book> book = new ArrayList<>(sortedList);
		Collections.sort(book);
		sortedList.setAll(book);
		tableView.setItems(sortedList);
		tableView.refresh();
	}

	// Method to filter Authors Still Active
	public void stillActive() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		ObservableList<Book> filteredList = FXCollections.observableArrayList();
		for (Book book : BookData.bookList) {
			if ((currentYear - book.getPublishedYear()) <= 5) {
				filteredList.add(book);
			}
		}
		tableView.setItems(filteredList);
	}

	public void Display() {
		Stage stage = new Stage();
		MenuBar menuBar = Main.createmenuBar(stage);
		HBox hb1 = new HBox(100);
		IconButton b = new IconButton("Back", "/application/icons8-back-50.png");
		b.setOnAction(x -> {
			stage.close();
		});
		IconButton b1 = new IconButton("Sort By Title", "/application/icons8-title-50.png");
		b1.setOnAction(x -> {
			sortbyTitle();
		});
		IconButton b2 = new IconButton("Sort By Author", "/application/icons8-author-32.png");
		b2.setOnAction(x -> {
			sortbyAuthor();
		});
		IconButton b3 = new IconButton("Sort By Published Year", "/application/icons8-timeline-24.png");
		b3.setOnAction(x -> {
			sortbyPublishedyear();
		});
		IconButton b4 = new IconButton("Still Active", "/application/icons8-active-64.png");
		b4.setOnAction(x -> {
			stillActive();
			a.infoAlert("Info", "Active books were published within the last five years.");
		});
		hb1.getChildren().addAll(b, b1, b2, b3, b4);
		hb1.setAlignment(Pos.CENTER);
		hb1.setStyle("-fx-padding: 20px;");

		BorderPane bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(tableView);
		bp.setBottom(hb1);

		Scene scene = new Scene(bp, 400, 400);
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.setTitle("Library Management System");
		stage.getIcons().add(new Image("/application/icons8-library-24.png"));
		stage.show();

	}
}
