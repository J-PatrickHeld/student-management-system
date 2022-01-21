package jpa.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourse;

public class StudentService implements StudentDAO {

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		try {	
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			String hql = "FROM Student";
			Query<Student> query = session.createQuery(hql);
			
			List<Student>studentList = query.getResultList();
			return studentList;
		}
		catch (Exception e) {
			System.out.println("Oops. Something went wrong.");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Student getStudentByEmail(String sEmail) {
		try {	
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			String email = sEmail;
			String hql = "FROM Student S WHERE S.sEmail = :sEmail";
			Query<Student> query = session.createQuery(hql);
			query.setParameter("sEmail", email);
			
			Student student = (Student) query.getSingleResult();
			
			System.out.println("\nName: " + student.getsName() + "\n | " + " Email: " + student.getsEmail());
			
			return student;
		}
		catch (NoResultException e) {
			System.out.println("User not found.");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean validateStudent(String sEmail, String sPassword) {
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			String email = sEmail;
			String password = sPassword;
			
			String hql = "SELECT S FROM Student S WHERE S.sEmail = :sEmail AND S.sPass = :sPass";
			Query<Student> query = session.createQuery(hql);
			
			query.setParameter("sEmail", email);
			query.setParameter("sPass", password);
			
			Student student = (Student) query.getSingleResult();
			
			System.out.println("\nName: " + student.getsName() + "\n | " + " Email: " + student.getsEmail());
			
			return true;
		}
		catch (NoResultException e) {
			System.out.println("No such user found.");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public void registerStudentToCourse(String sEmail, int cId) {
		String email = sEmail;
		int courseID = cId;
		
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			String hql = "FROM StudentCourse WHERE sEmail = :sEmail AND cId = :cId";
			Query<StudentCourse> query = session.createQuery(hql);
			query.setParameter("sEmail", email);
			query.setParameter("cId", courseID);
						
			List<StudentCourse> studentCourses = query.getResultList();
			
			if (!studentCourses.isEmpty()) {
				for (StudentCourse course : studentCourses) {
					System.out.println("Student Email: " + course.getsEmail() + "\n\t|" + "Course ID: " + course.getcId());
				}
				System.out.println("Student already registered for course.");
			}
			else {
				System.out.println("Student Email: " + email + "\n\t|" + "Course ID: " + courseID);
				System.out.println("Registering student for course.");
				StudentCourse newCourse = new StudentCourse(email, courseID);
				
				Transaction t = session.beginTransaction();
				session.save(newCourse);
				t.commit();
				System.out.println("Student successfully registered.");
			}
		}
		catch (NoResultException e) {
			System.out.println("Oops, something went wrong.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Course> getStudentCourses(String sEmail) {
		
		List<Course> courseList = null;
		
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			String email = "'" + sEmail + "'";
			
			String hql = "SELECT C "
					+ "FROM Course C "
					+ "JOIN StudentCourse as sc ON sc.cId=C.id "
					+ "WHERE sc.sEmail = "
					+ email;
			Query<Course> query = session.createQuery(hql);
			
			courseList = query.getResultList();
			
			if (courseList.isEmpty()) {
				System.out.println("Not registered for any courses.");
				//return null;  //returning null could create problems in Runner program
			}
		} catch (NoResultException e) {
			System.out.println("Could not find student.");
			return null;
		}
		
		
		return courseList;
	}

}
