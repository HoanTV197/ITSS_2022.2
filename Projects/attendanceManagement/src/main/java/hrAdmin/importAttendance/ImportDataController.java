package main.java.hrAdmin.importAttendance;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;

import attendanceLog.AttendanceDB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import main.java.worker.attendance.WorkerAttendanceByDay;

public class ImportDataController implements Initializable{
	
	private String filePath;
	
    private WorkerAttendanceByDay workerAttendanceByDay = new WorkerAttendanceByDay();
	
    @FXML
    private TextField fileName;

    @FXML
    private Button chooseFileBtn;
    
    @FXML
    private Button importBtn;

    public WorkerAttendanceByDay getWorkerAttendanceByDay() {
    	
    	return workerAttendanceByDay;
    }
    
    public void setCongNhan(WorkerAttendanceByDay workerAttendanceByDay) {
    	
    	this.workerAttendanceByDay = workerAttendanceByDay;
    }

    FileChooser fileChooser = new FileChooser();

    AttendanceDB attendanceDB = new AttendanceDB();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		chooseFileBtn.setOnMouseClicked(e -> {
			
			
			File file = fileChooser.showOpenDialog(null);

			this.filePath = file.getAbsolutePath();
			
			fileName.setText(filePath);
	       
		});
		
		importBtn.setOnMouseClicked(e -> {

	    	ReadExcel a = new ReadExcel();
	    	
	    	int numRow = a.countRows("WorkerInfor", filePath);
	    	
	    	for (int i = 1; i <= numRow-1; i++) {
	    		
	    		workerAttendanceByDay.setWorker_log_id(a.ReadExcelColumn("WorkerInfor", i, 0, filePath));
	    		workerAttendanceByDay.setWorker_id(a.ReadExcelColumn("WorkerInfor", i, 1, filePath));
	    		workerAttendanceByDay.setDay(a.ReadExcelColumn("WorkerInfor", i, 2, filePath));
	    		workerAttendanceByDay.setShift1(Double.parseDouble(a.ReadExcelColumn("WorkerInfor", i, 3, filePath)));
	    		workerAttendanceByDay.setShift2(Double.parseDouble(a.ReadExcelColumn("WorkerInfor", i, 4, filePath)));
	    		workerAttendanceByDay.setShift3(Double.parseDouble(a.ReadExcelColumn("WorkerInfor", i, 5, filePath)));
	    		
	    		try {
	    			
	    			int tontai = attendanceDB.checkIdExists(workerAttendanceByDay.getWorker_log_id());
	    			
	    			if (tontai == 1) {
	    				int result = attendanceDB.importWorkerAttendance(workerAttendanceByDay.getWorker_log_id(), workerAttendanceByDay.getWorker_id(), workerAttendanceByDay.getDay(), workerAttendanceByDay.getShift1(), workerAttendanceByDay.getShift2(), workerAttendanceByDay.getShift3());
	    				
	        			if (result == 1) {
	        				System.out.println("Thanh cong");
	        			}
	        			else System.out.println("Khong thanh cong");
	        			
	    			} else {	
	    				System.out.print("run alert");
	    				
	    				WarningDialog.createDialog(
	    						Alert.AlertType.WARNING,
	    	                    "Đồng chí giữ bình tĩnh",
	    	                    "", "Thông tin bị trùng mất rồi!" + " Trùng mã: " + workerAttendanceByDay.getWorker_log_id());
	    			}	    			
	    		} catch (SQLException error) {
	    			error.printStackTrace();
	    		}
	    	}
		});
		
	}
}
