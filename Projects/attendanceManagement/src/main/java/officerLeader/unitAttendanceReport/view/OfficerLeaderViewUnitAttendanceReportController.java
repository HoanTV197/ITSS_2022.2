package main.java.officerLeader.unitAttendanceReport.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

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
import main.java.officerLeader.unitAttendanceReport.OfficerUnitAttendanceReport;
import main.java.unit.Unit;

public class OfficerLeaderViewUnitAttendanceReportController implements Initializable{
	
	HrSystemDB hrSystemDB = new HrSystemDB();
	AttendanceDB attendanceDB = new AttendanceDB();
	
	 @FXML
	 private TextField unitSearchInput;

	 @FXML
	 private Label memNum;

	 @FXML
	 private Label unitName;

	 @FXML
	 private Button searchBtn;

	 @FXML
	 private Label unitId;

	 @FXML
	 private Label department;

	 @FXML
	 private Label leaderId;
	 
	 @FXML
	 private Text invalidNoti;
	 
	 @FXML
	 private StackPane attendanceReportTable;
	 
	 @FXML
	 private DatePicker datePicker;
	 
	 private void showUnitInfo(Unit unit) {
	    	
		 	unitName.setText(unit.getUnitName());
			unitId.setText(unit.getUnitId());
			department.setText(unit.getDepartment());
			memNum.setText(unit.getMemNum() + "");
	 }
	 
	 private void loadAferSearch(FXMLLoader loader) {
		 try {
				Parent fxml = loader.load();
				
				attendanceReportTable.getChildren().removeAll();
				attendanceReportTable.getChildren().setAll(fxml);
				
		} catch (IOException e1) {
				e1.printStackTrace();
		}
	 }
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Unit foundUnit = hrSystemDB.getUnitInfoById("004");
				
		if (foundUnit != null) {
					
			showUnitInfo(foundUnit);
					
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewOfficerUnitAttendanceReportTable.fxml"));   
			
	    	List<OfficerUnitAttendanceReport> offierUnitAttendanceReport = attendanceDB.getOfficerUnitAttendanceReport("004", "2023-06");

		    OfficerLeaderViewUnitAttendanceTableController officerLeaderViewUnitAttendanceTableController = new OfficerLeaderViewUnitAttendanceTableController("004", offierUnitAttendanceReport);
					
		    loader.setController(officerLeaderViewUnitAttendanceTableController);
					
			loadAferSearch(loader);
				
		}
		
		datePicker.setOnAction(event -> {
			
			LocalDate selectedDate = datePicker.getValue();
	           
	           int year = selectedDate.getYear();
	           
	           int monthValue = selectedDate.getMonthValue();
	           
	           String monthYear= new String();
	           
	           if (monthValue < 10) {
	        	   monthYear = "" + year + "-0" + monthValue; 
	        	   
	           } else {
	        	   monthYear = "" + year + "-" + monthValue; 
	           }
	           
	           FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewOfficerUnitAttendanceReportTable.fxml"));   
			
		       List<OfficerUnitAttendanceReport> offierUnitAttendanceReport = attendanceDB.getOfficerUnitAttendanceReport("004", monthYear);

			   OfficerLeaderViewUnitAttendanceTableController officerLeaderViewUnitAttendanceTableController = new OfficerLeaderViewUnitAttendanceTableController("004", offierUnitAttendanceReport);
						
			   loader.setController(officerLeaderViewUnitAttendanceTableController);
						
			   loadAferSearch(loader);
			});		 
		}
}
