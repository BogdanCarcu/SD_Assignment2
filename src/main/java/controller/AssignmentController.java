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

import model.Assignment;
import service.AssignmentService;

@RestController
@RequestMapping("assignment")
public class AssignmentController {

	private final AssignmentService aService;
	
	@Autowired
	public AssignmentController(AssignmentService aService) {
	        this.aService = aService;
	}
	
	 @GetMapping("")
	    public List<Assignment> getAllAssignments() {
	        try {
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
	            return aService.saveAssignment(assignment);
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
	
	    /*@GetMapping("listAssignmentsByLaboratory")
	    public List<Assignment> listAssignmentsByLaboratory(LaboratoryClass laboratoryClass) {
	    	
	    	 try {
		            return aService.findAllAssignmentsByLaboratoryClass(laboratoryClass);
		            
		        } catch (Exception e) {
		            
		        	return null;
		        }
	    	
	    }*/
	
	
}
