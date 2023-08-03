package main.java.officer.attendance;

import java.util.ArrayList;
import java.util.List;

public class OfficerAttendanceByMonth {
	
	private String monthYear;
	private List<OfficerAttendanceByDay> officerAttendanceByDays = new ArrayList<OfficerAttendanceByDay>();
	
	public OfficerAttendanceByMonth(String monthYear, List<OfficerAttendanceByDay> officerAttendanceByDays) {
		
		this.monthYear = monthYear;
		this.officerAttendanceByDays = officerAttendanceByDays;
	}
	
	public String getMonthYear() {
		
		return this.monthYear;
	}
	
	public OfficerAttendanceByDay getOfficerAttendanceByDay(int dayInMonth) {
		
		return this.officerAttendanceByDays.get(dayInMonth-1);
	}
	
	public  List<OfficerAttendanceByDay> getOfficerAttendanceByDays() {
		
		return this.officerAttendanceByDays;
	}
	
}
