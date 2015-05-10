package org.lamedh.pos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PosApplication.class)
@WebAppConfiguration
public class EmployeeControllerTest {
	@Test
	public void employeeNotFound() throws Exception {
		mockMvc.perform(get("/employee/99"))
				.andExpect(status().isNotFound());
	}

	@Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();

		employeeRepository.deleteAllInBatch();

		Employee suyama = new Employee();
		suyama.setCode("E01");
		suyama.setName("Suyama");

		Employee nancy = new Employee();
		nancy.setCode("E02");
		nancy.setName("Nancy");

		suyama = employeeRepository.save(suyama);
		nancy = employeeRepository.save(nancy);
	}

	private MockMvc mockMvc;
	private Employee employee;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;
}
