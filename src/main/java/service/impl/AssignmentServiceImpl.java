package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IAssignmentRepository;
import dao.dbmodel.AssignmentDto;
import dao.dbmodel.LaboratoryClassDto;
import model.Assignment;
import service.AssignmentService;
import service.mapper.LaboratoryIdMapper;
import service.mapper.LaboratorySaveMapper;

@Service
public class AssignmentServiceImpl implements AssignmentService{

	private final IAssignmentRepository assignmentRepository;
	private ModelMapper myMapper;

	@Autowired
	public AssignmentServiceImpl(IAssignmentRepository assignmentRepository) {
		
	        this.assignmentRepository = assignmentRepository;
	        myMapper = new ModelMapper();
	        myMapper.addMappings(new LaboratoryIdMapper());
	        myMapper.addMappings(new LaboratorySaveMapper());
	       
	}

	@Override
	public List<Assignment> getAllAssignments() {
		
		List<AssignmentDto> assignments = assignmentRepository.findAll();
    	List<Assignment> result = new ArrayList<Assignment>();
    	
    	for(AssignmentDto a : assignments) {
			
			Assignment as = myMapper.map(a, Assignment.class);
			result.add(as);
			
		}
    	
        return result;
	}

	@Override
	public Assignment getAssignmentById(Long assignmentId) {
		
		Optional<AssignmentDto> assignmentDto = assignmentRepository.findById(assignmentId);
    	Assignment a = myMapper.map(assignmentDto.get(), Assignment.class);
    	
    	return a;
	}

	@Override
	public Assignment saveAssignment(Assignment assignment) {
		AssignmentDto asdto = myMapper.map(assignment, AssignmentDto.class);
    	
        if (assignmentRepository.findByName(assignment.getName()) == null) {

            assignmentRepository.save(asdto);
            
            return assignment;

        }
        
        return null;
	}

	@Override
	public Assignment updateAssignment(Long assignmentId, Assignment assignment) {
		Optional<AssignmentDto> asToBeUpdated = assignmentRepository.findById(assignmentId);
		AssignmentDto newAssignmentDto = myMapper.map(assignment, AssignmentDto.class);

        if (asToBeUpdated != null) {
        	
            assignmentRepository.save(newAssignmentDto);
            
            return assignment;
            
        } else {
            return null;
        }
	}

	@Override
	public void deleteAssignmentById(Long assignmentId) {
		
		assignmentRepository.deleteById(assignmentId);
		
	}

	@Override
	public List<Assignment> findAllAssignmentsByLaboratoryClass(Long labId) {
		
		LaboratoryClassDto lab = new LaboratoryClassDto();
		lab.setLabId(labId);
		
		List<AssignmentDto> assignments = assignmentRepository.findAllByLaboratoryClass(lab);
		List<Assignment> result = new ArrayList<Assignment>();
		
		for(AssignmentDto a : assignments) {
			
			Assignment r = myMapper.map(a, Assignment.class);
			result.add(r);
			
		}
		
		return result;
	}
	
	
	
	
}
