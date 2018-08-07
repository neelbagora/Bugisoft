package com.beyondbell.bugisoft.old.EventHandling.GuildEventHandlers;

import com.beyondbell.bugisoft.old.Bot;
import com.beyondbell.bugisoft.old.EventHandling.EventHandler;
import com.beyondbell.bugisoft.old.Lobby.ClearChannels;
import com.beyondbell.bugisoft.old.Lobby.MovePeople;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;

public final class GuildVoiceMoveEventHandler extends EventHandler {
	private final GuildVoiceMoveEvent event;

	GuildVoiceMoveEventHandler(final GuildVoiceMoveEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected final void handle() {
		if (Bot.SETTINGS.getProperty("temporaryChannelsCategory") != null && Bot.SETTINGS.getProperty("hub") != null
				&& event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).size() != 0
				&& event.getChannelJoined().getParent() == event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0)
				&& event.getChannelJoined().getName().equals(Bot.SETTINGS.getProperty("hub"))) {
			new MovePeople(event);
		}

		if (Bot.SETTINGS.getProperty("temporaryChannelsCategory") != null && Bot.SETTINGS.getProperty("hub") != null
				&& event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).size() != 0
				&& event.getChannelLeft().getParent() == event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0)
				&& event.getChannelJoined().getParent() != event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0)) {
			new ClearChannels(event);
		}
	}
}
