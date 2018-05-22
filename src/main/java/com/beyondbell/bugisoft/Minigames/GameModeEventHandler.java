package com.beyondbell.bugisoft.Minigames;

import com.beyondbell.bugisoft.Minigames.RPS.RockPaperScissors;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;

public class GameModeEventHandler {
	static ArrayList<String> gameModeOn = new ArrayList<String>();


	public GameModeEventHandler(GuildMessageReceivedEvent event, String id, boolean status) {
		if(gameModeOn.contains(id) && !status) {
			gameModeOn.remove(gameModeOn.indexOf(id));
			event.getChannel().sendMessage(event.getAuthor().getName() + " is no longer in game").queue();
		} else if(status && !gameModeOn.contains(id)) {
			gameModeOn.add(id);
			event.getChannel().sendMessage(event.getAuthor().getName() + " is playing Bugisoft in rock paper scissors, all messages will be taken in the mini games").queue();
		} else if(!status && !gameModeOn.contains(id)){
			event.getChannel().sendMessage("User is not in game").queue();
		} else if(status && gameModeOn.contains(id)) {
			event.getChannel().sendMessage("User is already in a game!").queue();
		}
	}

	public GameModeEventHandler(GuildMessageReceivedEvent event) {
		if(event.getMessage().getContentRaw().toLowerCase().equals("$quit")) {
			new RockPaperScissors();
		} else {
			if(gameModeOn.contains(event.getAuthor().getId())) {
				event.getChannel().sendMessage(event.getAuthor().getName() + " has entered " + event.getMessage().getContentRaw().substring(1)).queue();
				new RockPaperScissors(event);
			}
		}

	}
}
