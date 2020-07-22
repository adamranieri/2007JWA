package dev.laurent.exceptions;

public class NegativeBalanceException extends Exception{
	public NegativeBalanceException(){
		super("Account balance cannot be less than 0");
	}
}
