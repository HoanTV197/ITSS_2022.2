package attendanceLog;

import java.sql.SQLException;
import java.util.List;

import main.java.officer.attendance.OfficerAttendanceByMonth;
import main.java.officer.attendanceDetail.AttendanceDetail;
import main.java.officerLeader.unitAttendanceReport.OfficerUnitAttendanceReport;
import main.java.utils.Notification;
import main.java.worker.attendance.WorkerAttendanceByMonth;
import main.java.workerLeader.unitAttendanceReport.WorkerUnitAttendanceReport;

public interface IAttendanceLog {

	
	OfficerAttendanceByMonth getOfficerAttendanceByMonth(String officerId, String month);
	WorkerAttendanceByMonth getWorkerAttendanceByMonth(String workerId, String month);
	List<OfficerUnitAttendanceReport> getOfficerUnitAttendanceReport(String unitId, String month);
	List<WorkerUnitAttendanceReport> getWorkerUnitAttendanceReport(String unitId, String month);
	List<Notification> getNotifications(String id);
	void sendNotification(String from_id, String to_id, String content);
	void modifyAttendance(String employeeId, String Date, String attribute, String to);
	List<AttendanceDetail> getWorkerAttendanceDetail(String worker_id, String date_log);
	List<AttendanceDetail> getOfficerAttendanceDetail(String officer_id, String date_log);
	int importWorkerAttendance(String worker_log_id, String worker_id, String date_log, double shift_1, double shift_2, double shift_3) throws SQLException;
	int checkIdExists(String worker_log_id) throws SQLException;
}
