package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;


@Entity
@Table(name="Mark")
public class Mark {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name="grade")
	private double mark;
	
	// relations
	@ManyToOne
	@JoinColumn(name = "FK_SUBJECT")
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "FK_STUDENT")
	private Student student;

	// id 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	// Mark
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	
	
	
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	//Constructor
	public Mark() {}
	public Mark(double mark) {
		this.mark = mark;
	}
	
}