package com.beyondbell.bugisoft.old.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

/**
 * Holder for both the player and a track scheduler for one guild.
 */
public class GuildMusicManager {
	/**
	 * Audio player for the guild.
	 */
	public final AudioPlayer player;
	/**
	 * Track scheduler for the player.
	 */
	public final TrackScheduler scheduler;

	/**
	 * Creates a player and a track scheduler.
	 */
	GuildMusicManager() {
		player = Music.AUDIO_PLAYER_MANAGER.createPlayer();
		scheduler = new TrackScheduler(player);
		player.addListener(scheduler);
	}

	/**
	 * @return Wrapper around AudioPlayer to use it as an AudioSendHandler.
	 */
	AudioPlayerSendHandler getSendHandler() {
		return new AudioPlayerSendHandler(player);
	}
}
