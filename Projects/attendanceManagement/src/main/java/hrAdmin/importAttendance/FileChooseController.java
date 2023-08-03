package main.java.hrAdmin.importAttendance;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

public class FileChooseController implements Initializable{

	FileChooser fileChooser = new FileChooser();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		fileChooser.setInitialDirectory(new File(""));
		
	}

}
