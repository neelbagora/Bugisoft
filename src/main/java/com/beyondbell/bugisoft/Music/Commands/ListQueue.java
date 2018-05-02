package com.beyondbell.bugisoft.Music.Commands;

import com.beyondbell.bugisoft.Music.GuildMusicManager;
import com.beyondbell.bugisoft.Music.Music;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ListQueue {
	private final Message message;

	public ListQueue(MessageReceivedEvent event) {
		GuildMusicManager musicManager = Music.getGuildAudioPlayer(event.getTextChannel().getGuild());

		EmbedBuilder embedBuilder = new EmbedBuilder();

		if (musicManager.player.getPlayingTrack() != null) {
			embedBuilder.addField("Current Song", musicManager.player.getPlayingTrack().getInfo().title, false);
		}

		message = event.getChannel().sendMessage(embedBuilder.build()).complete();

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
