package dev.zak.entities;

public class AppError {
	private int errorCode;
	private String errorMessage;
	
	public AppError() {
		super();
	}

	public AppError(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "AppError [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	
	
	
}
