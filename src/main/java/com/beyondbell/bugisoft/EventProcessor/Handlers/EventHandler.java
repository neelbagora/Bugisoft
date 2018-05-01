package com.beyondbell.bugisoft.EventProcessor.Handlers;

public abstract class EventHandler {
	public EventHandler() {
		Thread thread = new Thread(this::handle);
		thread.start();
	}

	public abstract void handle();
}
