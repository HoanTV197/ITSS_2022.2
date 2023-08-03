package main.java.hrAdmin.attendance.view;

import main.java.worker.attendance.WorkerAttendanceByMonth;
import main.java.workerLeader.workerAttendance.view.WorkerLeaderViewWorkerAttendanceTableController;

public class AdminViewWorkerAttendanceTableController extends WorkerLeaderViewWorkerAttendanceTableController{
	
	public AdminViewWorkerAttendanceTableController(String workerId, WorkerAttendanceByMonth workerAttendanceByMonth) {
		super(workerId, workerAttendanceByMonth);
	}
}
