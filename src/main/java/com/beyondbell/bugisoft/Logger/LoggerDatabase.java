package com.beyondbell.bugisoft.Logger;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.io.*;
import java.util.ArrayList;

public class LoggerDatabase {
	public static void logEvent(GuildMessageReceivedEvent event) {
		try {
			new FileWriter("logs/" + event.getGuild().getId() + "/" + event.getChannel().getId(), true)
					.append("\n")
					.append(LoggerFormatter.getCorrespondingLogMessage(event.getMessageId(), event.getAuthor().getId(), event.getMessage().getContentRaw()))
					.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Message[] getLastMessagesFromUser(final TextChannel textChannel, final String id, final int count) {
		final Message[] messages = new Message[count];

		String path;
		if (textChannel.getGuild().checkVerification()) {
			path = "Logs/" + textChannel.getGuild().getId() + "/" + textChannel.getId();
		} else {
			// TODO Not a Server Catch!!!
			return null; // Not Good
		}

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			for (int i = bufferedReader.lines().toArray().length - 1; i >= 0; i--) {
				if (LoggerFormatter.getIDFromLogMessage(bufferedReader.readLine()).equalsIgnoreCase(id)) {
					messages[]
					if (messages[count - 1] != null) {
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		*/
		return null;
	}

	public static Message[] getMessages(final MessageReceivedEvent event, final int... messagesParameters) {
		final int numberOfMessages;
		final int startingMessage;
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
