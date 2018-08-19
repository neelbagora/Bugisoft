package com.beyondbell.bugisoft.music

import com.beyondbell.bugisoft.utilities.ErrorMessage
import com.beyondbell.bugisoft.utilities.sendErrorMessage
import com.sedmelluq.discord.lavaplayer.format.StandardAudioDataFormats
import com.sedmelluq.discord.lavaplayer.player.AudioConfiguration
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.User
import net.dv8tion.jda.core.entities.VoiceChannel
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import java.util.HashMap

object Music {
	private val playerManager = DefaultAudioPlayerManager()
	private val audioPlayers = HashMap<Long, GuildMusicManager>()

	init {
		playerManager.configuration.opusEncodingQuality = AudioConfiguration.OPUS_QUALITY_MAX
		playerManager.configuration.resamplingQuality = AudioConfiguration.ResamplingQuality.HIGH
		playerManager.configuration.outputFormat = StandardAudioDataFormats.DISCORD_OPUS

		AudioSourceManagers.registerRemoteSources(playerManager)
	}

	fun join(event: GuildMessageReceivedEvent) {
		// Checks if Already Connected or Trying
		if (event.guild.audioManager.isConnected || event.guild.audioManager.isAttemptingToConnect) {
			return
		}

		// Finds User's Voice Channel
		val voiceChannel = getVoiceChannel(event.guild, event.author)
		if (voiceChannel == null) {
			sendErrorMessage(ErrorMessage.NotInVoice, event.channel)
			return
		}

		// Checks for Permission
		// TODO Check for Permission

		// Joins User's Voice Channel
		event.guild.audioManager.openAudioConnection(voiceChannel)
		event.guild.audioManager.sendingHandler = getGuildMusicManager(event.guild.idLong).getSendHandler()
	}

	fun leave(event: GuildMessageReceivedEvent) {
		event.guild.audioManager.closeAudioConnection()
	}

	fun play(event: GuildMessageReceivedEvent, songContext: Array<String>) {
		val guildMusicManager = getGuildMusicManager(event.guild.idLong)
		playerManager.loadItemOrdered(guildMusicManager.audioPlayer, TrackFinder.getAudioTrackURLFromString(songContext), AudioResultHandler(guildMusicManager, event.channel, songContext))
		join(event)
	}

	fun skip(event: GuildMessageReceivedEvent, count: Int) {
		val guildMusicManager = getGuildMusicManager(event.guild.idLong)
		guildMusicManager.trackScheduler.skip(count, guildMusicManager.audioPlayer, event.channel)
	}

	fun clear(event: GuildMessageReceivedEvent) {
		getGuildMusicManager(event.guild.idLong).trackScheduler.clear(event.channel)
	}

	fun shuffle(event: GuildMessageReceivedEvent) {
		getGuildMusicManager(event.guild.idLong).trackScheduler.shuffle(event.channel)
	}

	fun toggleRepeatMode(event: GuildMessageReceivedEvent) {
		getGuildMusicManager(event.guild.idLong).trackScheduler.toggleRepeat(event.channel)
	}

	fun list(event: GuildMessageReceivedEvent) {
		getGuildMusicManager(event.guild.idLong).trackScheduler.list(event)
	}

	fun getNewPlayer(): AudioPlayer {
		return playerManager.createPlayer()
	}

	@Synchronized
	fun getGuildMusicManager(guildId: Long): GuildMusicManager {
		return audioPlayers.getOrPut(guildId) {
			GuildMusicManager()
		}
	}

	private fun getVoiceChannel(guild: Guild, user: User): VoiceChannel? {
		for (voiceChannel in guild.voiceChannels) {
			for (voiceUser in voiceChannel.members) {
				if (user == voiceUser.user) {
					return voiceChannel
				}
			}
		}
		return null
	}
}