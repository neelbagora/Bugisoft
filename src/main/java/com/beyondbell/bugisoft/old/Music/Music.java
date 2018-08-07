package com.beyondbell.bugisoft.old.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;

import java.util.HashMap;
import java.util.Map;

public final class Music {
	public final static AudioPlayerManager AUDIO_PLAYER_MANAGER = new DefaultAudioPlayerManager();
	private static final Map<Long, GuildMusicManager> musicManagers = new HashMap<>();

	public static void init() {
		AUDIO_PLAYER_MANAGER.getConfiguration().setResamplingQuality(AudioConfiguration.ResamplingQuality.HIGH);
		AudioSourceManagers.registerRemoteSources(AUDIO_PLAYER_MANAGER);
	}

	public static GuildMusicManager getGuildAudioPlayer(final Guild guild) {
		if (musicManagers.get(guild.getIdLong()) == null) {
			musicManagers.put(guild.getIdLong(), new GuildMusicManager());
		}

		final GuildMusicManager musicManager = musicManagers.get(guild.getIdLong());

		guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

		return musicManager;
	}

	static void play(GuildMusicManager musicManager, AudioTrack track) {
		musicManager.scheduler.queue(track);
	}
}
