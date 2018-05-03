package com.beyondbell.bugisoft.EventProcessor.Handlers;

public abstract class EventHandler {
	protected EventHandler() {
		Thread thread = new Thread(this::handle);
		thread.start();
	}

	protected abstract void handle();
}
