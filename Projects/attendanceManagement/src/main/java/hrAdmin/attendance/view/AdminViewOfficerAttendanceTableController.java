package main.java.hrAdmin.attendance.view;

import main.java.officer.attendance.OfficerAttendanceByMonth;
import main.java.officer.attendance.view.OfficerViewOfficerAttendanceTableController;

public class AdminViewOfficerAttendanceTableController extends OfficerViewOfficerAttendanceTableController{

	public AdminViewOfficerAttendanceTableController(String officerId, OfficerAttendanceByMonth officerAttendanceByMonth) {
		super(officerId, officerAttendanceByMonth);
	}
}
