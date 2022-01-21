package jpa.dao;

import java.util.List;

import jpa.entitymodels.Course;

public interface StudentCourseDAO {
	public List<Course> getAllStudentCourses(String email);
}
