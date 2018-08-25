package com.beyondbell.bugisoft

import com.beyondbell.bugisoft.eventHandling.EventHandler
import com.sedmelluq.discord.lavaplayer.jdaudp.NativeAudioSendFactory
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.FileInputStream
import java.io.IOException
import java.util.Properties
import javax.security.auth.login.LoginException

fun main(args: Array<String>) {
	val logger: Logger = LogManager.getRootLogger()

	// Reads Tokens
	val tokenValues = try {
		readTokens(args)
	} catch (e: IllegalArgumentException) {
		// Displays Help if Tokens are Wrong
		displayHelp()
		return
	}

	// Checks if Help was Requested
	if (tokenValues.containsKey(Token.Help)) {
		displayHelp()
		return
	}

	// Checks if Version was Requested
	if (tokenValues.containsKey(Token.Version)) {
		displayVersion()
		return
	}

	// Checks for Update
	if (!tokenValues.containsKey(Token.NoUpdate)) {
		if (hasUpdate()) {
			update()
			return
		}
	}

	// Loads Settings
	loadSettings(tokenValues.getOrDefault(Token.SettingsFileLocation, DEFAULT_SETTINGS_FILE_NAME))

	// Loads Token
	val botProperties = try {
		readToken(tokenValues.getOrDefault(Token.TokenFile, DEFAULT_TOKEN_FILE_NAME))
	} catch (e: IOException) {
		logger.fatal("Cannot Find Token File! Creating Empty Token File. Please Populate It.")
		createEmptyTokenFile(tokenValues.getOrDefault(Token.TokenFile, DEFAULT_TOKEN_FILE_NAME))
		return
	}

	// Loads Bot
	try {
		JDABuilder(AccountType.BOT)
				.setToken(botProperties.getProperty("token"))
				.setAutoReconnect(true)
				.addEventListener(EventHandler)
				.setAudioEnabled(true)
				.setAudioSendFactory(NativeAudioSendFactory())
				.setCompressionEnabled(false)
				.build()
	} catch (e: LoginException) {
		logger.fatal("Please Place the Correct Token Inside of the Bot Properties File")
	}
}

private fun readTokens(args: Array<String>): HashMap<Token, String> {
	val tokens = HashMap<Token, String>()

	//throw IllegalArgumentException()

	return tokens
}

private fun displayHelp() {
	TODO("Display Arguments Help")
}

private fun displayVersion() {
	TODO("Display the Bot's Version")
}

private fun hasUpdate(): Boolean {
	return false
}

private fun update() {
	TODO("Update the Bot")
}

private fun loadSettings(file: String) {
	try {
		val botSettingsFile = FileInputStream(file)
		SETTINGS.load(botSettingsFile)
		botSettingsFile.close()
	} catch (e: IOException) {
		LOGGER.warn("Cannot Find Settings File! Creating a Blank One!")
		createNewSettingsFile(file)
		loadSettings(file)
	}
}

private fun createNewSettingsFile(file: String) {
	TODO("Create a New Settings File")
}

private fun readToken(tokenFile: String): Properties {
	val botProperties = Properties()
	try {
		val botPropertiesFile = FileInputStream(tokenFile)
		botProperties.load(botPropertiesFile)
		botPropertiesFile.close()
	} catch (e: IOException) {
		throw e
	}
	return botProperties
}

private fun createEmptyTokenFile(file: String) {
	TODO("Create an Empty Token File")
}

enum class Token {
	Help, Version, NoUpdate,
	TokenFile, SettingsFileLocation
}