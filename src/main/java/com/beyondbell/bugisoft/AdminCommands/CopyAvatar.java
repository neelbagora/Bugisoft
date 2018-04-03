package com.beyondbell.bugisoft.AdminCommands;

import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;

public class CopyAvatar implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		ArrayList<String> parameters = InputFormatter.stringToParameters(event);
		if (parameters.size() == 2 && parameters.get(0).equals(">") && parameters.get(1).equals("copyAvatar")) {
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
