package org.lamedh.pos.app.rest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lamedh.pos.PosApplication;
import org.lamedh.pos.domain.Address;
import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.Product;
import org.lamedh.pos.domain.repo.ProductRepository;
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
public class ProductControllerTest {

    @Test
    public void getEntityNotFound() throws Exception {
        mockMvc.perform(get("/product/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getEntityById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product" + "/" + momogi.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$content.id", Matchers.is(momogi.getId())))
                .andExpect(jsonPath("$content.name", Matchers.is(momogi.getName())));
    }

    @Test
    public void getEntities() throws Exception {
        String path = "$._embedded.productList";
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath(path, hasSize(2)))
                .andExpect(jsonPath(path + "[0].id", Matchers.is(momogi.getId())))
                .andExpect(jsonPath(path + "[0].name", Matchers.is(momogi.getName())))
                .andExpect(jsonPath(path + "[1].id", Matchers.is(pepsi.getId())))
                .andExpect(jsonPath(path + "[1].name", Matchers.is(pepsi.getName())));
    }

    @Test
    public void createEntity() throws Exception {
        Employee newEmployee = new Employee();
        newEmployee.setAddress(new Address("Akihabara", "342", "Shibuya"));
        String json = json(newEmployee);
        mockMvc.perform(post("/product")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void editEntity() throws Exception {
        momogi.setName("Chiki");
        String json = json(momogi);
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

        productRepository.deleteAllInBatch();

        momogi = new Product();
        momogi.setCode("P01");
        momogi.setName("Momogi");

        pepsi = new Product();
        pepsi.setCode("P02");
        pepsi.setName("Pepsi");

        momogi = productRepository.save(momogi);
        pepsi = productRepository.save(pepsi);
    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        json2Http = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
        assertNotNull("the JSON message converter must not be null", json2Http);
    }

    private MockMvc mockMvc;
    private Product momogi;
    private Product pepsi;

    private HttpMessageConverter json2Http;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
}
