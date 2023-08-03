package main.java.officer.request;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import attendanceLog.AttendanceDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OfficerRequestController implements Initializable{
	
	private String officerId;

	AttendanceDB attendanceDB = AttendanceDB.GetInstance();

    @FXML
    private TextArea reason;

    @FXML
    private TextField wantToModifyAttribute;
    
    @FXML
    private DatePicker wantToModifyDate;

    @FXML
    private Button sendButton;
    
	public OfficerRequestController(String officerId) {
		this.officerId = officerId;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		sendButton.setOnMouseClicked(e -> {
			
		    LocalDate selectedDate = wantToModifyDate.getValue();
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    
	        String formattedDate = selectedDate.format(formatter);
	        
	        String wantToModify = wantToModifyAttribute.getText();
	        
	        String reasonText = reason.getText();
	        
	        String content = "I want to modify my attendance at date: " + formattedDate + " in attribute: " + wantToModify + ". Because: " + reasonText + "Thanks you very much !!"; 
	        
	        attendanceDB.sendNotification(this.officerId, "HRAdmin" , content);
		});
	}
}
