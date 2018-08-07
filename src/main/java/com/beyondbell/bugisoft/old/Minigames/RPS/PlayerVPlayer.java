package com.beyondbell.bugisoft.old.Minigames.RPS;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerVPlayer {
	private static ArrayList<String> unMatched = new ArrayList<>();
	private static Map<String, Integer> playerOne = new HashMap<>();
	private static Map<String, Integer> playerTwo = new HashMap<>();

	public PlayerVPlayer(String user, GuildMessageReceivedEvent event) {
		//new user joining game, checks if already in game
		if(playerOne.containsKey(user) || playerTwo.containsKey(user)) {
			event.getChannel().sendMessage(event.getGuild().getMemberById(user).getUser().getName() + " is in game already.").queue();
		} else { //user is not in game and is added to unMatched array
			unMatched.add(user);
			if(unMatched.size() % 2 == 0) {
				playerOne.put(unMatched.get(0), 0);
				event.getChannel().sendMessage(event.getGuild().getMemberById(unMatched.get(0)).getUser().getName() + " versus " + event.getGuild().getMemberById(unMatched.get(1)).getUser().getName()).queue();
				event.getChannel().sendMessage("PM the bot to input using $rock, $paper, or $scissors").queue();
				unMatched.remove(0);
				playerTwo.put(unMatched.get(0), 0);
				unMatched.remove(0);
			} else {
				event.getChannel().sendMessage(event.getGuild().getMemberById(user).getUser().getName() + " is in queue for Rock Paper Scissors").queue();
			}
		}

	}

	public PlayerVPlayer(String user) throws UserNotInGameException {
		if(playerOne.containsKey(user)) {
			playerOne.remove(user);
		} else if(playerTwo.containsKey(user)) {
				playerTwo.remove(user);
		} else if(unMatched.contains(user)){
			unMatched.remove(user);
		} else {
			throw new UserNotInGameException();
		}
	}


}
