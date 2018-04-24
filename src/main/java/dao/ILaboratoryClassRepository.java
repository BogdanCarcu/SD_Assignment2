package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import dao.dbmodel.LaboratoryClassDto;

@Repository
@Transactional 
public interface ILaboratoryClassRepository extends JpaRepository<LaboratoryClassDto, Long>{
	
	public LaboratoryClassDto findByTitle(String title);
	
}
