package com.beyondbell.bugisoft.EventHandling;

public abstract class EventHandler {
	protected EventHandler() {
		final Thread thread = new Thread(this::handle);
		thread.start();
	}

	protected abstract void handle();
}
