package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import main.java.login.LoginController;

public class AppScreenController implements Initializable{
	
    @FXML
    private StackPane mainArea;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/LoginScreen.fxml"));    	
		
	    LoginController loginController = new LoginController();
			
	    loader1.setController(loginController);
	    			
		Parent fxml = null;
		
		try {
			
			fxml = loader1.load();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
		mainArea.getChildren().removeAll();
			
		mainArea.getChildren().setAll(fxml);
	}
	
}
