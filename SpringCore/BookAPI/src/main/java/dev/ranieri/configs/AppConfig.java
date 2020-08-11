package dev.ranieri.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import dev.ranieri.app.App;
import dev.ranieri.controllers.BookController;
import dev.ranieri.daos.BookDAO;
import dev.ranieri.daos.BookDAOLocal;
import dev.ranieri.services.BookService;
import dev.ranieri.services.BookServiceImpl;
import io.javalin.Javalin;

@Component
@Configuration
public class AppConfig {
	
	@Bean
	public BookDAO BookDAOlocal() {
		return new BookDAOLocal();
	}
	
	@Bean
	public BookService bookServiceImpl() {
		// constructor injection
		return new BookServiceImpl(this.BookDAOlocal());
	}
	
	@Bean
	public BookController bookController() {
		return new BookController(this.bookServiceImpl());
	}
	
	@Bean 
	public Javalin localJavalinAppNoCors() {
		Javalin app = Javalin.create(config ->{			
			config.enableCorsForAllOrigins();
		});
		
		app.get("/books", this.bookController().getBooks);
		app.get("/books/:id", this.bookController().getBookById);
		
		app.post("/books", this.bookController().addBook);
		
		app.put("/books/:id", this.bookController().updateBook);
		
		app.delete("/books/:id", this.bookController().deleteBook);
		
		return app;
	}
	
	@Bean 
	public Javalin restrictedToLocal() {
		Javalin app = Javalin.create();
		
		app.get("/books", this.bookController().getBooks);
		app.get("/books/:id", this.bookController().getBookById);
		
		app.post("/books", this.bookController().addBook);
		
		app.put("/books/:id", this.bookController().updateBook);
		
		app.delete("/books/:id", this.bookController().deleteBook);
		
		return app;
	}
	
	@Bean
	public Javalin decideImpl() {
		
		if(App.environment == 1) {
			return this.localJavalinAppNoCors();
		}else {
			return this.restrictedToLocal();
		}
		
		
	}
	
	

}
