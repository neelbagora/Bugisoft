package com.beyondbell.bugisoft.chatModeration

import com.beyondbell.bugisoft.OWNER_ID
import com.beyondbell.bugisoft.utilities.getMessagesInRange
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

fun delete(event: GuildMessageReceivedEvent, startingIndex: Int, amount: Int) {
	if (event.author.idLong == OWNER_ID) {
		val messages = getMessagesInRange(event.channel, startingIndex, startingIndex + amount, false)
		if (messages.isNotEmpty()) {
			if (messages.size > 1) {
				event.channel.deleteMessages(messages).queue()
			} else {
				messages[0].delete().reason("Delete Command").queue()
			}
		}
	}
}