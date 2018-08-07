package com.beyondbell.bugisoft.old.Utilities.MessageUtilities;

import net.dv8tion.jda.core.entities.Message;

public final class MessageBroadcaster {
	private final Message message;
	private final long millis;

	public MessageBroadcaster(final Message message, final long millis) {
		this.message = message;
		this.millis = millis;

		if (millis > 0) {
			final Thread thread = new Thread(this::deleteMessage);
			thread.setName("Message Broadcaster");
			thread.setDaemon(true);
			thread.start();
		} else {
			message.delete().queue();
		}
	}

	private void deleteMessage() {
		try {
			Thread.sleep(millis);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		message.delete().queue();
	}
}
