package com.beyondbell.bugisoft.EventProcessor;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class EventLoggerListener implements EventListener {
	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {

		}
	}
}
