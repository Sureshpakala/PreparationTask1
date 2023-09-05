package com.myapp.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.myapp.employee.dao.EmployeeDao;
import com.myapp.employee.entity.Employee;
import com.myapp.employee.exceptions.AgeNotEligibleException;
import com.myapp.employee.exceptions.AgeNotFoundListException;
import com.myapp.employee.exceptions.NameNotFoundException;

//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
@Service
public class EmployeeService {
	@Autowired
	EmployeeDao empDao;
	static Logger log=Logger.getLogger(EmployeeService.class);

	public String addEmployee(Employee e)throws AgeNotEligibleException {
	
			
			if(e.getAge()<18) {
				throw new AgeNotEligibleException("Age below 18");
			}
			else {
				return empDao.addEmployee(e);
			}
		}
		
		
	public List<Employee> getName(String name) throws NameNotFoundException{
		List<Employee> allemployees = empDao.getAllEmployees();
		List<Employee> k=allemployees.stream().filter(x -> x.getName().equalsIgnoreCase(name)).toList();
				if(k.isEmpty()) {
					throw new NameNotFoundException("There is no data in this name");
				}
				else {
					return k;
				}
		
	}
	
	public List<Employee> getListAge(int a) throws AgeNotFoundListException {
		List<Employee> allemployees=empDao.getAllEmployees();
		List<Employee> checkAge=allemployees.stream().filter(x->x.getAge()<a).toList();
		if(checkAge.isEmpty()) {
			throw new AgeNotFoundListException("There are no one is below "+a);
		}
		else {
			return checkAge;
		}
	}
	
//	public List<Employee> getIdEmp() throws IdNotFoundException{
//		List<Employee> id=empDao.getAllEmployees();
//		List<Employee> checkId=id.stream().map(x->x.getId()).toList();
//		if(checkId.isEmpty()) {
//			throw new IdNotFoundException("There was No Id Found");
//		}
//		else {
//			return checkId;
//		}
//	}	

	@GetMapping
	public Employee getEmployee(int id) {
		return empDao.getEmployee(id);
	}

	public String getEmp(List<Employee> emps) {
		return empDao.getEmp(emps);
	}

	@GetMapping
	public List<Employee> getList() {
		return empDao.getList();
	}

	public String getupdate(Employee a) {
		return empDao.getupdate(a);
	}

	public String delete() {
		return empDao.delete();
	}

	public List<Employee> idList(int k) {
		List<Employee> allemployees = empDao.getAllEmployees();
		return allemployees.stream().filter(x -> x.getId() == k).collect(Collectors.toList());
	}


	public List<Employee> getAgeEmp(int age) {
		List<Employee> allemployees = empDao.getAllEmployees();
		return allemployees.stream().filter(x -> x.getAge() > 26).toList();
	}

	public List<Employee> getGenderEmp(String gender) {
		List<Employee> allemployees = empDao.getAllEmployees();
		return allemployees.stream().filter(x -> x.getGender().equalsIgnoreCase(gender)).toList();
	}

	public List<Employee> getSalaryEmp(int age) {
		List<Employee> allemployees = empDao.getAllEmployees();
		return allemployees.stream().map(y -> {
			if (age > 30 && age < 35) {
				y.setSalary(y.getSalary() + y.getSalary() * 10 / 100);
				return y;
			} else if (age > 35 && age < 40) {
				y.setSalary(y.getSalary() + y.getSalary() * 15 / 100);
				return y;
			} else {
				y.setSalary(y.getSalary() + y.getSalary() * 20 / 100);
				return y;
			}
		}).toList();
	}

	public List<String> getFemaleEmp(String gender) {
		List<Employee> allemployees = empDao.getAllEmployees();
		return allemployees.stream().filter(x -> x.getGender().equalsIgnoreCase(gender)).map(y -> y.getName()).toList();
	}

	public List<Employee> getEmployeeBySalary(int salary) {
		return empDao.getEmployeeBySalary(salary);
	}

	public List<Employee> getEmployeeAge(int age) {
		PropertyConfigurator.configure("log.properties");
		log.warn(empDao.getEmployeeAge(age));
		return empDao.getEmployeeAge(age);
	}

}
