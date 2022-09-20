package ch.hevs.schoolservice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.Mark;
import ch.hevs.businessobject.Student;
import ch.hevs.businessobject.Subject;
import ch.hevs.businessobject.Teacher;

@Stateless
public class SchoolBean implements School{

	@PersistenceContext(name = "schoolPU", type=PersistenceContextType.TRANSACTION)
	private EntityManager em;

	//For mock up
	public List<Student> getStudents() {
		
		Query query = em.createQuery("FROM Student");
				
		return (List<Student>) query.getResultList();	
	}
	
	public List<Teacher> getTeachers() {
		Query query = em.createQuery("FROM Teacher");
		
		return (List<Teacher>) query.getResultList();	
	}
	
	public Student getStudent(String mail) {
		
		Query query = em.createQuery("FROM Student s WHERE s.mail=:mail");
		query.setParameter("mail", mail);
		
		
		return (Student) query.getSingleResult();		
	}
	
	public Teacher getTeacher(String mail) {
		Query query = em.createQuery("FROM Teacher s WHERE s.mail=:mail");
		query.setParameter("mail", mail);
		
		
		return (Teacher) query.getSingleResult();	
	}
	
	
	public List<Subject> getSubjects(){
		
		Query query = em.createQuery("FROM Subject");
			
		return (List<Subject>) query.getResultList();			
	}
	
	public Subject getSubject(String title) {
		
		Query query = em.createQuery("FROM Subject s WHERE s.subject=:title");
		query.setParameter("title", title);
			
		return (Subject) query.getSingleResult();			
	}
	
	public List<Double> getMarks(String mail, String subject){
		Student stud = getStudent(mail);
		Subject subj = getSubject(subject);
		
		Query query = em.createNativeQuery("SELECT grade FROM Mark WHERE FK_STUDENT=:id_student"
				+ " AND FK_SUBJECT=:id_subject");
		query.setParameter("id_subject", subj.getId());
		query.setParameter("id_student", stud.getId());
		
		return (List<Double>) query.getResultList();
	}
	
	public List<String> getSubjectsByStudent(Student student){
		
		Query query = em.createNativeQuery("SELECT subjects_id FROM subject_student s WHERE s.students_id=:FK_STUDENT");
		query.setParameter("FK_STUDENT",student.getId());
		
		List<BigInteger> subjectsIds =  (List<BigInteger>) query.getResultList();
	
		List<String> subjects = new ArrayList<String>();
		Query query2 = em.createNativeQuery("SELECT s.subject FROM Subject s WHERE s.id=:subject_id");
		
		for(BigInteger l : subjectsIds) {
			query2.setParameter("subject_id", l);
			String s = (String) query2.getSingleResult();
			subjects.add(s);
		}
		
		return subjects ;
	}
	
	public List<String> getSubjectsByTeacher(Teacher teacher) {
		Query query = em.createNativeQuery("SELECT subjects_id FROM subject_teacher s WHERE s.teachers_id=:FK_TEACHER");
		query.setParameter("FK_TEACHER",teacher.getId());
		
		List<BigInteger> subjectsIds =  (List<BigInteger>) query.getResultList();
	
		List<String> subjects = new ArrayList<String>();
		Query query2 = em.createNativeQuery("SELECT s.subject FROM Subject s WHERE s.id=:subject_id");
		
		for(BigInteger l : subjectsIds) {
			query2.setParameter("subject_id", l);
			String s = (String) query2.getSingleResult();
			subjects.add(s);
		}
		
		return subjects ;
	}
	
	public void gradeToSubject(Subject subject, Student student, Mark grade) throws Exception{		
		grade.setStudent(student);
		grade.setSubject(subject);
		em.persist(grade);
	}
	
	public void addStudentToSubject(Subject subject, Student student) throws Exception{
		em.createNativeQuery("INSERT INTO Subject_student(subjects_id,students_id) values(:subject_id,:student_id);")
		.setParameter("subject_id", subject.getId())
		.setParameter("student_id", student.getId())
		.executeUpdate();
	}
	
	public void addTeacherToSubject(Subject subject, Teacher teacher) throws Exception {
		em.createNativeQuery("INSERT INTO Subject_teacher(subjects_id,teachers_id) values(:subject_id,:teacher_id);")
		.setParameter("subject_id", subject.getId())
		.setParameter("teacher_id", teacher.getId())
		.executeUpdate();
		
	}
	
	public void createSubject(String name) throws Exception{
		Subject subject = new Subject(name);
		em.persist(subject);
	}
	
	
	public void createStudent(String firstname, String lastname, String mail) throws Exception {
		Student student = new Student(firstname,lastname, mail);
		em.persist(student);
	}
	
	public void createTeacher(String firstname, String lastname, String mail) throws Exception {
		Teacher teacher = new Teacher(firstname, lastname, mail);
		em.persist(teacher);
	}
	
	
	public void modifySubject(Subject subject, String name) throws Exception{
		Subject realSubject = em.merge(subject);
		realSubject.setSubject(name);
	}

	
	public void modifyStudent(Student student, String firstname, String lastname) {
		Student realStudent = em.merge(student);
		realStudent.setFirstName(firstname);
		realStudent.setName(lastname);
	}

	
	public void deleteStudents(Student student) {	
		Student realStudent = em.merge(student);
		em.remove(realStudent);
		
	}

	
	public void deleteSubject(Subject subject) {		
		Subject realSubject = em.merge(subject);
		realSubject.setRemoved(true);
		
	}

	
	public void deleteTeacher(Teacher teacher) {
		Teacher realTeacher = em.merge(teacher);
		em.remove(realTeacher);
		
	}

	
	public List<Teacher> getTeacherBySubject(String subjectName) {
		Subject subject = getSubject(subjectName);
		
		Query query = em.createNativeQuery("SELECT teachers_id FROM subject_teacher s WHERE s.subjects_id=:FK_SUBJECT");
		query.setParameter("FK_SUBJECT",subject.getId());
		
		List<BigInteger> teachersIds =  (List<BigInteger>) query.getResultList();
		
		List<Teacher> teachers = new ArrayList<Teacher>();
		Query query2 = em.createQuery("FROM Teacher t WHERE t.id=:teacher_id");
		
		for(BigInteger l : teachersIds) {
			long lo = l.longValue();			 
			query2.setParameter("teacher_id", lo);
			Teacher t = (Teacher) query2.getSingleResult();
			teachers.add(t);
		}
		
		return teachers ;
	}

	
	

	
	

	
	

	
	


	
}
