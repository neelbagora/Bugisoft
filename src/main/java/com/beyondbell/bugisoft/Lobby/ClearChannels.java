package com.beyondbell.bugisoft.Lobby;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public class ClearChannels {

	//event pushed from 'VoiceChannelLeaveEvent', conditions are met in the class
	public ClearChannels(GuildVoiceLeaveEvent event) {
		if(!event.getChannelLeft().getName().toLowerCase().equals("spotify")
				|| !event.getChannelLeft().getName().toLowerCase().equals("no game lobby")
				&& event.getChannelLeft().getMembers().size() == 0) {
			event.getChannelLeft().delete();
		}
	}
}
