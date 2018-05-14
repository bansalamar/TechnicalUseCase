package com.example.service;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.constant.Constant;
import com.example.exception.NoRecordFoundException;
import com.example.model.Employee;
import com.example.repo.EmpRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	MockMvc mockMvc;

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmpRepo empRepo;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeeServiceImpl).build();
	}

	@Test(expected = NoRecordFoundException.class)
	public void getEmpNoRecordFoundTest() throws Exception {
		Mockito.when(empRepo.findById(11))
				.thenThrow(new NoRecordFoundException("No Record Found"));
		employeeServiceImpl.getEmp("11");
		verify(empRepo).findById(Mockito.any());
	}
	
	@Test
	public void getEmpSuccessTest() throws Exception {
		Employee employee = new Employee();
		employee.setEmpName("qwerty");
		Optional<Employee> eOptional = Optional.of(employee);
		Mockito.when(empRepo.findById(1))
				.thenReturn(eOptional);
		Employee employee2 = employeeServiceImpl.getEmp("1");
		boolean flag = eOptional.get().getEmpName().equals(employee2.getEmpName());
		assert flag;
	}
	
	@Test
	public void updateInvalidIdTest() throws Exception {
		Employee employee = new Employee();
		String res = employeeServiceImpl.updateEmp(employee);
		boolean res1 = res.equals(Constant.INVALID_ID);
		assert res1;
	}
}
