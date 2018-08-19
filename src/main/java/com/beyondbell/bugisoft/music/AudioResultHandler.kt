package com.beyondbell.bugisoft.music

import com.beyondbell.bugisoft.DEFAULT_MESSAGE_TIMEOUT
import com.beyondbell.bugisoft.DEFAULT_TIME_UNIT
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.core.entities.TextChannel

class AudioResultHandler(private val guildMusicManager: GuildMusicManager, private val channel: TextChannel, private val trackContext: Array<String>) : AudioLoadResultHandler {
	override fun trackLoaded(audioTrack: AudioTrack) {
		guildMusicManager.trackScheduler.queue(audioTrack, guildMusicManager.audioPlayer)
		channel.sendMessage("Queued Song: ${audioTrack.info.title}.").complete()
				.delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
	}

	override fun playlistLoaded(audioPlaylist: AudioPlaylist) {
		var count = 0
		audioPlaylist.tracks.iterator().forEach {
			guildMusicManager.trackScheduler.queue(it, guildMusicManager.audioPlayer)
			count++
		}
		channel.sendMessage("Queued Playlist ${audioPlaylist.name} with ${audioPlaylist.tracks.size} Songs.").complete()
				.delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
	}

	override fun noMatches() {
		channel.sendMessage("Nothing found by $trackContext").complete()
				.delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
	}

	override fun loadFailed(exception: FriendlyException) {
		channel.sendMessage("Could not play: " + exception.message).complete()
				.delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
	}
}