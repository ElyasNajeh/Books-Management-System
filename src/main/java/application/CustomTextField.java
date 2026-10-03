package application;

import javafx.scene.control.TextField;

//To make the Text Field making process easier

public class CustomTextField extends TextField {
	@SuppressWarnings("this-escape")
	public CustomTextField() {
		this.setStyle("-fx-border-color: #C6E2FF; " + "-fx-font-size: 18px; " + "-fx-text-fill: black; "
				+ "-fx-background-color: white; " + "-fx-border-radius: 8px; " + "-fx-background-radius: 8px; "
				+ "-fx-padding: 5px; " + "-fx-font-weight: bold;");
		this.setPrefSize(320, 45);
	}
}
