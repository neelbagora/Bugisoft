package com.beyondbell.bugisoft.eventHandling

import com.beyondbell.bugisoft.utilities.getParameters
import net.dv8tion.jda.core.entities.Message
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
    if (event.message.contentRaw[0] != '!') {
        return
    }

    val parameters = getParameters(event.message.contentRaw)
}
