package com.myapp.employee.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import com.myapp.employee.entity.Employee;
import com.myapp.employee.repository.EmployeeRepository;

@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepository empRepo;
	static Logger log=Logger.getLogger(EmployeeDao.class);

	public String addEmployee(Employee e) {
		empRepo.save(e);
		return "Successfully added";
		
	}
	@GetMapping
	public Employee  getEmployee( int id) {
		 return empRepo.findById(id).get();
	}
	public String getEmp(List<Employee> emps) {
		empRepo.saveAll(emps);
		return "SuccessFully added";
	}
	@GetMapping
	public List<Employee> getList() {
		return empRepo.findAll();
	}
	public String getupdate(Employee a) {
		empRepo.save(a);
		return "SuccessFully Updated";
	}
	public 	String delete( ) {
		empRepo.deleteAll();
		return "SuccessFully deleted";
	}
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}
	public List<Employee> getEmployeeBySalary(int salary) {
		return empRepo.getEmployeeBySalary(salary);

	}
	public List<Employee> getEmployeeAge(int age) {
		PropertyConfigurator.configure("log.properties");
		log.info( empRepo.getEmployeeBySalary(age));
		return empRepo.getEmployeeBySalary(age);
	}
	
}
