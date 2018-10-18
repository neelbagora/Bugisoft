package com.beyondbell.pingDiscordBot

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.util.concurrent.TimeUnit

object Ping : ListenerAdapter() {
	internal val times: Array<Long?> = arrayOf(null, null, null, null, null)
	private var outboundRequests = 0

	override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
		if (event.author.isBot && event.message.contentRaw.startsWith("Ping Request: ")) {
			val totalPing = System.currentTimeMillis() - times[Integer.valueOf(event.message.contentRaw.removePrefix("Ping Request: "))] as Long

			event.message.editMessage(EmbedBuilder()
					.setTitle("Ping Request Receipt")
					.addField("Current Total Ping", totalPing.toString(), true)
					.addField("First Gateway Ping", event.jda.ping.toString(), true)
					.addField("Estimated Server Ping", (totalPing - event.jda.ping).toString(), true)
					.build()).complete()
			event.message.delete().queueAfter(1, TimeUnit.MINUTES)

			times[event.message.contentRaw.removePrefix("Ping Request: ").toInt()] = null

			changeListenerStatus(event.jda, false)
		}
	}

	internal fun getNextId(): Int {
		for (i in 0 until times.size) {
			if (times[i] == null) {
				return i
			}
		}
		return -1
	}

	internal fun changeListenerStatus(jda: JDA, register: Boolean) {
		if (register) {
			if (outboundRequests++ == 0) {
				jda.addEventListener(this)
			}
		} else {
			if (--outboundRequests == 0) {
				jda.removeEventListener(this)
			}
		}
	}
}