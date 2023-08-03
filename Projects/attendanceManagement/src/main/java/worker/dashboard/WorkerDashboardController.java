package main.java.worker.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import main.java.employee.Employee;
import main.java.login.LoginController;
import main.java.worker.attendance.view.WorkerViewWorkerAttendanceController;
import main.java.worker.request.WorkerRequestController;

public class WorkerDashboardController implements Initializable{

	private Employee employee;
	
    @FXML
    private Button requestTab;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button myAttendanceTab;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private StackPane allContentArea;
    
    public WorkerDashboardController(Employee employee) {
		super();
		this.employee = employee;
	}

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
   
   private void renderMyAttendancePage() {
   	
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/MyAttendanceScreen.fxml"));  
   	
	   WorkerViewWorkerAttendanceController workerViewWorkerAttendanceController = new WorkerViewWorkerAttendanceController(this.employee);

	   loader.setController(workerViewWorkerAttendanceController);

	   loadAfterNavigate(loader);
   }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
			
		renderMyAttendancePage();
		
		myAttendanceTab.setOnMouseClicked(e -> {
			renderMyAttendancePage();
			
		});
		
		requestTab.setOnMouseClicked(e -> {
			
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/main/java/screen/EmployeeRequestScreen.fxml"));    	
			
		    WorkerRequestController workerRequestController = new WorkerRequestController("CN001");
				
		    loader1.setController(workerRequestController);
				
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
