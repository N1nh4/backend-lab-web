package com.example.lab_web.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DataSource dataSource;

    public DataInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("[DataInitializer] Limpando banco...");
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("import.sql"));
            System.out.println("[DataInitializer] import.sql executado. Executando import_ubs.sql...");
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("import_ubs.sql"));
            System.out.println("[DataInitializer] Dados importados com sucesso.");
        }
    }
}
