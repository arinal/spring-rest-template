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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
            momogi.setUnitPriceByLong(500);
            momogi = productRepository.save(momogi);

            Product pepsi = new Product();
            pepsi.setCode("P02");
            pepsi.setName("Pepsi");
            pepsi.setUnitPriceByLong(5000);
            pepsi = productRepository.save(pepsi);

            Product ayam = new Product();
            ayam.setCode("P03");
            ayam.setName("Ayam");
            ayam.setUnitPriceByLong(50_000);
            ayam = productRepository.save(momogi);

            Product xiao = new Product();
            xiao.setCode("P04");
            xiao.setName("Xiao Long Bao");
            xiao.setUnitPriceByLong(40_000);
            xiao = productRepository.save(xiao);

            Product maybach = new Product();
            maybach.setCode("P05");
            maybach.setName("Maybach");
            maybach.setUnitPriceByLong(9_000_000_000L);
            maybach= productRepository.save(maybach);

            Product windows = new Product();
            windows.setCode("P06");
            windows.setName("Windows 10");
            windows.setUnitPriceByLong(500_000);
            windows = productRepository.save(windows);

            productRepository.save(Arrays.asList(momogi, pepsi, ayam, xiao, maybach, windows));

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
            s01.setCode("S01");
            s01.setCashier(suyama);
            s01.addLineItem(momogi, 2);
            s01.addLineItem(pepsi, 1);

            Sale s02 = new Sale();
            s02.setCode("S02");
            s02.setCashier(nancy);
            s02.addLineItem(ayam, 1);
            s02.addLineItem(pepsi, 1);
            s02.addLineItem(xiao, 1);

            Sale s03 = new Sale();
            s03.setCode("S03");
            s03.setCashier(buchanan);
            s03.addLineItem(maybach, 1);

            Sale s04 = new Sale();
            s04.setCode("S04");
            s04.setCashier(margy);
            s04.addLineItem(windows, 2);
            s04.addLineItem(xiao, 2);

            saleRepository.save(Arrays.asList(s01, s02, s03, s04));
        };
    }
}