package com.beyondbell.bugisoft

import com.beyondbell.bugisoft.eventHandling.EventHandler
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.EnumMap
import java.util.Properties
import javax.security.auth.login.LoginException

val LOGGER: Logger = LogManager.getRootLogger()
val SETTINGS = Properties()

fun main(vararg args: String) {
	val bugisoft = Bugisoft(args)
	SETTINGS.putAll(bugisoft.loadSettings())
	bugisoft.start()
}

private class Bugisoft(args: Array<out String>) {
	private val arguments = try {
		getTokensFromArgs(args)
	} catch (e: IllegalArgumentException) {
		displayHelp(true)
		EnumMap<Token, String>(Token::class.java)
	}

	private var canRun = true

	init {
		if (arguments.contains(Token.Help)) {
			displayHelp(false)
		}
	}

	fun start() {
		// Loads Token
		val botProperties = try {
			readToken(tokenValues.getOrDefault(Token.TokenFile, DEFAULT_TOKEN_FILE_NAME))
		} catch (e: IOException) {
			LOGGER.fatal("Cannot Find Token File! Creating Empty Token File. Please Populate It.")
			createEmptyTokenFile(tokenValues.getOrDefault(Token.TokenFile, DEFAULT_TOKEN_FILE_NAME))
			return
		}


		if (canRun) {
			try {
				JDABuilder()
						.addEventListener(EventHandler)
						.setAudioEnabled(true)
						.setBulkDeleteSplittingEnabled(false)
						.setAutoReconnect(true)
						.setMaxReconnectDelay(32)
						.setRequestTimeoutRetry(true)
						.setCompressionEnabled(false)
						.setContextEnabled(false)
						.setCorePoolSize(1)
						.setEnableShutdownHook(true)
						.setGame(Game.playing("as a Sleeping Guard!"))
						.setStatus(OnlineStatus.IDLE)
						.build()
			} catch (e: LoginException) {
				LOGGER.fatal("Please Place the Correct Token Inside of the Bot Properties File")
			}
		}
	}

	fun loadSettings() : Properties {
		val settings = Properties()
		if () {

		}
	}

	private fun getTokensFromArgs(args: Array<out String>): EnumMap<Token, String> {
		val tokens = EnumMap<Token, String>(Token::class.java)
		for (i in 0 until args.size) {
			if (args[i].startsWith('-')) {
				for (token in Token.values()) {
					for (tokenRepresentation in token.tokenRepresentations) {
						if (args[i] == tokenRepresentation) {
							tokens.put(token, )
							break
						}
					}
				}
				throw IllegalArgumentException
			}
		}
		for (arg in args) {
		}
		return tokens
	}

	private fun readToken(tokenFile: String): Properties {
		val botProperties = Properties()
		try {
			val botPropertiesFile = FileInputStream(tokenFile)
			botProperties.load(botPropertiesFile)
			botPropertiesFile.close()
		} catch (e: IOException) {
			createEmptyTokenFile(tokenFile)
		}
		return botProperties
	}

	internal fun loadSettingsFile(file: String) {
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

	private fun createEmptyTokenFile(file: String) {
		val botProperties = Properties()
		botProperties.put("token", "")
		val botTokenFile = FileOutputStream(file)
		botTokenFile.write()
	}

	private fun displayHelp(forced: Boolean) {
		canRun = false

		val helpMessageBuilder = StringBuilder()
		if (forced) {
			LOGGER.info(helpMessageBuilder.toString())
		} else {
			LOGGER.fatal(helpMessageBuilder.toString())
		}
	}

	private enum class Token(vararg val tokenRepresentations: String) {
		Help("-help", "-h"),
		TokenFileLocation("-t", "-token"), SettingsFileLocation("-s", "-settings")
	}
}