/*
 * Filename: SMSRunner.java
* Author: Stefanski
* 02/25/2020 
 */
package jpa.mainrunner;

import static java.lang.System.out;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentCourseService;
import jpa.service.StudentService;

/**1
 * 
 * @author Harry
 *
 */
public class SMSRunner {

	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		try {
			sms.run();
		}
		catch (InputMismatchException e) {
			System.out.println("Invalid input.");
		}
	}

	private void run() {
		// Login or quit
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			}
			break;
		case 2:
			out.println("Goodbye!");
			break;

		default:

		}
	}

	private int menu1() {
		sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		Student student = studentService.getStudentByEmail(email);
		currentStudent = student;
		if (currentStudent != null && currentStudent.getsPass().equals(password)) {
			List<Course> courses = studentService.getStudentCourses(email);
			out.println("MyClasses");
			for (Course course : courses) {
				out.println(course);
			}
			retValue = true;
		}
		else {
			out.println("User Validation failed. GoodBye!");
		}
		return retValue;
	}

	private void registerMenu() {
		sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		switch (sin.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();
			List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
			allCourses.removeAll(studentCourses);
			out.printf("%-5s%-30s%-20s%n", "ID", "Course", "Instructor");
			for (Course course : allCourses) {
				out.printf("%-5s%-30s%-20s%n", course.getcId(), course.getcName(), course.getcInstructorName());
			}
			out.println();
			out.print("Enter Course Number: ");
			int number = sin.nextInt();
			Course newCourse = courseService.getCourseByID(number);

			if (newCourse != null) {
				studentService.registerStudentToCourse(currentStudent.getsEmail(), newCourse.getcId());
				Student temp = studentService.getStudentByEmail(currentStudent.getsEmail());
				
				StudentCourseService scService = new StudentCourseService();
				List<Course> sCourses = scService.getAllStudentCourses(temp.getsEmail());
				
				out.println("MyClasses");
				out.printf("%-5s%-30s%-20s%n", "ID", "Course", "Instructor");
				for (Course course : sCourses) {
					out.printf("%-5s%-30s%-20s%n", course.getcId(), course.getcName(), course.getcInstructorName());
				}
			}
			else {
				System.out.println("Registration failed.");
			}
			break;
		case 2:
		default:
			out.println("Goodbye!");
		}
	}
}
