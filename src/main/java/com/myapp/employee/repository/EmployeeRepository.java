package com.myapp.employee.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

//	@Query(value="SELECT * FROM employee_details where salary>=?",nativeQuery=true)
//	public List<Employee> getEmployeeBySalary(int salary);
	
//	@Query(value="SELECT * FROM employee_details where age<=?",nativeQuery=true)
//	public List<Employee> getEmployeeAge(int age);
	
	@Query("select a from Employee a where a.salary >=:salary ")
	public List<Employee>  getEmployeeBySalary(@Param ("salary") int salary);
	
//	@Query("select a from Employee a where a.age >=: age ")
//	public List<Employee>  getEmployeeAge(@Param ("age")int age);
}
