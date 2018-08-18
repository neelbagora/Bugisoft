package com.beyondbell.bugisoft.ping

import com.beyondbell.bugisoft.MAX_CONCURRENT_PING_REQUESTS
import com.beyondbell.bugisoft.utilities.ErrorMessage
import com.beyondbell.bugisoft.utilities.sendErrorMessage
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.util.concurrent.TimeUnit

object Ping : ListenerAdapter() {
	private val times = HashMap<Int, Long>()
	private var outboundRequests = 0

	fun ping(event: GuildMessageReceivedEvent) {
		val id = getNextId()
		if (id != -1) {
			changeListenerStatus(event.jda, true)
			times[id] = System.currentTimeMillis()
			event.channel.sendMessage("Ping Request: $id").queue()
		} else {
			sendErrorMessage(ErrorMessage.PingLimitReached, event.channel)
		}
	}

	override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
		if (event.author.isBot && event.message.contentRaw.startsWith("Ping Request: ")) {
			val totalPing = System.currentTimeMillis() - times[Integer.valueOf(event.message.contentRaw.removePrefix("Ping Request: "))] as Long

			event.message.editMessage(EmbedBuilder()
					.setTitle("Ping Request Receipt")
					.addField("Current Total Ping", totalPing.toString(), true)
					.addField("First Gateway Ping", event.jda.ping.toString(), true)
					.addField("Estimated Server Ping", (totalPing - event.jda.ping).toString(), true)
					.build()).queue()
			event.message.delete().queueAfter(1, TimeUnit.HOURS)

			times.remove(Integer.valueOf(event.message.contentRaw.removePrefix("Ping Request: ")))

			changeListenerStatus(event.jda, false)
		}
	}

	@Synchronized
	private fun getNextId(): Int {
		for (i in 0 until MAX_CONCURRENT_PING_REQUESTS) {
			if (!times.containsKey(i)) {
				return i
			}
		}
		return -1
	}

	@Synchronized
	private fun changeListenerStatus(jda: JDA, register: Boolean) {
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