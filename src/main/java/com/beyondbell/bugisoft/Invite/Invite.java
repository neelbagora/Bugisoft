package com.beyondbell.bugisoft.Invite;

import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.server.invite.InviteBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;

public class Invite implements MessageCreateListener {

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		ArrayList<String> parameters = InputFormatter.stringToParameters(event);

		if (parameters.size() == 2 && parameters.get(0).equals("!") && parameters.get(1).equals("invite")) {
			InviteBuilder inviteBuilder = new InviteBuilder((ServerChannel) event.getChannel());
			inviteBuilder.create();
			event.getChannel().sendMessage();
		}
	}
}
