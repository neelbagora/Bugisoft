package com.beyondbell.bugisoft.old.EventHandling.JDAEventHandlers;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.StatusChangeEvent;

public final class JDAEventDelegator {
	public JDAEventDelegator(final Event event) {
		if (event instanceof StatusChangeEvent) {
			new StatusChangeEventHandler((StatusChangeEvent) event);
		}
	}
}
