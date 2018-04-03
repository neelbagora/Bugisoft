package com.beyondbell.bugisoft.Tournament;

import java.util.ArrayList;

public class Tournament {
	private TournamentType type;
	private ArrayList<Player> players;
	private ArrayList<Match> matches;


	public enum TournamentType {
		SingleElimination, Open
	}

	public Tournament(TournamentType type, double entryFee) {

	}
}
