package org.lamedh.pos.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.lamedh.pos.app.rest.domain.Address;
import org.lamedh.pos.app.rest.domain.Employee;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeJsonTest {

    @Test
    public void deserialize_address() throws IOException {
        Address original = new Address("Akihabara", "342", "Shibuya");

        ObjectMapper m = new ObjectMapper();
        String json = m.writeValueAsString(original);
        Address newAddress = m.readValue(json, Address.class);
        assertThat(newAddress).isEqualToComparingFieldByField(original);

        Employee suyama = new Employee();
        suyama.setAddress(original);
    }
}
