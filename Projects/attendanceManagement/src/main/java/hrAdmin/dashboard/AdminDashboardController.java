package main.java.hrAdmin.dashboard;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import main.java.hrAdmin.attendance.crud.HrAdminCrudEmployeeAttendanceController;
import main.java.hrAdmin.attendance.view.AdminViewEmployeeAttendanceController;
import main.java.hrAdmin.importAttendance.ImportDataController;
import main.java.hrAdmin.request.HrAdminReceiveRequestController;
import main.java.hrAdmin.unitReport.view.AdminViewUnitAttendanceReportController;
import main.java.login.LoginController;

public class AdminDashboardController implements Initializable{

    @FXML
    private Button modifyTab;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button homeTab;

    @FXML
    private Button unitTab;

    @FXML
    private Button employeeTab;
    
    @FXML
    private ImageView notificationIcon;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private StackPane allContentArea;
    
    @FXML
    private Button importTab;

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
    
    private void renderEmployeeTab() {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewEmployeeAttendanceScreen.fxml"));    	
		
		AdminViewEmployeeAttendanceController adminViewEmployeeAttendanceController = new AdminViewEmployeeAttendanceController();
		
		loader.setController(adminViewEmployeeAttendanceController);
		
		loadAfterNavigate(loader);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		renderEmployeeTab();
				
		employeeTab.setOnMouseClicked(e -> {
									
			renderEmployeeTab();
		});
		
		unitTab.setOnMouseClicked(e -> {
			
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewUnitAttendanceReportScreen.fxml"));    	
			
			AdminViewUnitAttendanceReportController adminViewOfficerUnitAttendanceReportController = new AdminViewUnitAttendanceReportController();
			
			loader.setController(adminViewOfficerUnitAttendanceReportController);
			
			loadAfterNavigate(loader);

		});
		
		notificationIcon.setOnMouseClicked(e -> {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/NotificationsScreen.fxml"));    	
			
			HrAdminReceiveRequestController hrAdminReceiveRequestController = new HrAdminReceiveRequestController();
			
			loader.setController(hrAdminReceiveRequestController);
			
			loadAfterNavigate(loader);
		});
		
		modifyTab.setOnMouseClicked(e -> {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/CrudAttendanceScreen.fxml"));    	
			
			HrAdminCrudEmployeeAttendanceController hrAdminCrudEmployeeAttendanceController = new HrAdminCrudEmployeeAttendanceController();
			
			loader.setController(hrAdminCrudEmployeeAttendanceController);
			
			loadAfterNavigate(loader);
		});
		
        importTab.setOnMouseClicked(e -> {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/ImportAttendanceData.fxml"));    	
			
		    ImportDataController importDataController = new ImportDataController();
				
		    loader1.setController(importDataController);
				
		    loadAfterNavigate(loader1);
			
		});
		
		logoutButton.setOnMouseClicked(e -> {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/LoginScreen.fxml"));    	
			
		    LoginController loginController = new LoginController();
				
		    loader1.setController(loginController);
				
		    loadAfterLogout(loader1);
			
		});
		
	}
}
