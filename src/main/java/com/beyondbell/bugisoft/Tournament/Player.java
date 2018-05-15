package com.beyondbell.bugisoft.Tournament;

class Player {
    private int wins = 0;
    private int losses = 0;
    private String name = null;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }
}
