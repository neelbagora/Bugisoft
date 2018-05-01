package com.beyondbell.bugisoft.Music.Commands;

import com.beyondbell.bugisoft.Music.GuildMusicManager;
import com.beyondbell.bugisoft.Music.Music;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ListQueue {
	public ListQueue(MessageReceivedEvent event) {
		GuildMusicManager musicManager = Music.getGuildAudioPlayer(event.getTextChannel().getGuild());

		EmbedBuilder embedBuilder = new EmbedBuilder();

		if (musicManager.player.getPlayingTrack() != null) {
			embedBuilder.addField("Current Song", musicManager.player.getPlayingTrack().getInfo().title, false);
		}

		event.getChannel().sendMessage(embedBuilder.build()).queue();
	}
}
