package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Student;
import service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {
	
	 private final StudentService studentService;

	    @Autowired
	    public StudentController(StudentService studentService) {
	        this.studentService = studentService;
	    }
	    
	    
	    @GetMapping("")
	    public List<Student> getAllStudents() {
	        try {
	            return studentService.getAllStudents();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    

	    @GetMapping("/{id}")
	    public Student getStudentById(@PathVariable(value = "id") Long studentId) {
	        try {
	            return studentService.getStudentById(studentId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	    @PostMapping("")
	    public Student saveStudent(@RequestBody Student student) {
	        try {
	            return studentService.saveStudent(student);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @PutMapping("")
	    public Student updateStudent(@RequestBody Student student) {
	        try {
	            return studentService.updateStudent(student.getStudentId(), student);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @DeleteMapping("{id}")
	    public String deleteStudentById(@PathVariable(value = "id") Long studentId) {
	        try {
	            studentService.deleteStudentById(studentId);
	            return "Student with id = " + studentId + " successful deleted!";
	        } catch (Exception e) {
	            return e.getMessage();
	        }
	    }
	    
	    /*@GetMapping("")
	    public Student registerStrudent(String token, String email, String password) {
	    	
	    	   try {
		            Student s = studentService.register(token, email, password);
		            return s;
		            
		        } catch (Exception e) {
		           
		        	return null;
		        }
	
	    }
	    
	    @GetMapping("")
	    public Student login(String email, String password) {
	    	
	    	 try {
		            Student s = studentService.login(email, password);
		            return s;
		            
		        } catch (Exception e) {
		           
		        	return null;
		        }
	    	
	    }*/

}
