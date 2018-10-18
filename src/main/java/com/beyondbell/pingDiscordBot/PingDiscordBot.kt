package com.beyondbell.pingDiscordBot

import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.core.hooks.EventListener
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.security.auth.login.LoginException

val LOGGER: Logger = LogManager.getRootLogger()

fun main(args: Array<String>) {
	val tokenProperties = Properties()
	tokenProperties.setProperty("token", "")
	try {
		val tokenFile = FileInputStream("token")
		tokenProperties.load(tokenFile)
		tokenFile.close()
		try {
			JDABuilder(tokenProperties.getProperty("token"))
					.addEventListener(EventListener {
						if (it is GuildMessageReceivedEvent && it.message.mentionedUsers.isNotEmpty() && it.message.mentionedUsers[0] == it.jda.selfUser) {
							it.message.delete().queue()
							val id = Ping.getNextId()
							if (id != -1) {
								Ping.changeListenerStatus(it.jda, true)
								Ping.times[id] = System.currentTimeMillis()
								it.channel.sendMessage("Ping Request: $id").queue()
							} else {
								LOGGER.warn("Ping Limit Reached!")
								it.channel.sendMessage("Ping Limit Reached!").complete().delete().queueAfter(5, TimeUnit.SECONDS)
							}
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