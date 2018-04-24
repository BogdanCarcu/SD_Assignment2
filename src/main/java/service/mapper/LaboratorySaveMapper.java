package service.mapper;

import org.modelmapper.PropertyMap;

import dao.dbmodel.AssignmentDto;
import model.Assignment;

public class LaboratorySaveMapper extends PropertyMap<Assignment, AssignmentDto> {

	@Override
	protected void configure() {
		
		map().getLaboratoryClass().setLabId(source.getLabId());
		
	}

}
