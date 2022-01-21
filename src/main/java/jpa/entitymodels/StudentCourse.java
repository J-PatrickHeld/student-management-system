package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_course")
public class StudentCourse {
	
	@Id
	@Column(name="Student_email")
	private String sEmail;
	
	@Column(name="sCourses_id")
	private int cId;
	
	public StudentCourse() {
		this.sEmail = "no email";
	}
	
	public StudentCourse(String sEmail, int cId) {
		this.sEmail = sEmail;
		this.cId = cId;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}
	
}
