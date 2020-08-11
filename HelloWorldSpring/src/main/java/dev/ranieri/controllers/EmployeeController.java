package dev.ranieri.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.ranieri.entities.Employee;

@Component
@Controller
public class EmployeeController {
	
	private List<Employee> employees = new ArrayList<Employee>();
	private int idMaker = 0;
	
	@RequestMapping(value = "/company/{cid}/employees", method = RequestMethod.POST)
	@ResponseBody
	// Spring will automatically take jsons in the body and marshall them into Java objects 
	public Employee createEmployee(@RequestBody Employee employee) {
		employee.setId(++idMaker); // assume this will be an actual service method to create an employee
		employees.add(employee);
		return employee;// when you return an object it will automatically serialize it into a json
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> allEmployees(){
		return this.employees;
	}

}
