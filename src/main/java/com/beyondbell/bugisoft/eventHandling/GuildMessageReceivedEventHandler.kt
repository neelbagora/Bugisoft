package com.beyondbell.bugisoft.eventHandling

import com.beyondbell.bugisoft.DEFAULT_INVITE_TIME
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
				"clear" -> {
					event.message.delete().queue()
					Music.clear(event.guild.idLong)
				}
				"skip" -> {
					event.message.delete().queue()
					if (parameters.size > 2) {
						Music.skip(event.guild.idLong, Integer.valueOf(parameters[2]))
					} else {
						Music.skip(event.guild.idLong, 1)
					}
				}
				"shuffle" -> {
					event.message.delete().queue()
					Music.shuffle(event.guild.idLong)
				}
				"repeat" -> {
					event.message.delete().queue()
					Music.toggleRepeatMode(event.guild.idLong)
				}
			}
		}
	}
}