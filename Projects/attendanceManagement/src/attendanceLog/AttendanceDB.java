package attendanceLog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.officer.attendance.OfficerAttendanceByDay;
import main.java.officer.attendance.OfficerAttendanceByMonth;
import main.java.officer.attendanceDetail.AttendanceDetail;
import main.java.officerLeader.unitAttendanceReport.OfficerUnitAttendanceReport;
import main.java.utils.Notification;
import main.java.worker.attendance.WorkerAttendanceByDay;
import main.java.worker.attendance.WorkerAttendanceByMonth;
import main.java.workerLeader.unitAttendanceReport.WorkerUnitAttendanceReport;

public class AttendanceDB implements IAttendanceLog{

	protected Connection connection;
	protected Statement statement;
	protected ResultSet rs = null;
	
	final String current_month = "2023-06";
	
	private static AttendanceDB DB;
	
	public static AttendanceDB GetInstance() {
		
		if (DB == null) { 
            synchronized (AttendanceDB.class) {
                if (DB == null) {  
                	DB = new AttendanceDB();
               }
           }
		}
		return DB;
	}
	
	public AttendanceDB() {
		
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
	public OfficerAttendanceByMonth getOfficerAttendanceByMonth(String officerId, String monthYear) {
		
		 String query = "SELECT * FROM tblofficerlogs WHERE date_log LIKE '" + monthYear + "%' AND officer_id ='" + officerId + "' ORDER BY date_log;" ;
		 
			List<OfficerAttendanceByDay> officerAttendanceByMonth = new ArrayList<OfficerAttendanceByDay>();
			
		    try {	    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    	
		    			while (rs.next()) {
		    				
		    				boolean morningSession;
		    				boolean afternoonSession;
		    				double hoursLate;
		    				double hoursEarlyLeave;

		    				morningSession = rs.getBoolean("morning_session");
		    				afternoonSession = rs.getBoolean("afternoon_session");
		    				hoursLate = rs.getDouble("hours_late");
		    				hoursEarlyLeave = rs.getDouble("hours_early_leave");
		    					
		    				OfficerAttendanceByDay officerAttendanceByDay = new OfficerAttendanceByDay(monthYear, morningSession, afternoonSession, hoursLate, hoursEarlyLeave);
		    				
		    				officerAttendanceByMonth.add(officerAttendanceByDay);
		    			}		    			
		    		return new OfficerAttendanceByMonth(monthYear, officerAttendanceByMonth);
		    	
		    } catch (SQLException e) {
		         System.out.println(e.getMessage());		         
		    }
		    return null;
	}

	@Override
	public WorkerAttendanceByMonth getWorkerAttendanceByMonth(String workerId, String monthYear) {
		
		String query = "SELECT * FROM tblworkerlogs WHERE date_log LIKE '" + monthYear + "%' AND worker_id ='" + workerId + "' ORDER BY date_log;" ;
		 
		List<WorkerAttendanceByDay> workerAttendanceByMonth = new ArrayList<WorkerAttendanceByDay>();
		
	    try {
	    	
	    	ResultSet rs = this.statement.executeQuery(query);
	    	
	    			while (rs.next()) {
	    				
	    				double shift1;
	    				double shift2;
	    				double shift3;
	    				
	    				shift1 = rs.getDouble("shift_1");
	    				shift2 = rs.getDouble("shift_2");
	    				shift3 = rs.getDouble("shift_3");
	    					    					
	    				WorkerAttendanceByDay workerAttendanceByDay = new WorkerAttendanceByDay(monthYear, shift1, shift2, shift3);
	    				
	    				workerAttendanceByMonth.add(workerAttendanceByDay);
	    			}
	    			
	    		return new WorkerAttendanceByMonth(monthYear, workerAttendanceByMonth);
	    	
	    } catch (SQLException e) {
	    	
	         System.out.println(e.getMessage());		         
	    }
	    return null;
	}

	@Override
	public List<OfficerUnitAttendanceReport> getOfficerUnitAttendanceReport(String unitId, String monthYear) {
		
		String query =  "SELECT\n"
				+ "  o.officer_id,\n"
				+ "  o.officer_name,\n"
				+ "  COUNT(l.officer_log_id) AS num_sessions,\n"
				+ "  SUM(l.hours_late) AS total_hours_late,\n"
				+ "  SUM(l.hours_early_leave) AS total_hours_early_leave\n"
				+ "FROM\n"
				+ "  tblOfficers o\n"
				+ "JOIN\n"
				+ "  tblOfficerLogs l ON o.officer_id = l.officer_id\n"
				+ "WHERE\n"
				+ "  o.unit = '"+ unitId + "' AND\n"
				+ "  date_log LIKE '" + monthYear + "%'\n"
				+ "GROUP BY\n"
				+ "  o.officer_id,\n"
				+ "  o.officer_name;";
		
		List<OfficerUnitAttendanceReport> officerUnitAttendanceReports = new ArrayList<OfficerUnitAttendanceReport>();
		
		 try {
		    	ResultSet rs = this.statement.executeQuery(query);
			 
    			while (rs.next()) {
    				
    				String officerId;
    				String officerName;
    				String numSessions;
    				String totalHoursLate;
    				String totalHoursEarlyLeave;
    				
    				officerId = rs.getString("officer_id");
    				officerName = rs.getString("officer_name");
    				numSessions = rs.getString("num_sessions");
    				totalHoursLate = rs.getString("total_hours_late")	;
    				totalHoursEarlyLeave = rs.getString("total_hours_early_leave");
    				
    				OfficerUnitAttendanceReport officerUnitAttendanceReport = new OfficerUnitAttendanceReport(officerId, officerName, monthYear, numSessions, totalHoursLate,totalHoursEarlyLeave);
    				
    				officerUnitAttendanceReports.add(officerUnitAttendanceReport);
    			}
    			
    			return officerUnitAttendanceReports;
    			
		    } catch (SQLException e) {
		    	
		         System.out.println(e.getMessage());	
		         
		         return null;
		    }		
	}

	@Override
	public List<WorkerUnitAttendanceReport> getWorkerUnitAttendanceReport(String unitId, String monthYear) {

		String query = "SELECT w.worker_id, w.worker_name, SUM(l.total_shifts) AS total_shifts_in_month\n"
				+ "FROM tblWorkers w\n"
				+ "JOIN (\n"
				+ "    SELECT worker_id, SUM(shift_1 + shift_2 + shift_3) AS total_shifts\n"
				+ "    FROM tblWorkerLogs\n"
				+ "    WHERE date_log LIKE '"+ monthYear +"%'\n"
				+ "    GROUP BY worker_id\n"
				+ ") l ON w.worker_id = l.worker_id AND w.unit = '"+ unitId +"'\n"
				+ "GROUP BY w.worker_id, w.worker_name;\n"
				+ "		" ;
		
		List<WorkerUnitAttendanceReport> workerUnitAttendanceReports =  new ArrayList<WorkerUnitAttendanceReport>();

		 try {
		    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    		
		    		String workerId;
		    		String workerName;
		    		String totalWorkingHoursInMonth;
		            
		    		while (rs.next()) {
		    				
	    				workerId = rs.getString("worker_id");
	    				workerName = rs.getString("worker_name");
	    				totalWorkingHoursInMonth = rs.getString("total_shifts_in_month");
	    				
	    				WorkerUnitAttendanceReport workerUnitAttendanceReport = new WorkerUnitAttendanceReport(workerId, workerName, monthYear, totalWorkingHoursInMonth, "0", "0");
	    				
	    				workerUnitAttendanceReports.add(workerUnitAttendanceReport);
		    		}
		    		
		    		return workerUnitAttendanceReports;
		    	
		    } catch (SQLException e) {
		    	
		         System.out.println(e.getMessage());		         
		    }

		
		return null;
	}

	@Override
	public void sendNotification(String from_id, String to_id, String content) {
		
		String query = "INSERT INTO notifications (from_id, to_id, content) VALUES('" + from_id + "','" + to_id + "','" + content + "');";
		
		 try {
		    	
		    this.statement.executeQuery(query);
		    			   	
		    } catch (SQLException e) {
		    	
		         System.out.println(e.getMessage());		         
		 }
	}

	@Override
	public List<Notification> getNotifications(String id) {
		
		String query = "SELECT * FROM notifications Where to_id = '" + id + "';";
		
		List<Notification> result = new ArrayList<Notification>();
		

		 try {
		    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    		
		    		String from_id;
		    		String to_id;
		    		String content;
		    	
		    		
		    		while(rs.next()) {
		    			
		    			 from_id = rs.getString("from_id");
		    			 to_id = rs.getString("to_id");
		    			 content = rs.getString("content");
		    			 
		    			 Notification aNotification = new Notification(from_id, to_id, content);
		    			 result.add(aNotification);
		    		}
		    	
		    	
		    		return result;
		    		
		    } catch (SQLException e) {
		    	
		         System.out.println(e.getMessage());		         
		    }

		
		return null;
	}

	@Override
	public void modifyAttendance(String employeeId, String date, String attribute, String to) {
		
		String query;
		
		if (employeeId.contains("CN")) {
			query = "UPDATE tblworkerlogs SET " + attribute + " = " + to + " WHERE worker_id = '" + employeeId + "' AND date_log = '" + date + "';";
		} else {
			query = "UPDATE tblofficerlogs SET " + attribute + " = " + to + " WHERE officer_id = '" + employeeId + "' AND date_log = '" + date + "';";
		}
		
		try {
	    	
			this.statement.executeQuery(query);
	    			    			    	
	    } catch (SQLException e) {
	    	
	         System.out.println(e.getMessage());		         
	 }
	}

	@Override
	public List<AttendanceDetail> getWorkerAttendanceDetail(String worker_id, String date_log) {
		
		String query = "SELECT * FROM tblWorkerDetailLogs WHERE worker_id = '" + worker_id + "' AND date_log = '" + date_log + "';";
		
		List<AttendanceDetail> result = new ArrayList<AttendanceDetail>();
		

		 try {
		    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    		
		    		String time;
		    		String timekeeper;
		    		String type;
		    	
		    		
		    		while(rs.next()) {
		    			
		    			 time = rs.getString("time");
		    			 timekeeper = rs.getString("timekeeper");
		    			 type = rs.getString("type");
		    			 
		    			 AttendanceDetail attendanceDetail = new AttendanceDetail(time, timekeeper, type);
		    			 
		    			 result.add(attendanceDetail);
		    		}
		    	
		    	
		    		return result;
		    		
		    } catch (SQLException e) {
		    	
		         System.out.println(e.getMessage());		         
		    }
		
		return null;
	}

	@Override
	public List<AttendanceDetail> getOfficerAttendanceDetail(String officer_id, String date_log) {
		
		
		String query = "SELECT * FROM tblOfficerDetailLogs WHERE officer_id = '" + officer_id + "' AND date_log = '" + date_log + "';";
		
		List<AttendanceDetail> result = new ArrayList<AttendanceDetail>();

		 try {
		    	
		    	ResultSet rs = this.statement.executeQuery(query);
		    		
		    		String time;
		    		String timekeeper;
		    		String type;
		    	
		    		
		    		while(rs.next()) {
		    			
		    			 time = rs.getString("time");
		    			 timekeeper = rs.getString("timekeeper");
		    			 type = rs.getString("type");
		    			 
		    			 AttendanceDetail attendanceDetail = new AttendanceDetail(time, timekeeper, type);
		    			 
		    			 result.add(attendanceDetail);
		    		}
		    	
		    	
		    		return result;
		    		
		    } catch (SQLException e) {
		    	
		         System.out.println(e.getMessage());		         
		    }
		
		return null;
	}

	@Override
	public int importWorkerAttendance(String worker_log_id, String worker_id, String date_log, double shift_1,
			double shift_2, double shift_3) throws SQLException {
		
	  	String INSERT_QUERY = "INSERT INTO tblworkerlogs (worker_log_id, worker_id, date_log, shift_1, shift_2, shift_3) VALUES (?, ?, ?, ?, ?, ?);";
	  	
    	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
    	preparedStatement.setString(1, worker_log_id);
    	preparedStatement.setString(2, worker_id);
    	preparedStatement.setString(3, date_log);
    	preparedStatement.setDouble(4, shift_1);
    	preparedStatement.setDouble(5, shift_2);
    	preparedStatement.setDouble(6, shift_3);
    	
    	return preparedStatement.executeUpdate();
    }

	@Override
	public int checkIdExists(String worker_log_id) throws SQLException {
		
		   String SELECT_QUERY = "SELECT COUNT(worker_log_id) FROM tblworkerlogs WHERE worker_log_id = ?";
		   
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
	        
	        preparedStatement.setString(1, worker_log_id);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        int count = 0;
	        if (resultSet.next()) {
	            count = resultSet.getInt(1);
	        }
	        
	        resultSet.close();
	        preparedStatement.close();
	        
	        if (count > 0) {
	            return 0; 
	        } else {
	            return 1; 
	        }
	}
}
