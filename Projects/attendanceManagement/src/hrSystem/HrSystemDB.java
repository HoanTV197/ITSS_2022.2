package hrSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.employee.Employee;
import main.java.unit.Unit;

public class HrSystemDB implements IHrSystem{
	
	protected Connection connection;
	protected Statement statement;
	protected ResultSet rs = null;
	
	private static HrSystemDB DB;
	
	public static HrSystemDB GetInstance() {
		
		if (DB == null) {  // First check
			 
            synchronized (HrSystemDB.class) {
            	
                if (DB == null) {  
                	DB = new HrSystemDB();
               }
          }
      }
		return DB;
	}
	
	public HrSystemDB() {
		
		String url = "jdbc:postgresql://localhost/attendance_management";
		String user = "buidanhtung";
		String password = "Tung001202033042";
		
		try {
			
			this.connection = DriverManager.getConnection(url, user, password);
			this.statement = this.connection.createStatement();
			
		} catch (SQLException e) {
	        	
	         System.out.println(e.getMessage());
	    }
	}

	@Override
	public Employee getWorkerPersonalInfoById(String employeeId) {
		
	    String query = "SELECT * FROM tblworkers WHERE worker_id = '" + employeeId + "';";

	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
		    	 String name = rs.getString("worker_name");
		    	 String gender = rs.getString("gender");
		    	 String date_of_birth = rs.getString("date_of_birth");
		    	 String unit = rs.getString("unit");
		    	 
		    	 return new Employee(name, employeeId, gender, date_of_birth, unit);
		      }
	    	 
	    	 return null;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}

	@Override
	public Employee getWorkerPersonalInfoByName(String employeeName) {
		
		  String query = "SELECT * FROM tblworkers WHERE worker_name = '" + employeeName + "';";

		    try {	    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    	
		    	 while (rs.next()) {
			    	 String id = rs.getString("worker_id");
			    	 String gender = rs.getString("gender");
			    	 String date_of_birth = rs.getString("date_of_birth");
			    	 String unit = rs.getString("unit");
			    	 
			    	 return new Employee(employeeName, id, gender, date_of_birth, unit);
			      }
		    	 
		    	 return null;
		    	
		    } catch (SQLException e) {
	        	
		         System.out.println(e.getMessage());
		         
		    }
		    
		    return null;
	}

	@Override
	public Employee getOfficerPersonalInfoById(String employeeId) {
		
		String query = "SELECT * FROM tblofficers WHERE officer_id = '" + employeeId + "';";

	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
		    	 String name = rs.getString("officer_name");
		    	 String gender = rs.getString("gender");
		    	 String date_of_birth = rs.getString("date_of_birth");
		    	 String unit = rs.getString("unit");
		    	 
		    	 return new Employee(name, employeeId, gender, date_of_birth, unit);
		      }
	    	 
	    	 return null;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}

	@Override
	public Employee getOfficerPersonalInfoByName(String employeeName) {
		
		String query = "SELECT * FROM tblofficers WHERE officer_name = '" + employeeName + "';";

	    try {	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    	 while (rs.next()) {
		    	 String id = rs.getString("officer_id");
		    	 String gender = rs.getString("gender");
		    	 String date_of_birth = rs.getString("date_of_birth");
		    	 String unit = rs.getString("unit");
		    	 
		    	 return new Employee(employeeName, id, gender, date_of_birth, unit);
		      }
	    	 
	    	 return null;
	    	
	    } catch (SQLException e) {
        	
	         System.out.println(e.getMessage());
	         
	    }
	    
	    return null;
	}

	@Override
	public Unit getUnitInfoById(String unitId) {
		
		String query = "SELECT * FROM tblunits WHERE unit_id = '" + unitId + "';";

		 try {	    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    	
		    	 while (rs.next()) {
		    		 
			    	 String unit_name = rs.getString("unit_name");
			    	 String department = rs.getString("department");
			    	 

			    	 
			    	 return new Unit(unitId, unit_name, department, 30);
			      }
		    	 
		    	 return null;
		    	
		   } catch (SQLException e) {
	        	
		         System.out.println(e.getMessage());
		         
		   }
		return null;
	}
}
