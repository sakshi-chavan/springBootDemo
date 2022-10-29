package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired 
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	public Student createStudent(CreateStudentRequest createStudentRequest) {
		
		Student student = new Student(createStudentRequest);
		
		Address address = new Address();
		
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		
		address = addressRepository.save(address);
		
		student.setAddress(address);
		student = studentRepository.save(student);
		
		List<Subject> subjectList = new ArrayList<Subject>();
		
		if(createStudentRequest.getSubjectsLearning() != null) {
			
			for(CreateSubjectRequest createSubjectRequest :
				createStudentRequest.getSubjectsLearning()) {
				
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarksObtained(createSubjectRequest.getMarksObtained());
				subject.setStudent(student);
				
				subjectList.add(subject);
			}
			
			subjectRepository.saveAll(subjectList);
			
		}
		
		student.setLearningSubjects(subjectList);
		return student;
	}
	
	public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
		Student student = studentRepository.findById(updateStudentRequest.getId()).get();
		
		if(updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()) {
			student.setFirstName(updateStudentRequest.getFirstName());
		}
		
		student = studentRepository.save(student); //save method is to create and update the records.
		return student;
	}
	
	public String deleteStudent(long id) {
		
		studentRepository.deleteById(id);
		return "Student has been deleted successfully.";
		
	}
	
	public List<Student> getByFirstName(String firstName) {
		
		return studentRepository.findByFirstName(firstName);
		
	}
	
	public Student getByFirstNameAndLastName(String firstName, String lastName) {
		
		return studentRepository.findByFirstNameAndLastName(firstName, lastName);
//		return studentRepository.getByLastNameAndFirstName(lastName, firstName);
	}
	
	public List<Student> getByFirstNameOrLastNameList(String firstName, String lastName) {
		
		return studentRepository.findByFirstNameOrLastName(firstName, lastName);
		
	}
	
	public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest) {
		
		return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
		
	}
	
	public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize) {
		
		// formula - (pageNo - 1) * pageSize
		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
		return studentRepository.findAll(pageable).getContent();
		
	}
	
	public List<Student> getAllStudentsWithSorting() {
		
		//order by student's first name in ascending order
		//eg. select * from student order by first_name asc;
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
		return studentRepository.findAll(sort);
		
	}
	
	public List<Student> like(String firstName) {
		
		return studentRepository.findByFirstNameContains(firstName);
		
	}
	
	public List<Student> startsWith(String firstName) {
		
		return studentRepository.findByFirstNameContains(firstName);
		
	}
	
	public Integer updateStudentWithJPQL(Long id, String firstName) {
		
		return studentRepository.updateFirstName(id, firstName);
		
	}
	
	public Integer deleteByFirstName(String firstName) {
		
		return studentRepository.deleteByFirstName(firstName);
		
	}
	
	public List<Student> getByCity(String city) {
		
		return studentRepository.findByAddressCity(city);
//		return studentRepository.getByAddressCity(city);
		
	}
	
}

/*
 * findAll() is provided by JPA
 * findAll() method gives all the records that we have on certain table.
 * Equivalent to native query, select * from abctable;
 * 
 * Why do we have separate repository for each and every table?
 * Whenever you call a findAll() method that is provided by JPARepository on a particular repository that you created with an entity class,
 * fetches all the records from a particular table.
 * 
 * Return type of this findAll() is List<>
 */
