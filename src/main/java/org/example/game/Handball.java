package org.example.game;

import org.example.Player;

import java.util.Map;

public class Handball extends Game {
    public Handball() {
        super("HANDBALL");
    }

    @Override
    public void processStats(String[] stats, Map<String, Player> playerMap) {
        String nickname = stats[1];
        int goalsMade = Integer.parseInt(stats[4]);
        int goalsReceived = Integer.parseInt(stats[5]);

        int ratingPoints = (2 * goalsMade) - goalsReceived;
        Player player = playerMap.get(nickname);
        player.addRatingPoints(ratingPoints);

        if (winnerTeam == null || goalsMade > Integer.parseInt(stats[4]))
            setWinnerTeam(stats[3]);
    }
}