package rocks.unixxer.day7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TheDayTest {

    @Inject
    SolveTasks solveTasks;

    @Test
    public void testConsumer_puzzle1() throws FileNotFoundException {
        assertEquals(37, solveTasks.solveTask1());
    }

    @Test
    public void testConsumer_puzzle2() throws FileNotFoundException {
        assertEquals(168, solveTasks.solveTask2());
    }
}
