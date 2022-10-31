package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

//@RestController - is a combination of @Controller and  @ResponseBody annotations.
@RestController
@RequestMapping("/api/student/")

public class StudentController {
	
	//@Value annotation only works with spring managed beans
	@Value("${app.name: Default Demo App}") //if app.name key is not present in application.properties then it will take default value after :, in this case it will be 'Default Demo App' text
	private String appName;
	
//	@GetMapping("/get")
	// instead of @GetMapping(), you can also use - @RequestMapping(Value = "/get", method = RequestMethod.GET)
	
	
//	public StudentResponse getStudent() {
//		StudentResponse studentResponse = new StudentResponse(1, "John","Smith");
//		return studentResponse;
	
	//	public String getStudent() {
	//	return appName;
	//}

	//spring boot will convert above object into json.
	//spring framework works with jackson library to convert these java objects into json.

	
	
	
	/* Error < Warn < Info < Debug < Trace
	 * this logger interface is used to place the logs.
	 *
	 */
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentService studentService;
	
	//recommended way.
	@GetMapping("getAll")
	public List<StudentResponse> getAllStudents() {
		
		//just to check logs by logger of student controller
		logger.error("Inside Error");
		logger.warn("Inside Warning");
		logger.info("Inside Info");
		logger.debug("Inside Debug");
		logger.trace("Inside Trace");
	
		List<Student> studentList = studentService.getAllStudents();
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
		
	}
	
	@PostMapping("create")
	
	//@RequestBody - This will convert whatever json coming in the Request Payload to a Model Class that we provide.
	public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
		
		Student student = studentService.createStudent(createStudentRequest);
		return new StudentResponse(student);
		
	}
	
	@PutMapping("update") 
	public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
		
		Student student = studentService.updateStudent(updateStudentRequest);
		return new StudentResponse(student);
		
	}
	
	//delete api using query params
//	@DeleteMapping("delete")
	
	//public String deleteStudent(@RequestParam("id") long idDifferent) {} - this is required to be specified when your var name an query param aren't same.
	public String deleteStudent(@RequestParam long id) {
		
		return studentService.deleteStudent(id);
	
	}
	
	//delete api using path variable
//	@DeleteMapping("delete/{id}")
//	public String deleteStudent(@PathVariable long id) {
//		
//		return studentService.deleteStudent(id);
//		
//	}
	
	@GetMapping("getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName(@PathVariable String firstName) {
		
		List<Student> studentList = studentService.getByFirstName(firstName);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
		
	}
	
	//this is how you can pass multiple path variables in uri.
	@GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
	public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
		
		return new StudentResponse(studentService.getByFirstNameAndLastName(firstName, lastName));
		
	}
	
	@GetMapping("getByLastNameAndFirstName/{lastName}/{firstName}")
	public StudentResponse getByLastNameAndFirstName(@PathVariable String lastName, @PathVariable String firstName) {
		
		return new StudentResponse(studentService.getByFirstNameAndLastName(lastName, firstName));
		
	}
	
	@GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName, @PathVariable String lastName) {
		
		 List<Student> studentList = studentService.getByFirstNameOrLastNameList(firstName, lastName);
		 List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		 
		 studentList.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
			});
			
		return studentResponseList;
		
	}
	
	@GetMapping("getByFirstNameIn")
	public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
		
//		logger.info("inQueryRequest = " + inQueryRequest);
		
		List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		 
		 studentList.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
			});
			
		 logger.info("StudentResponseList = " + studentResponseList);
		 
		return studentResponseList;
		
	}
	
	//for pagination 
	@GetMapping("getAllWithPagination")
	public List<StudentResponse> getAllStudentsWithPagination(@RequestParam int pageNo,
		@RequestParam int pageSize) {
		
		List<Student> studentList = studentService.getAllStudentsWithPagination(pageNo, pageSize);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		 
		 studentList.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
			});
			
		return studentResponseList;
		
		
	}
	
	//for sorting
	@GetMapping("getAllWithSorting")
	public List<StudentResponse> getAllStudentsWithSorting() {
		
		List<Student> studentList = studentService.getAllStudentsWithSorting();
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		 
		 studentList.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
		 });
			
		return studentResponseList;
		
	}
	
	//for like operator '%ty%' (contains)
	@GetMapping("like/{firstName}")
	public List<StudentResponse> like(@PathVariable String firstName) {
		
		List<Student> studentList = studentService.like(firstName);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		 
		 studentList.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
		 });
			
		return studentResponseList;
		
	}
	
	//for like operator 'abc%' (starts with)
	@GetMapping("startsWith/{firstName}")
	public List<StudentResponse> startsWith(@PathVariable String firstName) {
		
		List<Student> studentList = studentService.startsWith(firstName);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		 
		 studentList.stream().forEach(student -> {
				studentResponseList.add(new StudentResponse(student));
		 });
			
		return studentResponseList;
		
	}
	
	//update query with JPQL eg.
	@PutMapping("updateFirstName/{id}/{firstName}")
	public String updateStudentWithJPQL(@PathVariable Long id, @PathVariable String firstName) {
		
		return studentService.updateStudentWithJPQL(id, firstName) + " student(s) updated.";
		
	}
	
	//delete query with JPQL eg.
	@DeleteMapping("deleteByFirstName/{firstName}")
	public String deleteStudent(@PathVariable String firstName) {
		
		return studentService.deleteByFirstName(firstName) + "student(s) deleted.";
		
	}

	@GetMapping("/getByCity/{city}")
	public List<StudentResponse> getByCity(@PathVariable String city) {
		
		List<Student> studentList = studentService.getByCity(city);
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			
			studentResponseList.add(new StudentResponse(student));
			
		});
		return studentResponseList;
		
	}
}

/*
 * @Valid is required if you have specified any validation annotation on certain fields. eg.@NotBlank().
 * 
 * below method is not recommended
 * don't return your entity class from the controller that will get converted into json
	@GetMapping("/getAll")
	public List<Student> getAllStudents() {
	
		return studentService.getAllStudents();
		
	}
*/
