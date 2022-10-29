package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Student;
import com.example.entity.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode

@AllArgsConstructor //this annotation is used to create all args constructor of class.

//@NoArgsConstructor //this annotation is used to create default constructor.

@Data   //@Data annotation is a combination of @Getter @Setter @ToString @EqualsAndHashCode annotations.
public class StudentResponse {
	
	
	//@JsonIgnore - this annotation ignore the field or key in json response.
//	@JsonIgnore 
	private long id;
	private String firstName;
	private String lastName;
	private String email;
//	private String fullName;
	
	private String street;
	private String city;
	
	private List<SubjectResponse> learningSubjects;
	
	public StudentResponse (Student student) {
		
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		
//		this.fullName = student.getFirstName() + " " + student.getLastName();
		
		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();
		
		if(student.getLearningSubjects() != null) {
			
			learningSubjects = new ArrayList<SubjectResponse>();
			for(Subject subject: student.getLearningSubjects()) {
				
				learningSubjects.add(new SubjectResponse(subject));
				
			}
			
		}
	}
	
//	public StudentResponse() {
//		super();
//	}
	
//	@JsonProperty("first_name")  //using @JsonProperty annotation you can explicitly define how your key should be populated in json response.
	
	//@Getter and @Setter annotations will help to create getters and setters. 
	//by specifying these above class, you can have all the field's getter setters.
	
//	private String firstName;
//	
//	private String lastName;

//	public StudentResponse(long id, String firstName, String lastName) {
//		super();
//		this.id = id;
//		this.firstName = firstName;
//		this.lastName = lastName;
//	}

	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}

//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}

}
