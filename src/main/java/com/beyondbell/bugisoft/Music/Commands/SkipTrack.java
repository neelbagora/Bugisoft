package com.beyondbell.bugisoft.Music.Commands;

import com.beyondbell.bugisoft.Music.GuildMusicManager;
import com.beyondbell.bugisoft.Music.Music;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SkipTrack {
	public SkipTrack(MessageReceivedEvent event) {
		TextChannel channel = event.getTextChannel();
		GuildMusicManager musicManager = Music.getGuildAudioPlayer(channel.getGuild());
		musicManager.scheduler.nextTrack();

		channel.sendMessage("Skipped to next track.").queue();
	}

	public SkipTrack(MessageReceivedEvent event, String numberToSkipString) {
		int numberToSkip = Integer.parseInt(numberToSkipString);

		TextChannel channel = (TextChannel) event.getChannel();
		GuildMusicManager musicManager = Music.getGuildAudioPlayer(channel.getGuild());

		if (numberToSkip > 1) {
			musicManager.scheduler.skip(numberToSkip);
		} else {
			musicManager.scheduler.nextTrack();
		}


		channel.sendMessage("Skipped " + numberToSkip + " tracks.").queue();
	}
}
