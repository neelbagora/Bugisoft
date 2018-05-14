package com.beyondbell.bugisoft.Logger.Commands;

import com.beyondbell.bugisoft.Logger.LoggerDatabase;
import com.beyondbell.bugisoft.Utilities.TextFormatters.IdFormatter;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.util.ArrayList;

public class Report {
	public Report(MessageReceivedEvent event, String id, int numberReports) {
		id = IdFormatter.fixId(id);
		//identifies Member
		String display = event.getGuild().getMemberById(id).getNickname();

		//planning on using command to get amount user messages and sending them to chat

		//arranges messages into messages Array
		int count = 100;
		Message[] messages = LoggerDatabase.getMessagesFromUser(id,numberReports, LoggerDatabase.LoggerScope.ALL);
		if (messages == null) {
			//TODO Catch
			return;
		}

		ArrayList<Message> storage = new ArrayList<>();

		for (Message message : messages) {
			if (numberReports > storage.size()) {
			    if(message.getAuthor().getId() == id) {
                    storage.add(message);
                }
			} else {
				break;
			}
		}

		EmbedBuilder embed = new EmbedBuilder();

		embed.setTitle(storage.size() + " messages from: " + display);

		//for loop to add fields to embed
		for (int i = storage.size() - 1; i > -1; i--) {
			embed.addField("", storage.get(i).getCreationTime().toString() + ": " + storage.get(i).getContentDisplay(), true);

		}
		embed.setAuthor(event.getMessage().getAuthor().getName());
		event.getChannel().sendMessage(embed.build()).queue();

	}
}
