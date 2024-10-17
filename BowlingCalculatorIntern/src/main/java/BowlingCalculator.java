import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    public static final int LAST_FRAME = 9;
    List<Frame> frames = new ArrayList<>();

    // TODO: neid pole vaja
//    boolean isFirstRoll = true;
//    int score = 0;
//    int frameIndex = 0;

    public void addRoll(int pins) {
        if (frames.isEmpty() || frames.getLast().frameIsComplete()) {
            Frame frame = new Frame();
            frame.setFirstRoll(pins);
            frames.add(frame);
        } else {
            Frame currentFrame = frames.getLast();
            if (!currentFrame.hasSecondRoll()) {
                currentFrame.setSecondRoll(pins);
            }
        }
    }

//    todo: siin pooleli. fullGame test tootab, teised terve array omad mitte
    public int score() {
        int score = 0;
//        see vb pole vajalik
        int numberOfFrames = frames.size();
// kui panna juurde =, siis vist IndexOutOfBoundsError
        for (int i = 0; i <= numberOfFrames; i++) {

            var frame = frames.get(i);

            score += frame.scoreOfFrame(frame.getFirstRoll(), frame.getSecondRoll());

            if (i == numberOfFrames - 1) {return score;}

            var nextFrame = frames.get(i + 1);

            if (frame.isSpare()) {
                score += nextFrame.getFirstRoll();
            }
            if (frame.isStrike()) {
                score += nextFrame.scoreOfFrame(nextFrame.getFirstRoll(), nextFrame.getSecondRoll());
                if (i + 2 < numberOfFrames && nextFrame.isStrike()) {
                    score += frames.get(i + 2).getFirstRoll();
                }
            }
        }
        return score;
    }
}