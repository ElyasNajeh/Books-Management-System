package application;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

// To make the alert creation process easier.

public class Alerts {
	public boolean confirmationAlert(String title, String message) {
		Alert a1 = new Alert(AlertType.CONFIRMATION);
		a1.setTitle(title);
		a1.setHeaderText(null);
		a1.setContentText(message);
		Optional<ButtonType> res = a1.showAndWait();
		return res.isPresent() && res.get() == ButtonType.OK;
	}

	public void errorAlert(String title, String message) {
		Alert a2 = new Alert(AlertType.ERROR);
		a2.setTitle(title);
		a2.setHeaderText(null);
		a2.setContentText(message);
		a2.showAndWait();
	}

	public void infoAlert(String title, String message) {
		Alert a3 = new Alert(AlertType.INFORMATION);
		a3.setTitle(title);
		a3.setHeaderText(null);
		a3.setContentText(message);
		a3.showAndWait();
	}
}
