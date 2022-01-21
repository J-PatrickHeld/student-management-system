package jpa.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jpa.service.StudentService;

public class validateStudentTest {


	@Test
	public void testMatchEmailPass() {
		StudentService studentService = new StudentService();
		boolean test = studentService.validateStudent("hguerre5@deviantart.com", "OzcxzD1PGs");
		
		assertEquals(test, true);
	}

	@Test
	public void testBadEmailBadPass() {
		StudentService studentService = new StudentService();
		boolean test = studentService.validateStudent("james.held2@gmail.com", "mypassword");
		
		assertEquals(test, false);
	}
	
	@Test
	public void testGoodEmailBadPass() {
		StudentService studentService = new StudentService();
		boolean test = studentService.validateStudent("tattwool4@biglobe.ne.jp", "myPassword");
		
		assertEquals(test, false);
	}
	
	@Test
	public void testBadEmailGoodPass() {
		StudentService studentService = new StudentService();
		boolean test = studentService.validateStudent("james.held@gmail.com", "TWP4hf5j");
		
		assertEquals(test, false);
	}
	
	@Test
	public void testMismatchEmailPass() {
		StudentService studentService = new StudentService();
		boolean test = studentService.validateStudent("htaffley6@columbia.edu", "W6rJuxd");
		
		assertEquals(test, false);
	}

}
