package com.beyondbell.bugisoft.UserInfo;

class UserInfo {
	private final String username;
	private int level;
	private double cash;

	private int wins;
	private int losses;

	UserInfo(String username) {
		this.username = username;
	}

	UserInfo(String username, double cash) {
		this.username = username;
		this.cash = cash;
	}

	UserInfo(String username, int level, double cash) {
		this.username = username;
		this.level = level;
		this.cash = cash;
	}

	String getUsername() {
		return username;
	}

	public int getLevel() {
		return level;
	}

	public double getCash() {
		return cash;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}
}
