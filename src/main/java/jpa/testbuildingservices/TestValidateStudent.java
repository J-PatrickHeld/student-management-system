package jpa.testbuildingservices;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.entitymodels.Student;

public class TestValidateStudent {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			
			String email = "cjaulme9@bing.com";
			String password = "FnVklVgC6r6";
			
			String hql = "SELECT S FROM Student S WHERE S.sEmail = :sEmail AND S.sPass = :sPass";
			Query<Student> query = session.createQuery(hql);
			
			query.setParameter("sEmail", email);
			query.setParameter("sPass", password);
			
			Student student = (Student) query.getSingleResult();
			
			System.out.println("\nName: " + student.getsName() + "\n | " + " Email: " + student.getsEmail() + "\n | "
					+ " Password: " + student.getsPass());
		}
		catch (NoResultException e) {
			System.out.println("No such user found.");
		}
		

	}

}
