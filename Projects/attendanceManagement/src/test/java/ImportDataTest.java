package test.java;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import hrSystem.HrSystemDB;
import main.java.employee.Employee;

public class ImportDataTest {
	
	@Test
	public void testInvalidFileName() throws IOException {
		
		Employee employee= HrSystemDB.GetInstance().getWorkerPersonalInfoById("CN0001");
		
		assertNull(employee);
	}
	
	@Test
	public void testValidFileName() throws IOException {
		
		Employee employee= HrSystemDB.GetInstance().getWorkerPersonalInfoById("CN123");
		
		assertNull(employee);
	}
	
}
