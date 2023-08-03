package main.java.unit;

public class Unit {

	private String unitName;
	private String unitId;
	private String department;
	private int memNum;

	public Unit(String unitId, String unitName, String department, int memNum) {
		this.unitId = unitId;
		this.unitName = unitName;
		this.department = department;
		this.memNum = memNum;
		
	}
	
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getMemNum() {
		return memNum;
	}
	public void setMemNum(int memNum) {
		this.memNum = memNum;
	}
}
