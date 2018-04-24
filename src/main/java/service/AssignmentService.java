package service;

import java.util.List;

import model.Assignment;
import model.LaboratoryClass;

public interface AssignmentService {
	
	List<Assignment> getAllAssignments();

	Assignment getAssignmentById(Long assignmentId);

	Assignment saveAssignment(Assignment assignment);

	Assignment updateAssignment(Long assignmentId, Assignment assignment);

    void deleteAssignmentById(Long assignmentId);
    
    List<Assignment> findAllAssignmentsByLaboratoryClass(LaboratoryClass laboratoryClass);

}
