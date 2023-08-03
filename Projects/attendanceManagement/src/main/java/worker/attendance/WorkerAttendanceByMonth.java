package main.java.worker.attendance;

import java.util.ArrayList;
import java.util.List;

public class WorkerAttendanceByMonth {

	private String monthYear;

	private List<WorkerAttendanceByDay> workerAttendanceByDays = new ArrayList<WorkerAttendanceByDay>();
	
	public WorkerAttendanceByMonth(String monthYear, List<WorkerAttendanceByDay> workerAttendanceByDays) {
		
		this.monthYear = monthYear;
		this.workerAttendanceByDays = workerAttendanceByDays;
	}
	
	public String getMonthYear() {
		
		return this.monthYear;
	}
	
	public WorkerAttendanceByDay getWorkerAttendanceByDay(int day) {
		
		return workerAttendanceByDays.get(day-1);
	}
	
	public  List<WorkerAttendanceByDay> getWorkerAttendanceByDays() {
		
		return this.workerAttendanceByDays;
	}
}
