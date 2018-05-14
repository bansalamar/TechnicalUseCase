package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.model.Response;
import com.example.service.EmployeeService;

@RestController
public class DemoController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/getEmp/{empId}", method = RequestMethod.GET)
	public Object getEmp(@PathVariable String empId) throws Exception {
		return employeeService.getEmp(empId);
	}

	@RequestMapping(value = "/addEmp", method = RequestMethod.POST)
	public Response addEmp(@RequestBody Employee employee) throws Exception {
		return employeeService.addEmp(employee);
	}

	@RequestMapping(value = "/updateEmp", method = RequestMethod.POST)
	public String updateEmp(@RequestBody Employee employee) throws Exception {
		return employeeService.updateEmp(employee);
	}

	@RequestMapping(value = "/deleteEmp/{empId}", method = RequestMethod.DELETE)
	public String deleteEmp(@PathVariable String empId) throws Exception {
		return employeeService.deleteEmp(empId);
	}

}
