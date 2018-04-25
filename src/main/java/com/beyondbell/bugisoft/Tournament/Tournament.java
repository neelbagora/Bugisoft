package com.beyondbell.bugisoft.Tournament;

import com.beyondbell.bugisoft.Utilities.TextFormatters.InputFormatter;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.Event;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.ArrayList;

abstract class Tournament {
	private ArrayList<Player> players;
	private ArrayList<Match> matches;

	public void OnMessageCreate(MessageCreateEvent event) {
		final String[] parameters = InputFormatter.stringToParameters(event.getMessage().getReadableContent());
		if(parameters[0].equals("!") && parameters[1].equals("addplayer")) {
			if(event.getServer().get().getMembersByName(parameters[3]) != null) {
				players.add(new Player(event.getServer().get().getDisplayName
						((event.getServer().get()
								.getDisplayName((User) event.getServer().get().getMembersByName(parameters[3])));
			} else {
				event.getChannel().sendMessage("Invalid name, check for spelling");
			}
		}
		if(parameters[0].equals("!") && parameters[1].equals("setMatchSize") {
			players.size() / 
		}
	}

	public void generateBracketLayer() {
		//TODO
	}

}
