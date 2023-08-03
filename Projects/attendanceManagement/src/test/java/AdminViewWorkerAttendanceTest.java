package test.java;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import attendanceLog.AttendanceDB;
import hrSystem.HrSystemDB;
import main.java.employee.Employee;
import main.java.worker.attendance.WorkerAttendanceByMonth;

public class AdminViewWorkerAttendanceTest {
	
	@Test
	public void testInvalidSearchInput() throws IOException {
		
		Employee employee= HrSystemDB.GetInstance().getWorkerPersonalInfoById("CN0001");
		
		assertNull(employee);
	}
	
	@Test
	public void testValidSearchInputNotFoundEmployee() throws IOException {
		
		Employee employee= HrSystemDB.GetInstance().getWorkerPersonalInfoById("CN123");
		
		assertNull(employee);
	}
	
	@Test
	public void testValidSearchInputFoundEmployee() throws IOException {
		
		Employee employee= HrSystemDB.GetInstance().getWorkerPersonalInfoById("CN001");
    	WorkerAttendanceByMonth expectedValue = AttendanceDB.GetInstance().getWorkerAttendanceByMonth("CN001", "2023-06");
    	List<List<Double>> testData = new ArrayList<List<Double>>();
    	
    	List<Double> firstList = new ArrayList<Double>();
    	List<Double> secondList = new ArrayList<Double>();
    	List<Double> ThirdList = new ArrayList<Double>();

    	firstList.add(4.0);
    	firstList.add(4.0);
    	firstList.add(3.0);
    	secondList.add(4.0);
    	secondList.add(2.0);
    	secondList.add(4.0);
    	ThirdList.add(4.0);
    	ThirdList.add(4.0);
    	ThirdList.add(3.0);
    	testData.add(firstList);
    	testData.add(secondList);
    	testData.add(ThirdList);

		assertEquals("Phạm Trung Dũng", employee.getName());
		assertEquals("2002-08-24", employee.getDateOfBirth());
		assertEquals("001", employee.getUnitId());
		assertEquals("male", employee.getGender());
    	
    	for (int i = 0; i < 2; i ++) {
    		double delta = 0.0001; // Specify an acceptable margin of error
    		assertEquals(testData.get(i).get(0), expectedValue.getWorkerAttendanceByDay(i+1).getShift1(), delta);
    	}
	}
	
}
