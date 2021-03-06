package rocks.unixxer.day13;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.web.impl.Origin;

@ApplicationScoped
public class SolveTasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(SolveTasks.class);

    @Inject
    Origami origami;

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
        origami.fold(1);
        return origami.getPoints().size();
    }

    public long solveTask2() {
        origami.fold(-1);
        origami.draw();
        return 0;
    }

    public void readData(String filename) throws FileNotFoundException {
        origami.reset();
        try (Scanner s = new Scanner(new InputStreamReader(SolveTasks.class.getResourceAsStream(filename)))) {
           
            while (s.hasNext()) {
                origami.parseLine(s.nextLine());
            }
        }
    }
}
