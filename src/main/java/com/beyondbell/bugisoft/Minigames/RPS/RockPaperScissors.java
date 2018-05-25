package com.beyondbell.bugisoft.Minigames.RPS;

import com.beyondbell.bugisoft.Minigames.GameModeEventHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class RockPaperScissors implements Minigame {
	private static String value;
	private static String computerValue;
	private static int userScore = 0;
	private static int computerScore = 0;
	private static GuildMessageReceivedEvent event;

	public RockPaperScissors(GuildMessageReceivedEvent event) {
			this.event = event;
			value = event.getMessage().getContentRaw().substring(1).toLowerCase();
			calculateScores();
	}

	public RockPaperScissors() {
		value = null;
		computerValue = null;
		userScore = 0;
		computerScore = 0;
		event = null;
	}

	public void calculateScores() {
		int randomInt = new Random().nextInt(3);

		if (randomInt == 0) {
			computerValue = "rock";
		} else if (randomInt == 1) {
			computerValue = "scissors";
		} else if (randomInt == 2) {
			computerValue = "paper";
		}

		if(value.equals("scissors") && computerValue.equals("rock")) {
			computerScore++;
			event.getChannel().sendMessage("Computer won").queue();
		} else if(value.equals("rock") && computerValue.equals("paper")) {
			computerScore++;
			event.getChannel().sendMessage("Computer won").queue();
		} else if(value.equals("paper") && computerValue.equals("scissors")) {
			computerScore++;
			event.getChannel().sendMessage("Computer won").queue();
		} else if(value.equals("rock") && computerValue.equals("scissors")) {
			userScore++;
			event.getChannel().sendMessage("You won").queue();
		} else if(value.equals("paper") && computerValue.equals("rock")) {
			userScore++;
			event.getChannel().sendMessage("You won").queue();
		} else if(value.equals("scissors") && computerValue.equals("paper")) {
			userScore++;
			event.getChannel().sendMessage("You won").queue();
		} else if(value.equals(computerValue)) {
			event.getChannel().sendMessage("Tie!").queue();
		}

		EmbedBuilder newBuilder = new EmbedBuilder().setTitle("RPS")
				.addField(event.getAuthor().getName(), Integer.toString(userScore) ,true)
				.addField("Computer", Integer.toString(computerScore) ,true);

		if(computerScore >= 2 || userScore >= 2) {
			event.getChannel().sendMessage(computerScore > userScore ? "Computer won the game" : event.getAuthor().getName() + " won the game").queue();
			event.getChannel().sendMessage(newBuilder.build()).queue();
			new GameModeEventHandler(event, event.getAuthor().getId(),false);
			new RockPaperScissors();
		}
	}
}
