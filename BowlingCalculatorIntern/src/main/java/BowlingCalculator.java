import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    private static final int LAST_FRAME = 9;
    List<Frame> frames = new ArrayList<>();
    List<Integer> frameScores = new ArrayList<>();

    public void addRoll(int pins) {
        if (frames.isEmpty() || frames.get(frames.size() - 1).frameIsComplete()) {
            Frame frame = new Frame(pins);

            if (frame.isStrike()) {
                frame.setSecondRoll(0);
            }
            frames.add(frame);
        } else {
            frames.get(frames.size() - 1).setSecondRoll(pins);
        }
    }

    public int score() {
        int totalScore = 0;

        for (int i = 0; i <= LAST_FRAME; i++) {
            totalScore += frames.get(i).scoreOfFrame();
            if (i == frames.size() - 1) {
                frameScores.add(totalScore);
                return totalScore;
            }

            totalScore += addSpareBonus(i);
            totalScore += addStrikeBonus(i);
            frameScores.add(totalScore);
        }
        return totalScore;
    }

    private int addStrikeBonus(int i) {
        int strikeBonus = 0;
        var frame = frames.get(i);
        var nextFrame = frames.get(i + 1);

        if (frame.isStrike()) {
            strikeBonus += nextFrame.scoreOfFrame();
            if (nextFrame.isStrike() && frames.size() > i + 2) {
                strikeBonus += frames.get(i + 2).getFirstRoll();
                if (i == LAST_FRAME) strikeBonus += nextFrame.getSecondRoll();
            }
        }
        return strikeBonus;
    }

    private int addSpareBonus(int i) {
        var nextFrame = frames.get(i + 1);
        var frame = frames.get(i);

        if (frame.isSpare()) {
            return nextFrame.getFirstRoll();
        }
        return 0;
    }

    public String getResults() {

        //todo: last frame last roll is strike or spare!!

        String rollsToString = "";
        String scoreToString = "";
        for (int i = 0; i <= LAST_FRAME; i++) {
            Frame frame = frames.get(i);
            Integer frameScore = frameScores.get(i);

            rollsToString += "|" + frame;
            scoreToString += "|" + addSpaces(String.valueOf(frameScore).length()) + frameScore;
        }
        return rollsToString + "| \n" + scoreToString + "|";
    }

    public String addSpaces(int integerLength) {
        int numberOfSpaces = 4 - integerLength;
        return " ".repeat(Math.max(0, numberOfSpaces));
    }

    public static void main(String[] args) {
        BowlingCalculator calculator = new BowlingCalculator();
        Integer[] rolls = {8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 9, 1, 9, 1, 1};
        for (Integer roll : rolls) {
            calculator.addRoll(roll);
        }
        calculator.score();
        System.out.println(calculator.getResults());
    }
}