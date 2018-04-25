package com.beyondbell.bugisoft.Tournament;

import java.util.ArrayList;

public class TournamentDatabase {
	private ArrayList<Tournament> tournaments;

	public enum TournamentType {
		SingleElimination
	}

	public void createTournament(TournamentType tournamentType) {
		switch (tournamentType) {
			case SingleElimination:
//				tournaments.add(new SingleEliminationTournament(matches));
				break;
			default:

		}
	}

	public void startTournament(int id) {

	}
}
