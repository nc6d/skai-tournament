package org.example;

import lombok.Getter;

@Getter
public class Player {
    private final String name;
    private final String username;
    private final String team;
    private int ratingPoints;

    public Player(String name, String username, String team) {
        this.name = name;
        this.username = username;
        this.team = team;
        this.ratingPoints = 0;
    }

    public void addRatingPoints(int points) {
        ratingPoints += points;
    }

    @Override
    public String toString() {
        return Colorizer.ASCII_GRAY + team.toUpperCase() + ": " + name + " " + username + ": " + ratingPoints + " points";
    }

    public String toStringWinnerColoured() {
        return Colorizer.ASCII_GREEN + name + " " + username + ": " + ratingPoints + " points";
    }
}