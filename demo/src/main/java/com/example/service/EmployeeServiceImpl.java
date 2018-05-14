package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.constant.Constant;
import com.example.exception.NoRecordFoundException;
import com.example.model.Employee;
import com.example.model.Response;
import com.example.repo.EmpRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmpRepo empRepo;

	public Employee getEmp(String empId) throws Exception {
		if(empId == null) {
			throw new Exception();
		}
 		Optional<Employee> emp = empRepo.findById(Integer.parseInt(empId));
		if (emp != null && !emp.isPresent()) {
			throw new NoRecordFoundException(Constant.NO_RECORD_FOUND);
		}
		return emp.get();
	}

	public Response addEmp(Employee employee) throws Exception {
		empRepo.save(employee);
		return new Response(Constant.ADDED_SUCCESS, "Success", 200);
	}

	public String updateEmp(Employee employee) throws Exception {
		if (employee.getEmpId() == 0) {
			return Constant.INVALID_ID;
		}
		empRepo.save(employee);
		return Constant.UPDATE_SUCCESS;
	}

	public String deleteEmp(String empId) throws Exception {
		empRepo.deleteById(Integer.parseInt(empId));
		return Constant.DELETED_SUCCESS;
	}
}