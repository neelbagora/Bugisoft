package com.beyondbell.bugisoft.EventProcessor.Handlers.OtherHandlers.BotHandlers;

import com.beyondbell.bugisoft.EventProcessor.Handlers.EventHandler;
import net.dv8tion.jda.core.events.ReadyEvent;

public class ReadyEventHandler extends EventHandler {
	private final ReadyEvent event;

	public ReadyEventHandler(final ReadyEvent event) {
		super();
		this.event = event;
	}

	@Override
	public void handle() {
		System.out.println(event.getJDA().getStatus());
	}
}
