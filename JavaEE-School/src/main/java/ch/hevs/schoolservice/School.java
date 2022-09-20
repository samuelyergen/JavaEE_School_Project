package ch.hevs.schoolservice;

import java.util.List;

import javax.ejb.Local;

import ch.hevs.businessobject.Mark;
import ch.hevs.businessobject.Student;
import ch.hevs.businessobject.Subject;
import ch.hevs.businessobject.Teacher;

@Local
public interface School {
	
	List<Student> getStudents();
	List<Subject> getSubjects();
	List<Teacher> getTeachers();
	List<Double> getMarks(String studentMail, String Subject);	
	List<String> getSubjectsByStudent(Student student);
	List<String> getSubjectsByTeacher(Teacher teacher);
	List<Teacher> getTeacherBySubject(String subjectName);
	Student getStudent(String mail);
	Subject getSubject(String title);
	Teacher getTeacher(String mail);
	
	void gradeToSubject(Subject subject, Student student, Mark grade) throws Exception;
	void addStudentToSubject(Subject subject, Student student) throws Exception;
	void addTeacherToSubject(Subject subject, Teacher teacher) throws Exception;
	void createSubject(String name) throws Exception;
	void createStudent(String firstname, String lastname, String mail) throws Exception;
	void createTeacher(String firstname, String lastname, String mail) throws Exception;

	
	void modifySubject(Subject subject, String name) throws Exception;			
	void modifyStudent(Student student, String firstname, String lastname);
		
	void deleteStudents(Student student);
	void deleteSubject(Subject subject);
	void deleteTeacher(Teacher teacher);
}	
