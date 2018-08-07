package com.beyondbell.bugisoft.utilities

import net.dv8tion.jda.core.entities.Message

fun deleteMessage(message: Message?) {
    message?.delete()?.queue()
}