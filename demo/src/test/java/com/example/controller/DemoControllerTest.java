package com.example.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.constant.Constant;
import com.example.model.Employee;
import com.example.model.Response;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTest {

	MockMvc mockMvc;

	@InjectMocks
	DemoController demoController;

	@Mock
	EmployeeService employeeService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
	}

	 @Test
	 public void getEmpTest() throws Exception {
	 Mockito.when(employeeService.getEmp(Mockito.any()))
	 .thenReturn(new Employee());
	 mockMvc.perform(get("/getEmp/"+"1")
	 .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))).andExpect(status().isOk())
	 .andExpect(content().contentType(Constant.CONTENT_JSON_CHARSET));
	 verify(employeeService, times(1)).getEmp(Mockito.any());
	 verifyNoMoreInteractions(employeeService);
	 }

	@Test
	public void addEmpTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Employee data = new Employee();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		Mockito.when(employeeService.addEmp(data)).thenReturn(new Response());
		mockMvc.perform(post("/addEmp").accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))
				.contentType("application/json;charset=UTF-8").content(mapper.writeValueAsBytes(data)));
		verify(employeeService, times(1)).addEmp(Mockito.any());
		verifyNoMoreInteractions(employeeService);
	}

}
