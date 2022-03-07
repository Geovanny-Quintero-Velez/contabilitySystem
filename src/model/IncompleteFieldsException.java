package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class IncompleteFieldsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public void showErrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Incomplete Fields");
		alert.setHeaderText("Please fill in all fields when creating a new value.");
		alert.showAndWait();
	}

}
