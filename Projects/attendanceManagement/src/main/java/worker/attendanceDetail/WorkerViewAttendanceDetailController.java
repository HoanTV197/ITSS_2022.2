package main.java.worker.attendanceDetail;

import java.util.List;

import main.java.officer.attendanceDetail.AttendanceDetail;
import main.java.officer.attendanceDetail.OfficerViewAttendanceDetailController;

public class WorkerViewAttendanceDetailController extends OfficerViewAttendanceDetailController{

	public WorkerViewAttendanceDetailController(String dateMonthYear, List<AttendanceDetail> attendanceDetails) {
		
		super(dateMonthYear, attendanceDetails);
	}

}
