package com.beyondbell.bugisoft.Minigames;

import com.beyondbell.bugisoft.Minigames.RPS.RPSGame;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class GameHandler {
	private static Map<String, Boolean> gameMode = new HashMap<>();
	public GameHandler(String id, boolean x) {
		if(x) {
			gameMode.put(id, x);
		} else {
			gameMode.remove(id);
		}
	}

	public GameHandler(String id, GuildMessageReceivedEvent event) {
		if(event.getMessage().getContentRaw().substring(1).equals("quit")) {
			new RPSGame(event.getAuthor().getId());
			if(gameMode.containsKey(event.getAuthor().getId())) {
				event.getChannel().sendMessage("Game cancelled").queue();
			} else {
				event.getChannel().sendMessage(event.getAuthor().getName() + " is not in a game!").queue();
			}
		} else {
			if(gameMode.containsKey(id)) {
				if(gameMode.get(id).booleanValue()) {
					new RPSGame(event);
				}
			}
		}

	}
}
