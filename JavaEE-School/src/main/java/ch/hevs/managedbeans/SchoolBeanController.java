package ch.hevs.managedbeans;

import java.util.ArrayList;



import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Mark;
import ch.hevs.businessobject.Student;
import ch.hevs.businessobject.Subject;
import ch.hevs.businessobject.Teacher;
import ch.hevs.schoolservice.School;


@ManagedBean(eager=true)
public class SchoolBeanController {
		
	
	
	// list used for student activities
	private List<Student> students;
	private List<String> studentNames;
	private List<String> studentMails ;
	private List<String> studentFirstnames;
	private List<Subject> studentSubjects;
	private List<String> studentSubjectNames;
	private String studentSubjectName;
	
	// list used for teacher activities
	private List<Teacher> teachers ;
	private List<String> teacherMails ;
	private List<Teacher> teachersForSubject;
	private List<String> teacherSubjectNames ;
	
	// lists used for subjects activities
    private List<Subject> subjects;
    private List<String> subjectNames;
    
    // lists used for grades activities
    private List<Mark> marks;
    private List<Double> grades ;
    private double average ;
  
	//input values
	private double givenGrade;
	private String studentName;
	private String nameInput;
	private String studentFirstname ;
	private String firstnameInput ;
	private String subjectName;
	private String subjectNameInput;
	private String studentMail ;
	private String mailInput ;
	private String teacherMail ;
	
	//init
	private String userRole; 
	private String transactionResult;
	private School school;
	private String returnValue;
	
	
	  
	@PostConstruct 
	public void initialize() throws NamingException {
	  
	  // use JNDI to inject reference to school EJB 
	  InitialContext ctx = new InitialContext(); 
	  school = (School) ctx.lookup("java:global/JavaEE-School-0.0.1-SNAPSHOT/SchoolBean!ch.hevs.schoolservice.School");
	  
	  	  
	  // initialize lists
	  initializeStudentLists();
	  initializeSubjectLists();
	  initializeTeacherLists();	 
	}
	
		
//----------------------------------------------------------Getters, setters
	
		
	public String getUserRole() {
		return userRole;
	}
	
	public List<String> getTeacherMails() {
		return teacherMails;
	}

	public void setTeacherMails(List<String> teacherMails) {
		this.teacherMails = teacherMails;
	}

	public String getTeacherMail() {
		return teacherMail;
	}

	public void setTeacherMail(String teacherMail) {
		this.teacherMail = teacherMail;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<String> getStudentMails() {
		return studentMails;
	}

	public void setStudentMails(List<String> studentMails) {
		this.studentMails = studentMails;
	}

	public String getNameInput() {
		return nameInput;
	}

	public void setNameInput(String nameInput) {
		this.nameInput = nameInput;
	}

	public String getFirstnameInput() {
		return firstnameInput;
	}

	public void setFirstnameInput(String firstnameInput) {
		this.firstnameInput = firstnameInput;
	}

	public String getMailInput() {
		return mailInput;
	}

	public void setMailInput(String mailInput) {
		this.mailInput = mailInput;
	}

	public String getStudentMail() {
		return studentMail;
	}

	public void setStudentMail(String studentMail) {
		this.studentMail = studentMail;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<String> getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(List<String> studentNames) {
		this.studentNames = studentNames;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<String> getSubjectNames() {
		return subjectNames;
	}

	public void setSubjectNames(List<String> subjectNames) {
		this.subjectNames = subjectNames;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public List<Subject> getStudentSubjects() {
		return studentSubjects;
	}

	public void setStudentSubjects(List<Subject> studentSubjects) {
		this.studentSubjects = studentSubjects;
	}

	public List<String> getStudentSubjectNames() {
		return studentSubjectNames;
	}

	public void setStudentSubjectNames(List<String> studentSubjectNames) {
		this.studentSubjectNames = studentSubjectNames;
	}

	public String getStudentSubjectName() {
		return studentSubjectName;
	}

	public void setStudentSubjectName(String studentSubjectName) {
		this.studentSubjectName = studentSubjectName;
	}

	public double getGivenGrade() {
		return givenGrade;
	}

	public void setGivenGrade(double givenGrade) {
		this.givenGrade = givenGrade;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectNameInput() {
		return subjectNameInput;
	}

	public void setSubjectNameInput(String subjectNameInput) {
		this.subjectNameInput = subjectNameInput;
	}

	public String getTransactionResult() {
		return transactionResult;
	}

	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	
	 public List<String> getStudentFirstnames() {
		return studentFirstnames;
	}

	public void setStudentFirstnames(List<String> studentFirstnames) {
		this.studentFirstnames = studentFirstnames;
	}

	public String getStudentFirstname() {
		return studentFirstname;
	}

	public void setStudentFirstname(String studentFirstname) {
		this.studentFirstname = studentFirstname;
	}
	
	public List<Double> getGrades() {
		return grades;
	}

	public List<Teacher> getTeachersForSubject() {
		return teachersForSubject;
	}


	public void setTeachersForSubject(List<Teacher> teachersForSubject) {
		this.teachersForSubject = teachersForSubject;
	}


	public void setGrades(List<Double> grades) {
		this.grades = grades;
	}


	public double getAverage() {
		return average;
	}


	public void setAverage(double average) {
		this.average = average;
	}
	

	
	//----------------------------------------------------------Updates
	


	//update student name
	public void updateStudentName(ValueChangeEvent event) {
    	this.nameInput = (String)event.getNewValue();
    }
	
	public void updateStudentFirstname(ValueChangeEvent event) {
    	this.firstnameInput = (String)event.getNewValue();
    }
	
	// use to give a subject to a student
	public void updateStudentMail(ValueChangeEvent event) {
    	this.mailInput = (String)event.getNewValue();
    	this.studentMail = mailInput ;
    	
    	Student student = school.getStudent(mailInput);
    	this.studentSubjectNames = school.getSubjectsByStudent(student);
    		    	
    }
	
	public void updateTeacherMail(ValueChangeEvent event) {
    	this.mailInput = (String)event.getNewValue();
    	this.teacherMail = mailInput ;
    	
    	Teacher teacher = school.getTeacher(mailInput);
    	this.teacherSubjectNames = school.getSubjectsByTeacher(teacher);
    		    
    }

	//update subject name
	public void updateSubjectName(ValueChangeEvent event) {
    	this.subjectName = (String)event.getNewValue();
    	
    }
	//update student subject name
	public void updateStudentSubjectName(ValueChangeEvent event) {
    	this.studentSubjectName = (String)event.getNewValue();
    }

			
	//give grade process
    public String performGiveGrade() {
    	
    	try {
    		
    		Subject destSubject = school.getSubject(this.studentSubjectName);
			Student destStudent = school.getStudent( this.studentMail);
			Mark grade = new Mark(givenGrade);
	
			if (this.givenGrade > 6.0 || this.givenGrade < 0.0){
				
				this.transactionResult="Error: Not valid grade";
			} 
			else{
				// Grading
				school.gradeToSubject(destSubject, destStudent, grade);
				this.transactionResult="Success: grade for " + this.studentSubjectName + " added!";

			}
			
    	} catch (Exception e) {
    		
    		this.transactionResult="error happened try again";
	  		return "showResult" ;
	  		
    	}

		return "showResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
    

	//add student to course
    public String performAddStudentToCourse() {
    	
    	Subject destSubject = school.getSubject(this.subjectName);
		Student destStudent = school.getStudent(this.studentMail);
    	
    	try {
    		    					
			if (studentSubjectNames.contains(destSubject.getSubject())){
				
				this.transactionResult="Error: Student has already taken the course " +this.subjectName+"!";
				
			} 
			else{
					    		
				school.addStudentToSubject(destSubject, destStudent);
				this.transactionResult="Success: student added to course "+this.subjectName+" !";
				
			}
			
    	} catch (Exception e) {
    		
    		this.transactionResult="error happened try again";
	  		return "showResult" ;
	  		
    	}
    	
    	

		return "showResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
    
    
    public String performAddTeacherToCourse() {
    	
    	Subject destSubject = school.getSubject(this.subjectName);
		Teacher destTeacher = school.getTeacher(this.teacherMail);
    	
    	try {
    		
			
			if (teacherSubjectNames.contains(destSubject.getSubject())){
				
				this.transactionResult="Error: Teacher has already taken the course " +this.subjectName+"!";
				
			} 
			else{
					    		
				school.addTeacherToSubject(destSubject, destTeacher);
				this.transactionResult="Success: teacher added to course "+this.subjectName+"!";
				
			}		
    	} catch (Exception e) {
    		
    		this.transactionResult="error happened try again";
	  		return "showResult" ;
	  		
    	}
    	
    	
		return "showResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
    
    
    
    
    
    //----------------------------------------------------------------- Create
    // Methods to create a student, a teacher or a subject
    
    
  public String performCreateSubject() {
    	
				try {
					
					if (subjectNames.contains(this.subjectNameInput)){
						
						this.transactionResult="Error: Subject name already exist!";
						
					}else {
						
						school.createSubject(this.subjectNameInput);
						this.transactionResult="Success: "+this.subjectNameInput+" subject added!";
						 initializeSubjectLists();
						 
					}
					
				} catch (Exception e) {
					
					this.transactionResult="error happened try again";
			  		return "showResult" ;
			  		
				}
						
		return "showResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
  
  //Create new student
  public String performCreateStudent() {
				
				try {
					
						if (this.studentMails.contains(this.mailInput)){
						
						this.transactionResult="Error: Student mail already exist!";
						
					}else {
						
						school.createStudent(this.firstnameInput, this.nameInput, this.mailInput);
						this.transactionResult="Success: "+this.mailInput+" student added!";
						initializeStudentLists();
						 
					}
										
					
				} catch (Exception e) {
					
					this.transactionResult="error happened try again";
			  		return "showResult" ;
			  		
				}
				
				
							
		return "showResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
  
  public String performCreateTeacher() {
		
		try {
			
			if (this.teacherMails.contains(this.mailInput)){
				
				this.transactionResult="Error:  mail already exist!";
				
			}else {
				school.createTeacher(this.firstnameInput, this.nameInput, this.mailInput);
				initializeTeacherLists();				
				this.transactionResult="Success: "+this.mailInput+" teacher added!";
								 
			}
							
									
		} catch (Exception e) {
			
			this.transactionResult="error happened try again";
	  		return "showResult" ;
	  		
		}
	
	
		return "showResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
  } 
  
  
  //---------------------------------------------------------------------modify
  
  //modify existing subject
  public String performModifySubject() {
	  Subject subject = school.getSubject(this.subjectName);
  	
  	try {
			if (subjectNames.contains(this.subjectNameInput)){
				
				this.transactionResult="Error: Subject name already exist!";
			} 
			else {
				
				school.modifySubject(subject, this.subjectNameInput);
				initializeSubjectLists();
				this.transactionResult="Success: "+this.subjectName +" has been modified to "+this.subjectNameInput+"!";
			}
			
  	} catch (Exception e) {
  		
  		this.transactionResult="error happened";
  		return "showResult" ;
 		
  	}

		return "showResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
  
  
  public String performGetStudentMarksOnSubject() {
	  this.average = 0.0 ;
	  double sum = 0.0 ;
	  int count = 0 ;
	  	
	  	try {
	  		
			this.grades = school.getMarks(this.studentMail,this.studentSubjectName );
			this.teachersForSubject = school.getTeacherBySubject(studentSubjectName);
			
			if (grades.isEmpty()){
				
				this.transactionResult ="No grades on this subject";
				return "showResult" ;
				
			}else{
				
				for(double d : grades) {
					
					sum += d;
					count++;
					this.average = sum/count ;
					
				}							
			}																													
		  } catch (Exception e) {
			  
			  	this.transactionResult="error happened try again";
		  		return "showResult" ;
		 }
	  	
	  	
	  	return "marksResult";

		} 
  
  //----------------------------------------------------------------- Delete
  // Methods to delete a student, a teacher or a subject
 
  public String performStudentDeletion() {
		  
	  Student student = school.getStudent(this.studentMail);	
	  
	  try {
		  
		  school.deleteStudents(student);
		  this.transactionResult="Success: "+this.studentMail + " removed";
		  initializeStudentLists();
		  
	  }catch(Exception e) {
		  
		  this.transactionResult="error happened try again";
	  		return "showResult" ;
	  		
	  }
	  	  		
	return "showResult"; 	  
  }
  
  
 public String performTeacherDeletion() {
		  
	  Teacher teacher = school.getTeacher(this.teacherMail);	
	  
	  try {
		  
		  school.deleteTeacher(teacher);
		  this.transactionResult="Success: "+this.teacherMail + " removed";
		  initializeTeacherLists();
		  
	  }catch(Exception e) {
		  
		  this.transactionResult="error happened try again";
	  		return "showResult" ;
	  }
	  			
	return "showResult"; 	  
  }
  
  
  public String performSubjectDeletion() {
	  
	  Subject subject = school.getSubject(this.subjectName);
	  
	  try {
		  
		  school.deleteSubject(subject);
		  this.transactionResult="Success: "+this.subjectName + " removed";
		  initializeSubjectLists();
		  
	  }catch(Exception e) {
		  
		  this.transactionResult="error happened try again";
	  		return "showResult" ;
	  	
	  }
	  
	  return "showResult"; 
  }
  
  
  //--------------------------------------------------initialize all lists
  
  public void initializeStudentLists() {
	  this.students = new ArrayList<Student>();
	  this.students = school.getStudents();
	  this.studentNames = new ArrayList<String>(); 
	  this.studentFirstnames = new ArrayList<String>();
	  this.studentMails = new ArrayList<String>();
	  for (Student s : students) 
	  { 
		  
		  this.studentNames.add(s.getName()); 
		  this.studentFirstnames.add(s.getFirstName());
		  this.studentMails.add(s.getMail());
	  }
  }
  
  public void initializeTeacherLists() {
	  this.teachers = new ArrayList<Teacher>();
	  this.teachers = school.getTeachers();
	  this.teacherMails = new ArrayList<String>();
	  for(Teacher t : teachers) {
		  this.teacherMails.add(t.getMail());
	  }
  }
  
  public void initializeSubjectLists() {
	  this.subjects = new ArrayList<Subject>();
	  this.subjects = school.getSubjects();
	  this.subjectNames = new ArrayList<String>();
	  for(Subject s : subjects) {
		  if(s.isRemoved() == false)
			  this.subjectNames.add(s.getSubject());
	  }
  }
  
  // populate data in the DB
  public String populateDBData() {
	  try {
		  if (this.studentMails.contains("samuel.yergen@hevs.ch")){				
				this.transactionResult="Error: DB data already populate!";				
			}else {				
				school.createStudent("Samuel", "Yergen", "samuel.yergen@hevs.ch");
				school.createTeacher("Henrick", "Neads", "henrick.neads@hevs.ch");
				school.createSubject("Projet");
				this.subjectName = "Projet";
				this.studentMail = "samuel.yergen@hevs.ch";
				this.teacherMail = "henrick.neads@hevs.ch" ;
				this.studentSubjectName = "Projet" ;
				this.givenGrade = 4.0 ;
				Subject destSubject = school.getSubject(this.subjectName);
				Student destStudent = school.getStudent(this.studentMail);
				Teacher destTeacher = school.getTeacher(this.teacherMail);
				school.addTeacherToSubject(destSubject, destTeacher);
				school.addStudentToSubject(destSubject, destStudent);				
				performGiveGrade();
				this.givenGrade = 5.0 ;
				performGiveGrade();				
				this.transactionResult="Data populated";
		  		
			}
		
		initializeTeacherLists();
		initializeSubjectLists();
		initializeStudentLists();
		return "showResult" ;
		 
	} catch (Exception e) {
		 this.transactionResult="error happened try again";
	  		return "showResult" ;
	}
  }
  
}
