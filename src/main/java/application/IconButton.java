package application;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

// To make the button making process easier
public class IconButton extends Button {
	private static final String DEFAULT_STYLE = "-fx-background-color: linear-gradient(to bottom, #C6E2FF, #A1C4FD); "
			+ "-fx-text-fill: #333333; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 14px 22px; "
			+ "-fx-min-width: 170px; -fx-min-height: 55px; -fx-border-radius: 12px; "
			+ "-fx-background-radius: 12px; -fx-border-color: #6A89CC; -fx-border-width: 2px;";
	private static final String HOVER_STYLE = "-fx-background-color: linear-gradient(to bottom, #00509E, #003F7F); "
			+ "-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 14px 22px; "
			+ "-fx-min-width: 170px; -fx-min-height: 55px; -fx-border-radius: 12px; "
			+ "-fx-background-radius: 12px; -fx-border-color: #4A69BB; -fx-border-width: 2px;";

	// Constructor to create a button with text and an icon
	@SuppressWarnings("this-escape")
	public IconButton(String text, String imagePath) {
		super(text);
		Image icon = new Image(imagePath);
		ImageView im = new ImageView(icon);
		im.setFitWidth(25);
		im.setFitHeight(25);
		im.setPreserveRatio(true);
		this.setGraphic(im);
		this.setStyle(DEFAULT_STYLE);
		this.setCursor(Cursor.HAND);
		this.setOnMouseEntered(e -> this.setStyle(HOVER_STYLE));
		this.setOnMouseExited(e -> this.setStyle(DEFAULT_STYLE));

		this.setEffect(new DropShadow(10, Color.BLACK));
	}
}
