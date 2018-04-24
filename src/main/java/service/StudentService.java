package service;

import java.util.List;

import model.Student;

public interface StudentService {

	List<Student> getAllStudents();

    Student getStudentById(Long studentId);

    Student saveStudent(Student student);

    Student updateStudent(Long studentId, Student student);

    void deleteStudentById(Long studentId);
    
    Student register(String token, String email, String password);
    
    Student login(String email, String password);
	
}
