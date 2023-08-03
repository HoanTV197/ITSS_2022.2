package main.java.workerLeader.unitAttendanceReport.view;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import main.java.unit.Unit;
import main.java.workerLeader.unitAttendanceReport.WorkerUnitAttendanceReport;

public class WorkerLeaderViewUnitAttendanceReportController implements Initializable{
	
		HrSystemDB hrSystemDB = HrSystemDB.GetInstance();
		AttendanceDB attendanceDB = AttendanceDB.GetInstance();
		
	  	@FXML
	    private Text invalidNoti;

	    @FXML
	    private Label memNum;

	    @FXML
	    private Label unitName;

	    @FXML
	    private Label unitId;

	    @FXML
	    private StackPane attendanceReportTable;

	    @FXML
	    private Label department;

	    @FXML
	    private Label leaderId;
	    
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
									
			Unit foundUnit = hrSystemDB.getUnitInfoById("001");
					
			if (foundUnit != null) {
						
				showUnitInfo(foundUnit);
						
				 FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewWorkerUnitAttendanceReportTable.fxml"));    	
				 
			     List<WorkerUnitAttendanceReport> workerUnitAttendanceReports = attendanceDB.getWorkerUnitAttendanceReport("001", "2023-06");
				 
				 WorkerLeaderViewUnitAttendanceTableController workerLeaderViewUnitAttendanceTableController = new WorkerLeaderViewUnitAttendanceTableController("001", workerUnitAttendanceReports);
						
			     loader.setController(workerLeaderViewUnitAttendanceTableController);
						
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
		           
		           FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewWorkerUnitAttendanceReportTable.fxml"));   
				
			       List<WorkerUnitAttendanceReport> workerUnitAttendanceReport = attendanceDB.getWorkerUnitAttendanceReport("001", monthYear);

				   WorkerLeaderViewUnitAttendanceTableController workerLeaderViewUnitAttendanceTableController = new WorkerLeaderViewUnitAttendanceTableController("004", workerUnitAttendanceReport);
							
				   loader.setController(workerLeaderViewUnitAttendanceTableController);
							
				   loadAferSearch(loader);
				});	
		}
}
