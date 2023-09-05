package com.myapp.employee.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.myapp.employee.entity.Car;
import com.myapp.employee.entity.Employee;
import com.myapp.employee.exceptions.AgeNotEligibleException;
import com.myapp.employee.exceptions.AgeNotFoundListException;
import com.myapp.employee.exceptions.NameNotFoundException;
import com.myapp.employee.service.EmployeeService;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
	@Autowired
	EmployeeService empser;
	static Logger log=Logger.getLogger(EmployeeController.class);
	
	//ADDING SINGLE OBJECT INTO THE DATABASE
	@PostMapping(value="/add")
	public String addEmployee(@RequestBody Employee e) throws AgeNotEligibleException {
		return empser.addEmployee(e);
	}
	
	//SAME NAME AS VARIABLE NAME GETTING THAT EMPLOYEES DETAILS
	@GetMapping(value="/getName/{name}")
	public List<Employee> getName(@PathVariable String name)throws NameNotFoundException{
		return empser.getName(name);
	}
	
	//NoEmployee Id Exceptions
	@GetMapping(value="/getIdEmp/{a}")
	public List<Employee> getListAge(@PathVariable int a)throws AgeNotFoundListException{
		return empser.getListAge(a);
	}
	
//	//Find ID EMPLOYEES
//	@GetMapping(value="/getIdEmp")
//	public List<Employee> getIdEmp()throws IdNotFoundException{
//		return empser.getIdEmp();
//	}
	//GETTING THE VALUES IN DATABASE
	@GetMapping(value="/get/{id}")
	public Employee  getEmployee(@PathVariable int id) {
		return empser.getEmployee(id);
	}
	
	//ADDING THE LIST INTO THE  DATABASE
	@PostMapping(value="/addList")
	public String getEmp(@RequestBody List<Employee> emps){
		return empser.getEmp(emps);
	}
	
	//GETTING THE LIST INTO THE DATABASE USING POSTMAN HTTP METHOD
	@GetMapping(value="/getList")
	public List<Employee> getList() {
		return empser.getList();
	}
	@PutMapping(value="/update/{c}")
	public String getupdate(@RequestBody Employee a) {
		return empser.getupdate(a);
	}
	
	//DELETE WHOLEDATA INTO THE DATABASE
	@DeleteMapping(value="/delete")
	public 	String delete() {
		return empser.delete();
	}
	
	//PROBLEMS IN GETTING AND UPDATE INTO THE DATABASE
	
	@GetMapping(value="/getid/{k}")
	public List<Employee> idList(@PathVariable int k){
		return empser.idList(k);
	}
	
	//AGE IS SATISFY THE CONDITION
	@GetMapping(value="/getAge/{age}")
	public List<Employee> getAgeEmp(@PathVariable int age){
		return empser.getAgeEmp(age);
	}
	
	//GENDER IS EQUAL
	@GetMapping(value="/getGender/{gender}")
	public List<Employee> getGenderEmp(@PathVariable String gender){
		return empser.getGenderEmp(gender);
	}
	
	//SALARY EMPLOYEE AGE
	@GetMapping(value="/getSalary/{age}")
	public List<Employee> getSalaryEmp(@PathVariable int age){
		return empser.getSalaryEmp(age);
	}
	
	//FEMALE EMPLOYEE NAMES ONLY
	@GetMapping(value="/getFemale/{gender}")
	public List<String> getFemaleEmp(@PathVariable String gender){
		return empser.getFemaleEmp(gender);
	}
	
	//QUERY USING REPOSITORY
	@GetMapping(value="/getsalaryEmp/{salary}")
	public List<Employee> getEmployeeBySalary(@PathVariable int salary){
		return empser.getEmployeeBySalary(salary);
	}
	
	
	//QUERY USING REPOSITORY
	@GetMapping(value="/getAgeEmp/{age}")
	public List<Employee> getEmployeeAge(@PathVariable int age){
		PropertyConfigurator.configure("log.properties");
		log.warn(empser.getEmployeeAge(age));
		return empser.getEmployeeAge(age);
	}
	
	RestTemplate rest=new RestTemplate();
	@GetMapping(value="/importCar")
	public List<Car> importCar() {
		String url="http://localhost:8080/Car/getCars";
		ResponseEntity<List<Car>> response=rest.exchange(url,HttpMethod.GET, null,new ParameterizedTypeReference<List<Car>>() {
		});
		List<Car> value=response.getBody();
		return value;
	}
	
}
