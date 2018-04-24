package model;

import java.sql.Timestamp;
import java.util.Date;

public class Assignment {
	
	private Long assignmentId;
	
	private String name;

	private Date deadline;
	
	private String description;
	
	//private LaboratoryClass laboratoryClass;
	
	private Long labId;
	
	public Assignment() {
		
	}
	
	public Assignment(String name, Timestamp deadline, String description, Long labId) {
		
		this.name = name;
		this.deadline = deadline;
		this.setDescription(description);
		//this.setLaboratoryClass(laboratoryClass);
		this.labId = labId;
		
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

	/*public LaboratoryClass getLaboratoryClass() {
		return laboratoryClass;
	}

	public void setLaboratoryClass(LaboratoryClass laboratoryClass) {
		this.laboratoryClass = laboratoryClass;
	}*/

	public Long getLabId() {
		return labId;
	}

	public void setLabId(Long labId) {
		this.labId = labId;
	}

}
