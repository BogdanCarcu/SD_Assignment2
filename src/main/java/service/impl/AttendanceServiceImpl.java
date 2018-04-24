package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IAttendanceRepository;
import dao.dbmodel.AttendanceDto;
import dao.dbmodel.LaboratoryClassDto;
import dao.dbmodel.StudentDto;
import model.Attendance;
import service.AttendanceService;
import service.mapper.MapperForAttendance;

@Service
public class AttendanceServiceImpl implements AttendanceService{

	private IAttendanceRepository attRepository;
	private ModelMapper myMapper;
	
	@Autowired
	public AttendanceServiceImpl(IAttendanceRepository attRepository) {
	        this.attRepository = attRepository;
	        myMapper = new ModelMapper();
	        myMapper.addMappings(new MapperForAttendance());
	   
	}
	
	
	@Override
	public List<Attendance> getAllAttendances() {
		
		List<AttendanceDto> attendances = attRepository.findAll();
    	List<Attendance> result = new ArrayList<Attendance>();
    	
    	for(AttendanceDto a: attendances) {
			
			Attendance att = myMapper.map(a, Attendance.class);
			result.add(att);
			
		}
    	
        return result;
	}


	@Override
	public Attendance getAttendancesById(Long idStud, Long idLab) {
		
		StudentDto student = new StudentDto();
		student.setStudentId(idStud);
		
		LaboratoryClassDto lab = new LaboratoryClassDto();
		lab.setLabId(idLab);
		
		AttendanceDto attendance = attRepository.findByStudentAndLaboratory(student, lab);
		Attendance a = myMapper.map(attendance, Attendance.class);
		
		return a;
	}


	@Override
	public List<Attendance> getAttendancesByLaboratoryId(Long id) {
		
		LaboratoryClassDto lab = new LaboratoryClassDto();
		lab.setLabId(id);
		
		List<AttendanceDto> list = attRepository.findByLaboratory(lab);
		List<Attendance> newList = new ArrayList<Attendance>();
		
		for(AttendanceDto a : list) {
			
			Attendance na = myMapper.map(a,  Attendance.class);
			newList.add(na);
			
		}	
		
		return newList;
	}


	@Override
	public List<Attendance> getAttendancesByStudentId(Long id) {
		
		StudentDto student = new StudentDto();
		student.setStudentId(id);
		
		List<AttendanceDto> list = attRepository.findByStudent(student);
		List<Attendance> newList = new ArrayList<Attendance>();
		

		for(AttendanceDto a : list) {
			
			Attendance na = myMapper.map(a,  Attendance.class);
			newList.add(na);
			
		}	
		
		return newList;
	}


	@Override
	public Attendance saveAttendance(Attendance attendance) {
		
		StudentDto student = new StudentDto();
		student.setStudentId(attendance.getStudentId());
		
		LaboratoryClassDto lab = new LaboratoryClassDto();
		lab.setLabId(attendance.getLabId());
		
		if(attRepository.findByStudentAndLaboratory(student, lab) == null) {
		
			AttendanceDto att = new AttendanceDto();
			att.setStudent(student);
			att.setLaboratory(lab);
			att.setPresent(attendance.isPresent());
			
			attRepository.save(att);
			return attendance;
		}
		
		
		return null;
	}


	@Override
	public void deleteAttendanceById(Long studId, Long labId) {
		
		StudentDto student = new StudentDto();
		student.setStudentId(studId);
		
		LaboratoryClassDto lab = new LaboratoryClassDto();
		lab.setLabId(labId);
		
		AttendanceDto a = attRepository.findByStudentAndLaboratory(student, lab);
		
		attRepository.deleteById(a.getAttendanceId());
		
	}


	@Override
	public Attendance updateAttendance(Attendance attendance) {
		
		StudentDto student = new StudentDto();
		student.setStudentId(attendance.getStudentId());
		
		LaboratoryClassDto lab = new LaboratoryClassDto();
		lab.setLabId(attendance.getLabId());
		
		AttendanceDto a = attRepository.findByStudentAndLaboratory(student, lab);
		
		AttendanceDto ai = new AttendanceDto();
		ai.setAttendanceId(a.getAttendanceId());
		ai.setLaboratory(a.getLaboratory());
		ai.setStudent(a.getStudent());
		ai.setPresent(attendance.isPresent());
		
		attRepository.save(ai);
		
		return attendance;
	}


}
