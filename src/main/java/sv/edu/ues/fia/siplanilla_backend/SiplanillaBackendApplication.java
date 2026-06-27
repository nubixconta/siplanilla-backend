package sv.edu.ues.fia.siplanilla_backend;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SiplanillaBackendApplication {

	private static final Logger log = LoggerFactory.getLogger(SiplanillaBackendApplication.class);

	public static void main(String[] args) {
		System.out.println("PROYECTO CARGADO CORRECTAMENTEEE");
		SpringApplication.run(SiplanillaBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner logDataSourceUrl(@Autowired DataSource dataSource) {
		return args -> log.info("=== DATASOURCE URL: {}", ((HikariDataSource) dataSource).getJdbcUrl());
	}

}
