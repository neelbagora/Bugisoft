package com.beyondbell.bugisoft.Tournament;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class TournamentTest implements MessageCreateListener {
	@Override
	public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
		if (messageCreateEvent.getMessage().getContent().equals("test")) {
			TournamentDatabase.createTournament(Tournament.TournamentType.SingleElimination, "test");
			TournamentDatabase.getTournament(0).printMatchDetails(messageCreateEvent, 0);
		}
	}
}
