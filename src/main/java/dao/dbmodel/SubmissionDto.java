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
@Table(name = "submission")
public class SubmissionDto implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "submission_id")
	private Long submissionId;
	
    @ManyToOne
    @JoinColumn(name = "student_id")
	private StudentDto student;
	
    @ManyToOne
    @JoinColumn(name = "assignment_id")
	private AssignmentDto assignment;
	
	@Column(name = "grade")
	private float grade;
	
	@Column(name = "git_link")
	private String gitLink;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "admitted_number")
	private int admittedNumber;
	
	public SubmissionDto() {
		setAdmittedNumber(0);
	}
	
	public SubmissionDto(StudentDto student, AssignmentDto assignment, float grade, String gitLink, String remark) {
		
		setAdmittedNumber(0);
		this.student = student;
		this.assignment = assignment;
		this.grade = grade;
		this.gitLink = gitLink;
		this.remark = remark;
		
	}

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	public AssignmentDto getAssignment() {
		return assignment;
	}

	public void setAssignment(AssignmentDto assignment) {
		this.assignment = assignment;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public String getGitLink() {
		return gitLink;
	}

	public void setGitLink(String gitLink) {
		this.gitLink = gitLink;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public int getAdmittedNumber() {
		return admittedNumber;
	}

	public void setAdmittedNumber(int admittedNumber) {
		this.admittedNumber = admittedNumber;
	}

}
