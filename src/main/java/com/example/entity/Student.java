package com.example.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.request.CreateStudentRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity //it shows that the the class is not normal/POJO class but it is a db table.
@Table(name = "student") // this specifies the name of the table in db of entity specified class.


public class Student {
	
	@Id // @Id is for primary keys of table
	@GeneratedValue(strategy = GenerationType.IDENTITY) //this is not mandatory, but it is good to have it in case of auto_increment columns
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
//	@OneToOne(fetch = FetchType.LAZY) - for lazy loading
	@OneToOne // for eager loading, you don't have to explicitly specify the fetch type. By default it is eager loading only.
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "student")
	private List<Subject> learningSubjects;
	
	//@Transient //using this annotation denotes that below is not column of the table.
	//private String fullName;
	
	public Student (CreateStudentRequest createStudentRequest) {
		this.firstName = createStudentRequest.getFirstName();
		this.lastName = createStudentRequest.getLastName();
		this.email = createStudentRequest.getEmail();
	//	this.fullName = createStudentRequest.getFirstName() + " " + createStudentRequest.getLastName();
	}
	
}
