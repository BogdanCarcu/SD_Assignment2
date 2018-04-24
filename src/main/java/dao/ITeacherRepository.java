package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.dbmodel.TeacherDto;

@Repository
@Transactional 
public interface ITeacherRepository extends JpaRepository<TeacherDto, Long>{

	public TeacherDto findByEmailAndPassword(String email, String password);
	
}
