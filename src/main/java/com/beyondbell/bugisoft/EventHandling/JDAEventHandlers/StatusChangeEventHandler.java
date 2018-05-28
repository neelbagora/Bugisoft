package com.beyondbell.bugisoft.EventHandling.JDAEventHandlers;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.EventHandling.EventHandler;
import net.dv8tion.jda.core.events.StatusChangeEvent;

final class StatusChangeEventHandler extends EventHandler {
	private final StatusChangeEvent event;

	StatusChangeEventHandler(final StatusChangeEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected final void handle() {
		Bot.LOGGER.trace(event.toString());
	}
}
