package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MainRingApplication {
    public static void main(String[] args) {
        Map<String, Class<?>> pluginsMap = getPlugins();
        Queue<Map.Entry<String, Class<?>>> queue = new LinkedList<>(pluginsMap.entrySet());

        while (queue.size() > 1) {
            playMatch(queue);
        }

        if (!queue.isEmpty()) {
            System.out.println("The tournament is over!");
            System.out.println("The winner is: " + queue.peek().getKey());
        }
    }

    /**
     * Plays a match between two players
     *
     * @param queue queue of players
     */
    private static void playMatch(Queue<Map.Entry<String, Class<?>>> queue) {
        Map.Entry<String, Class<?>> entry1 = queue.poll();
        Map.Entry<String, Class<?>> entry2 = queue.poll();

        int score1 = 0, score2 = 0;

        for (int round = 1; round <= 3; round++) {
            int result = playRound(entry1, entry2, round);
            if (result == 1) {
                score1++;
            } else if (result == -1) {
                score2++;
            }
        }

        whoWinMatch(score1, score2, entry1, entry2, queue);
    }

    /**
     * Plays a round between two players
     *
     * @param entry1 first player
     * @param entry2 second player
     * @param round  round number
     * @return 1 if first player wins, -1 if second player wins, 0 if draw
     */
    private static int playRound(Map.Entry<String, Class<?>> entry1, Map.Entry<String, Class<?>> entry2, int round) {
        try {
            PlayableRockPaperScissors player1 = (PlayableRockPaperScissors) entry1.getValue().newInstance();
            PlayableRockPaperScissors player2 = (PlayableRockPaperScissors) entry2.getValue().newInstance();

            RockPaperScissorsEnum choice1 = player1.play();
            RockPaperScissorsEnum choice2 = player2.play();
            System.out.println("Round " + round + ":");
            System.out.println(entry1.getKey().substring(0, entry1.getKey().length() - 4) + " plays " + choice1);
            System.out.println(entry2.getKey().substring(0, entry2.getKey().length() - 4) + " plays " + choice2);

            return whoWinRound(choice1, choice2);

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Prints the winner of the match
     *
     * @param score1 score of first player
     * @param score2 score of second player
     * @param entry1 first player
     * @param entry2 second player
     * @param queue  queue of players
     */
    private static void whoWinMatch(int score1, int score2, Map.Entry<String, Class<?>> entry1,
                                    Map.Entry<String, Class<?>> entry2, Queue<Map.Entry<String, Class<?>>> queue) {
        if (score1 > score2) {
            System.out.println(entry1.getKey() + " wins the match\n");
            queue.add(entry1);
        } else if (score2 > score1) {
            System.out.println(entry2.getKey() + " wins the match\n");
            queue.add(entry2);
        } else {
            queue.add(entry1);
            queue.add(entry2);
            System.out.println("The match is a draw\n");
        }
    }

    /**
     * Determines who wins the round
     *
     * @param choice1 choice of first player
     * @param choice2 choice of second player
     * @return 1 if first player wins, -1 if second player wins, 0 if draw
     */
    private static int whoWinRound(RockPaperScissorsEnum choice1, RockPaperScissorsEnum choice2) {
        if (choice1 == choice2) {
            return 0;
        }
        return switch (choice1) {
            case ROCK -> (choice2 == RockPaperScissorsEnum.SCISSORS) ? 1 : -1;
            case PAPER -> (choice2 == RockPaperScissorsEnum.ROCK) ? 1 : -1;
            case SCISSORS -> (choice2 == RockPaperScissorsEnum.PAPER) ? 1 : -1;
        };
    }

    /**
     * Gets all plugins from plugins directory
     *
     * @return map of plugins
     */
    private static Map<String, Class<?>> getPlugins() {
        File[] jars = getPluginFiles();
        Map<String, Class<?>> pluginClasses = new HashMap<>();
        for (File jar : jars) {
            try {
                URL jarURL = jar.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
                String fileName = jar.getName();
                Class<?> clazz =
                        classLoader.loadClass("org.example.RockPaperScissorsPlayer");
                pluginClasses.put(fileName, clazz);
                classLoader.close();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        return pluginClasses;
    }

    /**
     * Gets all jar files from plugins directory
     *
     * @return array of jar files
     */
    private static File[] getPluginFiles() {
        File pluginDir = new File("/Users/gevorg/Desktop/SberJavaSchool/HomeWork7/task3/plugins");

        return pluginDir.listFiles(file ->
                file.isFile() && file.getName().endsWith(".jar"));
    }
}
