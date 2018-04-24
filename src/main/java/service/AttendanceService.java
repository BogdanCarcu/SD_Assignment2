package service;

import java.util.List;

import model.Attendance;

public interface AttendanceService {

	List<Attendance> getAllAttendances();

	List<Attendance> getAttendancesByLaboratoryId(Long id);
	
	List<Attendance> getAttendancesByStudentId(Long id);
	
	Attendance getAttendancesById(Long idStud, Long idLab);

	Attendance saveAttendance(Attendance attendance);
	
	Attendance updateAttendance(Attendance attendance);

    void deleteAttendanceById(Long studentId, Long labId);
	
}
