package dev.ranieri.services;

import java.util.Set;

import dev.ranieri.entities.Book;
import dev.ranieri.exceptions.BookNotFoundException;

public interface BookService {
	
	//Create
	Book addBook(Book book);
	
	//Read
	Book getBookById(int id);
	
	Set<Book> getAllBooks();
	Set<Book> getBooksByAuthor(String name);
	Set<Book> getBooksByGenre(String genre);
	Set<Book> getBooksByLength(String order);
	
	//Update
	Book updateBook(Book book);
	
	//Delete
	boolean deleteBook(Book book);
	boolean deleteBook(int id);

}
