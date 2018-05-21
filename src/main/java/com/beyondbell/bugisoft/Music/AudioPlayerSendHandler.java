package com.beyondbell.bugisoft.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;

final class AudioPlayerSendHandler implements AudioSendHandler {
	private final AudioPlayer audioPlayer;
	private AudioFrame lastFrame;

	AudioPlayerSendHandler(AudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	@Override
	public final boolean canProvide() {
		lastFrame = audioPlayer.provide();
		return lastFrame != null;
	}

	@Override
	public final byte[] provide20MsAudio() {
		return lastFrame.data;
	}

	@Override
	public final boolean isOpus() {
		return true;
	}
}