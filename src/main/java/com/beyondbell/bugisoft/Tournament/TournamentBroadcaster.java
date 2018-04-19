package com.beyondbell.bugisoft.Tournament;

import org.javacord.api.entity.message.Message;

import java.util.ArrayList;

public class TournamentBroadcaster {

	static ArrayList<Message> messages = new ArrayList<>();

	enum MessageType {
		GENERATING_BRACKET
	}

	static int openBroadcast(MessageType messageType) {




		switch (messageType) {
			case GENERATING_BRACKET:
				messages.add(event.getChannel().sendMessage("Ping Calculating..."));
				return messages.size() - 1;
				break;
		}

		return -1;
	}

	static void endBroadcast(int ) {
		if () {
			ArrayList
		}

		if (isEmpty()) {
			messages.clear();
		}
	}

	private static boolean isEmpty() {

	}
}
