package main.java.officerLeader.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import main.java.login.LoginController;
import main.java.officerLeader.officerAttendance.view.OfficerLeaderViewOfficerAttendanceController;
import main.java.officerLeader.unitAttendanceReport.view.OfficerLeaderViewUnitAttendanceReportController;

public class OfficerLeaderDashboardController implements Initializable{
	
    @FXML
    private Button requestTab;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button unitTab;

    @FXML
    private Button employeeTab;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private StackPane allContentArea;

    private void loadAfterNavigate(FXMLLoader loader) {
    	
    	try {
			
			Parent fxml = loader.load();
			
			contentArea.getChildren().removeAll();
			
			contentArea.getChildren().setAll(fxml);
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
    }
    
    private void loadAfterLogout(FXMLLoader loader) {
    	
    	try {
			
			Parent fxml = loader.load();
			
			allContentArea.getChildren().removeAll();
			
			allContentArea.getChildren().setAll(fxml);
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
    }
    
    private void renderUnitTab() {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/MyUnitAttendanceReportScreen.fxml"));    	
		
		OfficerLeaderViewUnitAttendanceReportController officerLeaderViewUnitAttendanceReportController = new OfficerLeaderViewUnitAttendanceReportController();
		
		loader.setController(officerLeaderViewUnitAttendanceReportController);
		
		loadAfterNavigate(loader);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		renderUnitTab();
		
		unitTab.setOnMouseClicked(e -> {
			
	    	renderUnitTab();

		});
		
		employeeTab.setOnMouseClicked(e -> {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewEmployeeAttendanceScreen.fxml"));    	
			
	    	OfficerLeaderViewOfficerAttendanceController officerLeaderViewOfficerAttendanceController = new OfficerLeaderViewOfficerAttendanceController();
			
			loader.setController(officerLeaderViewOfficerAttendanceController);
			
			loadAfterNavigate(loader);

		});
		
		logoutButton.setOnMouseClicked(e -> {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/LoginScreen.fxml"));    	
			
		    LoginController loginController = new LoginController();
				
		    loader1.setController(loginController);
				
		    loadAfterLogout(loader1);
			
		});
	}


}
