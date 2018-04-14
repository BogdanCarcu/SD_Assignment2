package dao;

import dao.dbmodel.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional 
public interface BookRepositoryInterface extends JpaRepository<BookDto, Long>{
	
	  BookDto findByTitleAndAuthor(String title, String author);
	
}
