package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.dbmodel.StudentDto;

@Repository
@Transactional 
public interface IStudentRepository extends JpaRepository<StudentDto, Long>{

	public StudentDto findByEmail(String email);
	public StudentDto findByEmailAndPassword(String email, String password);
	
}
