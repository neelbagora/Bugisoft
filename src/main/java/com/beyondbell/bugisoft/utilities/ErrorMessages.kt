package com.beyondbell.bugisoft.utilities

import com.beyondbell.bugisoft.DEFAULT_MESSAGE_TIMEOUT
import com.beyondbell.bugisoft.DEFAULT_TIME_UNIT
import net.dv8tion.jda.core.entities.TextChannel
import java.util.concurrent.TimeUnit

fun sendErrorMessage(errorMessage: ErrorMessage, channel: TextChannel) {
	channel.sendMessage(errorMessage.message).complete()
			.delete().queueAfter(DEFAULT_MESSAGE_TIMEOUT, DEFAULT_TIME_UNIT)
}

fun sendErrorMessage(errorMessage: ErrorMessage, channel: TextChannel, timeout: Long, timeUnit: TimeUnit) {
	channel.sendMessage(errorMessage.message).complete()
			.delete().queueAfter(timeout, timeUnit)
}

enum class ErrorMessage(val message: String) {
	InvalidParameters("Please Confirm You are Using the Correct Format for the Parameters."),
	NotInVoice("Please Join a Voice Channel Then Try Again."),
	PingLimitReached("Sorry, the Ping Limit has Been Reached. Please Wait Until Other Requests are Handled Before Trying Again."),
}