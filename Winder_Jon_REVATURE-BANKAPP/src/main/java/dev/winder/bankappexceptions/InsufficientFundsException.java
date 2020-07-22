package dev.winder.bankappexceptions;

public class InsufficientFundsException extends Exception {
	
	
	public InsufficientFundsException() {
		
		super("Due to insufficient funds, your transaction could not be completed");
	}
	
	

}
