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
            return
        }

        // Checks if Help was Requested
        // Checks if Version was Requested
        // Checks for Update
        // Loads Settings
        // Loads Token
        // Loads Bot

    private fun readTokens(args: Array<String>): HashMap<Token, String> {
        val tokens = HashMap<Token, String>()

        //throw IllegalArgumentException()

        return tokens
    }
    enum class Token {
        Help, Version, NoUpdate,
        TokenFile, SettingsFileLocation
    }
}