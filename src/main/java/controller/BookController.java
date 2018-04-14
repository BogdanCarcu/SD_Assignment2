package controller;

import model.Book;
import service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    
    @RequestMapping( "/" )
    public String getMsg() {
       return "It's working...!";
    }
    
    @GetMapping("getAllBooks")
    public List<Book> getAllBooks() {
        try {
            return bookService.getAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @GetMapping("getMahBooks")
    public String getMahBooks() {
        try {
        	List<Book> books = bookService.getAllBooks();
        	String res = "";
        	
        	for(Book b: books) {
        		
        		res += b;
        		res += "\n";
        	}
        	
        	return res;
        	
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @GetMapping("getBookById")
    public Book getBookById(@RequestParam Long bookId) {
        try {
            return bookService.getBookById(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("saveBook")
    public Book saveBook(@RequestBody Book book) {
        try {
            return bookService.saveBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("updateBook")
    public Book updateBook(@RequestParam Long bookId, @RequestBody Book book) {
        try {
            return bookService.updateBook(bookId, book);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("deleteBookById")
    public String deleteBookById(Long bookId) {
        try {
            bookService.deleteBookById(bookId);
            return "Book with id = " + bookId + " successful deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}