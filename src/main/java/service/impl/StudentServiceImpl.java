package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IStudentRepository;
import dao.dbmodel.StudentDto;
import model.Student;
import service.StudentService;


@Service
public class StudentServiceImpl implements StudentService{

	private final IStudentRepository studentRepository;
	private ModelMapper myMapper;
	
	private String generateRandomToken() {
		
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 128;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
		
	    return generatedString;
	}
	
	@Autowired
	public StudentServiceImpl(IStudentRepository studentRepository) {
	        this.studentRepository = studentRepository;
	        myMapper = new ModelMapper();
	}
	
	@Override
	public List<Student> getAllStudents() {
		
		List<StudentDto> students = studentRepository.findAll();
    	List<Student> result = new ArrayList<Student>();
    	
    	for(StudentDto s: students) {
			
			Student ns = myMapper.map(s, Student.class);
			result.add(ns);
			
		}
    	
        return result;
	
	}

	@Override
	public Student getStudentById(Long studentId) {
		
		Optional<StudentDto> studentDto = studentRepository.findById(studentId);
    	Student s = myMapper.map(studentDto.get(), Student.class);
    	
    	return s;
	}

	@Override
	public Student saveStudent(Student student) {
		
		StudentDto studentDto = myMapper.map(student, StudentDto.class);
		String token = generateRandomToken();
		studentDto.setToken(token);
		studentDto.setPassword(null);
		
		student.setToken(token);
		student.setPassword(null);
		
        if (studentRepository.findByEmail(student.getEmail()) == null) {

            studentRepository.save(studentDto);
            return student;

        }
        
        return null;
	}

	@Override
	public Student updateStudent(Long studentId, Student student) {
		
		Optional<StudentDto> studentToBeUpdated = studentRepository.findById(studentId);
		StudentDto newStudentDto = myMapper.map(student, StudentDto.class);

        if (studentToBeUpdated != null) {
        	
            studentRepository.save(newStudentDto);
            
            return student;
            
        } else {
            return null;
        }
	}

	@Override
	public void deleteStudentById(Long studentId) {
		
		studentRepository.deleteById(studentId);
		
	}

	@Override
	public Student register(String token, String email, String password) {
		
		StudentDto studentDto = studentRepository.findByEmail(email);
		if(studentDto.getToken().equals(token)) {
			
			studentDto.setPassword(password);
			studentRepository.save(studentDto);
			
			Student retStudent = myMapper.map(studentDto, Student.class);
			return retStudent;
		}
		
		return null;
	}

	@Override
	public Student login(String email, String password) {
		
		StudentDto studentDto = studentRepository.findByEmailAndPassword(email, password);
		
		if(studentDto != null) {
			
			Student retStudent = myMapper.map(studentDto, Student.class);
			return retStudent;
		}
		
		return null;
		
	}

}
