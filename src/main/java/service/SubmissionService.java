package service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import model.Submission;

@Service
public interface SubmissionService {
	
	List<Submission> getAllSubmissions();

	List<Submission> getSubmissionsByStudentId(Long studentId);
	
	List<Submission> getSubmissionsByAssignmentId(Long assignmentId);
	
	Submission getSubmissionByStudentAndAssignment(Long studentId, Long assignmentId);

	Submission saveSubmission(Submission submission);

	Submission updateSubmission(Submission submission);
	
	public boolean markSubmission(Long studentId, Long assignmentId, float grade);
	
	public boolean resubmit(Long studentId, Long assignmentId, String remark, String gitLink);

    void deleteSubmissionById(Long studentId, Long assignmentId);
    
    Map<String, Float> findAllGradesForAssignment(Long assingmentId);

}
