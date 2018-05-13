/*
package com.beyondbell.bugisoft.Tournament;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.ArrayList;

class Tournament {
	private final String name;
	private final TournamentType tournamentType;

	private ArrayList<Player> players;
	private ArrayList<Match> matches;

	Tournament(TournamentType tournamentType, String name) {
		this.tournamentType = tournamentType;
		this.name = name;
		players = new ArrayList<>();
		matches = new ArrayList<>();
//		matches.add(new Match(new Player[]{new Player("Slinke")}, new Player[]{new Player("literally anyone else")}, 1, false, 2, false));
	}

	public void generateBracketLayer() {
		// TODO
	}

	void printMatchDetails(MessageCreateEvent event, int matchIndex) {
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

		// Prints the Game Details
		event.getChannel().sendMessage(gameDetails);
	}

	public enum TournamentType {
		SingleElimination
	}

	// ...

	void test(MessageCreateEvent event) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		printMatchDetails(event, 0);
	}
}
*/
