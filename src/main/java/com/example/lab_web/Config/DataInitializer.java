package com.example.lab_web.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("[DataInitializer] Desabilitado - usando ddl-auto=create");
    }
}
