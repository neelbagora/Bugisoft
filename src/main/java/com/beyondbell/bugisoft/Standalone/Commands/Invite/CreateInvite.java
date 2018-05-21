package com.beyondbell.bugisoft.Standalone.Commands.Invite;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.requests.restaction.InviteAction;

import java.util.concurrent.TimeUnit;

public final class CreateInvite {
	private final long timeout;
	private final Message message;

	public CreateInvite(final GuildMessageReceivedEvent event, final String timeInput) {
		// Finds the Invite Timeout
		this.timeout = Integer.valueOf(timeInput);

		// Sends Invite
		this.message = event.getChannel().sendMessage(event.getAuthor().getName()
					+ " created the invite: "
					+ new InviteAction(event.getJDA(), event.getChannel().getId())
						.setMaxUses(1)
						.setUnique(true)
						.setMaxAge(timeout, TimeUnit.MINUTES)
						.reason(event.getAuthor().getName() + " wanted an invite.")
						.complete().getURL())
				.complete();

		// Starts Message Deleter
		final Thread thread = new Thread(this::deleteMessage);
		thread.setName("Invite Deleter");
		thread.setDaemon(true);
		thread.start();
	}

	private void deleteMessage() {
		try {
			Thread.sleep(timeout * 60 * 1000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		message.delete().queue();
	}
}
