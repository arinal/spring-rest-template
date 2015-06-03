package org.lamedh.pos.app.konsole;

import org.lamedh.pos.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Scratch {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationConfig.class);
        app.setAdditionalProfiles("QA");

        ConfigurableApplicationContext ctx = app.run(args);
    }
}

