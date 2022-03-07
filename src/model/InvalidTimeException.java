package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidTimeException extends Exception {

	private static final long serialVersionUID = 1L;

	public void showErrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Time fields invalid.");
		alert.setHeaderText("Please fill in the time fields correctly. ");
		alert.showAndWait();
	}
}
