package dao.dbmodel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "laboratory_class")
public class LaboratoryClassDto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lab_id")
	private Long labId;
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "title")	
	private String title;
	
	@Column(name = "curricula")
	private String curricula;
	
	@Column(name = "labText")
	private String labText;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "laboratoryClass",  cascade = CascadeType.ALL)
	private Set<AssignmentDto> assignments;
	
	@OneToMany(mappedBy = "laboratory", cascade = CascadeType.ALL)
	private Set<AttendanceDto> attendances;
	
	public LaboratoryClassDto() {
		
	}
	
	public LaboratoryClassDto(int number, Timestamp date, String title,
			Set<AssignmentDto> assignments, String curricula, String labText) {
		
		this.number = number;
		this.date = date;
		this.title = title;
		this.curricula = curricula;
		this.labText = labText;
		this.assignments = assignments;
		
	}

	public Long getLabId() {
		return labId;
	}

	public void setLabId(Long labId) {
		this.labId = labId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCurricula() {
		return curricula;
	}

	public void setCurricula(String curricula) {
		this.curricula = curricula;
	}

	public String getLabText() {
		return labText;
	}

	public void setLabText(String labText) {
		this.labText = labText;
	}


	public Set<AssignmentDto> getAssignments() {
		return assignments;
	}

	public void setAssignments(Set<AssignmentDto> assignments) {
		this.assignments = assignments;
	}
		

	public Set<AttendanceDto> getAttendances() {
		return attendances;
	}

	public void setAttendances(Set<AttendanceDto> attendances) {
		this.attendances = attendances;
	}
	
}
