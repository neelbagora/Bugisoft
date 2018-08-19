package com.beyondbell.bugisoft.music

class GuildMusicManager {
	val audioPlayer = Music.getNewPlayer()
	val trackScheduler = TrackScheduler()

	init {
		audioPlayer.addListener(trackScheduler)
	}

	fun getSendHandler(): AudioPlayerSendHandler {
		return AudioPlayerSendHandler(audioPlayer)
	}
}