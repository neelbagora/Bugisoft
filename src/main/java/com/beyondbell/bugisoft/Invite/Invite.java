package com.beyondbell.bugisoft.Invite;

import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.server.invite.InviteBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Invite implements MessageCreateListener {

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());

		if (parameters.length == 2 && parameters[0].equals("!") && parameters[1].equals("invite")) {
			InviteBuilder inviteBuilder = new InviteBuilder((ServerChannel) event.getChannel());
			inviteBuilder.create();
			event.getChannel().sendMessage();
		}
	}
}
