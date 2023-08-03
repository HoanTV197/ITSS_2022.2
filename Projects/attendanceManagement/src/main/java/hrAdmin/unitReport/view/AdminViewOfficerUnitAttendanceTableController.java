package main.java.hrAdmin.unitReport.view;

import main.java.officerLeader.unitAttendanceReport.view.OfficerLeaderViewUnitAttendanceTableController;

import java.util.List;

import main.java.officerLeader.unitAttendanceReport.OfficerUnitAttendanceReport;

public class AdminViewOfficerUnitAttendanceTableController extends OfficerLeaderViewUnitAttendanceTableController{

	public AdminViewOfficerUnitAttendanceTableController(String unitId, List<OfficerUnitAttendanceReport> officerUnitAttendanceReports) {
		super(unitId, officerUnitAttendanceReports);
	}

}
