package main.java.officer.attendanceDetail;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import main.java.officer.attendance.view.OfficerViewOfficerAttendanceTableController;

public class OfficerViewAttendanceDetailController implements Initializable{
	
	private List<AttendanceDetail> attendanceDetails;
	
	private String date;
	
	public OfficerViewAttendanceDetailController(String date, List<AttendanceDetail> attendanceDetails) {
		
		this.date = date;
		this.attendanceDetails = attendanceDetails;
	}
	@FXML
	private Label dateValue;
	
    @FXML
    private Button returnToMonth;
   
    @FXML
    private TableView<AttendanceDetail> employeeAttendanceDetaillTable;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 	TableColumn<AttendanceDetail, String> timeColumn = new TableColumn<>("Time");
		 	timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

		 	timeColumn.setPrefWidth(350);
		 	
	        TableColumn<AttendanceDetail, String> timekeeperColumn = new TableColumn<>("Timekeeper");
	        timekeeperColumn.setCellValueFactory(new PropertyValueFactory<>("timekeeper"));
	        
	        timekeeperColumn.setPrefWidth(350);
	        
	        TableColumn<AttendanceDetail, String> typeColumn = new TableColumn<>("Type");
	        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
	        
	        typeColumn.setPrefWidth(330);
	        
    		dateValue.setText(this.date);  
	        
    		employeeAttendanceDetaillTable.getColumns().addAll(timeColumn, timekeeperColumn, typeColumn);
			        	        
	        ObservableList<AttendanceDetail> data = FXCollections.observableArrayList(this.attendanceDetails);
	        
	        employeeAttendanceDetaillTable.setItems(data);
	}
	
}
