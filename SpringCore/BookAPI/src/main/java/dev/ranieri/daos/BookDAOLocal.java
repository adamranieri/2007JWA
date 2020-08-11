package dev.ranieri.daos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.ranieri.entities.Book;

public class BookDAOLocal implements BookDAO{
	
	private Map<Integer,Book> book_table = new HashMap<Integer,Book>();
	private int idMaker = 0;

	public Book createBook(Book book) {
		idMaker++;
		book.setId(idMaker);
		book_table.put(book.getId(), book);
		return book;
	}

	public Book getBookById(int id) {
		
		return book_table.get(id);
	}

	public Book updateBook(Book book) {
		book_table.put(book.getId(), book);		
		return book;
	}

	public boolean deleteBook(Book book) {
		Book b = book_table.remove(book.getId());
		return b == null ? false :true;
	}

	public Set<Book> getAllBooks() {
		Set<Book> books = new HashSet<Book>(book_table.values());
		return books;
	}

}
