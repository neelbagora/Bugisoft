package com.beyondbell.bugisoft.EventHandling.UserEventsHandler;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.EventHandling.EventHandler;
import com.beyondbell.bugisoft.Lobby.MovePeople;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;

public class UserUpdateGameEventHandler extends EventHandler {
	private final UserUpdateGameEvent event;

	public UserUpdateGameEventHandler(UserUpdateGameEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected void handle() {
		if (Bot.SETTINGS.getProperty("defaultTempChannel") == null) {
			event.getGuild().getDefaultChannel().sendMessage("Lobby not set").queue();
		} else {
			new MovePeople(event);
		}
	}
}
