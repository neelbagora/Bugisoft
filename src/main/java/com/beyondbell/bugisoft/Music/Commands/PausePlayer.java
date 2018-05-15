package com.beyondbell.bugisoft.Music.Commands;

import com.beyondbell.bugisoft.Music.Music;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class PausePlayer {
	private final Message message;

	public PausePlayer(GuildMessageReceivedEvent event, boolean shouldPause) {
		String messageText = "Player ";

		if (Music.getGuildAudioPlayer(event.getChannel().getGuild()).player.isPaused() != shouldPause) {
			Music.getGuildAudioPlayer(event.getChannel().getGuild()).player.setPaused(shouldPause);
			messageText += "is now ";
			if (shouldPause) {
				messageText += "paused!";
			} else {
				messageText += "unpaused!";
			}
		} else {
			messageText += "was already ";
			if (shouldPause) {
				messageText += "paused!";
			} else {
				messageText += "unpaused!";
			}
		}

		message = event.getChannel().sendMessage(messageText).complete();

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
