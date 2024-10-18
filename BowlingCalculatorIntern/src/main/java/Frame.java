import java.util.Objects;

public class Frame {
    int firstRoll;
    int secondRoll = -1;

    public Frame(int firstRoll, int secondRoll) {
        this(firstRoll);
        this.secondRoll = secondRoll;
    }

    public Frame(int firstRoll) {
        this.firstRoll = firstRoll;
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }

    public void setSecondRoll(int secondRoll) {
        this.secondRoll = secondRoll;
    }

    public int scoreOfFrame() {
        if (secondRoll == -1) {
            return firstRoll;
        }
        return firstRoll + secondRoll;
    }

    public boolean isSpare() {return firstRoll + secondRoll == 10 && firstRoll != 10;}

    public boolean isStrike() {
        return firstRoll == 10;
    }

    public boolean frameIsComplete() {
        return isStrike() || isSpare() || secondRoll != -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return firstRoll == frame.firstRoll && secondRoll == frame.secondRoll;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstRoll, secondRoll);
    }

    @Override
    public String toString() {
        if (frameIsComplete()) {
            if (isSpare()) {
                return firstRoll + "  /";
            }
            if (isStrike()) {
                return "X  -";
            }
            return firstRoll + "  " + secondRoll;
        }
        return Integer.toString(firstRoll);
    }
}
