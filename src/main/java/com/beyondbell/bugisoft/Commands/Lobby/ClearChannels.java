package com.beyondbell.bugisoft.Commands.Lobby;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public class ClearChannels {

	//event pushed from 'VoiceChannelLeaveEvent', conditions are met in the class
	public ClearChannels(GuildVoiceLeaveEvent event) {
		event.getChannelLeft().delete();
	}
}
