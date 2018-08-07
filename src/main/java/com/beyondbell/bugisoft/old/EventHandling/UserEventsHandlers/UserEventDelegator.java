package com.beyondbell.bugisoft.old.EventHandling.UserEventsHandlers;

import net.dv8tion.jda.core.events.user.GenericUserEvent;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;

public class UserEventDelegator {
	public UserEventDelegator(final GenericUserEvent event) {
		if (event instanceof UserUpdateGameEvent) {
			new UserUpdateGameEventHandler((UserUpdateGameEvent) event);
		}
	}
}
