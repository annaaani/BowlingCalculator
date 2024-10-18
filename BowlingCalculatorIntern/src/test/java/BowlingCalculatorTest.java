import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BowlingCalculatorTest {
    BowlingCalculator calc = new BowlingCalculator();



    @Test
    void oneFrame() {
        addRolls(1, 3);
        List<Frame> expected = List.of(new Frame(1, 3));
        assertEquals(expected, calc.frames);
    }

    @Test
    void multipleFrames() {
        addRolls(10, 1, 3);
        List<Frame> expected = List.of(new Frame(10, 0), new Frame(1, 3));
        assertEquals(expected, calc.frames);
    }

    @Test
    void oneRoll() {
        addRolls(9);
        assertEquals(9, calc.score());
        assertEquals(9, calc.score());
    }

    @Test
    void multipleRolls() {
        addRolls(5, 4, 3);
        assertEquals(12, calc.score());
    }

    @Test
    void oneSpare() {
        addRolls(8, 2, 5);
        assertEquals(20, calc.score());
    }

    @Test
    void multipleSpare() {
        addRolls(8, 2, 5, 5, 2);
        assertEquals(29, calc.score());
    }

    @Test
    void oneStrike() {
        addRolls(10, 4, 3);
        assertEquals(24, calc.score());
    }

    @Test
    void multipleStrike() {
        addRolls(10, 10, 10, 3, 4);
        assertEquals(77, calc.score());
    }

    @Test
    void regularEnd() {
        addRolls(8, 2, 0, 10, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 9, 1, 9, 0);
        assertEquals(143, calc.score());
    }

    @Test
    void spareEnd() {
        addRolls(8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 8, 1, 8, 2, 2);
        assertEquals(131, calc.score());
    }

    @Test
    void oneStrikeEnd() {
        addRolls(8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 8, 1, 10, 2, 3);
        assertEquals(134, calc.score());
    }

    @Test
    void doubleStrikeEnd() {
        addRolls(8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 8, 1, 10, 10, 10);
        assertEquals(149, calc.score());
    }

    @Test
    void fullGame() {
        for (int i = 0; i <= 11; i++) {
            calc.addRoll(10);
        }
        assertEquals(300, calc.score());
    }

//    @Test
//    void getMoreResults() {
//        addRolls(8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 9, 1, 9, 0);
//        calc.score();
//        assertEquals("""
//                        |8  /|5  4|9  0|X  -|X  -|5  /|5  3|6  3|9  /|9  0|
//                        |  15|  24|  33|  58|  78|  93| 101| 110| 129| 138|
//                        """, calc.toString());
//    }

    @Test
    void getResultsWithHalfFrame() {
        addRolls(8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5);
        calc.score();
        assertEquals("|8  /|5  4|9  0|X  -|X  -|5  /|5   | \n" +
                              "|  15|  24|  33|  58|  78|  93|  98|", calc.toString());
    }
//    @Test
//    void getResultWithStrikeEnd() {
//        addRolls(8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 8, 1, 10, 1, 2);
//        calc.score();
//        assertEquals("|8  /|5  4|9  0|X  -|X  -|5  /|5  3|6  3|8  1|X  1  2| \n" +
//                "|  15|  24|  33|  58|  78|  93| 101| 110| 119|    132|", calc.toString());
//    }
//    @Test
//    void getResultWithDoubleStrikeEnd() {
//        addRolls(8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 8, 1, 10, 10, 10);
//        calc.score();
//        assertEquals("|8  /|5  4|9  0|X  -|X  -|5  /|5  3|6  3|8  1|X  X  X| \n" +
//                "|  15|  24|  33|  58|  78|  93| 101| 110| 119|    149|", calc.toString());
//    }
//    @Test
//    void getResultWithSpareEnd() {
//        addRolls(8, 2, 5, 4, 9, 0, 10, 10, 5, 5, 5, 3, 6, 3, 8, 1, 8, 2, 2);
//        calc.score();
//        assertEquals("|8  /|5  4|9  0|X  -|X  -|5  /|5  3|6  3|8  1|8  /  2| \n" +
//                "|  15|  24|  33|  58|  78|  93| 101| 110| 119|    131|", calc.toString());
//    }

    @Test
    void addSpaces() {
        assertEquals("   ", calc.addSpaces(1));
        assertEquals("  ", calc.addSpaces(2));
        assertEquals(" ", calc.addSpaces(3));
    }

    private void addRolls(int... examplePins) {
        for (int example : examplePins) {
            calc.addRoll(example);
        }
    }
}