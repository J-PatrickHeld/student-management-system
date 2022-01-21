package jpa.testbuildingservices;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.entitymodels.Course;

public class TestGetAllCourses {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
//		Session session = factory.getCurrentSession();
		
//		Transaction t = session.beginTransaction();
		
		String hql = "FROM Course";
		Query<Course> query = session.createQuery(hql);
		
		List<Course> courses = query.getResultList();
		
		for (Course course : courses) {
			System.out.println(course.getcId() + "\t" + course.getcName());
		}
		
//		t.commit();
		session.close();

	}

}
