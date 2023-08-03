package main.java.hrAdmin.unitReport.view;

import java.util.List;

import main.java.workerLeader.unitAttendanceReport.WorkerUnitAttendanceReport;
import main.java.workerLeader.unitAttendanceReport.view.WorkerLeaderViewUnitAttendanceTableController;

public class AdminViewWorkerUnitAttendanceTableController extends WorkerLeaderViewUnitAttendanceTableController{

	public AdminViewWorkerUnitAttendanceTableController(String unitId, List<WorkerUnitAttendanceReport> workerUnitAttendanceReports) {
		super(unitId, workerUnitAttendanceReports);
	}
}
