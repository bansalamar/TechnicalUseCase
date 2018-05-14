package com.example.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Employee;

public interface EmpRepo extends CrudRepository<Employee, Integer> {

}
