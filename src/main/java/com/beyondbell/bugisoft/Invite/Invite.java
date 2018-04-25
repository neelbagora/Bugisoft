package com.beyondbell.bugisoft.Invite;

import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import org.javacord.api.entity.server.invite.InviteBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.ExecutionException;

public class Invite implements MessageCreateListener {
	private String id;
	private long timeout;
	private MessageCreateEvent event;

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());
		if (parameters.length >= 2 && parameters[0].equals("!") && parameters[1].equals("invite") && event.getServer().isPresent()) {
			// Deletes the Original Message
			event.getMessage().delete();

			// Starts Forming the Invite
			InviteBuilder inviteBuilder = new InviteBuilder(event.getServer().get().getChannelById(event.getChannel().getId()).get())
					.setMaxUses(1)
					.setUnique(true)
					.setAuditLogReason(event.getMessage().getAuthor().getDiscriminatedName() + " wanted an invite.");

			if(parameters[3] != null) {
				// Finishes Forming the Invite
				inviteBuilder.setMaxAgeInSeconds(Integer.valueOf(parameters[3]) * 60);

				// Sends the Message
				try {
					id = event.getChannel().sendMessage(inviteBuilder.create().toString()).get().getIdAsString();
				} catch (InterruptedException | ExecutionException ignored) {}

				// Sets Timeout
				timeout = Integer.valueOf(parameters[3]) * 60;
			} else {
				// Finishes Forming the Invite
				inviteBuilder.setMaxAgeInSeconds(60);

				// Sends the Message
				try {
					id = event.getChannel().sendMessage(inviteBuilder.create().toString()).get().getIdAsString();
				} catch (InterruptedException | ExecutionException ignored) {}

				// Sets Timeout
				timeout = 60;
			}
			Thread thread = new Thread(this::deleteMessage);
			thread.setDaemon(true);
			this.event = event;
			thread.start();
		}
	}

	private void deleteMessage() {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		event.getChannel().deleteAll(id);
	}
}
