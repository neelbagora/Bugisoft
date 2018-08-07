package com.beyondbell.bugisoft.old.EventHandling.JDAEventHandlers;

import com.beyondbell.bugisoft.old.Bot;
import com.beyondbell.bugisoft.old.EventHandling.EventHandler;
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
