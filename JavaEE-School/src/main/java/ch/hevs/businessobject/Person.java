package ch.hevs.businessobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long id;
	
	@Column(name="Name")
	protected String name = "Unknown";
	@Column(name="FirstName")
	protected String firstName = "Unknown";
	@Column(name="Mail")
	protected String mail = "Unknown";


	public Person() {}
	
	public Person(String firstName, String name, String mail) {
		this.name = name;
		this.firstName = firstName;
		this.mail = mail ;
	}
	//Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	// name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// Firstname
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}


}
