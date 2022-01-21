package jpa.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService implements CourseDAO {

	@SuppressWarnings("unchecked")
	public List<Course> getAllCourses() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		String hql = "FROM Course";
		Query<Course> query = session.createQuery(hql);
		
		List<Course> courses = query.getResultList();
		
		return courses;
	}
	
	@SuppressWarnings("unchecked")
	public Course getCourseByID(int id) {
		try {	
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			int courseID = id;
			
			String hql = "FROM Course course WHERE course.cId = :cId";
			Query<Course> query = session.createQuery(hql);
			
			query.setParameter("cId", courseID);
			
			Course course = (Course) query.getSingleResult();
			
			return course;
		}
		catch (NoResultException e) {
			System.out.println("Invalid course ID.");
			return null;
		}
	}

}
