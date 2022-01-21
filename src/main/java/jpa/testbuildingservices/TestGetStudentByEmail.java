package jpa.testbuildingservices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.entitymodels.Student;

public class TestGetStudentByEmail {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		String email = "qllorens2@howstuffworks.com";
		String hql = "FROM Student S WHERE S.sEmail = :sEmail";
		Query<Student> query = session.createQuery(hql);
		query.setParameter("sEmail", email);
		
		Student student = (Student) query.getSingleResult();
		
		System.out.println("\nName: " + student.getsName() + "\n | " + " Email: " + student.getsEmail() + "\n | "
				+ " Password: " + student.getsPass());

	}

}
