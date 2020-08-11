package dev.ranieri.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.ranieri.configs.AppConfig;
import dev.ranieri.controllers.BookController;
import dev.ranieri.daos.BookDAOLocal;
import dev.ranieri.services.BookService;
import dev.ranieri.services.BookServiceImpl;
import io.javalin.Javalin;

public class App {
	public static int environment = 1;
	
	
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);		
		Javalin app = ac.getBean("decideImpl",Javalin.class);		
		app.start(7000);
	}

}
