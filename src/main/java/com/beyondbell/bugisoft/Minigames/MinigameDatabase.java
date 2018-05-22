package com.beyondbell.bugisoft.Minigames;

import com.beyondbell.bugisoft.Minigames.RPS.Minigame;

import java.util.ArrayList;

public class MinigameDatabase {
	private static final ArrayList<Minigame> minigames = new ArrayList<>();

	public void addMiniGame(Minigame game) {
		minigames.add(game);
	}

}
