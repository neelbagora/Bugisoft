package com.beyondbell.bugisoft.EventHandling.Handlers;

import com.beyondbell.bugisoft.Lobby.MovePeople;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;

public final class GuildVoiceJoinEventHandler extends EventHandler {
	private final GuildVoiceJoinEvent event;

	public GuildVoiceJoinEventHandler(final GuildVoiceJoinEvent event) {
		super();
		this.event = event;
	}

	@Override
	final void handle() {
		new MovePeople(event);
	}
}
