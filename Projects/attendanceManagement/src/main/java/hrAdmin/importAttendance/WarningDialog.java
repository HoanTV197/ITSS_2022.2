package main.java.hrAdmin.importAttendance;

import javafx.scene.control.Alert;

public class WarningDialog {
	
	public static void createDialog(Alert.AlertType type, String title, String headerText, String contentText) {
		
        Alert warning = new Alert(type);
        warning.setTitle(title);
        warning.setHeaderText(headerText);
        warning.setContentText(contentText);
        warning.showAndWait();
    }
}