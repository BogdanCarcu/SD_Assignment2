package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.SubmissionService;

@RestController
@RequestMapping("grade")
public class SubmissionMarkController {

	private final SubmissionService subService;
	
	@Autowired
	public SubmissionMarkController(SubmissionService subService) {
	        this.subService = subService;
	}
	
	 @PutMapping("")
 	 public boolean gradeSubmission(@RequestParam Long studentId, @RequestParam Long assignmentId, 
 			 			@RequestParam Float grade) {
		 
		 try {
	        	return subService.markSubmission(studentId, assignmentId, grade);
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	            return false;
	        }
	        
	 	} 
	
}
