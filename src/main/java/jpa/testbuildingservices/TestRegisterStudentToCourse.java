package jpa.testbuildingservices;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.entitymodels.StudentCourse;
import jpa.service.StudentService;

public class TestRegisterStudentToCourse {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		StudentService studentService = new StudentService();
//		String email = "hluckham0@google.ru"; //has classes
//		String password = "X1uZcoIh0dj";
		
		String email = "htaffley6@columbia.edu"; //no classes
		String password = "xowtOQ";
		
		int courseID = 70;
		
		if (studentService.validateStudent(email, password)) {
			
			try {
				SessionFactory factory = new Configuration().configure().buildSessionFactory();
				Session session = factory.openSession();
				
				String hql = "FROM StudentCourse WHERE sEmail = :sEmail AND cId = :cId";
				Query query = session.createQuery(hql);
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
			catch (Exception e) {
				System.out.println("Oops, something went wrong.");
			}
		}
		else {
			System.out.println("Invalid email or password.");
		}
	}

}
