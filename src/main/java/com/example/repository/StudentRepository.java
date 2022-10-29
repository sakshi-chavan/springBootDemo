package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Student;

@Repository //this is to identify that interface is not a normal interface but a repository.
public interface StudentRepository extends JpaRepository<Student, Long> { //where student is a corresponding entity and Long is a data type of unique identifier.

	List<Student> findByFirstName(String firstName);
	
	//Whatever order you have in method name, same order you need to have while method parameters.
	Student findByFirstNameAndLastName(String firstName, String lastName);
	
	
	List<Student> findByFirstNameOrLastName(String firstName, String lastName);
	
	List<Student> findByFirstNameIn(List<String> firstNames);
	
	//eg. select * from student where first_name like '%ty%';
	List<Student> findByFirstNameContains(String firstName);
	
	//eg. select * from student where first_name like 'John%';
	List<Student> findByFirstNameStartsWith(String firstName);
	
	@Query("From Student where lastName = :lastName and firstName = :firstName")   //native query - select * from Student where first_name = "abc" and last_name = "xyz" ;
	Student getByLastNameAndFirstName(String lastName, String firstName);
	
	//JPQL Update eg.
	@Modifying //in case your query is modifying the data.
	@Transactional //as you added @Modifying
	@Query("Update Student set firstName = :firstName where id = :id")
	Integer updateFirstName(Long id, String firstName);
	
	
	//JPQL Delete eg.
	@Modifying
	@Transactional
	@Query("Delete From Student where firstName = :firstName")
	Integer deleteByFirstName(String firstName);
	
	//native query - select * from student s inner join address a on s.address_id = a.id where a.city = "Delhi";
	List<Student> findByAddressCity(String city);
	
	//by city name - using JPQL 
	@Query("From Student where address.city = :city")
	List<Student> getByAddressCity(String city);
	
}

/* 
 * Why we extended JPARepository?
 * JPARepository is basically a combination of CrudRepository<T, ID> and PagingAndSortingRepository<T, ID> 
 * where both of these are interfaces.
 * and we need both of these interfaces.
 * @Param - if you change the variable inside JPQL query,then you need to use @Param("jpql_var_name") to map it with s
 * Return type for update methods in JPQL can be void or Integer
*/
