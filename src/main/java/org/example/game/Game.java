package org.example.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.Player;

import java.util.Map;

@Getter
public abstract class Game {
    @Setter
    protected String winnerTeam;
    protected String sport;

    public Game(String sport) {
        this.sport = sport;
        this.winnerTeam = null;
    }

    public abstract void processStats(String[] stats, Map<String, Player> playerMap);
}