package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class UserService1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserService1Application.class, args);
	}

}
