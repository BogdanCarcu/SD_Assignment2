package dao.dbmodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "attendance")
public class AttendanceDto implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attendance_id")
	private Long attendanceId;
	
    @ManyToOne
    @JoinColumn(name = "student_id")
	private StudentDto student;
	
    @ManyToOne
    @JoinColumn(name = "lab_id")
	private LaboratoryClassDto laboratory; 
	
	@Column(name = "is_present")
	private boolean isPresent;
	
	public AttendanceDto() {
		
	}
	
	public AttendanceDto(StudentDto student, LaboratoryClassDto laboratory, boolean isPresent) {
		
		this.student = student;
		this.laboratory = laboratory;
		this.isPresent = isPresent;
		
	}

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	public LaboratoryClassDto getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(LaboratoryClassDto laboratory) {
		this.laboratory = laboratory;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	
}
