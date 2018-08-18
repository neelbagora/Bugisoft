package com.beyondbell.bugisoft.utilities

import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import java.util.ArrayList

fun getMessages(channel: MessageChannel, messageCount: Int, shouldCache: Boolean): List<Message> {
	val messages = ArrayList<Message>(messageCount)
	var messagesLeft = messageCount
	for (message in channel.iterableHistory.cache(shouldCache)) {
		messages.add(message)
		if (--messagesLeft <= 0) {
			break
		}
	}
	return messages
}

fun getMessagesByUser(channel: MessageChannel, userId: Long, messageCount: Int, shouldCache: Boolean): List<Message> {
	val messages = ArrayList<Message>(messageCount)
	var messagesLeft = messageCount
	for (message in channel.iterableHistory.cache(shouldCache)) {
		if (message.author.idLong == userId) {
			messages.add(message)
			if (--messagesLeft <= 0) {
				break
			}
		}
	}
	return messages
}

fun getMessagesInRange(channel: MessageChannel, startingIndex: Int, endingIndex: Int, shouldCache: Boolean): List<Message> {
	val messages = ArrayList<Message>(endingIndex - startingIndex + 1)
	var messageIndex = 0
	for (message in channel.iterableHistory.cache(shouldCache)) {
		if (messageIndex++ < startingIndex) {
			continue
		} else if (messageIndex > endingIndex) {
			break
		}
		messages.add(message)
	}
	return messages
}