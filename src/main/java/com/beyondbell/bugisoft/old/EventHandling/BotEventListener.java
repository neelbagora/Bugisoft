package com.beyondbell.bugisoft.old.EventHandling;

import com.beyondbell.bugisoft.old.EventHandling.GuildEventHandlers.GuildEventDelegator;
import com.beyondbell.bugisoft.old.EventHandling.JDAEventHandlers.JDAEventDelegator;
import com.beyondbell.bugisoft.old.EventHandling.PrivateMessageEventHandlers.PrivateMessageEventDelegator;
import com.beyondbell.bugisoft.old.EventHandling.UserEventsHandlers.UserEventDelegator;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.GenericGuildEvent;
import net.dv8tion.jda.core.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.core.events.user.GenericUserEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public final class BotEventListener implements EventListener {
	@Override
	public final void onEvent(final Event event) {
		if (event instanceof GenericGuildEvent) {
			new GuildEventDelegator((GenericGuildEvent) event);
		} else if (event instanceof GenericUserEvent) {
			new UserEventDelegator((GenericUserEvent) event);
		} else if (event instanceof GenericPrivateMessageEvent) {
			new PrivateMessageEventDelegator((GenericPrivateMessageEvent) event);
		} else {
			new JDAEventDelegator(event);
		}
	}
}
