package com.beyondbell.bugisoft.Standalone.Commands.Invite;

import net.dv8tion.jda.core.entities.Invite;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.requests.restaction.InviteAction;

import java.util.concurrent.TimeUnit;

public class CreateInvite {
	private long timeout;
	private final Message message;

	public CreateInvite(MessageReceivedEvent event, String timeInput) {
		// Finds the Invite Timeout
		try {
			this.timeout = Integer.valueOf(timeInput);
		} catch (Throwable throwable) {
			this.timeout = 1;
		}

		// Forms the CreateInvite
		Invite invite = new InviteAction(event.getJDA(), event.getChannel().getId())
				.setMaxUses(1)
				.setUnique(true)
				.setMaxAge(timeout, TimeUnit.MINUTES)
				.reason(event.getAuthor().getName() + " wanted an invite.")
				.complete();

		// Sends Invite
		message = event.getChannel().sendMessage(event.getAuthor().getName() + " created the invite: " + invite.getURL()).complete();

		// Starts Message Deleter
		Thread thread = new Thread(this::deleteMessage);
		thread.setDaemon(true);
		thread.start();
	}

	private void deleteMessage() {
		try {
			Thread.sleep(timeout * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		message.delete().queue();
	}
}
