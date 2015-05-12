package org.lamedh.pos.app.rest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lamedh.pos.PosApplication;
import org.lamedh.pos.domain.Address;
import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.repo.EmployeeRepository;
import org.lamedh.pos.domain.repo.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

    @Test
    public void getEmployeeById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/" + suyama.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$content.id", Matchers.is(suyama.getId())))
                .andExpect(jsonPath("$content.name", Matchers.is(suyama.getName())));
    }

    @Test
    public void getEmployees() throws Exception {
        String employeesPath = "$._embedded.employeeList";
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath(employeesPath, hasSize(2)))
                .andExpect(jsonPath(employeesPath + "[0].id", Matchers.is(suyama.getId())))
                .andExpect(jsonPath(employeesPath + "[0].name", Matchers.is(suyama.getName())))
                .andExpect(jsonPath(employeesPath + "[1].id", Matchers.is(nancy.getId())))
                .andExpect(jsonPath(employeesPath + "[1].name", Matchers.is(nancy.getName())));
    }

    @Test
    public void createEmployee() throws Exception {
        Employee newEmployee = new Employee();
        newEmployee.setAddress(new Address("Akihabara", "342", "Shibuya"));
        String json = json(newEmployee);
        mockMvc.perform(post("/employee")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void editEmployee() throws Exception {
        suyama.setName("Mikhail Suyama");
        String json = json(suyama);
        mockMvc.perform(post("/employee")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        json2Http.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @Before
	public void setup() throws Exception {
		mockMvc = webAppContextSetup(webApplicationContext).build();

        employeeRepository.deleteAllInBatch();

		suyama = new Employee();
		suyama.setCode("E01");
		suyama.setName("Suyama");

		nancy = new Employee();
		nancy.setCode("E02");
		nancy.setName("Nancy");

		suyama = employeeRepository.save(suyama);
		nancy = employeeRepository.save(nancy);
	}

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        json2Http = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        assertNotNull("the JSON message converter must not be null", json2Http);
    }

	private Employee suyama;
    private Employee nancy;
    private MockMvc mockMvc;
    private HttpMessageConverter json2Http;

	@Autowired
	private EmployeeRepository employeeRepository;
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
	private WebApplicationContext webApplicationContext;
}
