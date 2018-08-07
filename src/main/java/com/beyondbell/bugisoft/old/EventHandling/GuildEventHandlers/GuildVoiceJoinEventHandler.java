package com.beyondbell.bugisoft.old.EventHandling.GuildEventHandlers;

import com.beyondbell.bugisoft.old.Bot;
import com.beyondbell.bugisoft.old.EventHandling.EventHandler;
import com.beyondbell.bugisoft.old.Lobby.MovePeople;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;

public final class GuildVoiceJoinEventHandler extends EventHandler {
	private final GuildVoiceJoinEvent event;

	GuildVoiceJoinEventHandler(final GuildVoiceJoinEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected final void handle() {
		if(Bot.SETTINGS.getProperty("hub") != null) {
			if (event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).size() != 0
					&& event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0).getVoiceChannels().size() != 0) {
				if (event.getChannelJoined() == event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0).getVoiceChannels().get(0)) {
					new MovePeople(event);
				}
			}
		} else {
			Bot.LOGGER.warn("Hub not set");
		}
	}
}
