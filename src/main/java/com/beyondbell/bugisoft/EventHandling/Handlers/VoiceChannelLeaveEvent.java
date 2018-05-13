package com.beyondbell.bugisoft.EventHandling.Handlers;

import com.beyondbell.bugisoft.Lobby.ClearChannels;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public class VoiceChannelLeaveEvent extends EventHandler {
	private final GuildVoiceLeaveEvent event;

	public VoiceChannelLeaveEvent(final GuildVoiceLeaveEvent voice) {
		super();
		event = voice;
	}

	@Override
	void handle() {
		if(!event.getChannelLeft().getId().equals(event.getGuild().getAfkChannel().getId())
				&& (!event.getChannelLeft().getName().toLowerCase().contains("esl"))
				&& (!event.getChannelLeft().getName().toLowerCase().contains("strats"))
				&& !event.getChannelLeft().getId().equals(event.getGuild().getVoiceChannels().get(0).getId())
				&& (event.getChannelLeft().getMembers().size() == 0)) {
			new ClearChannels(event);
		}
	}

}
