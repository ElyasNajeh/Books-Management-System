package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DisplayStatistics {
	Alerts a = new Alerts();
	BorderPane bp = new BorderPane();
	Main m = new Main();
	ComboBox<String> comboBox;
	CustomTextField searchField;
	CustomTextField t1;
	CustomTextField t2;
	CustomTextField t3;
	CustomTextField t4;
	CustomTextField t5;
	CustomTextField t6;
	CustomTextField t7;

	// Method to count books by category
	public int countbyCategory(String category) {
		int count = 0;
		for (Book book : BookData.bookList) {
			if (book.getCategory().equalsIgnoreCase(category)) {
				count++;
			}
		}
		if (count == 0) {
			a.errorAlert("Error", "This category does not exist.");
			return -1;
		}
		return count;
	}
	// Method to count books by author

	public int countbyAuthor(String author) {
		int count = 0;
		for (Book book : BookData.bookList) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				count++;
			}

		}
		if (count == 0) {
			a.errorAlert("Error", "This author does not exist.");
			return -1;
		}
		return count;
	}
	// Method to count books published in a specific year

	public int countbyYear(int publishedYear) {
		int count = 0;
		for (Book book : BookData.bookList) {
			if (book.getPublishedYear() == publishedYear) {
				count++;
			}
		}
		if (count == 0) {
			a.errorAlert("Error", "No books were found for this year.");
			return -1;
		}
		return count;
	}

	// Method to find the year with the maximum number of books published and their
	// number
	public int[] yearrmaxNumBooks() {
		int maxCount = 0;
		int maxYear = 0;
		for (Book book : BookData.bookList) {
			int count = countbyYear(book.getPublishedYear());

			if (count > maxCount) {
				maxCount = count;
				maxYear = book.getPublishedYear();
			}
		}
		if (maxYear == 0) {
			a.errorAlert("Error", "No information is available.");
			return new int[] { -1, 0 };
		}
		return new int[] { maxYear, maxCount };
	}

	// Method to find the year with the minimum number of books published and their
	// number
	public int[] yearminNumBooks() {
		int minCount = Integer.MAX_VALUE;
		int minYear = 0;
		for (Book book : BookData.bookList) {
			int count = countbyYear(book.getPublishedYear());

			if (minCount > count && count > 0) {
				minCount = count;
				minYear = book.getPublishedYear();
			}
		}
		if (minYear == 0) {
			a.errorAlert("Error", "No information is available.");
			return new int[] { -1, 0 };
		}
		return new int[] { minYear, minCount };
	}

	// Method to find the author with the maximum number of books and their titles
	public String[] yearrmaxNumAuthor() {
		int maxCount = 0;
		String maxAuthor = "";
		for (Book book : BookData.bookList) {
			int count = countbyAuthor(book.getAuthor());
			if (count > maxCount) {
				maxCount = count;
				maxAuthor = book.getAuthor();
			}
		}
		if (maxAuthor.isEmpty()) {
			a.errorAlert("Error", "No information is available.");
			return new String[] { "No Author", "No Title" };
		}
		return new String[] { maxAuthor, titlesByAuthor(maxAuthor) };
	}

	// Method to find the author with the minimum number of books and their titles
	public String[] yearminNumAuthor() {
		int minCount = Integer.MAX_VALUE;
		String minAuthor = "";
		for (Book book : BookData.bookList) {
			int count = countbyAuthor(book.getAuthor());
			if (minCount > count && count > 0) {
				minCount = count;
				minAuthor = book.getAuthor();
			}
		}
		if (minAuthor.isEmpty()) {
			a.errorAlert("Error", "No information is available.");
			return new String[] { "No Author", "No Title" };
		}
		return new String[] { minAuthor, titlesByAuthor(minAuthor) };

	}

	private String titlesByAuthor(String author) {
		StringBuilder titles = new StringBuilder();
		for (Book book : BookData.bookList) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				if (!titles.isEmpty()) {
					titles.append(", ");
				}
				titles.append(book.getTitle());
			}
		}
		return titles.toString();
	}

	public void Style(TextField t) {
		t.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95);" + "-fx-border-color: black;"
				+ "-fx-border-width: 2px;" + "-fx-border-radius: 8px;" + "-fx-font-size: 18px;"
				+ "-fx-font-weight: bold;" + "-fx-text-fill: black;" + "-fx-padding: 6px;" + "-fx-opacity: 1;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 0, 3);");
	}

	public void Display() {
		Stage stage = new Stage();
		MenuBar menuBar = Main.createmenuBar(stage);
		m.setbackGround(bp);

		VBox vb1 = new VBox(35);

		HBox hb1 = new HBox(15);
		CustomLabel b1 = new CustomLabel("Number of Books by Category");
		b1.setMaxWidth(250);
		b1.setWrapText(true);
		t1 = new CustomTextField();
		t1.setPrefWidth(160);
		Style(t1);
		t1.setDisable(true);
		hb1.getChildren().addAll(t1, b1);

		HBox hb2 = new HBox(15);
		CustomLabel b2 = new CustomLabel("Number of Books by Author");
		b2.setMaxWidth(250);
		b2.setWrapText(true);
		t2 = new CustomTextField();
		Style(t2);
		t2.setDisable(true);
		t2.setPrefWidth(160);
		hb2.getChildren().addAll(t2, b2);

		HBox hb3 = new HBox(15);
		CustomLabel b3 = new CustomLabel("Number of Books Published in Specific Year");
		b3.setMaxWidth(250);
		b3.setWrapText(true);
		t3 = new CustomTextField();
		Style(t3);
		t3.setDisable(true);
		t3.setPrefWidth(160);
		hb3.getChildren().addAll(t3, b3);

		vb1.getChildren().addAll(hb1, hb2, hb3);
		vb1.setAlignment(Pos.CENTER);

		VBox vb2 = new VBox(30);

		HBox hb4 = new HBox(15);
		CustomLabel b4 = new CustomLabel("The Year With the Maximum  Number of Books");
		t4 = new CustomTextField();
		t4.setPrefWidth(200);
		Style(t4);
		t4.setDisable(true);
		hb4.getChildren().addAll(b4, t4);
		hb4.setAlignment(Pos.CENTER_LEFT);

		HBox hb5 = new HBox(15);
		CustomLabel b5 = new CustomLabel("The Year With the Minimum Number of Books");
		t5 = new CustomTextField();
		t5.setPrefWidth(210);
		Style(t5);
		t5.setDisable(true);
		hb5.getChildren().addAll(b5, t5);
		hb5.setAlignment(Pos.CENTER_LEFT);

		HBox hb6 = new HBox(15);

		CustomLabel b6 = new CustomLabel("The Author With the Maximum Number of Books");
		t6 = new CustomTextField();
		Style(t6);
		t6.setPrefWidth(450);
		t6.setDisable(true);
		hb6.getChildren().addAll(b6, t6);

		HBox hb7 = new HBox(15);

		CustomLabel b7 = new CustomLabel("The Author With the Minimum Number of Books");
		t7 = new CustomTextField();
		Style(t7);
		t7.setPrefWidth(450);
		t7.setDisable(true);
		hb7.getChildren().addAll(b7, t7);

		Label l = new Label("");
		l.setMinHeight(200);
		vb2.getChildren().addAll(hb4, hb5, l, hb6, hb7);
		vb2.setAlignment(Pos.CENTER);

		HBox hb8 = new HBox(150);
		hb8.setAlignment(Pos.CENTER);
		hb8.setPadding(new Insets(15));
		IconButton b8 = new IconButton("Back", "/application/icons8-back-50.png");
		b8.setOnAction(x -> {
			stage.close();
		});
		IconButton b9 = new IconButton("Run", "/application/icons8-start-64.png");
		b9.setOnAction(x -> {
			String selected = comboBox.getValue();
			String searchValue = searchField.getText().trim();
			if (selected == null) {
				a.errorAlert("Error", "Please select an option first.");
				return;
			}
			if (!searchField.isDisabled() && searchValue.isEmpty()) {
				a.errorAlert("Error", "Please enter a value.");
				return;
			}
			switch (selected) {
			case "Number of Books by Category":
				int categoryCount = countbyCategory(searchValue);
				if (categoryCount >= 0) {
					t1.setText(String.valueOf(categoryCount));
				}
				break;

			case "Number of Books by Author":
				int authorCount = countbyAuthor(searchValue);
				if (authorCount >= 0) {
					t2.setText(String.valueOf(authorCount));
				}
				break;

			case "Number of Books Published in Specific Year":
				try {
					int yearCount = countbyYear(Integer.parseInt(searchValue));
					if (yearCount >= 0) {
						t3.setText(String.valueOf(yearCount));
					}
				} catch (NumberFormatException e) {
					a.errorAlert("Error", "Published year must be a whole number.");
				}
				break;

			case "The Year With the Maximum  Number of Books":
				int[] result1 = yearrmaxNumBooks();
				if (result1[0] >= 0) {
					t4.setText("Year " + result1[0] + ", Books " + result1[1]);
				}
				break;

			case "The Year With the Minimum Number of Books":
				int[] result2 = yearminNumBooks();
				if (result2[0] >= 0) {
					t5.setText("Year " + result2[0] + ", Books " + result2[1]);
				}
				break;

			case "The Author With the Maximum Number of Books":
				String[] result3 = yearrmaxNumAuthor();
				t6.setText("Author: " + result3[0] + ", Titles: " + result3[1]);
				break;

			case "The Author With the Minimum Number of Books":
				String[] result4 = yearminNumAuthor();
				t7.setText("Author: " + result4[0] + ", Titles: " + result4[1]);
				break;
			}

		});
		hb8.getChildren().addAll(b8, b9);

		HBox hb9 = new HBox(100);
		comboBox = new ComboBox<>();
		comboBox.getItems().addAll("The Year With the Maximum  Number of Books",
				"The Year With the Minimum Number of Books", "The Author With the Maximum Number of Books",
				"The Author With the Minimum Number of Books", "Number of Books by Category",
				"Number of Books by Author", "Number of Books Published in Specific Year");
		comboBox.setPromptText("Select a Statistic, Then Click Run ..");
		searchField = new CustomTextField();
		Style(searchField);
		searchField.setPrefWidth(200);
		searchField.setDisable(true);
		comboBox.setOnAction(x -> {
			String selected = comboBox.getValue();
			if (selected.equals("Number of Books by Category") || selected.equals("Number of Books by Author")
					|| selected.equals("Number of Books Published in Specific Year")) {
				searchField.setDisable(false);
				searchField.setPromptText("Enter Value ..");
			} else {
				searchField.setDisable(true);
				searchField.clear();
			}
		});

		hb9.getChildren().addAll(comboBox, searchField);
		hb9.setAlignment(Pos.CENTER);

		VBox vb3 = new VBox(15);
		vb3.getChildren().addAll(hb9, hb8);
		vb3.setPadding(new Insets(15));

		bp.setTop(menuBar);
		bp.setRight(vb1);
		bp.setLeft(vb2);
		bp.setBottom(vb3);

		Scene scene = new Scene(bp, 400, 400);
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.setTitle("Library Management System");
		stage.getIcons().add(new Image("/application/icons8-library-24.png"));
		stage.show();
	}
}
