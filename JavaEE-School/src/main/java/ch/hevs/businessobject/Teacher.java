package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;


@Entity
@Table(name="Teacher")
public class Teacher extends Person {
	
	@ManyToMany(mappedBy="teachers", cascade = CascadeType.ALL)
	private Set<Subject> subjects;
	
	public Teacher() {super();}
	public Teacher(String firstname, String name, String mail) {
		super(firstname, name, mail);
	}
	
	public Teacher(Long id, String firstname, String name, String mail) {
		super(firstname, name, mail);
	}
	
	// id 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}