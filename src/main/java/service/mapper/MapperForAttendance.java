package service.mapper;
import model.Attendance;

import org.modelmapper.PropertyMap;

import dao.dbmodel.AttendanceDto;

public class MapperForAttendance extends PropertyMap<AttendanceDto, Attendance>{

	@Override
	protected void configure() {
	
		map().setLabId(source.getLaboratory().getLabId());
		map().setStudentId(source.getStudent().getStudentId());
		map().setPresent(source.isPresent());
		
	}
	
	

}
