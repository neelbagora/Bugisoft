package com.beyondbell.bugisoft.UserInfo;

import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class UserInfoRegister implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());
		if (parameters.length == 2 && parameters[0].equals(";") && parameters[1].equals("register")) {
			UserInfoDatabase.registerUser(event.getMessage().getAuthor());
			event.getChannel().sendMessage(new EmbedBuilder()
					.setTitle("Register Receipt")
					.addField("Has Registered", "Yes", true)
					.setAuthor(event.getMessage().getAuthor()));
		}
	}
}
