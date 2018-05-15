package com.beyondbell.bugisoft.Lobby;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.managers.GuildController;

public class ClearChannels {

	//event pushed from 'VoiceChannelLeaveEvent', conditions are met in the class
	public ClearChannels(GuildVoiceLeaveEvent event) {
		event.getChannelLeft().delete();
	}
}
