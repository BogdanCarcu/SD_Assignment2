package controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Assignment;
import model.Student;
import service.AssignmentService;
import service.StudentService;

@RestController
@RequestMapping("assignment")
public class AssignmentController {

	private final AssignmentService aService;
	private final StudentService studService;
	private JavaMailSender sender;
	
	@Autowired
	public AssignmentController(AssignmentService aService, StudentService studService, JavaMailSender sender) {
	        this.aService = aService;
	        this.studService = studService;
	        this.sender = sender;
	}
	

	private void sendEmail(String destination, String token) throws Exception{
	
		       MimeMessage message = sender.createMimeMessage();
		
		       MimeMessageHelper helper = new MimeMessageHelper(message);
		
		       helper.setTo(destination);
		
		       helper.setText(token);
		
		       helper.setSubject("New Laboratory Assignment");
		        
		       sender.send(message);
		    }
	
	 @GetMapping("")
	    public List<Assignment> getAllAssignments(@RequestParam(required=false) Long labId) {
	        try {
	        	if(labId != null)
	        		return aService.findAllAssignmentsByLaboratoryClass(labId);
	        		
	            return aService.getAllAssignments();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    

	    @GetMapping("{id}")
	    public Assignment getAssignmentById(@PathVariable(value = "id" ) Long assignmentId) {
	        try {
	            return aService.getAssignmentById(assignmentId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @PostMapping("")
	    public Assignment saveAssignment(@RequestBody Assignment assignment) {
	        try {
	   
	            Assignment a =  aService.saveAssignment(assignment);
	            List<Student> students = studService.getAllStudents();
	            
	            List<String> emails = new ArrayList<String>();
	            for(Student s : students)
	            	emails.add(s.getEmail());
	            
	            for(String e : emails) 
	            	try {
	    				sendEmail(e, "New Assignment! Name: " + assignment.getName());
	    			} catch (Exception ex) {
	    				// TODO Auto-generated catch block
	    				ex.printStackTrace();
	    			}
	            
	            return a;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @PutMapping("")
	    public Assignment updateAssignment(@RequestBody Assignment assignment) {
	        try {
	            return aService.updateAssignment(assignment.getAssignmentId(), assignment);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @DeleteMapping("{id}")
	    public String deleteAssignmentById(@PathVariable(value = "id" ) Long assignmentId) {
	        try {
	            aService.deleteAssignmentById(assignmentId);
	            return "Assignment with id = " + assignmentId + " successful deleted!";
	        } catch (Exception e) {
	            return e.getMessage();
	        }
	    }
	
	
	
}
