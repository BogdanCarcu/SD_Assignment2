package service.mapper;

import org.modelmapper.PropertyMap;

import dao.dbmodel.AssignmentDto;
import model.Assignment;

public class LaboratoryIdMapper extends PropertyMap<AssignmentDto, Assignment> {

	
	@Override
	protected void configure() {
		
		map().setLabId(source.getLaboratoryClass().getLabId());
		
	}
	
}
