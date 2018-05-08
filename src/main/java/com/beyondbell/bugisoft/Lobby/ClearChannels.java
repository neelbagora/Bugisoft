package com.beyondbell.bugisoft.Lobby;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;

public class ClearChannels {
	public ClearChannels(GuildVoiceLeaveEvent event) {
		event.getChannelLeft().delete().queue();
	}
}
