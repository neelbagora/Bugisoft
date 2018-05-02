package com.beyondbell.bugisoft.Music.Commands;

import com.beyondbell.bugisoft.Music.GuildMusicManager;
import com.beyondbell.bugisoft.Music.Music;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SkipTrack {
	private final Message message;

	public SkipTrack(MessageReceivedEvent event) {
		TextChannel channel = event.getTextChannel();
		GuildMusicManager musicManager = Music.getGuildAudioPlayer(channel.getGuild());
		musicManager.scheduler.nextTrack();

		message = channel.sendMessage("Skipped to next track.").complete();

		Thread thread = new Thread(this::deleteMessage);
		thread.setDaemon(true);
		thread.start();
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

		message = channel.sendMessage("Skipped " + numberToSkip + " tracks.").complete();

		Thread thread = new Thread(this::deleteMessage);
		thread.setDaemon(true);
		thread.start();
	}

	private void deleteMessage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ignored) {

		}
		message.delete().queue();
	}
}
