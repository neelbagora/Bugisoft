package com.beyondbell.bugisoft.EventHandling.GuildEventHandlers;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.EventHandling.EventHandler;
import com.beyondbell.bugisoft.Lobby.ClearChannels;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public final class GuildVoiceLeaveEventHandler extends EventHandler {
	private final GuildVoiceLeaveEvent event;

	GuildVoiceLeaveEventHandler(final GuildVoiceLeaveEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected final void handle() {
		if(Bot.SETTINGS.getProperty("hub") != null) {
			Bot.LOGGER.error("Lobby Not Set");
		} else {
			new ClearChannels(event);
		}
	}
}
