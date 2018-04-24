package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.IUser;
import service.StudentService;
import service.TeacherService;

@RestController
@RequestMapping("login")
public class LoginController {

	private final StudentService studentService;
	private final TeacherService teacherService;
	
	@Autowired
	public LoginController(StudentService studentService, TeacherService teacherService) {
		
		this.studentService = studentService;
		this.teacherService = teacherService;
		
	}
	
	@GetMapping("")
	public IUser login(String email, String password) {
		
		try {
			
			IUser user = studentService.login(email, password);
			
			if(user == null)
				user = teacherService.login(email, password);
			
			return user;
			
		} catch (Exception e) {
			
			return null;
		}	
		
	}
	
}
