package com.labmanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import controller.BookController;
import model.Book;
import service.BookService;
import service.BookServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LaboratoryManagementApplicationTests {

	@Autowired
	private BookController controller;
	
	@Test
	public void contextLoads() { 
		
		Book book = new Book();
		book.setAuthor("Bogdan");	
		book.setTitle("Borsec si alte apuri");
		
		controller.saveBook(book);
		Book found = controller.getBookById(book.getBookUid());
		
		assert(book.getAuthor().equals(found.getAuthor()));
	
	}

}
