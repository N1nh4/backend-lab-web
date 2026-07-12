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
            var rs = conn.prepareStatement("SELECT COUNT(*) FROM unidade").executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                System.out.println("[DataInitializer] Banco vazio, executando import.sql...");
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("import.sql"));
                System.out.println("[DataInitializer] Dados importados com sucesso.");
            } else {
                System.out.println("[DataInitializer] Banco ja possui dados (" + count + " unidades). Ignorando.");
            }
        }
    }
}
