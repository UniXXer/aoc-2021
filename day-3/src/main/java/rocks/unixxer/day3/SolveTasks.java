package rocks.unixxer.day3;

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
import io.vertx.mutiny.core.eventbus.EventBus;

@ApplicationScoped
public class SolveTasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(SolveTasks.class);

    @Inject
    EventBus bus; 

    @Inject
    DiagnosticReportConsumer diagnosticReportConsumer;

    void onStart(@Observes StartupEvent ev) throws FileNotFoundException {               
        LOGGER.info("The application is starting...");
        readData("/input.txt");
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping...");

        LOGGER.info("Puzzle 1 Result: {}", solveTask1());
        LOGGER.info("Puzzle 2 Result: {}", solveTask2());
    }

    public Integer solveTask1() {
        return diagnosticReportConsumer.getGammaRate() * diagnosticReportConsumer.getEpsilonrate();
    }

    public Integer solveTask2() {
        return diagnosticReportConsumer.findOxygenGeneratorRating() *diagnosticReportConsumer.findCo2ScrubberRating();
    }

    public void readData(String filename) throws FileNotFoundException {
        try (Scanner s = new Scanner(new InputStreamReader(SolveTasks.class.getResourceAsStream(filename)))) {
            while (s.hasNext()) {
                bus.publish("diagnosticReports", s.nextLine());
            }
        }

        LOGGER.info("diagnosticReports finished...");
    }
}
