package jpa.testbuildingservices;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.entitymodels.Student;

public class TestGetAllStudents {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		String hql = "FROM Student";
		Query<Student> query = session.createQuery(hql);
		
		List<Student> studentList = query.getResultList();
		
		for (Student student: studentList) {
			System.out.println("\nName: " + student.getsName() + "\n | " + " Email: " + student.getsEmail() + "\n | "
					+ " Password: " + student.getsPass());
		}

	}

}
