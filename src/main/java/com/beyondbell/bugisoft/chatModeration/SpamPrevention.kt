package com.beyondbell.bugisoft.chatModeration

import com.beyondbell.bugisoft.utilities.getMessages
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

fun preventSpam(event: GuildMessageReceivedEvent) {
	if (event.message.contentRaw == getMessages(event.channel, 2, false)[1].contentRaw) {
		event.message.delete().reason("Spam Prevention").queue()
	}
}