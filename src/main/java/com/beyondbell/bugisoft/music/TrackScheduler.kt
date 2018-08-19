package com.beyondbell.bugisoft.music

import com.beyondbell.bugisoft.DEFAULT_MESSAGE_TIMEOUT
import com.beyondbell.bugisoft.DEFAULT_TIME_UNIT
import com.beyondbell.bugisoft.SONGS_TO_SHOW_IN_LIST
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class TrackScheduler : AudioEventAdapter() {
	private val queue: BlockingQueue<AudioTrack> = LinkedBlockingQueue()

	private var repeat: Boolean = false

	@Volatile
	private lateinit var currentSong: AudioTrack

	override fun onPlayerPause(player: AudioPlayer) {

	}

	override fun onPlayerResume(player: AudioPlayer) {

	}

	override fun onTrackStart(player: AudioPlayer, track: AudioTrack) {
		currentSong = track
	}

	override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
		if (endReason.mayStartNext) {
			if (repeat) {
				player.playTrack(currentSong)
			} else {
				player.playTrack(queue.take())
			}
		}
	}

	override fun onTrackException(player: AudioPlayer, track: AudioTrack, exception: FriendlyException) {
		exception.printStackTrace()
	}

	override fun onTrackStuck(player: AudioPlayer, track: AudioTrack, thresholdMs: Long) {
		queue.take()
	}

	fun queue(audioTrack: AudioTrack, audioPlayer: AudioPlayer) {
		if (!audioPlayer.startTrack(audioTrack, true)) {
			queue.put(audioTrack)
		}
	}

	fun skip(count: Int, audioPlayer: AudioPlayer, channel: TextChannel) {
		for (i in 1 until count) {
			if (queue.size == 0) {
				break
			} else {
				queue.remove()
			}
		}
		audioPlayer.stopTrack()
		audioPlayer.startTrack(queue.take(), true)
		channel.sendMessage("Skipped $count Songs.").complete().delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
	}

	fun clear(channel: TextChannel) {
		val queueSize = queue.size
		queue.clear()
		channel.sendMessage("Cleared $queueSize Songs from Queue.").complete().delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
	}

	fun toggleRepeat(channel: TextChannel) {
		repeat = !repeat
		if (repeat) {
			channel.sendMessage("Repeating Song: ${currentSong.info.title}.").complete().delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
		} else {
			channel.sendMessage("Stopped Repeating.").complete().delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
		}
	}

	@Synchronized
	fun shuffle(channel: TextChannel) {
		val shuffledQueue = queue.shuffled()
		queue.clear()
		shuffledQueue.iterator().forEach {
			queue.put(it)
		}
		channel.sendMessage("Shuffled Queue!").complete().delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
	}

	fun list(event: GuildMessageReceivedEvent) {
		val embedBuilder = EmbedBuilder().setTitle("Music Queue").setAuthor(event.author.name)
		embedBuilder.addField("Current Song:", currentSong.info.title, false)
		var max = 1
		queue.forEach {
			if (max <= SONGS_TO_SHOW_IN_LIST) {
				embedBuilder.addField("Song $max:", it.info.title, false)
			}
			max++
		}
		event.channel.sendMessage(embedBuilder.build()).queue()
	}
}