package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.dbmodel.AssignmentDto;
import dao.dbmodel.StudentDto;
import dao.dbmodel.SubmissionDto;

@Repository
@Transactional 
public interface ISubmissionRepository extends JpaRepository<SubmissionDto, Long>{

	public SubmissionDto findByStudentAndAssignment(StudentDto student, AssignmentDto assignment);
	
	public List<SubmissionDto> findByStudent(StudentDto student);
	
	public List<SubmissionDto> findByAssignment(AssignmentDto assignment);
	
}
