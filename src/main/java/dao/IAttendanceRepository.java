package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.dbmodel.AttendanceDto;
import dao.dbmodel.LaboratoryClassDto;
import dao.dbmodel.StudentDto;

@Repository
@Transactional 
public interface IAttendanceRepository extends JpaRepository<AttendanceDto, Long>{

	public AttendanceDto findByStudentAndLaboratory(StudentDto student, LaboratoryClassDto laboratory);
	
	public List<AttendanceDto> findByStudent(StudentDto student);
	
	public List<AttendanceDto> findByLaboratory(LaboratoryClassDto laboratory);
	
}
