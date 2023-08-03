package main.java.officer.attendance.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import attendanceLog.AttendanceDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import main.java.employee.Employee;
import main.java.officer.attendance.OfficerAttendanceByMonth;

public class OfficerViewOfficerAttendanceController implements Initializable{

	private Employee employee;
	private OfficerAttendanceByMonth  officerAttendanceByMonth;

	public OfficerViewOfficerAttendanceController(Employee employee) {
		super();
		this.employee = employee;
	}

	@FXML
    private Label gender;

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
    private DatePicker datePicker;
    
	AttendanceDB attendanceDB = new AttendanceDB();
	
	public OfficerAttendanceByMonth getOfficerAttendanceByMonth() {
		return officerAttendanceByMonth;
	}

	public void setOfficerAttendanceByMonth(OfficerAttendanceByMonth officerAttendanceByMonth) {
		this.officerAttendanceByMonth = officerAttendanceByMonth;
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
				
		if (this.employee != null) {
			
	        setOfficerAttendanceByMonth(attendanceDB.getOfficerAttendanceByMonth(this.employee.getEmployeeId(), "2023-06"));
			
			showEmployeePersonalInfo(this.employee);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewOfficerAttendanceTable.fxml"));   
			
			OfficerViewOfficerAttendanceTableController officerViewOfficerAttendanceTableController =  new OfficerViewOfficerAttendanceTableController(this.employee.getEmployeeId(), this.officerAttendanceByMonth);
			
			loader.setController(officerViewOfficerAttendanceTableController);
			
	    	loadAfterSelectDate(loader);
		}
		
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
	          	           
	           setOfficerAttendanceByMonth(attendanceDB.getOfficerAttendanceByMonth(this.employee.getEmployeeId(), monthYear));
	           
	           FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewOfficerAttendanceTable.fxml"));   
				
	           OfficerViewOfficerAttendanceTableController officerViewOfficerAttendanceTableController =  new OfficerViewOfficerAttendanceTableController(this.employee.getEmployeeId(), this.officerAttendanceByMonth);
				
			   loader.setController(officerViewOfficerAttendanceTableController);
				
		       loadAfterSelectDate(loader);
	             
		 });
	}

}
