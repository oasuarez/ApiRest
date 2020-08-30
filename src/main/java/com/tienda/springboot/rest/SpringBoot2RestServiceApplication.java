package com.tienda.springboot.rest;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tienda.springboot.rest.entity.Role;
import com.tienda.springboot.rest.entity.User;
import com.tienda.springboot.rest.services.UserService;

@SpringBootApplication
public class SpringBoot2RestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2RestServiceApplication.class, args);
	}
	
    @Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            service.save(new User("user", "password123", 
            		Arrays.asList(new Role("ADMIN")),
            		"Administrador"));
        };
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }   
}
