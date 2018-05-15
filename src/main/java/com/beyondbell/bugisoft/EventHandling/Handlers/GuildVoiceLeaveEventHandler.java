package com.beyondbell.bugisoft.EventHandling.Handlers;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public class GuildVoiceLeaveEventHandler extends EventHandler {
	private final GuildVoiceLeaveEvent event;

	public GuildVoiceLeaveEventHandler(final GuildVoiceLeaveEvent event) {
		super();
		this.event = event;
	}

	@Override
	void handle() {
		if (event.getChannelLeft().getMembers().size() == 0 && event.getChannelLeft() != event.getGuild().getAfkChannel() && event.getChannelLeft() != event.getGuild().getVoiceChannels().get(0)) {
			// new ClearChannels(event);
		}
	}
}
