package com.beyondbell.bugisoft.chatModeration

import com.beyondbell.bugisoft.OWNER_ID
import com.beyondbell.bugisoft.eventHandling.EventHandler
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

fun temporaryMute(event: GuildMessageReceivedEvent, shouldEnable: Boolean) {
	if (event.author.idLong == OWNER_ID) {
		if (shouldEnable) {
			event.jda.removeEventListener(EventHandler)
		} else {
			event.jda.addEventListener(EventHandler)
		}
	}
}