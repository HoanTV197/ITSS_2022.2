package main.java.officerLeader.unitAttendanceReport.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import attendanceLog.AttendanceDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.officerLeader.unitAttendanceReport.OfficerUnitAttendanceReport;

public class OfficerLeaderViewUnitAttendanceTableController implements Initializable{

		AttendanceDB attendanceDB = new AttendanceDB();
		
		private List<OfficerUnitAttendanceReport> officerUnitAttendanceReports = new ArrayList<OfficerUnitAttendanceReport>();
		
		private String unitId;
		
		public OfficerLeaderViewUnitAttendanceTableController(String unitId, List<OfficerUnitAttendanceReport> officerUnitAttendanceReports) {
			this.unitId = unitId;
			this.officerUnitAttendanceReports = officerUnitAttendanceReports;
		}
		
		public String getUnitId() {
			return this.unitId;
		}
		
		public void setUnitId(String unitId) {
			
			this.unitId = unitId;
		}
		
	   public List<OfficerUnitAttendanceReport> getOfficerUnitAttendanceReport() {
		   
			return officerUnitAttendanceReports;
		}

		public void setOfficerUnitAttendanceReport(List<OfficerUnitAttendanceReport> officerUnitAttendanceReports) {
			
			this.officerUnitAttendanceReports = officerUnitAttendanceReports;
		}

		@FXML
	    private TableView<OfficerUnitAttendanceReport> reportTable;
	    
	    @FXML
	    private Button importTab;
	    
	    @FXML
	    private Button exportButton;
	    
	    public void export(TableView<OfficerUnitAttendanceReport> tableView){

	        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
	        HSSFSheet hssfSheet=  hssfWorkbook.createSheet("Sheet1");
	        HSSFRow firstRow= hssfSheet.createRow(0);

	        ///set titles of columns
	        for (int i=0; i<tableView.getColumns().size();i++){

	            firstRow.createCell(i).setCellValue(tableView.getColumns().get(i).getText());
	        }

	        for (int row=0; row<tableView.getItems().size();row++){

	            HSSFRow hssfRow= hssfSheet.createRow(row+1);

	            for (int col=0; col<tableView.getColumns().size(); col++){

	                Object celValue = tableView.getColumns().get(col).getCellObservableValue(row).getValue();

	                try {
	                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
	                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
	                    }
	                } catch (  NumberFormatException e ){

	                    hssfRow.createCell(col).setCellValue(celValue.toString());
	                }

	            }

	        }

	        //save excel file and close the workbook
	        try {
	        	FileOutputStream fileOut = new FileOutputStream("WorkBook.xls");
	            hssfWorkbook.write(fileOut);

	            fileOut.close();
	            hssfWorkbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    private void renderReportTable(String unitId, String monthYear) {
	    		           	           
	           TableColumn<OfficerUnitAttendanceReport, String> column1 = new TableColumn<>("Officer Id");
	           
	           column1.setPrefWidth(200);
	        	   
	           column1.setCellValueFactory(new PropertyValueFactory<>("officerId"));
	               
	           reportTable.getColumns().add(column1);
	           
	           TableColumn<OfficerUnitAttendanceReport, String> column2 = new TableColumn<>("Officer Name");
	           
	           column2.setPrefWidth(200);
       	   
	           column2.setCellValueFactory(new PropertyValueFactory<>("officerName"));
	               
	           reportTable.getColumns().add(column2);
	           
	           TableColumn<OfficerUnitAttendanceReport, String> column3 = new TableColumn<>("Month");
	           
	           column3.setPrefWidth(200);
       	   
	           column3.setCellValueFactory(new PropertyValueFactory<>("monthYear"));
	               
	           reportTable.getColumns().add(column3);
	           
	           TableColumn<OfficerUnitAttendanceReport, String> column4 = new TableColumn<>("Total working sessions");
	           
	           column4.setPrefWidth(200);
       	   
	           column4.setCellValueFactory(new PropertyValueFactory<>("totalWorkingSessions"));
	           
	           TableColumn<OfficerUnitAttendanceReport, String> column5 = new TableColumn<>("Total late hours");
	           
	           column5.setPrefWidth(200);
       	   
	           column5.setCellValueFactory(new PropertyValueFactory<>("totalLateHours"));	
	               
	           reportTable.getColumns().add(column5);
	           
	           TableColumn<OfficerUnitAttendanceReport, String> column6 = new TableColumn<>("Total early leave hours");
	           
	           column6.setPrefWidth(200);
       	   
	           column6.setCellValueFactory(new PropertyValueFactory<>("totalEarlyHours"));	
	               
	           reportTable.getColumns().add(column6);
	           
	           ObservableList<OfficerUnitAttendanceReport> rowDataList = FXCollections.observableList(this.officerUnitAttendanceReports);
	           
	           reportTable.setItems(rowDataList);
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			reportTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); 
			
	        renderReportTable(this.unitId, "2023-06");
			
			exportButton.setOnMouseClicked(e -> {
				export(reportTable);
			});
		           
		    if (this.officerUnitAttendanceReports.size() > 0) {
		    	 renderReportTable(this.unitId, this.officerUnitAttendanceReports.get(0).getMonthYear());		    	  
		    }
	}
}
