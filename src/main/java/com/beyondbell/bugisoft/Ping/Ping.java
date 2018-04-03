package com.beyondbell.bugisoft.Ping;

import com.beyondbell.bugisoft.TextFormatters.InputFormatter;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;

public class Ping implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		ArrayList<String> parameters = InputFormatter.stringToParameters(event);
		if (parameters.size() == 2 && parameters.get(0).equals("!") && parameters.get(1).equals("ping")) {
			event.getChannel().sendMessage("Ping Calculating...");
			PingCoordinator.startingTime = System.currentTimeMillis();
		}
	}
}
