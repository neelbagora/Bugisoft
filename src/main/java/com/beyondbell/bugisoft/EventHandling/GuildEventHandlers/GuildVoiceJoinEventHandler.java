package com.beyondbell.bugisoft.EventHandling.GuildEventHandlers;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.EventHandling.EventHandler;
import com.beyondbell.bugisoft.Lobby.MovePeople;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;

public final class GuildVoiceJoinEventHandler extends EventHandler {
	private final GuildVoiceJoinEvent event;

	GuildVoiceJoinEventHandler(final GuildVoiceJoinEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected final void handle() {
		if(Bot.SETTINGS.getProperty("defaultTempChannel") != null) {
			new MovePeople(event);
		} else {
			if (event.getGuild().getDefaultChannel() != null) {
				event.getGuild().getDefaultChannel().sendMessage("Lobby not set").queue();
			} else {
				Bot.LOGGER.warn("Cannot Access Default Channel of Guild: " + event.getGuild().getId());
			}
		}
	}
}
