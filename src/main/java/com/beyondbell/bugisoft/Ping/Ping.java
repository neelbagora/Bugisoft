package com.beyondbell.bugisoft.Ping;

import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Ping implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());
		if (parameters.length == 2 && parameters[0].equals("!") && parameters[1].equals("ping")) {
			event.getChannel().sendMessage("Ping Calculating...");
			PingCoordinator.startingTime = System.currentTimeMillis();
		}
	}
}
