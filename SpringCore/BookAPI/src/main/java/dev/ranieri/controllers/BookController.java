package dev.ranieri.controllers;

import java.util.Set;

import com.google.gson.Gson;

import dev.ranieri.entities.Book;
import dev.ranieri.services.BookService;
import io.javalin.http.Handler;

public class BookController {
	
	private BookService bserv;
	private Gson gson = new Gson();

	public BookController(BookService bserv) {
		this.bserv = bserv;
	}
	
	//GET
	public Handler getBookById = (ctx) ->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		Book book = this.bserv.getBookById(id);
		String json = this.gson.toJson(book);
		ctx.result(json);
	};
	
	public Handler getBooks = (ctx) ->{
		Set<Book> books = this.bserv.getAllBooks();
		String json = this.gson.toJson(books);
		ctx.result(json);
	};
	
	//POST
	public Handler addBook = (ctx) ->{
		Book book = this.gson.fromJson(ctx.body(), Book.class);
		this.bserv.addBook(book);
		String json = this.gson.toJson(book);
		ctx.result(json);
	};
	
	
	//PUT
	public Handler updateBook = (ctx) ->{
		Book book = this.gson.fromJson(ctx.body(), Book.class);
		this.bserv.updateBook(book);
		String json = this.gson.toJson(book);
		ctx.result(json);		
	};
	
	
	//DELETE
	public Handler deleteBook = (ctx)->{
		int id = Integer.parseInt(ctx.pathParam("id"));
		Book book = this.bserv.getBookById(id);
		Boolean success = this.bserv.deleteBook(book);
		ctx.result(success.toString());
	};

}
