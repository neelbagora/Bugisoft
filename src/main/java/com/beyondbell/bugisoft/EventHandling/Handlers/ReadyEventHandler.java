package com.beyondbell.bugisoft.EventHandling.Handlers;

import net.dv8tion.jda.core.events.ReadyEvent;

public class ReadyEventHandler extends EventHandler {
	private final ReadyEvent event;

	public ReadyEventHandler(final ReadyEvent event) {
		super();
		this.event = event;
	}

	@Override
	void handle() {
		System.out.println(event.getJDA().getStatus());
	}
}
