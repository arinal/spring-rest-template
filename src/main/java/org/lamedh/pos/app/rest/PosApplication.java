package org.lamedh.pos.app.rest;

import org.lamedh.pos.app.rest.domain.Address;
import org.lamedh.pos.app.rest.domain.Employee;
import org.lamedh.pos.app.rest.domain.repo.EmployeeRepository;
import org.lamedh.pos.app.rest.domain.Product;
import org.lamedh.pos.app.rest.domain.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class PosApplication {
    @Bean
    CommandLineRunner init(ProductRepository productRepository, EmployeeRepository employeeRepository) {
        return (evt) -> {
            Product momogi = new Product();
            momogi.setCode("P01");
            momogi.setName("Momogi");
            momogi.setUnitPriceByInt(500);

            Product pepsi = new Product();
            pepsi.setCode("P02");
            pepsi.setName("Pepsi");
            pepsi.setUnitPriceByInt(5000);

            Product ayam = new Product();
            ayam.setCode("P03");
            ayam.setName("Ayam");
            ayam.setUnitPriceByInt(50000);

            productRepository.save(Arrays.asList(momogi, pepsi, ayam));

            Employee suyama = new Employee();
            suyama.setCode("E01");
            suyama.setName("Suyama");
            suyama.setAddress(new Address("Akihabara", "234", "Japan"));

            Employee nancy = new Employee();
            nancy.setCode("E02");
            nancy.setName("Nancy");
            suyama.setAddress(new Address("Pisa", "346", "Italy"));

            employeeRepository.save(Arrays.asList(suyama, nancy));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }
}
