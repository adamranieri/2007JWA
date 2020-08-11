package dev.ranieri.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dev.ranieri") // Scan for for components in any package that starts dev.ranieri
public class HelloWorldSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldSpringApplication.class, args);
	}

}
