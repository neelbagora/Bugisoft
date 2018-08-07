package com.beyondbell.bugisoft.old.Minigames.RPS;

public class UserNotInGameException extends Exception {
	public UserNotInGameException() {
		super();
	}

	public UserNotInGameException(String error) {
		super(error);
	}
}
