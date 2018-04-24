package dao.dbmodel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentDto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Long studentId;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "`group`")
	private String group;
	
	@Column(name = "hobby")
	private String hobby;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "token")
	private String token;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private Set<AttendanceDto> attendances;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private Set<SubmissionDto> submissions;
	
	public StudentDto() {
		
	}
	
	public StudentDto(String firstName, String lastName, String email, String password,
			String group, String hobby, String token, Set<AttendanceDto> attendances, 
			Set<SubmissionDto> submissions) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.group = group;
		this.hobby = hobby;
		this.token = token;
		this.attendances = attendances;
		this.submissions = submissions;
	
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<AttendanceDto> getAttendances() {
		return attendances;
	}

	public void setAttendances(Set<AttendanceDto> attendances) {
		this.attendances = attendances;
	}

	public Set<SubmissionDto> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(Set<SubmissionDto> submissions) {
		this.submissions = submissions;
	}
	
}
