package com.beyondbell.bugisoft.EventProcessor;

import com.beyondbell.bugisoft.EventProcessor.Handlers.CommandHandlers.ServerHandlers.MessageReceivedEventHandler;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class EventCommandListener implements EventListener {
	@Override
	public void onEvent(Event event) {
		if (event instanceof MessageReceivedEvent) {
			new MessageReceivedEventHandler((MessageReceivedEvent) event);
		}
	}
}
