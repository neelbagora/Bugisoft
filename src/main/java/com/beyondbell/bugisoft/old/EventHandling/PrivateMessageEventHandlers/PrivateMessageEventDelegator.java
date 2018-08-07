package com.beyondbell.bugisoft.old.EventHandling.PrivateMessageEventHandlers;

import net.dv8tion.jda.core.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;

public class PrivateMessageEventDelegator {
	public PrivateMessageEventDelegator(final GenericPrivateMessageEvent event) {
		if (event instanceof PrivateMessageReceivedEvent) {
			new PrivateMessageReceivedEventHandler((PrivateMessageReceivedEvent) event);
		}
	}
}
