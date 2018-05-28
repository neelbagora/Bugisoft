package com.beyondbell.bugisoft.Minigames.RPS;

import java.util.Random;

public class RockPaperScissors implements Minigame {
	private String value;
	private String computerValue;

	public RockPaperScissors(String value) {
		this.value = value;
	}

	public RockPaperScissors(String value, String computerValue) {
		this.value = value;
		this.computerValue = computerValue;
	}


	public boolean calculateComputer() {
		int randomInt = new Random().nextInt(3);

		if (randomInt == 0) {
			computerValue = "rock";
		} else if (randomInt == 1) {
			computerValue = "scissors";
		} else if (randomInt == 2) {
			computerValue = "paper";
		}

		if(value.equals("scissors") && computerValue.equals("rock")) {
			return false;
		} else if(value.equals("rock") && computerValue.equals("paper")) {
			return false;
		} else if(value.equals("paper") && computerValue.equals("scissors")) {
			return false;
		} else if(value.equals("rock") && computerValue.equals("scissors")) {
			return true;
		} else if(value.equals("paper") && computerValue.equals("rock")) {
			return true;
		} else if(value.equals("scissors") && computerValue.equals("paper")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean calculatePlayer() {
		if(value.equals("scissors") && computerValue.equals("rock")) {
			return false;
		} else if(value.equals("rock") && computerValue.equals("paper")) {
			return false;
		} else if(value.equals("paper") && computerValue.equals("scissors")) {
			return false;
		} else if(value.equals("rock") && computerValue.equals("scissors")) {
			return true;
		} else if(value.equals("paper") && computerValue.equals("rock")) {
			return true;
		} else if(value.equals("scissors") && computerValue.equals("paper")) {
			return true;
		} else {
			return false;
		}
	}
}
