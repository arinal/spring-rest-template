package org.lamedh.pos.app.rest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lamedh.pos.ApplicationConfig;
import org.lamedh.pos.domain.employee.Employee;
import org.lamedh.pos.domain.sale.Sale;
import org.lamedh.pos.domain.employee.EmployeeRepository;
import org.lamedh.pos.domain.sale.SaleRepository;
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
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@WebAppConfiguration
public class SaleControllerTest {

    @Test
    public void getEntityNotFound() throws Exception {
        mockMvc.perform(get("/sale/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEntityById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/sale/" + sale1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.id", Matchers.is(sale1.getId())))
                .andExpect(jsonPath("$.code", Matchers.is(sale1.getCode())))
                .andExpect(jsonPath("$.cashier.name", Matchers.is(sale1.getCashier().getName())));
    }

    @Test
    public void getEntities() throws Exception {
        String path = "$._embedded.saleList";
        mockMvc.perform(get("/sale"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath(path, hasSize(2)))
                .andExpect(jsonPath(path + "[0].id", Matchers.is(sale1.getId())))
                .andExpect(jsonPath(path + "[0].code", Matchers.is(sale1.getCode())))
                .andExpect(jsonPath(path + "[0].cashier.name", Matchers.is(sale1.getCashier().getName())))
                .andExpect(jsonPath(path + "[1].id", Matchers.is(sale2.getId())))
                .andExpect(jsonPath(path + "[1].code", Matchers.is(sale2.getCode())))
                .andExpect(jsonPath(path + "[1].cashier.name", Matchers.is(sale2.getCashier().getName())));
    }

    @Test
    public void createEntity() throws Exception {
        Sale newSale = new Sale();
        String json = json(newSale);
        mockMvc.perform(post("/sale")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void editEntity() throws Exception {
        sale1.setCode("S1");
        String json = json(sale1);
        mockMvc.perform(post("/product")
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

        saleRepository.deleteAll();
        employeeRepository.deleteAll();

        suyama = new Employee();
        suyama.setCode("E01");
        suyama.setName("Suyama");

        nancy = new Employee();
        nancy.setCode("E02");
        nancy.setName("Nancy");

        suyama = employeeRepository.save(suyama);
        nancy = employeeRepository.save(nancy);

        sale1 = new Sale();
        sale1.setCode("S01");
        sale1.setCashier(suyama);

        sale2 = new Sale();
        sale2.setCode("S02");
        sale2.setCashier(nancy);

        sale1 = saleRepository.save(sale1);
        sale2 = saleRepository.save(sale2);
    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        json2Http = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        assertNotNull("the JSON message converter must not be null", json2Http);
    }

    Sale sale1;
    Sale sale2;
    Employee suyama;
    Employee nancy;

    HttpMessageConverter json2Http;
    MockMvc mockMvc;

    @Autowired EmployeeRepository employeeRepository;
    @Autowired SaleRepository saleRepository;
    @Autowired WebApplicationContext webApplicationContext;
}
