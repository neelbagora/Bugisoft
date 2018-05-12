package com.beyondbell.bugisoft.Tournament.Tournaments;

import com.beyondbell.bugisoft.Tournament.Match;
import com.beyondbell.bugisoft.Tournament.Player;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;

public abstract class Tournament {
	private final String name;

	private ArrayList<Player> players;
	private ArrayList<Match> matches;



	Tournament(String name) {
		this.name = name;
		players = new ArrayList<>();
		matches = new ArrayList<>();
	}

	void printMatchDetails(MessageReceivedEvent event, int matchIndex) {
		if (matchIndex == -1) { // Full Tournament

		} else {                // Single Match
			// Creates Main Details
			EmbedBuilder gameDetails = new EmbedBuilder()
					.setTitle("Match: " + matchIndex + " In Tournament: " + name + " Details")
					.addField("Match Status", matches.get(matchIndex).getMatchPhase().name(), false)
					.addField("Blue Team Players", matches.get(matchIndex).getBlueTeamPrintable(), true)
					.addField("Orange Team Players", matches.get(matchIndex).getOrangeTeamPrintable(), true)
					.addField("Maps Played", matches.get(matchIndex).getMapsPlayedPrintable(), false);
			// TODO Add Banned Operators

			// Checks for Team Victory
			if (matches.get(matchIndex).getMatchPhase() == Match.MatchPhase.Finished) {
				if (matches.get(matchIndex).getBlueWon()) {
					gameDetails.setColor(Color.BLUE).setFooter("Blue Team Won!");
				} else {
					gameDetails.setColor(Color.ORANGE).setFooter("Orange Team Won!");
				}
			} else {
				gameDetails.setFooter("Game Results Pending...");
			}
		}
		
		// Prints the Game Details
		event.getChannel().sendMessage(gameDetails);
	}
}
