package application;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartNow {
	Alerts a = new Alerts();
	TableView<Book> tableView = new TableView<>();
	CustomTextField l1;
	boolean columnsAdded = false; // to ensure columns are added only once

	// Method to search for books by ID, title, or author
	public void searchBooks(String search) {
		if (search == null || search.isEmpty()) {
			a.errorAlert("Error", "Please enter a book ID, title, or author to search.");
			return;
		}
		ObservableList<Book> bookListt = FXCollections.observableArrayList();
		for (Book book : BookData.bookList) {
			if (book.getBookId().equalsIgnoreCase(search.trim()) || book.getTitle().equalsIgnoreCase(search.trim())
					|| book.getAuthor().equalsIgnoreCase(search.trim())) {
				bookListt.add(book);
			}
		}

		if (bookListt.isEmpty()) {
			a.errorAlert("Error", "No book was found with this ID, title, or author.");
			tableView.setItems(BookData.bookList);
		} else {
			tableView.setItems(bookListt);
		}

	}

	public void Display() {
		Stage stage = new Stage();
		MenuBar menuBar = Main.createmenuBar(stage);
		if (!columnsAdded) {
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
			columnsAdded = true;
		}
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		tableView.setStyle("-fx-border-color: #C6E2FF; -fx-border-width: 1;");
		tableView.setItems((BookData.bookList)); // Load books into the table

		HBox hb1 = new HBox(20);
		IconButton search = new IconButton("Search", "/application/icons8-search-24.png");
		search.setOnAction(x -> {
			searchBooks(l1.getText().trim());

		});
		l1 = new CustomTextField();
		l1.setPrefWidth(230);
		l1.setPromptText("Book ID, title, or author");
		l1.setOnAction(x -> searchBooks(l1.getText().trim()));
		IconButton sort = new IconButton("Sort", "/application/icons8-sort-64.png");
		sort.setOnAction(x -> {
			Sort s = new Sort();
			s.Display();
		});

		hb1.getChildren().addAll(search, l1, sort);
		hb1.setAlignment(Pos.CENTER);

		HBox hb2 = new HBox(50);

		IconButton insert = new IconButton("Add", "/application/icons8-add-50.png");
		insert.setOnAction(x -> {
			AddButton ad = new AddButton();
			ad.Display();
		});
		IconButton delete = new IconButton("Delete", "/application/icons8-delete-30.png");
		delete.setOnAction(x -> {
			Book selectedBook = tableView.getSelectionModel().getSelectedItem();
			if (selectedBook == null) {
				a.errorAlert("Error", "Please select a row to delete.");
				return;
			}
			int selectedIndex = BookData.bookList.indexOf(selectedBook);
			DeleteButton d = new DeleteButton(selectedIndex, BookData.bookList);
			d.stage.setOnHidden(event -> {
				if (d.deleted) {
					tableView.setItems(BookData.bookList);
					l1.clear();
				}
			});
			d.Display();
		});
		IconButton update = new IconButton("Update", "/application/icons8-update-50.png");
		update.setOnAction(x -> {
			Book selectedBook = tableView.getSelectionModel().getSelectedItem();
			if (selectedBook == null) {
				a.errorAlert("Error", "Please select a row to update.");
				return;
			}
			int selectedIndex = BookData.bookList.indexOf(selectedBook);
			UpdateButton u = new UpdateButton(selectedIndex, BookData.bookList);
			u.stage.setOnHidden(event -> {
				if (u.updated) {
					tableView.refresh();
				}
			});
			u.Display();
		});
		hb2.getChildren().addAll(delete, insert, update);
		hb2.setAlignment(Pos.CENTER);

		HBox hb3 = new HBox(50);

		IconButton load = new IconButton("Load Items", "/application/icons8-load-from-file-48.png");
		load.setOnAction(x -> {
			LoadButton l = new LoadButton();
			if (l.Display()) {
				tableView.setItems(BookData.bookList);
				l1.clear();
			}
		});
		IconButton back = new IconButton("Back", "/application/icons8-back-50.png");
		back.setOnAction(x -> {
			stage.close();
		});
		IconButton save = new IconButton("Save Data", "/application/icons8-save-50.png");
		save.setOnAction(x -> {
			SaveButton s = new SaveButton(BookData.bookList);
			s.Display();
		});
		hb3.getChildren().addAll(back, load, save);
		hb3.setAlignment(Pos.CENTER);

		VBox vb = new VBox(20);
		vb.getChildren().addAll(hb1, hb2, hb3);
		vb.setAlignment(Pos.CENTER);
		vb.setStyle("-fx-padding: 20px;");

		BorderPane bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(tableView);
		bp.setBottom(vb);

		Scene scene = new Scene(bp, 400, 400);
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.setTitle("Library Management System");
		stage.getIcons().add(new Image("/application/icons8-library-24.png"));
		stage.show();

	}

}
