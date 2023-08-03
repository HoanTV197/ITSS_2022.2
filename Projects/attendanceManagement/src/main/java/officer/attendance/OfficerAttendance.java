package main.java.officer.attendance;

import java.util.ArrayList;
import java.util.List;

public class OfficerAttendance {

	final int startYear = 2010;
	
	private List<List<OfficerAttendanceByMonth>> officerAttendanceByMonths = new ArrayList<>();
	
	public OfficerAttendanceByMonth getOfficerAttendanceByMonth(String year, String month) {
		
		try {
			
			int yearInt = Integer.parseInt(year);
			int monthInt = Integer.parseInt(month);
			
			return officerAttendanceByMonths.get(yearInt-startYear).get(monthInt);	
			
		} catch(NumberFormatException nfe) {
			
			nfe.printStackTrace();
		
			return null;
		}
	}
	
	public void setOfficerAttendanceByMonth(String year, String month, OfficerAttendanceByMonth officerAttendanceByMonth) {

		try {			
			int yearInt = Integer.parseInt(year);
			int monthInt = Integer.parseInt(month);
			
			officerAttendanceByMonths.get(yearInt-startYear).set(monthInt-1, officerAttendanceByMonth);
			
		} catch(NumberFormatException nfe) {
			
			nfe.printStackTrace();
		}
	}
}
