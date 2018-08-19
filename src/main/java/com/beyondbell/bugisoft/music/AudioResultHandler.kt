package com.beyondbell.bugisoft.music

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack

class AudioResultHandler(private val guildMusicManager: GuildMusicManager) : AudioLoadResultHandler {
	override fun trackLoaded(audioTrack: AudioTrack) {
		guildMusicManager.trackScheduler.queue(audioTrack, guildMusicManager.audioPlayer)
	}

	override fun playlistLoaded(audioPlaylist: AudioPlaylist) {
		var count = 0
		audioPlaylist.tracks.iterator().forEach {
			guildMusicManager.trackScheduler.queue(it, guildMusicManager.audioPlayer)
			count++
		}
	}

	override fun noMatches() {
		System.out.println("NO MATCHES")
//		MessageBroadcaster(event.channel.sendMessage("Nothing found by $trackUrl").complete(), 5000)
	}

	override fun loadFailed(exception: FriendlyException) {
		System.out.println("NO MATCHES")
//		MessageBroadcaster(event.channel.sendMessage("Could not play: " + exception.message).complete(), 5000)
	}
}