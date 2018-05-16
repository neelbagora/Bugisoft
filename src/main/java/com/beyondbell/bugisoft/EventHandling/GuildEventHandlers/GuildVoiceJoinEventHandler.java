package com.beyondbell.bugisoft.EventHandling.GuildEventHandlers;

import com.beyondbell.bugisoft.Bot;
import com.beyondbell.bugisoft.EventHandling.EventHandler;
import com.beyondbell.bugisoft.Lobby.MovePeople;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;

public final class GuildVoiceJoinEventHandler extends EventHandler {
	private final GuildVoiceJoinEvent event;

	public GuildVoiceJoinEventHandler(final GuildVoiceJoinEvent event) {
		super();
		this.event = event;
	}

	@Override
	protected final void handle() {
		if(Bot.settings.getProperty("defaultTempChannel") == "") {
			event.getGuild().getDefaultChannel().sendMessage("Lobby not set");
		} else {
			new MovePeople(event);
		}
	}
}
