package com.beyondbell.bugisoft.Logger;

import com.beyondbell.bugisoft.Utilities.TextFormatters.LoggerFormatter;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sun.reflect.generics.scope.Scope;

import java.io.*;
import java.util.ArrayList;

public class LoggerDatabase {
	public enum LoggerScope {
		ALL, NOT_DELETED, NOT_BOT, NOT_DELETED_NOT_BOT
	}

	public static void logEvent(MessageReceivedEvent event) {
		try {
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("logs/" + event.getGuild().getId() + "/" + event.getTextChannel().getId()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Message[] getMessagesFromUser(String id, int count, Scope scope) {
		return null;
	}

	public static Message[] getMessages(MessageReceivedEvent event, int... messagesParameters) {
		int numberOfMessages;
		int startingMessage;
		if (messagesParameters.length == 1) {
			numberOfMessages = messagesParameters[0];
			startingMessage = 0;
		} else if (messagesParameters.length == 2) {
			numberOfMessages = messagesParameters[0];
			startingMessage = messagesParameters[1];
		} else {
			// TODO Catch Too Many Parameters
			return null;
		}

		String path;
		if (event.getGuild().checkVerification()) {
			path = "Logs/" + event.getGuild().getId() + "/" + event.getChannel().getId();
		} else {
			// TODO Not a Server Catch!!!
			return null; // Not Good
		}

		Object[] objects = null;
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			objects = bufferedReader.lines().toArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (objects == null) {
			return null;
			// TODO Catch
		}

		String[] logMessages = (String[]) objects;
		ArrayList<String> messageIDs = new ArrayList<>();
		for (int i = logMessages.length - 1 - startingMessage; i > logMessages.length - 1 - startingMessage - numberOfMessages; i--) {
			messageIDs.add(LoggerFormatter.getIDFromLogMessage(logMessages[i]));
		}

		Message[] messages = new Message[numberOfMessages];
		for (int i = 0; i < messages.length; i++) {
			messages[i] = event.getChannel().getMessageById(messageIDs.get(i)).complete();
		}

		return messages;
	}
}
