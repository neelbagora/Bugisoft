package com.beyondbell.bugisoft.UserInfo;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class UserInfoQuery {
	public UserInfoQuery(GuildMessageReceivedEvent event) {
		UserInfo user = UserInfoDatabase.findUser(event.getMessage().getAuthor());
		EmbedBuilder embed = new EmbedBuilder()
				.setTitle("User Info").setAuthor(event.getMessage().getAuthor().getName());

		final String[] properties = user.getUserProperties().keySet().toArray(new String[0]);

		for (String property : properties) {
			embed.addField(property, user.getUserProperties().getProperty(property), true);
		}

		event.getChannel().sendMessage(embed.build()).queue();
	}

	public static boolean getGameModeEnabled(long idLong) {
		return false;
	}
}
