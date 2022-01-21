CREATE USER student IDENTIFIED BY 'student';

GRANT ALL PRIVILEGES ON sms.* TO 'student';
FLUSH PRIVILEGES;

SHOW GRANTS FOR 'student';

CREATE TABLE student_table;
CREATE TABLE course_table;

INSERT INTO student_course (Student_email, sCourses_id) VALUES ('hluckham0@google.ru', 4);
INSERT INTO student_course (Student_email, sCourses_id) VALUES ('hluckham0@google.ru', 6);

/*Experimenting with below SQL queries to construct appropriate HQL for java services*/
SELECT course.name, course.id
FROM course
JOIN student_course ON student_course.sCourses_id=course.id
WHERE student_course.Student_email = 'hluckham0@google.ru';

SELECT sCourses_id, Student_email
FROM student_course
WHERE student_course.Student_email = 'hluckham0@google.ru'
AND student_course.sCourses_id = 3;