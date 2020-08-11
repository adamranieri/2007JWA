package dev.ranieri.services;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import dev.ranieri.daos.BookDAO;
import dev.ranieri.entities.Book;
import dev.ranieri.exceptions.BookNotFoundException;


public class BookServiceImpl implements BookService {

	private BookDAO bdao;

	public BookServiceImpl(BookDAO bdao) {
		this.bdao = bdao;
	}

	public Book addBook(Book book) {
		return this.bdao.createBook(book);
	}

	public Book getBookById(int id) {
		return this.bdao.getBookById(id);
	}

	public Set<Book> getBooksByAuthor(String name) {
		Set<Book> allBooks = this.bdao.getAllBooks();

		Set<Book> authorBooks = new HashSet<Book>();

		for (Book b : allBooks) {
			if (b.getAuthor().equals(name)) {
				authorBooks.add(b);
			}
		}

		return authorBooks;
	}

	public Set<Book> getBooksByGenre(String genre) {

		Set<Book> allBooks = this.bdao.getAllBooks();

		Set<Book> genreBooks = new HashSet<Book>();

		for (Book b : allBooks) {
			if (b.getGenre().equals(genre)) {
				genreBooks.add(b);
			}
		}

		return genreBooks;
	}

	public Set<Book> getBooksByLength(String order) {
		Comparator<Book> ascendingSort = (book1,book2)->{
			
			if(book1.getPages()<book2.getPages()) {
				return -1;
			}
			if(book1.getPages()>book2.getPages()) {
				return 1;
			}
			return 0;			
		};
		
		Comparator<Book> descedningSort = (book1,book2)->{
			
			if(book1.getPages()>book2.getPages()) {
				return -1;
			}
			if(book1.getPages()<book2.getPages()) {
				return 1;
			}
			return 0;			
		};
		
		if(order.equals("asc")) {
			Set<Book> ordered = new TreeSet<Book>(ascendingSort);
			ordered.addAll(this.bdao.getAllBooks());
			return ordered; 
		}else {
			Set<Book> ordered = new TreeSet<Book>(descedningSort);
			ordered.addAll(this.bdao.getAllBooks());
			return ordered; 
		}

	}

	public Book updateBook(Book book) {

		if(book != null) {
			Book btest = bdao.getBookById(book.getId());
			if (btest == null) {
				throw new BookNotFoundException();
			} else {
				return this.bdao.updateBook(book);
			}		
		} else {
			throw new BookNotFoundException();
		}
		

	}

	public boolean deleteBook(Book book) {
		boolean result = this.bdao.deleteBook(book);
		return result;
	}

	public boolean deleteBook(int id) {
		Book book = new Book();
		book.setId(id);
		boolean result = this.deleteBook(book);
		return result;
	}

	@Override
	public Set<Book> getAllBooks() {
		return this.bdao.getAllBooks();
	}

}
