package main.java.hrAdmin.unitReport.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import main.java.officerLeader.unitAttendanceReport.OfficerUnitAttendanceReport;
import main.java.unit.Unit;
import main.java.workerLeader.unitAttendanceReport.WorkerUnitAttendanceReport;

public class AdminViewUnitAttendanceReportController implements Initializable{
	
		private String unitSearchValue;

	    public String getUnitSearchValue() {
			return unitSearchValue;
		}

		public void setUnitSearchValue(String unitSearchValue) {
			this.unitSearchValue = unitSearchValue;
		}

		@FXML
	    private Text invalidNoti;

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
	    private StackPane attendanceReportTable;

	    @FXML
	    private Label department;

	    @FXML
	    private Label leaderId;
	    
	    @FXML
	    private DatePicker datePicker;
	    
	    private boolean validateInputFormat(String input) {
	    	
	        String pattern = "^\\d{3}$";
	        Pattern regex = Pattern.compile(pattern);
	        Matcher matcher = regex.matcher(input);
	        return matcher.matches();
	    }
	    
	    private void showInfoForUnit(Unit unit) {
	    	unitId.setText(unit.getUnitId());
	    	unitName.setText(unit.getUnitName());
	    	department.setText(unit.getDepartment());
	    	leaderId.setText("001");
	    	memNum.setText("30");
	    }
	    
	    private void loadAfterSearch(FXMLLoader loader) {
	    	try {
		        Parent fxml = loader.load();
			
		        attendanceReportTable.getChildren().removeAll();
		        attendanceReportTable.getChildren().setAll(fxml);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    }
	    
	    HrSystemDB hrSystemDB = HrSystemDB.GetInstance();
	    AttendanceDB attendanceDB = AttendanceDB.GetInstance();
	    
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			searchBtn.setOnMouseClicked(e -> {
				
				setUnitSearchValue(unitSearchInput.getText());
				
				if (validateInputFormat(this.unitSearchValue)) { // Check seachInput valid with format "CNxxx" or "NVxxx";
					
					invalidNoti.setText("");
					
					Unit unit = hrSystemDB.getUnitInfoById(this.unitSearchValue);
					
					if (Integer.parseInt(this.unitSearchValue.substring(2, 3)) < 4) {
												
						if (unit != null) {
										
							showInfoForUnit(unit);
							
					    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewWorkerUnitAttendanceReportTable.fxml"));
					    	
					    	List<WorkerUnitAttendanceReport> workerUnitAttendanceReports = attendanceDB.getWorkerUnitAttendanceReport(this.unitSearchValue, "2023-06");

					    	AdminViewWorkerUnitAttendanceTableController adminViewWorkerAttendanceTableController =  new AdminViewWorkerUnitAttendanceTableController(this.unitSearchValue, workerUnitAttendanceReports);
					    	
							loader.setController(adminViewWorkerAttendanceTableController);
					    	
					    	loadAfterSearch(loader);
					    	
						} else {
							invalidNoti.setText("Not found employee");
						}
						
					} else {
						
						if (unit != null) {
							
							showInfoForUnit(unit);
							
					    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewOfficerUnitAttendanceReportTable.fxml"));
					    	
					    	List<OfficerUnitAttendanceReport> officerUnitAttendanceReports = attendanceDB.getOfficerUnitAttendanceReport(this.unitSearchValue, "2023-06");

					    	AdminViewOfficerUnitAttendanceTableController adminViewOfficerAttendanceTableController =  new AdminViewOfficerUnitAttendanceTableController(this.unitSearchValue,officerUnitAttendanceReports );
					    	
							loader.setController(adminViewOfficerAttendanceTableController);
					    	
					    	loadAfterSearch(loader);
					    	
						} else {
							invalidNoti.setText("Not found employee");
						}
						}
				} else {
					
					invalidNoti.setText("Invalid format");
				}
			});
		}

}
