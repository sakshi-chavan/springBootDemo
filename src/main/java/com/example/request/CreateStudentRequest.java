package com.example.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

/*
 * Whoever is consuming this post api needs to pass this data in the format of json.
 * This consumer can be anyone. eg. .Net application, PHP application or UI as well, etc.
 * And this input is called your Request Payload.
*/
public class CreateStudentRequest {
	
	@NotBlank(message = "first name is required.")
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String street;
	
	private String city;
	
	private List<CreateSubjectRequest> subjectsLearning;
}
