package main.java.hrAdmin.attendance.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import attendanceLog.AttendanceDB;
import hrSystem.HrSystemDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.java.employee.Employee;
import main.java.officer.attendance.OfficerAttendanceByMonth;
import main.java.officer.attendance.view.OfficerViewOfficerAttendanceTableController;
import main.java.worker.attendance.WorkerAttendanceByMonth;
import main.java.worker.attendance.view.WorkerViewWorkerAttendanceTableController;

public class AdminViewEmployeeAttendanceController implements Initializable{
	
		private String searchValue;
					
	    @FXML
	    private TextField searchInput;

	    @FXML
	    private Label gender;

	    @FXML
	    private Button searchBtn;

	    @FXML
	    private Label fullName;

	    @FXML
	    private Label unitId;

	    @FXML
	    private Label employeeId;

	    @FXML
	    private Label dateOfBirth;

	    @FXML
	    private StackPane attendanceTable;

	    @FXML
	    private Text invalidFormatNoti;
	    
	    @FXML
	    private DatePicker datePicker;
	    
	    public String getSearchValue() {
	    	
	    	return this.searchValue;
	    }
	    
	    public void setSearchValue(String searchValue) {
	    	
	    	this.searchValue = searchValue;
	    }
	    
	    public String getNotificationText() {
	    	
	    	return invalidFormatNoti.getText();
	    }
	    
		HrSystemDB hrSystemDB = HrSystemDB.GetInstance();
		AttendanceDB attendanceDB = AttendanceDB.GetInstance();
	    	    
	    private static boolean checkFormat(String input) {
	        String regex = "(CN|NV)\\d{3}";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(input);
	        
	        return matcher.matches();
	    }
	    
	    private void showEmployeePersonalInfo(Employee employee) {
	    	
	    	fullName.setText(employee.getName());
			dateOfBirth.setText(employee.getDateOfBirth());
			gender.setText(employee.getGender());
			employeeId.setText(employee.getEmployeeId());
			unitId.setText(employee.getUnitId());
	    }
	    
	    private void loadAfterSelectDate(FXMLLoader loader) {
	    	
	    	try {
		        Parent fxml = loader.load();
			
				attendanceTable.getChildren().removeAll();
				attendanceTable.getChildren().setAll(fxml);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    }
	    
	    private void renderInfomation() {
	    	
			if (checkFormat(this.searchValue)) { // Check seachInput valid with format "CNxxx" or "NVxxx";
				
				invalidFormatNoti.setText("");
				
				Employee foundEmployee;
				
				if (this.searchValue.substring(0, 2).equals("CN")) {
					
					foundEmployee = hrSystemDB.getWorkerPersonalInfoById(this.searchValue);
					
					if (foundEmployee != null) {
									
						showEmployeePersonalInfo(foundEmployee);
						
				    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewWorkerAttendanceTable.fxml"));
				    	
				    	WorkerAttendanceByMonth workerAttendanceByMonth = attendanceDB.getWorkerAttendanceByMonth(this.searchValue, "2023-06");

				    	AdminViewWorkerAttendanceTableController adminViewWorkerAttendanceTableController =  new AdminViewWorkerAttendanceTableController(this.searchValue, workerAttendanceByMonth);
				    	
						loader.setController(adminViewWorkerAttendanceTableController);
				    	
				    	loadAfterSelectDate(loader);
				    	
					} else {
						invalidFormatNoti.setText("Not found employee");
					}
					
				} else {
					
						foundEmployee = hrSystemDB.getOfficerPersonalInfoById(this.searchValue);
					
						if (foundEmployee != null) {
							showEmployeePersonalInfo(foundEmployee);
							
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewOfficerAttendanceTable.fxml"));
							
					    	OfficerAttendanceByMonth officerAttendanceByMonth = attendanceDB.getOfficerAttendanceByMonth(this.searchValue, "2023-06");
							
							AdminViewOfficerAttendanceTableController adminViewOfficerAttendanceTableController =  new AdminViewOfficerAttendanceTableController(this.searchValue, officerAttendanceByMonth);
							
							loader.setController(adminViewOfficerAttendanceTableController);
							
					    	loadAfterSelectDate(loader);
						} else {
							invalidFormatNoti.setText("Not found employee");
						}
					}
			} else {
				invalidFormatNoti.setText("Invalid format");
			}
	    }
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
						
			searchBtn.setOnMouseClicked(e -> {
												
				setSearchValue(searchInput.getText());
				
				renderInfomation();
			});
			
			 datePicker.setOnAction(event -> {
		   		   
		           LocalDate selectedDate = datePicker.getValue();
		           
		           int year = selectedDate.getYear();
		           
		           int monthValue = selectedDate.getMonthValue();
		           
		           String monthYear = new String();
		           
		           if (monthValue < 10) {
		        	   
		        	   monthYear = "" + year + "-0" + monthValue;
		        	   
		           } else {
		        	   monthYear = "" + year + "-" + monthValue; 
		           }
		          	           
		           if (this.searchValue.substring(0, 2).equals("CN")) {
		        	   		        	   
		        	   WorkerAttendanceByMonth  workerAttendanceByMonth = attendanceDB.getWorkerAttendanceByMonth(this.searchValue, monthYear);
		        	   
			           FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewWorkerAttendanceTable.fxml"));   
						
			           WorkerViewWorkerAttendanceTableController workerViewOfficerAttendanceTableController =  new WorkerViewWorkerAttendanceTableController(this.searchValue, workerAttendanceByMonth);
						
					   loader.setController(workerViewOfficerAttendanceTableController);
						
				       loadAfterSelectDate(loader);
		        	   
		           } else {
		        	   
		        	   OfficerAttendanceByMonth  officerAttendanceByMonth = attendanceDB.getOfficerAttendanceByMonth(this.searchValue, monthYear);
		        	   
			           FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewOfficerAttendanceTable.fxml"));   
						
			           OfficerViewOfficerAttendanceTableController officerViewOfficerAttendanceTableController =  new OfficerViewOfficerAttendanceTableController(this.searchValue, officerAttendanceByMonth);
						
					   loader.setController(officerViewOfficerAttendanceTableController);
						
				       loadAfterSelectDate(loader);
		           }
		                
			 });
			
		}

}
