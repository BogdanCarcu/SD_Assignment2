package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Student;
import service.StudentService;

@RestController
@RequestMapping("register")
public class RegisterController {

	private final StudentService studentService;
	
	@Autowired
	public RegisterController(StudentService studentService) {
		
		this.studentService = studentService;
		
	}
	
	@GetMapping("")
	public Student studentLogin(String token, String email, String password) {
		
		try {
			
			return studentService.register(token, email, password);
			
		} catch (Exception e) {
			
			return null;
		}
		
		
	}
	
}
