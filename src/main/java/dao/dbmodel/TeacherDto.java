package dao.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class TeacherDto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id")
	private Long teacherId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	public TeacherDto() {
		
	}
	
	public TeacherDto(String email, String password) {
		
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
