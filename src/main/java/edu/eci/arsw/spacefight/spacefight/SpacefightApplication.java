package edu.eci.arsw.spacefight.spacefight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.spacefight.spacefight"})
public class SpacefightApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpacefightApplication.class, args);
	}
}
