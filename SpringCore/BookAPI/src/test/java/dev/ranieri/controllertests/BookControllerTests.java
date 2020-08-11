package dev.ranieri.controllertests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.ranieri.controllers.BookController;
import dev.ranieri.daos.BookDAO;
import dev.ranieri.daos.BookDAOLocal;
import dev.ranieri.services.BookService;
import dev.ranieri.services.BookServiceImpl;
import io.javalin.Javalin;

//Integrated tests require that dependencies work correctly
class BookControllerTests {

	private static BookController bookController;
	
	
//	@BeforeAll
//	public static void setUpApp() {
//		Javalin app = Javalin.create(config ->{			
//			config.enableCorsForAllOrigins();
//		}).start(7000);
//		
//		BookService bookService = new BookServiceImpl(new BookDAOLocal());
//		BookController bookController = new BookController(bookService);
//		
//		app.get("/books", bookController.getBooks);
//		app.get("/books/:id", bookController.getBookById);
//		
//		app.post("/books", bookController.addBook);
//		
//		app.put("/books/:id", bookController.updateBook);
//		
//		app.delete("/books/:id", bookController.deleteBook);
//		
//	}
	
	@Test
	void shouldFail() {
		fail("Will fail");
	}

}
