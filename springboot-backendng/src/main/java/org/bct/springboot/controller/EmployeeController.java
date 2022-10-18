package org.bct.springboot.controller;

import java.util.List;

import org.bct.springboot.exception.ResourceNotFoundException;
import org.bct.springboot.model.Employee;
import org.bct.springboot.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRespository employeeRespository;
	
	//Get all Employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRespository.findAll();
	}
	//Create
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRespository.save(employee);
	}
	//Get By Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee=
				employeeRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"Employee not exists with id: " + id));
		return ResponseEntity.ok(employee);
	}
	//Update Employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, 
			@RequestBody Employee employeeDetails){
		Employee employee = employeeRespository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee not exists with id: " + id));
		
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setEmail(employeeDetails.getEmail());
		
		Employee updateEmployee=employeeRespository.save(employee);
		
		return ResponseEntity.ok(updateEmployee);
	}
}
