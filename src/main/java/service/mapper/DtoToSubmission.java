package service.mapper;

import org.modelmapper.PropertyMap;

import dao.dbmodel.SubmissionDto;
import model.Submission;

public class DtoToSubmission extends PropertyMap<SubmissionDto, Submission>{

	@Override
	protected void configure() {

		map().setStudentId(source.getStudent().getStudentId());
		map().setAssignmentId(source.getAssignment().getAssignmentId());
		
	}

}
