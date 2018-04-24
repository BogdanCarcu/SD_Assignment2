package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Attendance;
import service.AttendanceService;

@RestController
@RequestMapping("attendance")
public class AttendanceController {

	private final AttendanceService aService;
	
	@Autowired
	public AttendanceController(AttendanceService aService) {
	        this.aService = aService;
	}
 

    @GetMapping("")
    public List<Attendance> getAttendancesById(@RequestParam(required = false) Long studId, 
    									@RequestParam(required = false) Long labId) {
        try {
        	
        	if(studId == null && labId == null)
        		return aService.getAllAttendances();
        	
        	if(studId == null && labId != null)
        		return aService.getAttendancesByLaboratoryId(labId);
        	
        	if(studId != null && labId == null)
        		return aService.getAttendancesByStudentId(studId);
        	
        	Attendance unique = aService.getAttendancesById(studId, labId);
        	List<Attendance> oneAttList = new ArrayList<Attendance>();
        	oneAttList.add(unique);
        	
            return oneAttList;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @PostMapping("")
    public Attendance saveAttendance(@RequestBody Attendance attendance) {
        try {
            return aService.saveAttendance(attendance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @PutMapping("")
    public Attendance updateAttendance(@RequestBody Attendance attendance) {
        try {
            return aService.updateAttendance(attendance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    @DeleteMapping("")
    public String deleteAttendance(@RequestParam Long studId, @RequestParam Long labId) {
        try {
            aService.deleteAttendanceById(studId, labId);
            return "Attendance with laboratory id = " + labId + " and student id = " + studId + " was successfuly deleted!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
