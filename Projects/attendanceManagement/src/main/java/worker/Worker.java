package main.java.worker;

import main.java.employee.Employee;

public class Worker extends Employee{

	public Worker(String name, String employeeId, String unitId) {
		super(name, employeeId, unitId);
	}
	
	public Worker(String name, String employeeId, String gender, String dateOfBirth, String unitId) {
		super(name, employeeId, gender, dateOfBirth, unitId);
	}
}
