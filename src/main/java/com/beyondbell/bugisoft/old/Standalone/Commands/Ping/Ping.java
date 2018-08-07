package com.beyondbell.bugisoft.old.Standalone.Commands.Ping;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public final class Ping {
	private static volatile long startingTime = System.currentTimeMillis();
	private static volatile boolean released = true;
	private static Message message;

	public static void ping(final GuildMessageReceivedEvent event) {
		if (released) {
			released = false;
			message = event.getChannel().sendMessage("Ping Calculating...").complete();
			startingTime = System.currentTimeMillis();
		} else {
			event.getChannel().sendMessage("A Ping Check is Already in Process").queue();
		}
	}

	public static void pingReceived(final GuildMessageReceivedEvent event) {
		final MessageEmbed messageEmbed = new EmbedBuilder()
				.addField("Bot to Server Ping", String.valueOf(System.currentTimeMillis() - startingTime), true)
				.addField("Gateway Ping", String.valueOf(event.getJDA().getPing()), true)
				.addField("Server Ping", String.valueOf(System.currentTimeMillis() - startingTime - event.getJDA().getPing()), true)
				.build();
		event.getChannel().sendMessage(messageEmbed).queue();
		event.getChannel().deleteMessageById(message.getId()).queue();

		message = null;
		released = true;
	}
}
