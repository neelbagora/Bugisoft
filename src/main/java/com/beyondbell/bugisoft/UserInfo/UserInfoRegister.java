package com.beyondbell.bugisoft.UserInfo;

import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;

public class UserInfoRegister implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		ArrayList<String> parameters = InputFormatter.stringToParameters(event);
		if (parameters.size() == 2 && parameters.get(0).equals(";") && parameters.get(1).equals("register")) {
			event.getChannel().sendMessage(new EmbedBuilder()
					.setTitle("Ping Query")
					.addField("Ping", "IDFK", true)
					.setAuthor(event.getMessage().getAuthor()));
		}
	}
}
