package main.java.worker.attendance;

import java.util.ArrayList;
import java.util.List;

public class WorkerAttendance {

	final int startYear = 2010;

	private List<List<WorkerAttendanceByMonth>> workerAttendancenByMonths = new ArrayList<>();
	
	public WorkerAttendanceByMonth getWorkerAttendanceByMonth(String year, String month) {
		
		try{
			
			int yearInt = Integer.parseInt(year);
			int monthInt = Integer.parseInt(month);
			
			return workerAttendancenByMonths.get(yearInt-startYear).get(monthInt);
			
		} catch(NumberFormatException nfe) {
			
			nfe.printStackTrace();
		
			return null;
		}
	}
	
	public void setWorkerAttendanceByMonth(String year, String month, WorkerAttendanceByMonth workerAttendanceByMonth) {
		
		try{
			
			int yearInt = Integer.parseInt(year);
			int monthInt = Integer.parseInt(month);
			
			workerAttendancenByMonths.get(yearInt-startYear).set(monthInt-1, workerAttendanceByMonth);
			
		} catch(NumberFormatException nfe) {
			
			nfe.printStackTrace();
		}
	}
}
