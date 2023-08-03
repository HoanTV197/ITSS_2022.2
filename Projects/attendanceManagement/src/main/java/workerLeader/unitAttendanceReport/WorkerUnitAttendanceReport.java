package main.java.workerLeader.unitAttendanceReport;

public class WorkerUnitAttendanceReport {

	private String workerId;
	
	private String workerName;

	private String monthYear;
	
	private String totalWorkingHoursInMonth;
	
	private String totalLateHoursInMonth;
	
	private String totalEarlyLeaveHoursInMonth;
	
	public WorkerUnitAttendanceReport(String workerId, String workerName, String monthYear,
			String totalWorkingHoursInMonth, String totalLateHoursInMonth, String totalEarlyLeaveHoursInMonth) {
		super();
		this.workerId = workerId;
		this.workerName = workerName;
		this.monthYear = monthYear;
		this.totalWorkingHoursInMonth = totalWorkingHoursInMonth;
		this.totalLateHoursInMonth = totalLateHoursInMonth;
		this.totalEarlyLeaveHoursInMonth = totalEarlyLeaveHoursInMonth;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public String getTotalWorkingHoursInMonth() {
		return totalWorkingHoursInMonth;
	}

	public void setTotalWorkingHoursInMonth(String totalWorkingHoursInMonth) {
		this.totalWorkingHoursInMonth = totalWorkingHoursInMonth;
	}

	public String getTotalLateHoursInMonth() {
		return totalLateHoursInMonth;
	}

	public void setTotalLateHoursInMonth(String totalLateHoursInMonth) {
		this.totalLateHoursInMonth = totalLateHoursInMonth;
	}

	public String getTotalEarlyLeaveHoursInMonth() {
		return totalEarlyLeaveHoursInMonth;
	}

	public void setTotalEarlyLeaveHoursInMonth(String totalEarlyLeaveHoursInMonth) {
		this.totalEarlyLeaveHoursInMonth = totalEarlyLeaveHoursInMonth;
	}

	
}
