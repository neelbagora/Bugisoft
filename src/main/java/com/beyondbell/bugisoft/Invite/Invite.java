package com.beyondbell.bugisoft.Invite;

import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.server.invite.InviteBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.ExecutionException;

public class Invite implements MessageCreateListener {



	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		Thread thread = new Thread(this::deleteMessage);

		thread.setDaemon(true);
		final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());

		if (parameters.length == 2 && parameters[0].equals("!") && parameters[1].equals("invite")) {
			InviteBuilder inviteBuilder = new InviteBuilder((ServerChannel) event.getChannel());
			long id = event.getMessageId();
			try {
				event.getChannel().sendMessage(inviteBuilder.create().get().getUrl().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			if(parameters[3] != null) {
				try {
					id = event.getChannel().sendMessage(inviteBuilder.setMaxAgeInSeconds(Integer.parseInt(parameters[3]) * 60)
							.create().toString()).get().getId();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}


			} else {
				try {
					id = event.getChannel().sendMessage(inviteBuilder.setMaxAgeInSeconds(60)
							.create().toString()).get().getId();
				} catch(InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}

				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			event.getChannel().deleteAll(id);
		}
	}

	public void deleteMessage() {

		try {
			thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
