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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.LaboratoryClass;
import service.LaboratoryService;


@RestController
@RequestMapping("laboratory")
public class LaboratoryClassController {
	
	private final LaboratoryService labService;
	
	@Autowired
    public LaboratoryClassController(LaboratoryService labService) {
		
        this.labService = labService;
    }
	
	 @GetMapping("")
	    public List<LaboratoryClass> getAllLaboratoryClasses(@RequestParam(required = false) String keyword) {
	        try {
	        	
	        	if(keyword == null)
	        		return labService.getAllLaboratoryClasses();
	        	
	        	return labService.viewFilteredList(keyword);
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    

	    @GetMapping("{id}")
	    public LaboratoryClass getLaboratoryClassById(@PathVariable(value = "id" ) Long labId) {
	        try {
	            return labService.getLaboratoryClassById(labId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @PostMapping("")
	    public LaboratoryClass saveLaboratoryClass(@RequestBody LaboratoryClass laboratory) {
	        try {
	            return labService.saveLaboratoryClass(laboratory);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @PutMapping("")
	    public LaboratoryClass updateLaboratoryClass(@RequestBody LaboratoryClass laboratory) {
	        try {
	            return labService.updateLaboratoryClass(laboratory.getLabId(), laboratory);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @DeleteMapping("{id}")
	    public String deleteBookById(@PathVariable(value = "id" ) Long labId) {
	        try {
	            labService.deleteLaboratoryClassById(labId);
	            return "Laboratory Class with id = " + labId + " successful deleted!";
	        } catch (Exception e) {
	            return e.getMessage();
	        }
	    }
	  
}
