package main.java.officer.attendance.view;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import main.java.officer.attendance.OfficerAttendanceByDay;
import main.java.officer.attendance.OfficerAttendanceByMonth;
import main.java.officer.attendanceDetail.AttendanceDetail;
import main.java.officer.attendanceDetail.OfficerViewAttendanceDetailController;
import main.java.utils.DataColumn;
import main.java.utils.RowData;


public class OfficerViewOfficerAttendanceTableController implements Initializable {
	
	private String officerId;
	private OfficerAttendanceByMonth officerAttendanceByMonth;
		
    @FXML
    TableView<RowData> officerAttendanceTable = new TableView<>();
    
    @FXML
    private StackPane contentArea;
    
    @FXML
    private Button returnToMonthBtn;
    
    private Stack<javafx.scene.Node> removedPanes = new Stack<>();
    
	AttendanceDB attendanceDB = AttendanceDB.GetInstance();

    public OfficerViewOfficerAttendanceTableController(String officerId, OfficerAttendanceByMonth officerAttendanceByMonth) {
    	
    	this.officerId = officerId;
    	this.officerAttendanceByMonth = officerAttendanceByMonth;
    }
        
    private void showAttendance() {
    	  
    	officerAttendanceTable.getColumns().clear();
                                   
        List<OfficerAttendanceByDay> officerAttendanceByDays = this.officerAttendanceByMonth.getOfficerAttendanceByDays();
        
        int dayNum = officerAttendanceByDays.size();
        
        List<DataColumn<String>> data = new ArrayList<>();
        
        for (int i = 0; i < dayNum; i ++) {
     	   
     	   OfficerAttendanceByDay dayAttendance = officerAttendanceByDays.get(i);
     	   
     	   String[] colData = new String[4];
     	   
     	   for (int rowIndex = 0; rowIndex < 4; rowIndex ++) {
     		   
     		   if (rowIndex == 0) {
     			   
     			   colData[0] = Boolean.toString(dayAttendance.getMorningSession());
     			   
     		   } else if (rowIndex == 1) {
     			   colData[1] = Boolean.toString(dayAttendance.getAfternoonSession());
     			   
     		   } else if (rowIndex == 2) {
     			   colData[2]= Double.toString(dayAttendance.getHoursLate());
     			   
     		   } else if (rowIndex == 3) {
     			   colData[3] = Double.toString(dayAttendance.getHoursEarlyLeave());
     		   }
     	   }
     	   
     	   DataColumn<String> dataColumn = new DataColumn<>(colData);
     	   data.add(dataColumn);
        }
          
        List<List<String>> officerAttendanceByProperties = new ArrayList<>();
        
        for (int i = 0; i < 4; i ++) {	
     	   
     	   List<String> officerAttendanceByProperty = new ArrayList<String>();
     	   
     	   final int i1 = i;
     	   
     	   if (i1 == 0) {
     		   officerAttendanceByProperty.add("morning session");
     		   
     	   } else if (i1 == 1) {
     		   officerAttendanceByProperty.add("afternoon session");
     		   
     	   } else if (i1 == 2) {
     		   officerAttendanceByProperty.add("hours late");
     		   
     	   } else if (i1 == 3) {
     		   officerAttendanceByProperty.add("hours early leave");
     	   }
     	   
     	   data.forEach(day -> {
     		   
     		   officerAttendanceByProperty.add(day.getData(i1));
     	   });
     	   
     	   for (int j = dayNum; j < 31; j ++) {
     		   
     		   officerAttendanceByProperty.add("");
            }
     	   officerAttendanceByProperties.add(officerAttendanceByProperty);
        }
               
 	   TableColumn<RowData, String> firstColumn = new TableColumn<>("Date");
 	   
 	   firstColumn.getStyleClass().add("dateColumn");

 	   firstColumn.setCellValueFactory(new PropertyValueFactory<>("column" + 0));

        officerAttendanceTable.getColumns().add(firstColumn);

        
        for (int i  = 1; i <= 31; i ++) {
     	   
     	   TableColumn<RowData, String> column = new TableColumn<>(this.officerAttendanceByMonth.getMonthYear() + "-" + i );
     	   
            column.setCellValueFactory(new PropertyValueFactory<>("column" + i));
            
            
            officerAttendanceTable.getColumns().add(column);
        }

        ObservableList<RowData> rowDataList = FXCollections.observableArrayList();
        
        for (int i = 0; i < 4; i++) {
     	   
     		RowData eachRowData = new RowData(officerAttendanceByProperties.get(i));  
     		
     	   rowDataList.add(eachRowData);
        }
                  
        officerAttendanceTable.setItems(rowDataList);
    }
    private void returnToPreviousPane() {
        if (!removedPanes.isEmpty()) {
        	
            javafx.scene.Node previousPane = removedPanes.pop();
            
            contentArea.getChildren().add(previousPane);
        }
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	   
	   officerAttendanceTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); 

	   returnToMonthBtn.setVisible(false);
	   
       showAttendance();
       
       returnToMonthBtn.setOnMouseClicked(e -> returnToPreviousPane());
       
       officerAttendanceTable.setOnMouseClicked(event -> {
    	   
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
    	        	
    	        	dayMonthYear = this.officerAttendanceByMonth.getMonthYear() + "-0" + dayInMonth;
    	        	
    	        } else {
    	        	dayMonthYear = this.officerAttendanceByMonth.getMonthYear() + "-" + dayInMonth;
    	        }
    	        
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/screen/ViewEmployeeAttendanceDetailTable.fxml"));    	
    			
    	        List<AttendanceDetail> attendanceDetails = attendanceDB.getOfficerAttendanceDetail(this.officerId, dayMonthYear);
    	        
    			OfficerViewAttendanceDetailController officerViewAttendanceDetailController = new OfficerViewAttendanceDetailController(dayMonthYear, attendanceDetails);
    			
    			loader.setController(officerViewAttendanceDetailController);
    			
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
