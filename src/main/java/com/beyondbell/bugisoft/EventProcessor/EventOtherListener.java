package com.beyondbell.bugisoft.EventProcessor;

import com.beyondbell.bugisoft.EventProcessor.Handlers.OtherHandlers.BotHandlers.ReadyEventHandler;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class EventOtherListener implements EventListener {
	@Override
	public void onEvent(Event event) {
		if (event instanceof ReadyEvent) {
			new ReadyEventHandler((ReadyEvent) event);
		}
	}
}
