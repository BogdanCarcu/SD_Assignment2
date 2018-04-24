package model;

public class Teacher implements IUser{
	
	private Long teacherId;
	
	private String email;
	
	private String password;

	public Teacher() {
		
	}
	
	public Teacher(String email, String password) {
		
		this.email = email;
		this.password = password;
		
	}
	
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
