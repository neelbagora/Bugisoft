package com.beyondbell.bugisoft.Music.Commands;

import com.beyondbell.bugisoft.Music.AudioResultHandler;
import com.beyondbell.bugisoft.Music.GuildMusicManager;
import com.beyondbell.bugisoft.Music.Music;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class PlaySong {
	public PlaySong(final GuildMessageReceivedEvent event, final String trackUrl) {
		TextChannel channel = event.getChannel();

		GuildMusicManager musicManager = Music.getGuildAudioPlayer(channel.getGuild());

		Music.AUDIO_PLAYER_MANAGER.loadItemOrdered(musicManager, trackUrl, new AudioResultHandler(event, trackUrl));
	}
}
