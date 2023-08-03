package main.java.worker.attendance.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import attendanceLog.AttendanceDB;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import main.java.officer.attendanceDetail.AttendanceDetail;
import main.java.utils.DataColumn;
import main.java.utils.RowData;
import main.java.worker.attendance.WorkerAttendanceByDay;
import main.java.worker.attendance.WorkerAttendanceByMonth;
import main.java.worker.attendanceDetail.WorkerViewAttendanceDetailController;

public class WorkerViewWorkerAttendanceTableController implements Initializable{

	WorkerAttendanceByMonth workerAttendanceByMonth;
	
	int dayNum;
	
	private String workerId;
	
	public WorkerViewWorkerAttendanceTableController(String workerId, WorkerAttendanceByMonth workerAttendanceByMonth) {
		
		this.workerId = workerId;
		this.workerAttendanceByMonth = workerAttendanceByMonth;
	}
	
    private Stack<javafx.scene.Node> removedPanes = new Stack<>();

	@FXML
	private TableView<RowData> workerAttendanceTable;
	
	@FXML
	private DatePicker datePicker;
	
	@FXML
	private StackPane contentArea;
	
    @FXML
    private Button returnToMonthBtn;
	
	AttendanceDB attendanceDB = AttendanceDB.GetInstance();
	
	private void showAttendance () {
		
		workerAttendanceTable.getColumns().clear();
        
        List<WorkerAttendanceByDay> workerAttendanceByDays = this.workerAttendanceByMonth.getWorkerAttendanceByDays();
        
        this.dayNum = workerAttendanceByDays.size();

        List<DataColumn<String>> data = new ArrayList<>();
        
        for (int i = 0; i < dayNum; i ++) {
     	   
     	   WorkerAttendanceByDay dayAttendance = workerAttendanceByDays.get(i);
     	   
     	   String[] colData = new String[4];
     	   
     	   for (int rowIndex = 0; rowIndex < 4; rowIndex ++) {
     		   
     		   if (rowIndex == 0) {
     			   colData[0] = Double.toString(dayAttendance.getShift1());
     			   
     		   } else if (rowIndex == 1) {
     			   colData[1] = Double.toString(dayAttendance.getShift2());
     			   
     		   } else if (rowIndex == 2) {
     			   colData[2]= Double.toString(dayAttendance.getShift3());
     			   
     		   }
     	   }
     	   
     	   DataColumn<String> dataColumn = new DataColumn<>(colData);
     	   data.add(dataColumn);
        }
        
        List<List<String>> workerAttendanceByProperties = new ArrayList<>();
        
        for (int i = 0; i < 3; i ++) {	
     	   
     	   List<String> workerAttendanceByProperty = new ArrayList<String>();
     	   
     	   final int i1 = i;
     	   
     	  if (i1 == 0) {
     		  workerAttendanceByProperty.add("Shift 1");
     		  
    	   } else if (i1 == 1) {
    		   workerAttendanceByProperty.add("Shift 2");
    		   
    	   } else if (i1 == 2) {
    		   workerAttendanceByProperty.add("Shift 3");
    		   
    	   } 
     	   
     	   data.forEach(day -> {
     		   
     		   workerAttendanceByProperty.add(day.getData(i1));
     	   });
     	   
     	   for (int j = this.dayNum; j <= 31; j ++) {
     		   
     		   workerAttendanceByProperty.add("");
            }
     	   workerAttendanceByProperties.add(workerAttendanceByProperty);
        }
        	           
 	   TableColumn<RowData, String> firstColumn = new TableColumn<>("Date");

 	   firstColumn.setCellValueFactory(new PropertyValueFactory<>("column" + 0));
 	   
 	   firstColumn.setPrefWidth(100);
 	   
 	   firstColumn.getStyleClass().add("dateColumn");

 	  workerAttendanceTable.getColumns().add(firstColumn);
        
        
        for (int i  = 1; i <= 31; i ++) {
     	   
     	   TableColumn<RowData, String> column = new TableColumn<>(this.workerAttendanceByMonth.getMonthYear() + "-" + i );
     	   
            column.setCellValueFactory(new PropertyValueFactory<>("column" + i));
            
            workerAttendanceTable.getColumns().add(column);
        }

        ObservableList<RowData> rowDataList = FXCollections.observableArrayList();
        
        for (int i = 0; i < 3; i++) {
     	   
     		RowData eachRowData = new RowData(workerAttendanceByProperties.get(i));    
     		
     	   rowDataList.add(eachRowData);
        }
                  
        workerAttendanceTable.setItems(rowDataList);
	}
	
    private void returnToPreviousPane() {
        if (!removedPanes.isEmpty()) {
        	
            javafx.scene.Node previousPane = removedPanes.pop();
            
            contentArea.getChildren().add(previousPane);
        }
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		workerAttendanceTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); 
		
		returnToMonthBtn.setVisible(false);
		
        showAttendance();
        
        returnToMonthBtn.setOnMouseClicked(e -> returnToPreviousPane());
        
        workerAttendanceTable.setOnMouseClicked(event -> {
     	   
     	   String dayInMonth = new String();
     	   
     	    if (event.getTarget() instanceof TableColumnHeader) {
     	    	
     	        TableColumnHeader columnHeader = (TableColumnHeader) event.getTarget();
     	        
     	        @SuppressWarnings({ "unchecked" })
 				TableColumn<RowData, String> clickedColumn = (TableColumn<RowData, String>) columnHeader.getTableColumn();
     	        
     	        Callback<CellDataFeatures<RowData, String>, ObservableValue<String>> cellValueFactory = clickedColumn.getCellValueFactory();

     	        if (cellValueFactory instanceof PropertyValueFactory) {
     	        	
     	            PropertyValueFactory<?, ?> propertyValueFactory = (PropertyValueFactory<RowData, String>) cellValueFactory;
     	            
 					String propertyName = propertyValueFactory.getProperty();
 					
 					dayInMonth = propertyName.replaceFirst("column", "");
 					
     	            event.consume(); 
     	        }
     	        
     	        String dayMonthYear = new String();
     	        
     	        if (Integer.parseInt(dayInMonth) < 10) {    	        	
     	        	dayMonthYear = this.workerAttendanceByMonth.getMonthYear() + "-0" + dayInMonth;
     	        	
     	        } else {
     	        	dayMonthYear = this.workerAttendanceByMonth.getMonthYear() + "-" + dayInMonth;
     	        }
     	        
     	        System.out.println(dayMonthYear);
     	        
     	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewEmployeeAttendanceDetailTable.fxml"));    	
     			
     	        List<AttendanceDetail> attendanceDetails = attendanceDB.getWorkerAttendanceDetail(this.workerId, dayMonthYear);
     	        
     			WorkerViewAttendanceDetailController workerViewAttendanceDetailController = new WorkerViewAttendanceDetailController(dayMonthYear, attendanceDetails);
     			
     			loader.setController(workerViewAttendanceDetailController);
     			
     		try {
     			
     			Parent fxml = loader.load();

    	        javafx.scene.Node currentPane = contentArea.getChildren().get(contentArea.getChildren().size() - 1);
    			
    			removedPanes.push(currentPane);
     			
     			contentArea.getChildren().removeAll();
     			
     			contentArea.getChildren().setAll(fxml);
     			
    			returnToMonthBtn.setVisible(true);
     			
     		} catch (IOException e1) {
     			
     			e1.printStackTrace();
     		}
     	        
     	        event.consume(); 
     	    }
     	});
        
	}
}
