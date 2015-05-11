package org.lamedh.pos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
        mockMvc.perform(get("/employee/" + suyama.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(suyama.getId())))
                .andExpect(jsonPath("$.name", is(suyama.getName())));
    }

    @Test
    public void getEmployees() throws Exception {
        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(suyama.getId())))
                .andExpect(jsonPath("$[0].name", is(suyama.getName())))
                .andExpect(jsonPath("$[1].id", is(nancy.getId())))
                .andExpect(jsonPath("$[1].name", is(nancy.getName())));
    }

    @Test
    public void createEmployee() throws Exception {
        Employee newEmployee = new Employee();
        String json = json(newEmployee);
        this.mockMvc.perform(post("/employee")
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
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

	private MockMvc mockMvc;
	private Employee suyama;
    private Employee nancy;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private HttpMessageConverter json2Http;

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private WebApplicationContext webApplicationContext;
}
