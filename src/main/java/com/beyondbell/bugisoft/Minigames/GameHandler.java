package com.beyondbell.bugisoft.Minigames;

import com.beyondbell.bugisoft.Minigames.RPS.RPSGame;
import com.beyondbell.bugisoft.Minigames.RPS.UserNotInGameException;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class GameHandler {
	private static Map<String, Boolean> gameMode = new HashMap<>();

	//gamemode handler
	public GameHandler(String id, boolean x) {
		if(x) {
			gameMode.put(id, x);
		} else {
			gameMode.remove(id);
		}
	}

	//quit and rock paper scissors sign pusher
	public GameHandler(String id, GuildMessageReceivedEvent event) throws UserNotInGameException {
		if(event.getMessage().getContentRaw().substring(1).equals("quit")) {
			new RPSGame(event.getAuthor().getId());
			if(!gameMode.containsKey(event.getAuthor().getId())) {
				throw new UserNotInGameException();
			} else {
				gameMode.remove(id);
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
