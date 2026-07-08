package com.system.employee_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(EmployeeManagementApplication.class);
		// Hard-enforce a non-8080 default to avoid conflicts with other local servers.
		app.setDefaultProperties(Map.of("server.port", "8081"));
		app.run(args);
	}

}
