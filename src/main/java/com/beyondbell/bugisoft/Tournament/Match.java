package com.beyondbell.bugisoft.Tournament;

import java.util.ArrayList;


class Match {


	private ArrayList<Player> blueTeam;
	private ArrayList<Player> orangeTeam;

	public void setMatch(int size) {
		switch (size) {
			case 1 :
				blueTeam = new ArrayList<Player>(1);
				orangeTeam = new ArrayList<Player>(1);
				break;
			case 2 :
				blueTeam = new ArrayList<Player>(2);
				orangeTeam = new ArrayList<Player>(2);
				break;
			case 3 :
				blueTeam = new ArrayList<Player>(3);
				orangeTeam = new ArrayList<Player>(3);
				break;
			case 4 :
				blueTeam = new ArrayList<Player>(4);
				orangeTeam = new ArrayList<Player>(4);
				break;
			case 5 :
				blueTeam = new ArrayList<Player>(5);
				orangeTeam = new ArrayList<Player>(5);
				break;
			default:
				blueTeam = new ArrayList<Player>(1);
				orangeTeam = new ArrayList<Player>(1);
				break;
		}
	}

	public void addBlueTeam(Player player) {
		blueTeam.add(player);
	}

	public void addOrangeTeam(Player player) {
		orangeTeam.add(player);
	}


}
