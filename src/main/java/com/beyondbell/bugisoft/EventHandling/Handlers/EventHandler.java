package com.beyondbell.bugisoft.EventHandling.Handlers;

abstract class EventHandler {
	EventHandler() {
		Thread thread = new Thread(this::handle);
		thread.start();
	}

	abstract void handle();
}
