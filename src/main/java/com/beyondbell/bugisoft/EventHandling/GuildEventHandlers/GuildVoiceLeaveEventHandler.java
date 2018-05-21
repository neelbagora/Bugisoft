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
		if(Bot.SETTINGS.getProperty("temporaryChannelsCategory") != null
				&& event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).size() != 0
				&& event.getChannelLeft().getParent() == event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0)) {
			new ClearChannels(event);
		}
	}
}
