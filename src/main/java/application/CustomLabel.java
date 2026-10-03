package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

//To make the Label making process easier

public class CustomLabel extends Label {

	@SuppressWarnings("this-escape")
	public CustomLabel(String text) {
		super(text);
		this.setStyle("-fx-text-fill: white;" + "-fx-font-size: 24px;" + "-fx-font-weight: bold;"
				+ "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 5, 0, 0, 2);"
				+ "-fx-font-family: 'Arial Rounded MT Bold', sans-serif;" + "-fx-background-color: rgba(0, 0, 0, 0.7);"
				+ "-fx-padding: 5px 15px;" + "-fx-background-radius: 10px;");
		this.setAlignment(Pos.CENTER_LEFT);
	}
}
