package main.java.officer.attendance;

public class OfficerAttendanceByDay {

	private String dayMonthYear;
	private boolean morningSession;
	private boolean afternoonSession;
	private double hoursLate;
	private double hoursEarlyLeave;
	
	public OfficerAttendanceByDay(String dayMonthYear, boolean morningSession, boolean afternoonSession, double hoursLate, double hoursEarlyLeave) {
		
		this.dayMonthYear = dayMonthYear;
		this.morningSession = morningSession;
		this.afternoonSession = afternoonSession;
		this.hoursLate = hoursLate;
		this.hoursEarlyLeave = hoursEarlyLeave;
		
	}
	public String getDay() {
		return dayMonthYear;
	}
	public void setDay(String day) {
		this.dayMonthYear = day;
	}
	public boolean getMorningSession() {
		return morningSession;
	}
	public void setMorningSession(boolean morningSession) {
		this.morningSession = morningSession;
	}
	public boolean getAfternoonSession() {
		return afternoonSession;
	}
	public void setAfternoonSession(boolean afternoonSession) {
		this.afternoonSession = afternoonSession;
	}
	public double getHoursLate() {
		return hoursLate;
	}
	public void setHoursLate(double hoursLate) {
		this.hoursLate = hoursLate;
	}
	public double getHoursEarlyLeave() {
		return hoursEarlyLeave;
	}
	public void setHoursEarlyLeave(double hoursEarlyLeave) {
		this.hoursEarlyLeave = hoursEarlyLeave;
	}
}
