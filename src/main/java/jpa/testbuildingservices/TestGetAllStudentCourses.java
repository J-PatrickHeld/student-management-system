package jpa.testbuildingservices;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.entitymodels.Course;

public class TestGetAllStudentCourses {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
			
		try {	
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			String email = "'hluckham0@google.ru'";
//			String email = "'notexists@email.com'";
			
			String hql = "SELECT C "
					+ "FROM Course C "
					+ "JOIN StudentCourse as sc ON sc.cId=C.id "
					+ "WHERE sc.sEmail = "
					+ email;
			Query<Course> query = session.createQuery(hql);
			
			List<Course> courseList = query.getResultList();
			
			if (courseList.isEmpty()) {
				System.out.println("Could not find student email.");
			}
			for (Course course: courseList) {
				System.out.println(course.getcName());
			}
		} catch(Exception e) {
			System.out.println("No such student found.");
		}

	}

}
