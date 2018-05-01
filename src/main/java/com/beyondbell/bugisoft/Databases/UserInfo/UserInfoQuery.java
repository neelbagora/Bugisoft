package com.beyondbell.bugisoft.Databases.UserInfo;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class UserInfoQuery {
	public UserInfoQuery(MessageReceivedEvent event) {
		UserInfo user = UserInfoDatabase.findUser(event.getMessage().getAuthor());
		MessageEmbed embed = new EmbedBuilder()
				.setTitle("User Info")
				.addField("Full Name", user.getUsername(), true)
				.addField("ID", user.getID(), true)
				.addField("Cash", user.getCash(), true)
				.addField("Level", user.getLevel(), true)
				.addField("Wins", user.getWins(), true)
				.addField("Losses", user.getLosses(), true)
				.setAuthor(event.getMessage().getAuthor().getName())
				.build();
		event.getChannel().sendMessage(embed).queue();
	}
}
