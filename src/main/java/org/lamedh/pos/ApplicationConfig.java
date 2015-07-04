package org.lamedh.pos;

import org.lamedh.pos.domain.employee.Address;
import org.lamedh.pos.domain.employee.Employee;
import org.lamedh.pos.domain.employee.Gender;
import org.lamedh.pos.domain.product.Product;
import org.lamedh.pos.domain.sale.Sale;
import org.lamedh.pos.domain.employee.EmployeeRepository;
import org.lamedh.pos.domain.product.ProductRepository;
import org.lamedh.pos.domain.sale.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class ApplicationConfig {
    @Bean @Profile("QA")
    CommandLineRunner init(ProductRepository productRepository,
                           EmployeeRepository employeeRepository, SaleRepository saleRepository) {
        return evt -> {
            Product momogi = new Product();
            momogi.setCode("P01");
            momogi.setName("Momogi");
            momogi.setUnitPriceByInt(500);
            momogi = productRepository.save(momogi);

            Product pepsi = new Product();
            pepsi.setCode("P02");
            pepsi.setName("Pepsi");
            pepsi.setUnitPriceByInt(5000);
            pepsi = productRepository.save(pepsi);

            Product ayam = new Product();
            ayam.setCode("P03");
            ayam.setName("Ayam");
            ayam.setUnitPriceByInt(50000);
            ayam = productRepository.save(momogi);

            productRepository.save(Arrays.asList(momogi, pepsi, ayam));

            Employee suyama = new Employee();
            suyama.setCode("E01");
            suyama.setName("Michael Suyama");
            suyama.setBirthDate(LocalDate.of(1970, 4, 15));
            suyama.setAddress(new Address("Akihabara", "234", "Japan"));
            suyama = employeeRepository.save(suyama);

            Employee nancy = new Employee();
            nancy.setCode("E02");
            nancy.setName("Nancy DaVolio");
            nancy.setBirthDate(LocalDate.of(1975, 12, 20));
            nancy.setAddress(new Address("Pisa", "346", "Italy"));
            nancy.setGender(Gender.Female);
            nancy = employeeRepository.save(nancy);

            Employee andrew = new Employee();
            andrew.setCode("E03");
            andrew.setName("Andrew Fuller");
            andrew = employeeRepository.save(andrew);

            Employee janet = new Employee();
            janet.setCode("E04");
            janet.setName("Janet Leverling");
            janet.setGender(Gender.Female);
            janet = employeeRepository.save(janet);

            Employee margy = new Employee();
            margy.setCode("E05");
            margy.setName("Margareth Peacock");
            margy.setGender(Gender.Female);
            margy = employeeRepository.save(margy);

            Employee buchanan = new Employee();
            buchanan.setCode("E06");
            buchanan.setName("David Buchanan");
            buchanan = employeeRepository.save(buchanan);

            Sale s01 = new Sale();
            s01.setCashier(suyama);
            s01.addLineItem(momogi, 2);
            s01.addLineItem(pepsi, 1);

            Sale s02 = new Sale();
            s02.setCashier(nancy);
            s02.addLineItem(ayam, 1);
            s02.addLineItem(pepsi, 1);

            Sale s03 = new Sale();
            s03.setCashier(buchanan);
            s03.addLineItem(momogi, 2);
            s03.addLineItem(ayam, 1);

            saleRepository.save(Arrays.asList(s01, s02, s03));
        };
    }
}