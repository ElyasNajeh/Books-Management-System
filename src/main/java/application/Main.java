package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

// Elyas Najeh Ihmud 
// 1230892 

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		BorderPane bp = new BorderPane();

		setbackGround(bp);// Set background image

		MenuBar menuBar = createmenuBar(stage);
		bp.setTop(menuBar);

		VBox vb = item(stage);
		vb.setAlignment(Pos.CENTER);
		bp.setCenter(vb);

		IconButton b = new IconButton("Help Us Improve", "/application/icons8-improve-64.png");
		b.setOnAction(x -> {
			Improve im = new Improve();
			im.Display();
		});
		BorderPane.setAlignment(b, Pos.BOTTOM_RIGHT);
		bp.setBottom(b);

		Scene scene = new Scene(bp, 400, 400);
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.setTitle("Library Management System");
		stage.getIcons().add(new Image("/application/icons8-library-24.png"));
		stage.show();

	}

	// Method to create the menu bar
	public static MenuBar createmenuBar(Stage stage) {
		MenuBar m = new MenuBar();
		m.setStyle(
				"-fx-background-color: #0A192F; -fx-text-fill: white; -fx-font-size: 18px; -fx-border-radius: 0px; -fx-background-radius: 0px; -fx-padding: 8px; "
						+ "-fx-border-width: 0 0 3px 0; -fx-border-color: #C6E2FF;");
		StartNow st = new StartNow();
		Menu m1 = new Menu("Books");
		MenuItem m11 = new MenuItem("Books Record");
		m1.setStyle("-fx-background-color: #C6E2FF; -fx-text-fill: #101010 ; -fx-font-size: 18px; -fx-padding: 14px;");
		m11.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
		m11.setOnAction(x -> st.Display());
		m1.getItems().add(m11);

		Menu m2 = new Menu(" | ");
		m2.setStyle("-fx-font-size: 30px; -fx-text-fill: black;");

		Menu m3 = new Menu("Close");
		MenuItem m33 = new MenuItem("Exit");
		m3.setStyle("-fx-background-color: #C6E2FF; -fx-text-fill: #101010 ; -fx-font-size: 18px; -fx-padding: 14px;");
		m33.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		m33.setOnAction(x -> stage.close());
		m3.getItems().add(m33);

		Menu m4 = new Menu(" | ");
		m4.setStyle("-fx-font-size: 30px; -fx-text-fill: black;");

		Menu m5 = new Menu("Statistics");
		MenuItem m55 = new MenuItem("Display Statistics");
		m55.setOnAction(x -> {
			DisplayStatistics s = new DisplayStatistics();
			s.Display();
		});

		m5.setStyle("-fx-background-color: #C6E2FF; -fx-text-fill: #101010 ; -fx-font-size: 18px; -fx-padding: 14px;");
		m55.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
		m5.getItems().add(m55);

		m.getMenus().addAll(m1, m2, m5, m4, m3);
		return m;
	}

	// Method to create the main welcome screen with a start button
	public VBox item(Stage stage) {
		VBox vb = new VBox(15);
		Label l1 = new Label("Welcome to Your Digital Library");
		l1.setPrefSize(500, 120);
		l1.setStyle("-fx-text-fill: white; " + "-fx-font-size: 30px; " + "-fx-font-weight: bold; "
				+ "-fx-effect: dropshadow(gaussian, black, 8, 0.8, 0, 0);"
				+ "-fx-font-family: 'Arial Rounded MT Bold', sans-serif;");

		IconButton b1 = new IconButton("Start Now .. !", "/application/icons8-start-64.png");
		StartNow st = new StartNow();
		b1.setOnAction(x -> {
			st.Display();
		});
		b1.setPrefSize(270, 70);
		vb.getChildren().addAll(l1, b1);
		return vb;
	}

	// Method to set the background image of the main window
	public void setbackGround(BorderPane bp) {
		bp.setStyle("-fx-background-image: url('/application/Library.png'); -fx-background-repeat: no-repeat; "
				+ "-fx-background-position: center; -fx-background-size: cover;");
	}

}
