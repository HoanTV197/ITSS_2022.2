package hrSystem;

import main.java.employee.Employee;
import main.java.unit.Unit;

public interface IHrSystem {
	
	Employee getWorkerPersonalInfoById(String employeeId);
	Employee getWorkerPersonalInfoByName(String employeeId);
	Employee getOfficerPersonalInfoById(String employeeId);
	Employee getOfficerPersonalInfoByName(String employeeId);
	Unit getUnitInfoById(String unitId);
	
}
