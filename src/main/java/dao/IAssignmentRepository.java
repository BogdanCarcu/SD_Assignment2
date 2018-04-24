package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dao.dbmodel.AssignmentDto;
import dao.dbmodel.LaboratoryClassDto;

@Repository
@Transactional 
public interface IAssignmentRepository extends JpaRepository<AssignmentDto, Long>{
	
	public AssignmentDto findByName(String name);
	public List<AssignmentDto> findAllByLaboratoryClass (LaboratoryClassDto laboratoryClass);
	
}
