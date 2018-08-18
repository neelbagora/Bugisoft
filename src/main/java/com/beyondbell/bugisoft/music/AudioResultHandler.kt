package com.beyondbell.bugisoft.music

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent


class AudioResultHandler : AudioLoadResultHandler {
	override fun loadFailed(exception: FriendlyException?) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun trackLoaded(track: AudioTrack?) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun noMatches() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun playlistLoaded(playlist: AudioPlaylist?) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}


	private val event: GuildMessageReceivedEvent
	private val guildMusicManager: GuildMusicManager
	private val trackUrl: String

	private var playlist: AudioPlaylist? = null

	fun AudioResultHandler(event: GuildMessageReceivedEvent, trackUrl: String): ??? {
		this.event = event
		this.trackUrl = trackUrl

		this.guildMusicManager = Music.getGuildAudioPlayer(event.guild)
	}

	override fun trackLoaded(track: AudioTrack) {
		JoinCurrentVoice()
		if (Music.getGuildAudioPlayer(event.channel.guild).player.isPaused()) {
			Music.getGuildAudioPlayer(event.channel.guild).player.setPaused(false)
		}

		MessageBroadcaster(event.channel.sendMessage("Adding to queue " + track.info.title).complete(), 5000)

		Music.play(guildMusicManager, track)
	}

	override fun playlistLoaded(playlist: AudioPlaylist) {
		JoinCurrentVoice()
		if (Music.getGuildAudioPlayer(event.channel.guild).player.isPaused()) {
			Music.getGuildAudioPlayer(event.channel.guild).player.setPaused(false)
		}

		this.playlist = playlist

		val t = Thread(Runnable { this.queueSongs() })
		t.run()
	}

	override fun noMatches() {
		MessageBroadcaster(event.channel.sendMessage("Nothing found by $trackUrl").complete(), 5000)
	}

	override fun loadFailed(exception: FriendlyException) {
		MessageBroadcaster(event.channel.sendMessage("Could not play: " + exception.message).complete(), 5000)
	}

	private fun queueSongs() {
		var count = 0
		for (audioTrack in playlist!!.tracks) {
			if (audioTrack != null) {
				Music.play(guildMusicManager, audioTrack)
				count++
			}
		}
		playlist = null
		MessageBroadcaster(event.channel.sendMessage("Added to Queue " + count + " songs of the playlist " + playlist!!.name).complete(), 5000)
	}

	private fun JoinCurrentVoice() {
		if (!event.guild.audioManager.isConnected && !event.guild.audioManager.isAttemptingToConnect) {
			for (voiceChannel in event.guild.voiceChannels) {
				for (member in voiceChannel.members) {
					if (event.member === member) {
						event.guild.audioManager.openAudioConnection(voiceChannel)
						break
					}
				}
			}
		}
	}
}