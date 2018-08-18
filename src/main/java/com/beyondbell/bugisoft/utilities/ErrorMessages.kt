package com.beyondbell.bugisoft.utilities

import net.dv8tion.jda.core.entities.TextChannel
import java.util.concurrent.TimeUnit

fun sendErrorMessage(errorMessage: ErrorMessage, channel: TextChannel) {
	channel.sendMessage(errorMessage.message).queue()
}

fun sendErrorMessage(errorMessage: ErrorMessage, channel: TextChannel, timeout: Long, timeUnit: TimeUnit) {
	val message = channel.sendMessage(errorMessage.message).complete()
	message.delete().queueAfter(timeout, timeUnit)
}

enum class ErrorMessage(val message: String) {
	NotInVoice("Please Join a Voice Channel Then Try Again."),
	PingLimitReached("Sorry, the Ping Limit has Been Reached. Please Wait Until Other Requests are Handled Before Trying Again."),
	MusicTrackException(""),
	MusicTrackStuck("")
}