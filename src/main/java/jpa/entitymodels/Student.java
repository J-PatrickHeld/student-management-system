package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.persistence.Transient;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@Column(name="email")
	private String sEmail;
	
	@Column(name="name")
	private String sName;
	
	@Column(name="password")
	private String sPass;
	
//	@OneToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
	@ManyToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
//	@ManyToOne(targetEntity = Course.class, cascade = CascadeType.ALL)
	private List<Course> sCourses;
	
	public Student() {
		this.sEmail = "no email on file";
		this.sName = "no name on file";
		this.sPass = "no password on file";
		this.sCourses = new ArrayList<Course>();
	}
	
	public Student(String sEmail, String sName, String sPass) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = new ArrayList<Course>();
	}
	
	public String getsEmail() {
		return this.sEmail;
	}
	
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}
	
	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}
}
