package main.java.workerLeader.workerAttendance.view;

import java.io.IOException;
import java.net.URL;
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
import main.java.worker.attendance.WorkerAttendanceByMonth;

public class WorkerLeaderViewWorkerAttendanceController implements Initializable{
	
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
    
    HrSystemDB hrSystemDB = HrSystemDB.GetInstance();
	AttendanceDB attendanceDB = AttendanceDB.GetInstance();
    
    private static boolean checkFormatOfWorkerId(String input) {
        String regex = "(CN)\\d{3}";
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		searchBtn.setOnMouseClicked(e -> {
			
			setSearchValue(searchInput.getText());
			
			if (checkFormatOfWorkerId(this.searchValue)) { 
				
				invalidFormatNoti.setText("");
				
				Employee foundEmployee;
				
				foundEmployee = hrSystemDB.getWorkerPersonalInfoById(this.searchValue);
				
				if (foundEmployee != null) {
					
					showEmployeePersonalInfo(foundEmployee);
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewWorkerAttendanceTable.fxml"));
			    	
			    	WorkerAttendanceByMonth workerAttendanceByMonth = attendanceDB.getWorkerAttendanceByMonth(this.searchValue, "2023-06");

			    	WorkerLeaderViewWorkerAttendanceTableController workerLeaderViewWorkerAttendanceTableController =  new WorkerLeaderViewWorkerAttendanceTableController(this.searchValue, workerAttendanceByMonth);
			    	
					loader.setController(workerLeaderViewWorkerAttendanceTableController);
			    	
			    	loadAfterSelectDate(loader);
					
				} else {
					invalidFormatNoti.setText("Not found employee");
				}
			} else {
				invalidFormatNoti.setText("Invalid format or you dont't have privilege to see this employee");
			}
		});
	}
}
