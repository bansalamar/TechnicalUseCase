package com.example.service;

import com.example.model.Employee;
import com.example.model.Response;

public interface EmployeeService {
	
	public Employee getEmp(String empId) throws Exception;

	public Response addEmp(Employee employee) throws Exception;

	public String updateEmp(Employee employee) throws Exception;

	public String deleteEmp(String empId) throws Exception;
}