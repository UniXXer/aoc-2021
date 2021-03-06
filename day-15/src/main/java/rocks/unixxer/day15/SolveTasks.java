package rocks.unixxer.day15;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class SolveTasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(SolveTasks.class);

    @Inject
    Cave cave;

    void onStart(@Observes StartupEvent ev) throws FileNotFoundException {               
        LOGGER.info("The application is starting...");
        readData("/input.txt");

        LOGGER.info("Puzzle 1 Result: {}", solveTask1());
        LOGGER.info("Puzzle 2 Result: {}", solveTask2());
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");
    }

    public int solveTask1() {
        return cave.lowestRiskPath();
    }

    public long solveTask2() {
        cave.expandMap();
        LOGGER.info("Expansion done...");
        return cave.evaluateRisk();
    }

    public void readData(String filename) throws FileNotFoundException {
        try (Scanner s = new Scanner(new InputStreamReader(SolveTasks.class.getResourceAsStream(filename)))) {
           
            List<String> lines = new ArrayList<>();

            while (s.hasNext()) {
                lines.add(s.nextLine());
            }

            cave.parseLine(lines);
        }
    }
}
