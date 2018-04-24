package service;

import model.Teacher;

public interface TeacherService {

	Teacher login(String email, String password);
	
}
