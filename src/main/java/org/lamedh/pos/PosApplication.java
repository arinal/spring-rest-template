package org.lamedh.pos;

import org.lamedh.pos.domain.Address;
import org.lamedh.pos.domain.Employee;
import org.lamedh.pos.domain.Sale;
import org.lamedh.pos.domain.repo.EmployeeRepository;
import org.lamedh.pos.domain.Product;
import org.lamedh.pos.domain.repo.ProductRepository;
import org.lamedh.pos.domain.repo.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
    @Profile("QA")
    CommandLineRunner init(ProductRepository productRepository, EmployeeRepository employeeRepository,
                           SaleRepository saleRepository) {
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
            suyama.setAddress(new Address("Akihabara", "234", "Japan"));
            suyama = employeeRepository.save(suyama);

            Employee nancy = new Employee();
            nancy.setCode("E02");
            nancy.setName("Nancy DaVolio");
            nancy.setAddress(new Address("Pisa", "346", "Italy"));
            nancy = employeeRepository.save(nancy);

            Employee andrew = new Employee();
            andrew.setCode("E03");
            andrew.setName("Andrew Fuller");
            andrew = employeeRepository.save(andrew);

            Employee janet = new Employee();
            janet.setCode("E04");
            janet.setName("Janet Leverling");
            janet = employeeRepository.save(janet);

            Employee margy = new Employee();
            margy.setCode("E05");
            margy.setName("Margareth Peacock");
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

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PosApplication.class);
        app.setAdditionalProfiles("QA");
        ConfigurableApplicationContext ctx = app.run(args);
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