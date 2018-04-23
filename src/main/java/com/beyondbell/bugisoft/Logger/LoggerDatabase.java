package com.beyondbell.bugisoft.Logger;

import com.beyondbell.bugisoft.Utilities.TextFormatters.LoggerFormatter;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LoggerDatabase {
	public static Message[] getMessages(MessageCreateEvent event, int... messagesParameters) {
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
		if (event.getServer().isPresent()) {
			path = "Logs/" + event.getServer().get().getIdAsString() + "/" + event.getChannel().getIdAsString();
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
			try {
				messages[i] = event.getChannel().getMessageById(messageIDs.get(i)).get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		return messages;
	}
}
