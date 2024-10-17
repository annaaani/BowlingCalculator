import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void isStrike() {
        assertTrue(new Frame(10, 0).isStrike());
    }

    @Test
    void isSpare() {
        assertTrue(new Frame(4, 6).isSpare());
    }

    @Test
    void scoreOfFrame() {
        assertEquals(8, new Frame(2, 6).scoreOfFrame());
    }

    @Test
    void frameIsComplete() {
        assertTrue(new Frame(2, 6).frameIsComplete());
    }

    @Test
    void scoreToString() {
        assertEquals("3 4", new Frame(3, 4).toString());
    }

    @Test
    void spareToString() {
        assertEquals("9 /", new Frame(9, 1).toString());
    }

    @Test
    void strikeToString() {
        assertEquals("X  -", new Frame(10).toString());
    }

}