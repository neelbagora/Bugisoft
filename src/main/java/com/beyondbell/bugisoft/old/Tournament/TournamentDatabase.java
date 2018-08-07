package com.beyondbell.bugisoft.old.Tournament;

import com.beyondbell.bugisoft.old.Tournament.Errors.GameAlreadyExistsThrowable;
import com.beyondbell.bugisoft.old.Tournament.Tournaments.DarwinsProjectTournament;
import com.beyondbell.bugisoft.old.Tournament.Tournaments.RainbowSixSiegeTournament;
import com.beyondbell.bugisoft.old.Tournament.Tournaments.Tournament;

import java.util.HashMap;
import java.util.Map;

public class TournamentDatabase {
	private static final Map<String, Tournament> tournamentMap = new HashMap<>();

	static Tournament createTournament(String name, Game game) throws GameAlreadyExistsThrowable {
		if (tournamentMap.containsKey(name)) {
			throw new GameAlreadyExistsThrowable();
		}

		switch (game) {
			case RainbowSixSiege:
				tournamentMap.put(name, new RainbowSixSiegeTournament(name));
				return tournamentMap.get(name);
			case DarwinsProject:
				tournamentMap.put(name, new DarwinsProjectTournament(name));
				return tournamentMap.get(name);
			default:
				return null;
		}
	}

	static Tournament getTournament(String name) {
		return tournamentMap.get(name);
	}

	public void startTournament(int id) {

	}

	public enum Game {
		RainbowSixSiege, DarwinsProject
	}
}
