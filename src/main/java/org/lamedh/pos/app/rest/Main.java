package org.lamedh.pos.app.rest;

import org.lamedh.pos.ApplicationConfig;
import org.springframework.boot.SpringApplication;

public class Main {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApplicationConfig.class);
        app.setAdditionalProfiles("QA");
        app.run(args);
    }
}
