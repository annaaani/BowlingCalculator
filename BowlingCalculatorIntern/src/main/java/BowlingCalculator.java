import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    public static final int LAST_FRAME = 9;
    List<Frame> frames = new ArrayList<>();

    public void addRoll(int pins) {
        if (frames.isEmpty() || frames.getLast().frameIsComplete()) {
            Frame frame = new Frame(pins);

            if (frame.isStrike()){
                frame.setSecondRoll(0);
            }
            frames.add(frame);
        } else {
            frames.getLast().setSecondRoll(pins);
        }
    }

    public int score() {
        int score = 0;

        for (int i = 0; i <= LAST_FRAME; i++) {

            var frame = frames.get(i);

            score += frame.scoreOfFrame();

            if (i == frames.size() - 1) return score;

            var nextFrame = frames.get(i + 1);

            if (frame.isSpare()) {
                score += nextFrame.getFirstRoll();
            }

            if (frame.isStrike()) {
                score += nextFrame.scoreOfFrame();
                if (nextFrame.isStrike() && frames.size() > i+2) {
                    score += frames.get(i + 2).getFirstRoll();
                    if(i == LAST_FRAME) score += frames.get(i+1).getSecondRoll();
                }
            }
        }
        return score;
    }
}