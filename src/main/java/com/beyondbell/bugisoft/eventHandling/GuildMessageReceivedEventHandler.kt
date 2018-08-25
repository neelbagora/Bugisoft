package com.beyondbell.bugisoft.eventHandling

import com.beyondbell.bugisoft.DEFAULT_INVITE_TIME
import com.beyondbell.bugisoft.chatModeration.delete
import com.beyondbell.bugisoft.chatModeration.preventSpam
import com.beyondbell.bugisoft.chatModeration.temporaryMute
import com.beyondbell.bugisoft.invite.createInvite
import com.beyondbell.bugisoft.music.Music
import com.beyondbell.bugisoft.ping.Ping
import com.beyondbell.bugisoft.utilities.getParameters
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

fun handleGuildMessageReceivedEvent(event: GuildMessageReceivedEvent) {
	// Checks if Message is Empty
	if (event.message.contentRaw.isEmpty()) {
		return
	}

	// Checks for Spam
	preventSpam(event)

	// Checks if It is Bot
	if (event.author.isBot) {
		return
	}

	// Checks for Prefixes
	if (event.message.contentRaw[0] != '!' && event.message.contentRaw[0] != ';') {
		return
	}

	val parameters = getParameters(event.message.contentRaw)

	when (parameters[0]) {
		"!" -> {
			when (parameters[1].toLowerCase()) {
				"invite" -> {
					event.message.delete().queue()
					if (parameters.size == 3) {
						try {
							createInvite(event, Integer.parseInt(parameters[2]))
						} catch (e: NumberFormatException) {
							createInvite(event, DEFAULT_INVITE_TIME)
						}
					} else {
						createInvite(event, DEFAULT_INVITE_TIME)
					}
				}
				"ping" -> {
					event.message.delete().queue()
					Ping.ping(event)
				}
				"delete" -> {
					event.message.delete().queue()
					when {
						parameters.size == 2 -> delete(event, 1, 1)
						parameters.size == 3 -> delete(event, Integer.valueOf(parameters[2]), 1)
						parameters.size == 4 -> delete(event, Integer.valueOf(parameters[2]), Integer.valueOf(parameters[3]))
					}
				}
				"mute" -> {
					event.message.delete().queue()
					when (parameters[2]) {
						"true" -> temporaryMute(event, true)
						"false" -> temporaryMute(event, false)
					}
				}
			}
		}
		";" -> {
			when (parameters[1].toLowerCase()) {
				"join" -> {
					event.message.delete().queue()
					Music.join(event)
				}
				"leave" -> {
					event.message.delete().queue()
					Music.leave(event)
				}
				"play" -> {
					event.message.delete().queue()
					if (parameters.size > 2) {
						Music.play(event, parameters.copyOfRange(2, parameters.size))
					}
				}
				"pause" -> {
					event.message.delete().queue()
					Music.pause(event, true)
				}
				"unpause" -> {
					event.message.delete().queue()
					Music.pause(event, false)
				}
				"clear" -> {
					event.message.delete().queue()
					Music.clear(event)
				}
				"skip" -> {
					event.message.delete().queue()
					if (parameters.size > 2) {
						Music.skip(event, Integer.valueOf(parameters[2]))
					} else {
						Music.skip(event, 1)
					}
				}
				"shuffle" -> {
					event.message.delete().queue()
					Music.shuffle(event)
				}
				"repeat" -> {
					event.message.delete().queue()
					Music.toggleRepeatMode(event)
				}
				"list" -> {
					event.message.delete().queue()
					Music.list(event)
				}
			}
		}
	}
}