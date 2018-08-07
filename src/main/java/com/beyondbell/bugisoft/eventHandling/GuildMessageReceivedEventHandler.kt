package com.beyondbell.bugisoft.eventHandling

import com.beyondbell.bugisoft.DEFAULT_INVITE_TIME
import com.beyondbell.bugisoft.invite.Invite
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

    when (parameters[0]) {
        "!" -> when (parameters[1].toLowerCase()) {
            "invite" -> {
                if (parameters.size == 3) {
                    try {
                        Invite.createInvite(event, Integer.parseInt(parameters[2]))
                    } catch (e: NumberFormatException) {
                        Invite.createInvite(event, DEFAULT_INVITE_TIME)
                    }
                } else {
                    Invite.createInvite(event, DEFAULT_INVITE_TIME)
                }
            }
        }
    }
}

}
