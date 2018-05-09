package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Submission;
import service.SubmissionService;

@RestController
@RequestMapping("submission")
public class SubmissionController {
	
		private final SubmissionService subService;
	
		@Autowired
		public SubmissionController(SubmissionService subService) {
		        this.subService = subService;
		}
 
		@GetMapping("")
	    public List<Submission> getAllSubmissionsById(@RequestParam(required = false) Long studentId, 
	    									@RequestParam(required = false) Long assignmentId) {
	        try {
	        	
	        	if(studentId == null && assignmentId == null)
	        		return subService.getAllSubmissions();
	        	
	        	if(studentId == null && assignmentId != null)
	        		return subService.getSubmissionsByAssignmentId(assignmentId);
	        	
	        	if(studentId != null && assignmentId == null)
	        		return subService.getSubmissionsByStudentId(studentId);
	        	
	        	Submission unique = subService.getSubmissionByStudentAndAssignment(studentId, assignmentId);
	        	List<Submission> oneAttList = new ArrayList<Submission>();
	        	
	        	if(unique != null) {
	      
	        		oneAttList.add(unique);
	        		
	        	} else
	        		return null;
	        	  	
	            return oneAttList;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	 	@PostMapping("")
	    public Submission saveSubmission(@RequestBody Submission submission) {
	        try {
	            return subService.saveSubmission(submission);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
	 	
	 	 @PutMapping("")
	 	 public boolean updateSubmission(@RequestParam Long studentId, @RequestParam Long assignmentId, 
	 			 			@RequestParam(required = false) String remark,
	 			 			@RequestParam(required = false) String gitLink) {
	 		
	 	
	 			 try {
			            return subService.resubmit(studentId, assignmentId, remark, gitLink);
			            
			        } catch (Exception e) {
			            e.printStackTrace();
			            
			            return false;
			        }
	 		
	 	 }
	 	 
	    @DeleteMapping("")
	    public String deleteSubmission(@RequestParam Long studentId, @RequestParam Long assignmentId) {
	        try {
	            subService.deleteSubmissionById(studentId, assignmentId);
	            return "Submission with student id = " + studentId + " and assignment id = " +
	            		assignmentId + " was successfuly deleted!";
	        } catch (Exception e) {
	            return e.getMessage();
	        }
	    }
	 
	    @GetMapping("{id}")
	    public Map<String, Float> getAllGradesForAssignment(@PathVariable(name = "id") Long id) {
	    	
	    	 try {
		           
	    		 	return subService.findAllGradesForAssignment(id);
	    		 
		        } catch (Exception e) {
		            return null;
		        }
	    	
	    }
	

}
