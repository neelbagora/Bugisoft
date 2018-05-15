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
		try {
			if(!event.getChannelJoined().getId().equals(event.getGuild().getAfkChannel().getId())
					&& event.getChannelJoined().getName().substring(0, 5).toLowerCase().equals("lobby")) {
				new MovePeople(event);
			}
		} catch(NullPointerException e) {
			if(event.getChannelJoined().getName().toLowerCase().contains("lobby")) {
				new MovePeople(event);
			}
		}


	}
}
