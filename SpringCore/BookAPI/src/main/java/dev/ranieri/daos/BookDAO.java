package dev.ranieri.daos;

import java.util.Set;

import dev.ranieri.entities.Book;

public interface BookDAO {
	
	Book createBook(Book book);
	
	Book getBookById(int id);
	Set<Book> getAllBooks();
	
	Book updateBook(Book book);
	
	boolean deleteBook(Book book);
	

}
