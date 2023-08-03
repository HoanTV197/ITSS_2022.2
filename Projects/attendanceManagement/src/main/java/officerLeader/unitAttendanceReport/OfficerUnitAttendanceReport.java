package main.java.officerLeader.unitAttendanceReport;

public class OfficerUnitAttendanceReport {
	
	private String officerId;
	
	private String officerName;
	
	private String monthYear;
	
	private String totalWorkingSessions;
	
	private String totalLateHours;
	
	private String totalEarlyHours;

	public OfficerUnitAttendanceReport(String officerId, String officerName, String monthYear,
			String totalWorkingSessions, String totalLateHours, String totalEarlyHours) {
		
		super();
		this.officerId = officerId;
		this.officerName = officerName;
		this.monthYear = monthYear;
		this.totalWorkingSessions = totalWorkingSessions;
		this.totalLateHours = totalLateHours;
		this.totalEarlyHours = totalEarlyHours;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public String getTotalWorkingSessions() {
		return totalWorkingSessions;
	}

	public void setTotalWorkingSessions(String totalWorkingSessions) {
		this.totalWorkingSessions = totalWorkingSessions;
	}

	public String getTotalLateHours() {
		return totalLateHours;
	}

	public void setTotalLateHours(String totalLateHours) {
		this.totalLateHours = totalLateHours;
	}

	public String getTotalEarlyHours() {
		return totalEarlyHours;
	}

	public void setTotalEarlyHours(String totalEarlyHours) {
		this.totalEarlyHours = totalEarlyHours;
	}
	
}
