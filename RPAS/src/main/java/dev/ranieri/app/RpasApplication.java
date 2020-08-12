package dev.ranieri.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("dev.ranieri") // Spring will find classes labeled with  @component
@EntityScan("dev.ranieri.entities")// Spring data can find entities labled with @entity
@EnableJpaRepositories("dev.ranieri.repositories")// Spring data can find the repositories with @Repository
public class RpasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpasApplication.class, args);
	}

}
