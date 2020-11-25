package com.github.elhamjamshidpey.africanBoardGame.exception;

public class InvalidMoveException extends Exception {
	
	private final String message;

	public InvalidMoveException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


}
