package jpa.testbuildingservices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.entitymodels.Course;

public class TestGetCourseByID {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		int courseID = 8;
		
		String hql = "FROM Course course WHERE course.cId = :cId";
		Query<Course> query = session.createQuery(hql);
		
		query.setParameter("cId", courseID);
		
		Course course = (Course) query.getSingleResult();
		System.out.println(course.getcName());
		
		

	}

}
