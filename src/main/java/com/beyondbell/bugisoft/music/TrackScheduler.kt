package com.beyondbell.bugisoft.music

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

class TrackScheduler : AudioEventAdapter() {
	private val queue: BlockingQueue<AudioTrack> = LinkedBlockingQueue()
	private lateinit var currentSong: AudioTrack

	private var repeat: Boolean = false

	override fun onPlayerPause(player: AudioPlayer) {

	}

	override fun onPlayerResume(player: AudioPlayer) {

	}

	override fun onTrackStart(player: AudioPlayer, track: AudioTrack) {

	}

	override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
		if (endReason.mayStartNext) {
			if (repeat) {
				player.playTrack(currentSong)
			} else {
				currentSong = queue.take()
				player.playTrack(currentSong)
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

	fun skip(count: Int, audioPlayer: AudioPlayer) {
		for (i in 1 until count) {
			if (queue.size == 0) {
				break
			} else {
				System.out.println("Skipped: " + queue.peek())
				queue.remove()
			}
		}
		audioPlayer.stopTrack()
		audioPlayer.startTrack(queue.take(), true)
	}

	fun clear() {
		queue.clear()
	}

	fun toggleRepeat() {
		repeat = !repeat
	}

	@Synchronized
	fun shuffle() {
		val shuffledQueue = queue.shuffled()
		queue.clear()
		shuffledQueue.iterator().forEach {
			queue.put(it)
		}
	}
}