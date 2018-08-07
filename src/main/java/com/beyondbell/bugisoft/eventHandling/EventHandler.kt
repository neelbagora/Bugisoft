package com.beyondbell.bugisoft.eventHandling

import net.dv8tion.jda.core.events.Event
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.EventListener

object EventHandler : EventListener {
    override fun onEvent(event: Event?) {
        if (event != null) {
            Thread(Runnable { handle(event) }).start()
        }
    }

    private fun handle(event: Event) {
        }
    }
}