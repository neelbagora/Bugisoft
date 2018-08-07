package com.beyondbell.bugisoft.old.Minigames.RPS;

import com.beyondbell.bugisoft.old.Minigames.GameHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class RPSGame {

	static Map<String, Integer> userRoundWins = new HashMap<>();
	static Map<String, Integer> computerRoundsWins = new HashMap<>();

	int rounds = 3;
	int winsNeeded = ( rounds / 2 + 1);

	public RPSGame(GuildMessageReceivedEvent event) {
		if(!userRoundWins.containsKey(event.getAuthor().getId())) {
			userRoundWins.put(event.getAuthor().getId(), 0);
		}

		if(!computerRoundsWins.containsKey(event.getAuthor().getId())) {
			computerRoundsWins.put(event.getAuthor().getId(), 0);
		}

		if(new RockPaperScissors(event.getMessage().getContentRaw().substring(1)).calculateComputer()) {
			userRoundWins.put(event.getAuthor().getId(), userRoundWins.get(event.getAuthor().getId()) + 1);
			event.getChannel().sendMessage("User won the round").queue();
		} else {
			computerRoundsWins.put(event.getAuthor().getId(), computerRoundsWins.get(event.getAuthor().getId()) + 1);
			event.getChannel().sendMessage("Computer won that round").queue();
		}


		if(userRoundWins.get(event.getAuthor().getId()) == winsNeeded) {
			new GameHandler(event.getAuthor().getId(), false);
			event.getChannel().sendMessage(event.getAuthor().getName() + " has won the game!").queue();
			userRoundWins.remove(event.getAuthor().getId());
			event.getChannel().sendMessage(new EmbedBuilder()
					.addField(event.getAuthor().getName(), Integer.toString(winsNeeded), true)
					.addField("Computer", Integer.toString(rounds - winsNeeded), true)
					.build()).queue();

		} else if(computerRoundsWins.get(event.getAuthor().getId()) == winsNeeded) {
			new GameHandler(event.getAuthor().getId(), false);
			event.getChannel().sendMessage("Computer has won the game").queue();
			event.getChannel().sendMessage(new EmbedBuilder()
					.addField("Computer", Integer.toString(winsNeeded), true)
					.addField(event.getAuthor().getName(), Integer.toString(rounds - winsNeeded), true)
					.build()).queue();
			computerRoundsWins.remove(event.getAuthor().getId());
		}
	}

	public RPSGame(String user) {
		if(userRoundWins.containsKey(user)) {

			try {
				computerRoundsWins.remove(user);
				userRoundWins.remove(user);
			} catch(NullPointerException error){}

		}
	}
}
