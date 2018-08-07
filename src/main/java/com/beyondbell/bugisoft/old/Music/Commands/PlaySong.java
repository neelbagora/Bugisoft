package com.beyondbell.bugisoft.old.Music.Commands;

import com.beyondbell.bugisoft.old.Music.AudioResultHandler;
import com.beyondbell.bugisoft.old.Music.GuildMusicManager;
import com.beyondbell.bugisoft.old.Music.Music;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class PlaySong {
	public PlaySong(final GuildMessageReceivedEvent event, final String trackUrl) {
		GuildMusicManager musicManager = Music.getGuildAudioPlayer(event.getGuild());
		Music.AUDIO_PLAYER_MANAGER.loadItemOrdered(musicManager, trackUrl, new AudioResultHandler(event, trackUrl));
	}
}
