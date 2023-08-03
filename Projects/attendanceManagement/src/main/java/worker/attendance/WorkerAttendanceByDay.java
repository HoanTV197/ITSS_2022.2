package main.java.worker.attendance;

public class WorkerAttendanceByDay {
	
	private String worker_log_id;
	private String worker_id;
	private String day;
	private double shift1;
	private double shift2;
	private double shift3;
	
	public WorkerAttendanceByDay() {
		super();
	}

	public WorkerAttendanceByDay(String day, double shift1, double shift2, double shift3) {
		this.day = day;
		this.shift1 = shift1;
		this.shift2 = shift2;
		this.shift3 = shift3;
	}

	public WorkerAttendanceByDay(String worker_log_id, String worker_id, String day, double shift1, double shift2,
		double shift3) {
		super();
		this.worker_log_id = worker_log_id;
		this.worker_id = worker_id;
		this.day = day;
		this.shift1 = shift1;
		this.shift2 = shift2;
		this.shift3 = shift3;
	}


	public String getWorker_log_id() {
		return worker_log_id;
	}


	public void setWorker_log_id(String worker_log_id) {
		this.worker_log_id = worker_log_id;
	}


	public String getWorker_id() {
		return worker_id;
	}


	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public double getShift1() {
		return shift1;
	}

	public void setShift1(double shift1) {
		this.shift1 = shift1;
	}

	public double getShift2() {
		return shift2;
	}

	public void setShift2(double shift2) {
		this.shift2 = shift2;
	}

	public double getShift3() {
		return shift3;
	}

	public void setShift3(double shift3) {
		this.shift3 = shift3;
	}
	
	
}
