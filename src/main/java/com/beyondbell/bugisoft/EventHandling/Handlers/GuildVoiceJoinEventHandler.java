package com.beyondbell.bugisoft.EventHandling.Handlers;

import com.beyondbell.bugisoft.Lobby.MovePeople;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;

public class GuildVoiceJoinEventHandler extends EventHandler {
	private final GuildVoiceJoinEvent event;

	public GuildVoiceJoinEventHandler(final GuildVoiceJoinEvent event) {
		super();
		this.event = event;
	}

	@Override
	void handle() {
		if(!event.getChannelJoined().equals(event.getGuild().getAfkChannel()) && event.getChannelJoined().getId().equals(event.getGuild().getVoiceChannels().get(0).getId())) {
			new MovePeople(event);
		}
	}
}
