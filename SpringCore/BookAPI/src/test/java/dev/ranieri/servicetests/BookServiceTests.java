package dev.ranieri.servicetests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.ranieri.daos.BookDAO;
import dev.ranieri.entities.Book;
import dev.ranieri.exceptions.BookNotFoundException;
import dev.ranieri.services.BookService;
import dev.ranieri.services.BookServiceImpl;

class BookServiceTests {
	
	private BookService bserv;
	
	@BeforeEach
	public void setUpMock() {
		BookDAO bdao = Mockito.mock(BookDAO.class);
		Set<Book> books = new HashSet<Book>();
		Book pridePrejudice = new Book(0,"Pride and Prejudice","Jane Austen",160,"Drama");
		Book pridePrejudice2 = new Book(0,"Pride and Prejudice 2" ,"Jane Austen",145,"Drama");
		Book lotr1 = new Book(0,"Fellowship of the Ring","Tolkien",320,"Adventure");
		Book lotr2 = new Book(0,"The Two Towers","Tolkien",340,"Adventure");
		Book lotr3 = new Book(0,"Return of the King","Tolkien",360,"Adventure");
		Book scimarillion = new Book(0,"The Scimarillion","Tolkien",320,"Lore");
		
		books.add(scimarillion);
		books.add(pridePrejudice);
		books.add(pridePrejudice2);
		books.add(lotr1);
		books.add(lotr2);
		books.add(lotr3);
		
		Mockito.when(bdao.getAllBooks()).thenReturn(books);
		
		// inject mock
		this.bserv = new BookServiceImpl(bdao);
	}

	@Test
	void sortByAuthor() {
		Set<Book> books = this.bserv.getBooksByAuthor("Tolkien");
		Assertions.assertEquals(4, books.size());
	}
	
	@Test
	void sortByGenre() {
		Set<Book> books = this.bserv.getBooksByGenre("Lore");
		Assertions.assertEquals(1, books.size());		
	}
	
	@Test
	void sortByLengthAscending() {
		Set<Book> books = this.bserv.getBooksByLength("asc");
		
		int pages = 0;
		for(Book b: books) {
			if(b.getPages()<pages) {
				fail();
			}
			pages = b.getPages();
		}
	}
	
	@Test
	void sortByLengthDescending() {		
		Set<Book> books = this.bserv.getBooksByLength("dsc");
			
		int pages = Integer.MAX_VALUE;
		for(Book b: books) {
			if(b.getPages()>pages) {
				fail();
			}
			pages = b.getPages();
		}
		
	}
	
	@Test
	void testBookNotFoundException() {
		Exception e = assertThrows(BookNotFoundException.class, ()-> {
			Book b = null;
			bserv.updateBook(b);
		});
		
		Assertions.assertEquals("Book not found. Please try again.", e.getMessage());
	}
	
	@Test
	void testBookNotFoundExceptionNegative() {
		Exception e = assertThrows(BookNotFoundException.class, ()-> {
			Book b = new Book(0,"The Two Towers","Tolkien",340,"Adventure");
			bserv.updateBook(b);
		});
		
		Assertions.assertEquals("Book not found. Please try again.", e.getMessage());
	}
	
}
