package org.example;

public class RockPaperScissorsPlayer implements PlayableRockPaperScissors {
    @Override
    public RockPaperScissorsEnum play() {
        var options = RockPaperScissorsEnum.values();

        int optionNumber = getRandomNumber(0, options.length);

        return options[optionNumber];
    }

    /**
     * @param min min value
     * @param max max value
     * @return random number between min and max
     */
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
