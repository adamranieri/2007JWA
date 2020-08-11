package dev.ranieri.exceptions;

public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public BookNotFoundException() {
		super("Book not found. Please try again.");
	}

}
