package main.java.workerLeader.unitAttendanceReport.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import attendanceLog.AttendanceDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.workerLeader.unitAttendanceReport.WorkerUnitAttendanceReport;

public class WorkerLeaderViewUnitAttendanceTableController implements Initializable{

	AttendanceDB attendanceDB = AttendanceDB.GetInstance();
	
	private List<WorkerUnitAttendanceReport> workerUnitAttendanceReports;

	private String unitId;
	
	public WorkerLeaderViewUnitAttendanceTableController(String unitId, List<WorkerUnitAttendanceReport> workerUnitAttendanceReports) {
		
		this.unitId = unitId;
		this.workerUnitAttendanceReports = workerUnitAttendanceReports;
	}
	
    @FXML
    private TableView<WorkerUnitAttendanceReport> reportTable;

    @FXML
    private DatePicker datePicker;
    
    @SuppressWarnings("unchecked")
	private void renderReportTable (String unitId, String monthYear) {
    		           
        TableColumn<WorkerUnitAttendanceReport, String> workerIdColumn = new TableColumn<>("Worker Id");
        workerIdColumn.setPrefWidth(200);
        
        TableColumn<WorkerUnitAttendanceReport, String> workerNameColumn = new TableColumn<>("Worker Name");
        workerNameColumn.setPrefWidth(300);
        
        TableColumn<WorkerUnitAttendanceReport, String> monthYearColumn = new TableColumn<>("Month");

        TableColumn<WorkerUnitAttendanceReport, String> totalWorkingHoursColumn = new TableColumn<>("Total working hours in month");

        TableColumn<WorkerUnitAttendanceReport, String> totalLateHoursColumn = new TableColumn<>("Total late hours in month");

        TableColumn<WorkerUnitAttendanceReport, String> totalEarlyLeaveHoursColumn = new TableColumn<>("Total early leave hours in month");


        // Set cell value factories to extract values from Person object properties
        workerIdColumn.setCellValueFactory(new PropertyValueFactory<>("workerId"));
        workerNameColumn.setCellValueFactory(new PropertyValueFactory<>("workerName"));
        monthYearColumn.setCellValueFactory(new PropertyValueFactory<>("monthYear"));
        totalWorkingHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalWorkingHoursInMonth"));
        totalLateHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalLateHoursInMonth"));
        totalEarlyLeaveHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalEarlyLeaveHoursInMonth"));
        
        reportTable.getColumns().addAll(workerIdColumn, workerNameColumn, monthYearColumn, totalWorkingHoursColumn,totalLateHoursColumn, totalEarlyLeaveHoursColumn );
 
        ObservableList<WorkerUnitAttendanceReport> data = FXCollections.observableArrayList(
     		   this.workerUnitAttendanceReports
        );

        reportTable.setItems(data);
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		reportTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); 
		
        renderReportTable(this.unitId, "2023-06");
		
		if (this.workerUnitAttendanceReports.size() > 0) {
			
			renderReportTable(this.unitId, this.workerUnitAttendanceReports.get(0).getMonthYear());
		}
	           
	             		
	}
	
}
