package com.beyondbell.bugisoft.Minigames.RPS;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.util.ArrayList;

public class PlayerVPlayer {
	private static ArrayList<String> unMatched = new ArrayList<>();
	private static ArrayList<String> playerOne = new ArrayList<>();
	private static ArrayList<String> playerTwo = new ArrayList<>();

	public PlayerVPlayer(String user, GuildMessageReceivedEvent event) {
		//new user joining game, checks if already in game
		if(playerOne.contains(user) || playerTwo.contains(user)) {
			event.getChannel().sendMessage(event.getGuild().getMemberById(user).getUser().getName() + " is in game already.").queue();
		} else { //user is not in game and is added to unMatched array
			unMatched.add(user);
			if(unMatched.size() % 2 == 0) {
				playerOne.add(unMatched.get(0));
				event.getChannel().sendMessage(event.getGuild().getMemberById(unMatched.get(0)).getUser().getName() + " versus " + event.getGuild().getMemberById(unMatched.get(1)).getUser().getName()).queue();
				event.getChannel().sendMessage("PM the bot to input using $rock, $paper, or $scissors").queue();
				unMatched.remove(0);
				playerTwo.add(unMatched.get(0));
				unMatched.remove(0);
			} else {
				event.getChannel().sendMessage(event.getGuild().getMemberById(user).getUser().getName() + " is in queue for Rock Paper Scissors").queue();
			}
		}

	}

	public PlayerVPlayer(String user) throws UserNotInGameException {
		if(playerOne.contains(user)) {
			playerOne.remove(playerOne.indexOf(user));
		} else if(playerTwo.contains(user)) {
				playerTwo.remove(playerTwo.indexOf(user));
		} else if(unMatched.contains(user)){
			unMatched.remove(unMatched.indexOf(user));
		} else {
			throw new UserNotInGameException();
		}
	}


}
