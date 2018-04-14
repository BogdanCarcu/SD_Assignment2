package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import dao.BookRepositoryInterface;
import dao.dbmodel.BookDto;
import model.Book;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepositoryInterface bookDAO;
    private ModelMapper myMapper;

    @Autowired
    public BookServiceImpl(BookRepositoryInterface bookDAO) {
        
    	this.bookDAO = bookDAO;
    	myMapper = new ModelMapper();
    	
    }

    public List<Book> getAllBooks() {
    	
    	List<BookDto> books = bookDAO.findAll();
    	List<Book> result = new ArrayList<Book>();
    	
    	for(BookDto b: books) {
			
			Book nb = myMapper.map(b, Book.class);
			result.add(nb);
			
		}
    	
        return result;
    }

    public Book getBookById(Long bookId) {
    	
    	Optional<BookDto> bookdto = bookDAO.findById(bookId);
    	Book book = myMapper.map(bookdto.get(), Book.class);
    	
        return book;
    }

    public Book saveBook(Book book) {

    	BookDto bookdto = myMapper.map(book, BookDto.class);
    	
        if (bookDAO.findByTitleAndAuthor(book.getTitle(), book.getAuthor()) == null) {

            bookDAO.save(bookdto);
            return book;

        }
        
        return null;
    }

    public Book updateBook(Long bookId, Book book) {
       
    	Optional<BookDto> bookToBeUpdated = bookDAO.findById(bookId);

        if (bookToBeUpdated != null) {
            bookToBeUpdated.get().setAuthor(book.getAuthor());
            bookToBeUpdated.get().setTitle(book.getTitle());
            
            bookDAO.save(bookToBeUpdated.get());
            
            Book ret = myMapper.map(bookToBeUpdated.get(), Book.class);
        
            
            return ret;
            
        } else {
            return null;
        }
    }

    public void deleteBookById(Long bookId) {
    	
        bookDAO.deleteById(bookId);
        
    }
}