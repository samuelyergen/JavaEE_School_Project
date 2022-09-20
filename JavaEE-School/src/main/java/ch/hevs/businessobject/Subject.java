package ch.hevs.businessobject;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="subject")
	private String subject;
	@Column(name="isRemoved")
	private boolean isRemoved;
	
	// relations
	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Mark> marks;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Student> students;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Teacher> teachers;
	
	// id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public boolean isRemoved() {
		return isRemoved;
	}
	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	
	public Subject() {}
	
	public Subject(String name) {
		this.subject = name;
	}

}