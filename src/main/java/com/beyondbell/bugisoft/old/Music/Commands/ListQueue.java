package com.beyondbell.bugisoft.old.Music.Commands;

import com.beyondbell.bugisoft.old.Music.GuildMusicManager;
import com.beyondbell.bugisoft.old.Music.Music;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class ListQueue {
	private final Message message;

	public ListQueue(GuildMessageReceivedEvent event) {
		GuildMusicManager musicManager = Music.getGuildAudioPlayer(event.getChannel().getGuild());

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
