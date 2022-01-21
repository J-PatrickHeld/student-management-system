Database used: MariaDB
User: student
Password: student

I used a @ManyToMany relationship, but the intermediate table created by Hibernate sets email as the primary key,
which can't have duplicates. So when I try to register a student who already has courses (is already
in the student_course table), it doesn't allow it. I got around this by setting the email column to be
a normal foreign key, not unique, which means the table has no primary key (which I understand is not
best practice, but it's the only work-around I could find for this assignment - I'm sure there are better
options out there, but I couldn't seem to find one that worked for the scope of this project).After that
change, I can add any courses to any students.

SQL scripts used to create tables, add entities, and test queries are included in the
src/main/resources file.

JUnit test is in the src/test/java folder, jpa.test package, validateStudentTest class.

Normally I would override the equals method for the Student and Course entities (for testing purposes), 
to compare all of the fields inside the entities, 
but it wasn't a requirement of the assignment, 
and I'd like to be done so I can enjoy the holiday weekend. 
I hope this is OK.