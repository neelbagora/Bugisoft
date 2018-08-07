package com.beyondbell.bugisoft.old.Lobby;

import com.beyondbell.bugisoft.old.Bot;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;

public class ClearChannels {
	public ClearChannels(final GuildVoiceLeaveEvent event) {
		if (Bot.SETTINGS.getProperty("temporaryChannelsCategory") != null && event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).size() != 0 && Bot.SETTINGS.getProperty("hub") != null) {
			for (final VoiceChannel voiceChannel : event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0).getVoiceChannels()) {
				if (!voiceChannel.getName().equals(Bot.SETTINGS.getProperty("hub")) && voiceChannel.getMembers().size() == 0) {
					voiceChannel.delete().queue();
				}
			}
		}
	}

	public ClearChannels(GuildVoiceMoveEvent event) {
		if (Bot.SETTINGS.getProperty("temporaryChannelsCategory") != null && event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).size() != 0 && Bot.SETTINGS.getProperty("hub") != null) {
			for (final VoiceChannel voiceChannel : event.getGuild().getCategoriesByName(Bot.SETTINGS.getProperty("temporaryChannelsCategory"), true).get(0).getVoiceChannels()) {
				if (!voiceChannel.getName().equals(Bot.SETTINGS.getProperty("hub")) && voiceChannel.getMembers().size() == 0) {
					voiceChannel.delete().queue();
				}
			}
		}
	}
}
