/*
package com.beyondbell.bugisoft.AdminCommands;


import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class CopyAvatar implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());
		if (parameters.length == 2 && parameters[0].equals(">") && parameters[1].equals("copyAvatar")) {
			if (!event.getMessage().getAuthor().isBotOwner()) {
				event.getChannel().sendMessage("You are not allowed to use this command!");
			} else {
				event.getApi()
						.updateAvatar(event.getMessage().getAuthor().getAvatar()) // Update the avatar
						.thenAccept(aVoid -> event.getChannel().sendMessage("Ok, I'm now using your avatar :-)")) // Send the user a message if the update was successful
						.exceptionally(throwable -> {
							// Send the user a message, if the update failed
							event.getChannel().sendMessage("Something went wrong: " + throwable.getMessage());
							return null;
						});
			}
		}
	}
}
*/