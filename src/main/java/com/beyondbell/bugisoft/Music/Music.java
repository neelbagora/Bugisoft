package com.beyondbell.bugisoft.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.HashMap;
import java.util.Map;

public class Music {
	public final static AudioPlayerManager AUDIO_PLAYER_MANAGER = new DefaultAudioPlayerManager();
	private static final Map<Long, GuildMusicManager> musicManagers = new HashMap<>();

	public static void init() {
		AUDIO_PLAYER_MANAGER.getConfiguration().setResamplingQuality(AudioConfiguration.ResamplingQuality.HIGH);
		AudioSourceManagers.registerRemoteSources(AUDIO_PLAYER_MANAGER);
		AudioSourceManagers.registerLocalSource(AUDIO_PLAYER_MANAGER);
	}

	public static GuildMusicManager getGuildAudioPlayer(Guild guild) {
		long guildId = Long.parseLong(guild.getId());
		GuildMusicManager musicManager = musicManagers.get(guildId);

		if (musicManager == null) {
			musicManager = new GuildMusicManager(AUDIO_PLAYER_MANAGER);
			musicManagers.put(guildId, musicManager);
		}

		guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

		return musicManager;
	}

	public static void play(Guild guild, GuildMusicManager musicManager, AudioTrack track) {
		connectToFirstVoiceChannel(guild.getAudioManager());

		musicManager.scheduler.queue(track);
	}

	private static void connectToFirstVoiceChannel(AudioManager audioManager) {
		if (!audioManager.isConnected() && !audioManager.isAttemptingToConnect()) {
			for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
				audioManager.openAudioConnection(voiceChannel);
				break;
			}
		}
	}

}
