package com.beyondbell.bugisoft.Minigames.RPS;

public class UserNotInGameException extends Exception {
	public UserNotInGameException() {
		super();
	}

	public UserNotInGameException(String error) {
		super(error);
	}
}
