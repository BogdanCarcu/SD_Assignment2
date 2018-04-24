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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")
public class AssignmentDto {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assignment_id")
	private Long assignmentId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "deadline")
	private Date deadline;
	
	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lab_id", nullable = false)
	private LaboratoryClassDto laboratoryClass; 
	
	@OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
	private Set<SubmissionDto> submissions;
	
	public AssignmentDto() {
		
	}
	
	public AssignmentDto(String name, Timestamp deadline, String description, LaboratoryClassDto laboratoryClass,
			Set<SubmissionDto> submissions) {
		
		this.name = name;
		this.deadline = deadline;
		this.setDescription(description);
		this.setLaboratoryClass(laboratoryClass);
		this.submissions = submissions;
		
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public LaboratoryClassDto getLaboratoryClass() {
		return laboratoryClass;
	}

	public void setLaboratoryClass(LaboratoryClassDto laboratoryClass) {
		this.laboratoryClass = laboratoryClass;
	}

	
}
