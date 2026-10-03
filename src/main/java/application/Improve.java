package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Improve {
	BorderPane bp = new BorderPane();
	Main m = new Main();

	// Method to display the Improve window

	public void Display() {
		Stage stage = new Stage();
		m.setbackGround(bp);
		MenuBar menuBar = Main.createmenuBar(stage);

		HBox hb = new HBox(20);
		CustomLabel l1 = new CustomLabel(
				"We are Always looking to Improve our Digital Library .. ! Share your Ideas  with us At  --> :");
		CustomLabel l2 = new CustomLabel("Elyasnajeh5@gmail.com");
		IconButton b1 = new IconButton("Back", "/application/icons8-back-50.png");
		b1.setOnAction(x -> {
			stage.close();
		});
		hb.getChildren().addAll(l1, l2);
		hb.setAlignment(Pos.CENTER);
		bp.setTop(menuBar);
		bp.setCenter(hb);
		BorderPane.setAlignment(b1, Pos.BOTTOM_CENTER);
		bp.setBottom(b1);

		Scene scene = new Scene(bp, 400, 400);
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.setTitle("Library Management System");
		stage.getIcons().add(new Image("/application/icons8-library-24.png"));
		stage.show();
	}
}
