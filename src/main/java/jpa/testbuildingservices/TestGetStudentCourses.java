package jpa.testbuildingservices;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.entitymodels.Course;

public class TestGetStudentCourses {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		List<Course> courseList = null;
		
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			String email = "'" + "htaffley6@columbia.edu" + "'";
			
			String hql = "SELECT C "
					+ "FROM Course C "
					+ "JOIN StudentCourse as sc ON sc.cId=C.id "
					+ "WHERE sc.sEmail = "
					+ email;
			Query<Course> query = session.createQuery(hql);
			
			courseList = query.getResultList();
			
			if (courseList.isEmpty()) {
				System.out.println("Could not find student email.");
			}
			else {
				for (Course course : courseList) {
					System.out.println(course.getcName());
				}
			}
		} catch (NoResultException e) {
			System.out.println("Could not find student.");
		}
		
	}

}
