package model;

public class Attendance {
	
	//private Student student;
	
	//private LaboratoryClass laboratory;
	
	private Long studentId;
	
	private Long labId;
	
	private boolean isPresent;

	public Attendance() {
		
	}
	
	public Attendance(//Student student, LaboratoryClass laboratory, 
			boolean isPresent, Long labId, Long studentId) {
		
		//this.student = student;
		//this.laboratory = laboratory;
		this.isPresent = isPresent;
		this.labId = labId;
		this.studentId = studentId;
		
	}
	
	/*public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public LaboratoryClass getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(LaboratoryClass laboratory) {
		this.laboratory = laboratory;
	}*/

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getLabId() {
		return labId;
	}

	public void setLabId(Long labId) {
		this.labId = labId;
	}
	

}
