package org.example.game;

import org.example.Player;

import java.util.Map;

import static java.util.Objects.isNull;

public class Basketball extends Game {

    public Basketball() {
        super("BASKETBALL");
    }

    @Override
    public void processStats(String[] stats, Map<String, Player> playerMap) {
        String nickname = stats[1];
        int scoredPoints = Integer.parseInt(stats[4]);
        int rebounds = Integer.parseInt(stats[5]);
        int assists = Integer.parseInt(stats[6]);

        int ratingPoints = (2 * scoredPoints) + rebounds + assists;
        Player player = playerMap.get(nickname);
        player.addRatingPoints(ratingPoints);

        if (isNull(winnerTeam) || scoredPoints > Integer.parseInt(stats[4]))
            setWinnerTeam(stats[3]);
    }
}