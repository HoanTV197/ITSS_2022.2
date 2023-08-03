package main.java.login;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import hrSystem.HrSystemDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.java.employee.Employee;
import main.java.hrAdmin.dashboard.AdminDashboardController;
import main.java.officer.dashboard.OfficerDashboardController;
import main.java.officerLeader.dashboard.OfficerLeaderDashboardController;
import main.java.worker.dashboard.WorkerDashboardController;
import main.java.workerLeader.dashboard.WorkerLeaderDashboardController;

public class LoginController implements Initializable{
	
	protected HashMap<String, String> logInfo = new Credentials().getLoginInfo();
	
	HrSystemDB hrSystemDB = HrSystemDB.GetInstance();

    @FXML
    public TextField usernameField;

    @FXML
    public Button loginButton;

    @FXML
    public StackPane afterLoginContent;

    @FXML
    public PasswordField passwordField;
    
    @FXML
    private Text invalidNoti;

    
    public void loadAfterLogin(FXMLLoader loader) {
    	
    	try {
	        Parent fxml = loader.load();
			
			afterLoginContent.getChildren().removeAll();
			
			afterLoginContent.getChildren().setAll(fxml);
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

		loginButton.setOnMouseClicked(event -> {
			String userName = usernameField.getText();
			String password = passwordField.getText();

			if(logInfo.containsKey(userName)) {
				
				// successfully login with hradmin role
				
				if(logInfo.get(userName).equals(password) && userName.equals("admin")) {
					
					
			    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/AdminDashboardScreen.fxml"));    	
					
					AdminDashboardController hrAdminDashboardController = new AdminDashboardController();
					
					loader.setController(hrAdminDashboardController);
										
					loadAfterLogin(loader);
										
				} else if(logInfo.get(userName).equals(password) && userName.equals("workerLeader")){
					
					// successfully login with worker unit leader role

			    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/WorkerLeaderDashboardScreen.fxml"));    	
					
					WorkerLeaderDashboardController workerLeaderDashboardController = new WorkerLeaderDashboardController();
					
					loader.setController(workerLeaderDashboardController);
										
					loadAfterLogin(loader);
					
					
				} else if(logInfo.get(userName).equals(password) && userName.equals("worker")){
					
					// successfully login with worker role
					Employee employee = hrSystemDB.getWorkerPersonalInfoById("CN001");

			    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/WorkerDashboardScreen.fxml"));    	
					
					WorkerDashboardController workerDashboardController = new WorkerDashboardController(employee);
					
					loader.setController(workerDashboardController);
										
					loadAfterLogin(loader);
					
				} else if(logInfo.get(userName).equals(password) && userName.equals("officerLeader")){
					
					// successfully login with officer unit leader role
					
			    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/OfficerLeaderDashboardScreen.fxml"));    	
					
					OfficerLeaderDashboardController officerLeaderDashboardController= new OfficerLeaderDashboardController();
					
					loader.setController(officerLeaderDashboardController);
										
					loadAfterLogin(loader);
					
				} else if(logInfo.get(userName).equals(password) && userName.equals("officer")){
					
					Employee employee = hrSystemDB.getOfficerPersonalInfoById("NV001");
					
			    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/OfficerDashboardScreen.fxml"));    	
					
					OfficerDashboardController officerDashboardController= new OfficerDashboardController(employee);
					
					loader.setController(officerDashboardController);
										
					loadAfterLogin(loader);
					
				} else {
					
					invalidNoti.setText("The password is incorrect !!");
				}
				
			} else {
				
				invalidNoti.setText("The account is not in system !!");
				
			}
		});
	}
	    
    

}
