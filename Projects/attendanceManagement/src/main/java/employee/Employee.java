package main.java.employee;

public class Employee {

	private String name;
	private String employeeId;
	private String gender;
	private String dateOfBirth;
	private String unitId;
	
	public Employee(String name, String employeeId, String unitId) {
		this.name = name;
		this.employeeId = employeeId;
		this.unitId = unitId;
	}
	
	public Employee(String name, String employeeId, String gender, String dateOfBirth, String unitId) {
		this.name = name;
		this.employeeId = employeeId;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.unitId = unitId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
}
