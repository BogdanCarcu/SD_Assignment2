package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Book;

@Service
public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(Long bookId);

    Book saveBook(Book bookDTO);

    Book updateBook(Long bookId, Book bookDTO);

    void deleteBookById(Long bookId);
}