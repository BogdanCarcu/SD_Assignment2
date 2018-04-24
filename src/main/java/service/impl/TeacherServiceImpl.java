package service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ITeacherRepository;
import dao.dbmodel.TeacherDto;
import model.Teacher;
import service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

	private final ITeacherRepository teacherRepository;
	private ModelMapper myMapper;
	
	@Autowired
	public TeacherServiceImpl(ITeacherRepository teacherRepository) {
	        this.teacherRepository = teacherRepository;
	        myMapper = new ModelMapper();
	}
	
	@Override
	public Teacher login(String email, String password) {
		
		TeacherDto t = teacherRepository.findByEmailAndPassword(email, password);
		Teacher result = myMapper.map(t, Teacher.class);
		
		return result;
	}
	
}
