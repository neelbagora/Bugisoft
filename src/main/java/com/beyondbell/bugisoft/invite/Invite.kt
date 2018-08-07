package com.beyondbell.bugisoft.invite

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.requests.restaction.InviteAction
import java.util.concurrent.TimeUnit

object Invite {
    @JvmStatic
    fun createInvite(event: GuildMessageReceivedEvent, timeout: Int) {
        // Sends invite
        val message = event.channel.sendMessage(
                event.author.name + " created the invite: "
                        + InviteAction(event.jda, event.channel.id)
                        .setUnique(true)
                        .setMaxAge(timeout.toLong(), TimeUnit.MINUTES)
                        .setMaxUses(1)
                        .reason(event.author.name + " wanted an invite.")
                        .complete().url)
                .complete()

        // Deletes Message When it Expires
        message.delete().queueAfter(timeout.toLong(), TimeUnit.MINUTES)
    }
}