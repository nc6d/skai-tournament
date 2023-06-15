package org.example;

import org.example.exception.DuplicateUsernameException;
import org.example.exception.UnsupportedSportException;
import org.example.game.Basketball;
import org.example.game.Game;
import org.example.game.Handball;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Tournament {

    public static void main(String[] args) {
        Instant start = Instant.now();

        List<File> files = extractFilesFromFolder(new File("csv"));
        Map<String, Player> playerMap = new HashMap<>();

        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String sport = br.readLine();
                Game game = null;
                switch (sport) {
                    case "BASKETBALL" -> game = new Basketball();
                    case "HANDBALL" -> game = new Handball();
                }

                if (isNull(game))
                    throw new UnsupportedSportException(sport, file.getName());

                String line;
                Map<String, Player> duplicateCheckMap = new HashMap<>();
                while ((line = br.readLine()) != null) {
                    String[] stats = line.split(";");

                    if (duplicateCheckMap.containsKey(stats[1]))
                        throw new DuplicateUsernameException(stats[1], file.getName());

                    Player player = playerMap.containsKey(stats[1])
                            ? playerMap.get(stats[1])
                            : new Player(stats[0], stats[1], stats[3]);

                    duplicateCheckMap.put(stats[1], player);
                    playerMap.put(stats[1], player);
                    game.processStats(stats, playerMap);
                    System.out.println(player);
                }
                duplicateCheckMap.clear();

                System.out.println(Colorizer.ASCII_YELLOW + "Winner team of " + game.getSport() + " match #" + file.getName().replaceAll("\\D", "") + ": " + game.getWinnerTeam());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Player mvp = null;
        for (Player player : playerMap.values()) {
            if (isNull(mvp) || player.getRatingPoints() > mvp.getRatingPoints()) {
                mvp = player;
            }
        }

        if (nonNull(mvp)) System.out.println(Colorizer.ASCII_GREEN + "Most Valuable Player (MVP): " +  mvp.toStringWinnerColoured());


        Instant end = Instant.now();
        System.out.println(Colorizer.ASCII_CYAN + "\nMatches processed: " + files.size());
        System.out.println("Execution time (ms): " + Duration.between(start, end).toMillis());
    }
    public static List<File> extractFilesFromFolder(File folder) {
        File[] files = folder.listFiles();
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(files)));
    }
}