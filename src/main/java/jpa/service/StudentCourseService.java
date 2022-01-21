package jpa.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.dao.StudentCourseDAO;
import jpa.entitymodels.Course;

public class StudentCourseService implements StudentCourseDAO {

	@SuppressWarnings("unchecked")
	public List<Course> getAllStudentCourses(String sEmail) {
		
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
				System.out.println("Could not find student email.");
			}
		} catch (NoResultException e) {
			System.out.println("Could not find student.");
			return null;
		}
		
		
		return courseList;
	}
	
}
