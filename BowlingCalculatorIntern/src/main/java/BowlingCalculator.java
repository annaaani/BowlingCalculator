import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    private static final int LAST_FRAME_INDEX = 9;
    List<Frame> frames = new ArrayList<>();
    List<Integer> frameScores = new ArrayList<>();
    int lastFrame = 10;

    public void addRoll(int pins) {
        if (frames.size() <= lastFrameNumber()) {
            if (frames.isEmpty() || frames.getLast().frameIsComplete()) {
                Frame frame = new Frame(pins);
                if (frame.isStrike()) {
                    frame.setSecondRoll(0);
                }
                frames.add(frame);
            } else {
                frames.getLast().setSecondRoll(pins);
            }
        }
    }

    public int lastFrameNumber() {
        if (frames.size() == 10 && (frames.get(9).isSpare() || frames.get(9).isStrike())) {
            lastFrame++;
        }
        if (frames.size() == 11 && frames.get(10).isStrike()) {
            lastFrame++;
        }
        return lastFrame;
    }

    public int score() { // TODO: should be immutable, no side-effects
        int totalScore = 0;

        for (int i = 0; i <= LAST_FRAME_INDEX; i++) {

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
                if (i == LAST_FRAME_INDEX) strikeBonus += nextFrame.getSecondRoll();
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

    @Override
    public String toString() {
        var rollsToString = new StringBuilder();
        var scoreToString = new StringBuilder();
        var combined = new StringBuilder();

        for (int i = 0; i <= LAST_FRAME_INDEX; i++) {
            if (i == frames.size()) {
                return (combined.append(rollsToString).append("   | \n")
                        .append(scoreToString)
                        .append("|").toString());
            }

            Frame frame = frames.get(i);
            Integer frameScore = frameScores.get(i);

            if (i == LAST_FRAME_INDEX && (frame.isStrike() || frame.isSpare())) {
                if (frame.isStrike()) {
                    rollsToString = getStrikeBonus(rollsToString, i);
                } else if (frame.isSpare()) {
                    rollsToString.append("|")
                            .append(frame)
                            .append("  ")
                            .append(frames.get(i + 1));
                }
                scoreToString.append("|")
                        .append(addSpaces(String.valueOf(frameScore).length()))
                        .append("   ")
                        .append(frameScore);
            } else {
                rollsToString.append("|")
                        .append(frame);
                scoreToString.append("|")
                        .append(addSpaces(String.valueOf(frameScore).length()))
                        .append(frameScore);
            }
        }
        return (combined.append(rollsToString).append("| \n")
                .append(scoreToString)
                .append("|").toString());
    }

    private StringBuilder getStrikeBonus(StringBuilder rollsToString, int i) {
        Frame frame = frames.get(i);
        Frame nextFrame = frames.get(i + 1);
        rollsToString.append("|")
                .append(String.valueOf(frame).replace("X  -", "X"))
                .append("  ")
                .append(String.valueOf(nextFrame).replace("X  -", "X"));
        if (nextFrame.isStrike()) {
            rollsToString.append(String.valueOf(frames.get(i + 2)).replace("X  -", "  X"));
        }
        return rollsToString;
    }

    public String addSpaces(int integerLength) {
        int numberOfSpaces = 4 - integerLength;
        return " ".repeat(Math.max(0, numberOfSpaces));
    }

    public static void main(String[] args) {
        var calculator = new BowlingCalculator();
        Integer[] rolls = {8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 9, 1, 9, 0};
        for (Integer roll : rolls) {
            calculator.addRoll(roll);
        }
        calculator.score();
        System.out.println(calculator);
    }
}