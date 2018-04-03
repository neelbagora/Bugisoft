package com.beyondbell.bugisoft.Ping;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class PingReceiver implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessage().getAuthor().isYourself() && event.getMessage().getContent().equals("Ping Calculating...")) {
			event.getMessage().delete();
			event.getChannel().sendMessage(new EmbedBuilder()
					.setTitle("Ping Query")
					.addField("Bot Ping", String.valueOf(System.currentTimeMillis() - PingCoordinator.startingTime) + " ms", true)
					.setAuthor(event.getMessage().getAuthor()));
		}
	}
}
