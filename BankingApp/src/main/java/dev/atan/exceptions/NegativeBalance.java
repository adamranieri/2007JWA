package dev.atan.exceptions;

public class NegativeBalance extends Exception{
	public NegativeBalance() {
		super("Cannot have a negative balance on this account!");
	}
}
