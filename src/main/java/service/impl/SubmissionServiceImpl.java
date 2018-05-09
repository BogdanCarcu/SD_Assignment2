package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IAssignmentRepository;
import dao.ISubmissionRepository;
import dao.dbmodel.AssignmentDto;
import dao.dbmodel.StudentDto;
import dao.dbmodel.SubmissionDto;
import model.Submission;
import service.SubmissionService;
import service.mapper.DtoToSubmission;

@Service
public class SubmissionServiceImpl implements SubmissionService{

	private ISubmissionRepository subRepository;
	private IAssignmentRepository asRepository;
	private ModelMapper myMapper;

	@Autowired
	public SubmissionServiceImpl(ISubmissionRepository subRepository, IAssignmentRepository asRepository) {
	        this.subRepository = subRepository;
	        this.asRepository = asRepository;
	        myMapper = new ModelMapper();
	        myMapper.addMappings(new DtoToSubmission());
	   
	}
	
	@Override
	public List<Submission> getAllSubmissions() {

		List<SubmissionDto> submissions = subRepository.findAll();
    	List<Submission> result = new ArrayList<Submission>();
    	
    	for(SubmissionDto s: submissions) {
			
			Submission sub = myMapper.map(s, Submission.class);
			result.add(sub);
			
		}
    	
        return result;
	}

	@Override
	public List<Submission> getSubmissionsByStudentId(Long studentId) {
		
		StudentDto student = new StudentDto();
		student.setStudentId(studentId);
		
		List<SubmissionDto> submissions = subRepository.findByStudent(student);
		List<Submission> result = new ArrayList<Submission>();
		
		for(SubmissionDto s: submissions) {
			
			Submission sub = myMapper.map(s, Submission.class);
			result.add(sub);
			
		}
		
		return result;
	}

	@Override
	public List<Submission> getSubmissionsByAssignmentId(Long assignmentId) {
		
		AssignmentDto assignment = new AssignmentDto();
		assignment.setAssignmentId(assignmentId);
		
		List<SubmissionDto> submissions = subRepository.findByAssignment(assignment);
		List<Submission> result = new ArrayList<Submission>();
		
		for(SubmissionDto s: submissions) {
			
			Submission sub = myMapper.map(s, Submission.class);
			result.add(sub);
			
		}
		
		return result;
	}

	@Override
	public Submission getSubmissionByStudentAndAssignment(Long studentId, Long assignmentId) {
		
		StudentDto student = new StudentDto();
		student.setStudentId(studentId);
		
		AssignmentDto assignment = new AssignmentDto();
		assignment.setAssignmentId(assignmentId);
		
		SubmissionDto submission = subRepository.findByStudentAndAssignment(student, assignment);
		Submission result;
		if(submission != null)
			result = myMapper.map(submission, Submission.class);
		else
			result = null;
		return result;
		
	}

	@Override
	public Submission saveSubmission(Submission submission) {
		
		AssignmentDto assignment = asRepository.findById(submission.getAssignmentId()).get();
	
		if(assignment.getDeadline().before(new Date()))
				return null;
		
		StudentDto student = new StudentDto();
		student.setStudentId(submission.getStudentId());
	
		//AssignmentDto assignment = new AssignmentDto();
		//assignment.setAssignmentId(submission.getAssignmentId());
		
		if(subRepository.findByStudentAndAssignment(student, assignment) == null) {
			
			SubmissionDto s = new SubmissionDto();
			
			s.setAssignment(assignment);
			s.setGitLink(submission.getGitLink());
			s.setGrade(0);
			s.setRemark(submission.getRemark());
			s.setStudent(student);
		
			subRepository.save(s);
			return submission;
		}
		
		return null;
		
	}

	@Override
	public Submission updateSubmission(Submission submission) {

		AssignmentDto assignment = asRepository.findById(submission.getAssignmentId()).get();
		
		if(assignment.getDeadline().before(new Date()))
				return null;
		
		StudentDto student = new StudentDto();
		student.setStudentId(submission.getStudentId());
		
		//AssignmentDto assignment = new AssignmentDto();
		//assignment.setAssignmentId(submission.getAssignmentId());
		
		SubmissionDto s = subRepository.findByStudentAndAssignment(student, assignment);
		
		s.setGitLink(submission.getGitLink());
		s.setRemark(submission.getRemark());
		
		subRepository.save(s);
		return submission;
	}
	
	@Override
	public boolean markSubmission(Long studentId, Long assignmentId, float grade) {

		StudentDto student = new StudentDto();
		student.setStudentId(studentId);
		
		AssignmentDto assignment = new AssignmentDto();
		assignment.setAssignmentId(assignmentId);
		
		SubmissionDto s = subRepository.findByStudentAndAssignment(student, assignment);
	
		s.setGrade(grade);
		
		subRepository.save(s);
		return true;
	}
	
	@Override
	public boolean resubmit(Long studentId, Long assignmentId, String remark, String gitLink) {
		
		AssignmentDto assignment = asRepository.findById(assignmentId).get();
		
		if(assignment.getDeadline().before(new Date()))
				return false;
		
		StudentDto student = new StudentDto();
		student.setStudentId(studentId);
		
		//AssignmentDto assignment = new AssignmentDto();
		//assignment.setAssignmentId(assignmentId);
		
		SubmissionDto s = subRepository.findByStudentAndAssignment(student, assignment);
		
		if(s.getAdmittedNumber() >= 3)
			return false;
		
		s.setRemark(remark);
		s.setGitLink(gitLink);
		
		int prev = s.getAdmittedNumber();
		prev++;
		s.setAdmittedNumber(prev);
		
		subRepository.save(s);
		return true;
		
	}

	@Override
	public void deleteSubmissionById(Long studentId, Long assignmentId) {
		
		StudentDto student = new StudentDto();
		student.setStudentId(studentId);
		
		AssignmentDto assignment = new AssignmentDto();
		assignment.setAssignmentId(assignmentId);
		
		SubmissionDto s = subRepository.findByStudentAndAssignment(student, assignment);
		
		subRepository.deleteById(s.getSubmissionId());
		
	}

	@Override
	public Map<String, Float> findAllGradesForAssignment(Long assignmentId) {
	
		AssignmentDto assignment = new AssignmentDto();
		assignment.setAssignmentId(assignmentId);
		
		List<SubmissionDto> submissions = subRepository.findByAssignment(assignment);
		Map<String, Float> result = new HashMap<String, Float>();
		
		for(SubmissionDto s : submissions) {
			
			String student = s.getStudent().getFirstName() + " " + s.getStudent().getLastName(); 
			Float grade = s.getGrade();
			
			result.put(student, grade);
			
		}
		
		return result;
	}

	
	
}
