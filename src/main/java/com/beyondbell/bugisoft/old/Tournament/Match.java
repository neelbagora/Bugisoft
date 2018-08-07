package com.beyondbell.bugisoft.old.Tournament;

import com.beyondbell.bugisoft.old.Utilities.Enums.RainbowSixSiege.Map;
import com.beyondbell.bugisoft.old.Utilities.Enums.RainbowSixSiege.Operator;

class Match {
	private final Player[] players;
	private final Player[] blueTeam;
	private final Player[] orangeTeam;

	private volatile MatchPhase matchPhase;

	private Map[] playedMaps;
	private Operator[][] bannedOperators;

	private boolean blueWon;
	private boolean[] results;

	Match(Player[] blueTeam, Player[] orangeTeam, int numberOfMaps, boolean shouldBanOperators, int numberOfOperatorBans, boolean shouldBanOperatorsPerMap) {
		// Sets Match Phase to Creating
		matchPhase = MatchPhase.Creating;


		// Sets Team Size

		// Finds Largest Team Size
		int teamPlayerCount;
		if (blueTeam.length >= orangeTeam.length) {
			teamPlayerCount = blueTeam.length;
		} else {
			teamPlayerCount = orangeTeam.length;
		}

		// Ensures Largest Team Size is Equal or Smaller Than Five
		if (teamPlayerCount > 5 || teamPlayerCount == 0) {
			// TODO Nullify Match (This Probably Should Have Been Checked Higher Up By Whatever Created The Match)
			this.players = null;
			this.blueTeam = null;
			this.orangeTeam = null;
			matchPhase = MatchPhase.Error;
			return;
		}


		// Sets Up Teams

		// Creates Full Players Array
		this.players = new Player[blueTeam.length + orangeTeam.length];
		System.arraycopy(blueTeam, 0, this.players, 0, blueTeam.length);
		System.arraycopy(orangeTeam, 0, this.players, blueTeam.length, blueTeam.length + orangeTeam.length - blueTeam.length);

		// Initializes Teams With Team Size
		this.blueTeam = new Player[teamPlayerCount];
		this.orangeTeam = new Player[teamPlayerCount];

		// Adds Players to Teams
		System.arraycopy(blueTeam, 0, this.blueTeam, 0, blueTeam.length);
		System.arraycopy(orangeTeam, 0, this.orangeTeam, 0, orangeTeam.length);


		// Sets Up Variables

		// Sets Up Number of Map
		playedMaps = new Map[numberOfMaps];

		// Sets Up Number of Operator Bans
		if (shouldBanOperators) {
			if (shouldBanOperatorsPerMap) {
				bannedOperators = new Operator[numberOfMaps][numberOfOperatorBans];
			} else {
				bannedOperators = new Operator[1][numberOfOperatorBans];
			}
		} else {
			bannedOperators = new Operator[0][0];
		}

		// Initializes Results With a Size of Amount of Players in the Game


		// Ends Creating Game Phase and Starts Banning Maps Phase
		matchPhase = MatchPhase.BanningMaps;
	}

	MatchPhase getMatchPhase() {
		return matchPhase;
	}


	// Banning Maps

	void BanMap(Map map) {
		// TODO Ban Map
	}


	// Banning Operators

	void BanOperator(Operator operator) {
		// TODO Ban Operator
	}


	// Playing Games

	void start() {
		// TODO Changed Game Phase
		// TODO Broadcast Game Starting
	}

	void enterResults(boolean blueWon) {
		// TODO Add to Array
		// TODO Check to See All Players Voted
	}

	void end() {
		// TODO Close the Game and Allow Players to be "Freed"
	}


	// Accessor Methods

	boolean getBlueWon() {
		return blueWon;
	}

	public Player[] getBlueTeam() {
		return blueTeam;
	}

	public Player[] getOrangeTeam() {
		return orangeTeam;
	}


	// Printable

	String getBlueTeamPrintable() {
		StringBuilder blueTeamPrintable = new StringBuilder();
		for (Player player : blueTeam) {
			if (player != null) {
				blueTeamPrintable.append(player.getName());
			}
			blueTeamPrintable.append("\n");
		}
		return blueTeamPrintable.toString();
	}

	String getOrangeTeamPrintable() {
		StringBuilder orangeTeamPrintable = new StringBuilder();
		for (Player player : orangeTeam) {
			if (player != null) {
				orangeTeamPrintable.append(player.getName());
			}
			orangeTeamPrintable.append("\n");
		}
		return orangeTeamPrintable.toString();
	}

	String getMapsPlayedPrintable() {
		StringBuilder mapsPlayed = new StringBuilder();
		for (Map map : playedMaps) {
			mapsPlayed.append(map).append("\t");
		}
		return mapsPlayed.toString();
	}


	// Utilities

	enum MatchPhase {
		Creating, BanningMaps, BanningOperators, Playing, Finished,
		Error
	}
}
