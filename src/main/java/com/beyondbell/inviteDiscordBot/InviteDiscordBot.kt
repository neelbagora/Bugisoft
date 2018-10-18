package com.beyondbell.inviteDiscordBot

import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.EventListener
import net.dv8tion.jda.core.requests.restaction.InviteAction
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.security.auth.login.LoginException

val LOGGER: Logger = LogManager.getRootLogger()
var lastMessageId: Long? = null

fun main(args: Array<String>) {
	val tokenProperties = Properties()
	tokenProperties.setProperty("token", "")
	try {
		val tokenFile = FileInputStream("token")
		tokenProperties.load(tokenFile)
		tokenFile.close()
		try {
			JDABuilder(tokenProperties.getProperty("token"))
					.addEventListener(EventListener { event ->
						if (event is GuildMessageReceivedEvent && event.message.mentionedUsers.isNotEmpty() && event.message.mentionedUsers[0] == event.jda.selfUser) {
							event.message.delete().queue()
							val message = event.author.name + " created the invite: " +
									InviteAction(event.jda, event.channel.id)
											.setUnique(false)
											.setMaxAge(1, TimeUnit.DAYS)
											.setMaxUses(1)
											.reason(event.author.name + " wanted an invite.")
											.complete().url
							LOGGER.info(message)
							val messageId = event.channel.sendMessage(message).complete().idLong
							lastMessageId?.let { event.channel.deleteMessageById(it) }
							lastMessageId = messageId
						}
					}).build()
		} catch (e: LoginException) {
			LOGGER.fatal("Please Place the Correct Token Inside of the Bot Properties File! Attempted token was [${tokenProperties.getProperty("token")}].")
		}
	} catch (e: IOException) {
		LOGGER.fatal("Cannot Find Bot Token File! Defaulting One.")
		val tokenFile = FileOutputStream("token")
		tokenProperties.store(tokenFile, "Token File")
		tokenFile.close()
	}
}