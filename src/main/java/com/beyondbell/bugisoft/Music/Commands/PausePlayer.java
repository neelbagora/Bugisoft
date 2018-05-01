package com.beyondbell.bugisoft.Music.Commands;

import com.beyondbell.bugisoft.Music.Music;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PausePlayer {
	public PausePlayer(MessageReceivedEvent event, boolean shouldPause) {
		if (Music.getGuildAudioPlayer(event.getTextChannel().getGuild()).player.isPaused() != shouldPause) {
			Music.getGuildAudioPlayer(event.getTextChannel().getGuild()).player.setPaused(shouldPause);
		}
	}
}
