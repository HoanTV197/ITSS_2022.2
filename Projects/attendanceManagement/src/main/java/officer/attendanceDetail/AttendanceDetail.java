package main.java.officer.attendanceDetail;

public class AttendanceDetail {

	private String time;
	
	private String timekeeper;
	
	private String type;
	
	public AttendanceDetail(String time, String timekeeper, String type) {
		super();
		this.time = time;
		this.timekeeper = timekeeper;
		this.type = type;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimekeeper() {
		return timekeeper;
	}
	public void setTimekeeper(String timekeeper) {
		this.timekeeper = timekeeper;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
