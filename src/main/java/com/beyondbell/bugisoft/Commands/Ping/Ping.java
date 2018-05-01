package com.beyondbell.bugisoft.Commands.Ping;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Ping {
	private static volatile long startingTime = System.currentTimeMillis();
	private static volatile boolean released = true;
	private static Message message;

	public static void ping(MessageReceivedEvent event) {
		if (released) {
			released = false;
			message = event.getChannel().sendMessage("Ping Calculating...").complete();
			startingTime = System.currentTimeMillis();
		} else {
			event.getChannel().sendMessage("A Ping Check is Already in Process").queue();
		}
	}

	public static void pingReceived(MessageReceivedEvent event) {
		EmbedBuilder embedBuilder = new EmbedBuilder()
				.addField("Bot to Server Ping", String.valueOf(System.currentTimeMillis() - startingTime), true)
				.addField("Gateway Ping", String.valueOf(event.getJDA().getPing()), true)
				.addField("Server Ping", String.valueOf(System.currentTimeMillis() - startingTime - event.getJDA().getPing()), true);
		event.getChannel().sendMessage(embedBuilder.build()).queue();
		event.getChannel().deleteMessageById(message.getId()).queue();
		message = null;
		released = true;
	}
}
