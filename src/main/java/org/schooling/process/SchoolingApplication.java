package org.schooling.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.schooling.process")
@EnableAutoConfiguration
public class SchoolingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolingApplication.class, args);
	}

}
