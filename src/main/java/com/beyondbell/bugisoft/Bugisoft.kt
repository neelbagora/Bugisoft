package com.beyondbell.bugisoft

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.*
import kotlin.collections.HashMap

object Bot {
    val SETTINGS = Properties()
    val LOGGER: Logger = LogManager.getRootLogger()

    @JvmStatic
    fun main(args: Array<String>) {
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
        // Loads Token
        // Loads Bot

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
    enum class Token {
        Help, Version, NoUpdate,
        TokenFile, SettingsFileLocation
    }
}