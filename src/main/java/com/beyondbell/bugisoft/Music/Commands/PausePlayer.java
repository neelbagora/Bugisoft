package com.beyondbell.bugisoft.Music.Commands;

import com.beyondbell.bugisoft.Music.Music;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PausePlayer {
	private final Message message;

	public PausePlayer(MessageReceivedEvent event, boolean shouldPause) {
		String messageText = "Player ";

		if (Music.getGuildAudioPlayer(event.getTextChannel().getGuild()).player.isPaused() != shouldPause) {
			Music.getGuildAudioPlayer(event.getTextChannel().getGuild()).player.setPaused(shouldPause);
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

		message = event.getTextChannel().sendMessage(messageText).complete();

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
