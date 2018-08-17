package com.beyondbell.bugisoft.chatModeration

import com.beyondbell.bugisoft.eventHandling.EventHandler
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

fun temporaryMute(event: GuildMessageReceivedEvent, shouldEnable: Boolean) {
    if (shouldEnable) {
        event.jda.removeEventListener(EventHandler)
    } else {
        event.jda.addEventListener(EventHandler)
    }
}