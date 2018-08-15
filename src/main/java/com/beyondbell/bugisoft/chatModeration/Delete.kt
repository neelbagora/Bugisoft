package com.beyondbell.bugisoft.chatModeration

import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent

fun delete(event: GuildMessageReceivedEvent, startingIndex: Long, amount: Int) {
    // TODO Find Message with Id Starting Index
    val messages = ArrayList<Message>()
    // TODO Buffer amount Messages After Starting Index

    for (message in messages) {
        message.delete().queue()
    }

    event.channel
}