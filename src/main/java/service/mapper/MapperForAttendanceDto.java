package service.mapper;

import org.modelmapper.PropertyMap;
import dao.dbmodel.AttendanceDto;
import model.Attendance;

public class MapperForAttendanceDto extends PropertyMap<Attendance, AttendanceDto>{

	@Override
	protected void configure() {
		
		map().getLaboratory().setLabId(source.getLabId());
		map().getStudent().setStudentId(source.getStudentId());
		map().setPresent(source.isPresent());
		
	}

}
