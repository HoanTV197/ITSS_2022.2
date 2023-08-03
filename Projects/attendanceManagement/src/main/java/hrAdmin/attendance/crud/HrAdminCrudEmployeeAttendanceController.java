package main.java.hrAdmin.attendance.crud;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import attendanceLog.AttendanceDB;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class HrAdminCrudEmployeeAttendanceController implements Initializable{

	AttendanceDB attendanceDB = AttendanceDB.GetInstance();
	
    @FXML
    private Button submitButton;

    @FXML
    private TextField modifyAttribute;

    @FXML
    private DatePicker modifyDate;

    @FXML
    private TextField employeeId;

    @FXML
    private TextField to;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		submitButton.setOnMouseClicked(e -> {
			
			String employeeIdValue = employeeId.getText();
			
			LocalDate selectedDate = modifyDate.getValue();
			    
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 
		     String formattedDate = selectedDate.format(formatter);
		     
		     String toValue = to.getText();
		     
		     String attributeValue = modifyAttribute.getText();
		     
		     attendanceDB.modifyAttendance(employeeIdValue, formattedDate, attributeValue, toValue);
		});
	}

}
