package org.lamedh.pos;

import org.lamedh.pos.domain.Address;
import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.repo.EmployeeRepository;
import org.lamedh.pos.domain.Product;
import org.lamedh.pos.domain.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            suyama.setName("Michael Suyama");
            suyama.setAddress(new Address("Akihabara", "234", "Japan"));

            Employee nancy = new Employee();
            nancy.setCode("E02");
            nancy.setName("Nancy DaVolio");
            nancy.setAddress(new Address("Pisa", "346", "Italy"));

            Employee andrew = new Employee();
            andrew.setCode("E03");
            andrew.setName("Andrew Fuller");

            Employee janet = new Employee();
            janet.setCode("E04");
            janet.setName("Janet Leverling");

            Employee margy = new Employee();
            margy.setCode("E05");
            margy.setName("Margareth Peacock");

            Employee buchanan = new Employee();
            buchanan.setCode("E06");
            buchanan.setName("David Buchanan");

            employeeRepository.save(Arrays.asList(suyama, nancy, andrew, janet, margy, buchanan));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }
}

@Configuration
class WebMvcConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Remove the Jaxb2 that is automatically added because some other dependency brings it into the classpath
        List<HttpMessageConverter<?>> baseConverters = new ArrayList<>();
        super.configureMessageConverters(baseConverters);

        converters.addAll(baseConverters.stream()
                .filter(c -> !(c instanceof Jaxb2RootElementHttpMessageConverter))
                .collect(Collectors.toList()));
    }

}