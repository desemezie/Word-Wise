package ca.uwo.cs2212.group2.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Unit tests for Metrics class */
class MetricsTest {

  private static final String TEST_FILE = "test_metrics.json";

  @BeforeAll
  static void setup() throws IOException {
    // Set up resources before all tests
    Files.deleteIfExists(Path.of(TEST_FILE));
  }

  @AfterAll
  static void cleanup() throws IOException {
    // Clean up resources after all tests, such as deleting the test file
    Files.deleteIfExists(Path.of(TEST_FILE));
  }

  @Test
  void saveToFileAndLoadFromFile() throws IOException {
    Metrics metrics = new Metrics(1, 2, 3, 4, 5, 6, 7, 8);
    metrics.saveToFile(TEST_FILE);

    Metrics loadedMetrics = Metrics.loadFromFile(TEST_FILE);

    Assertions.assertEquals(metrics.getNumberOfCharacters(), loadedMetrics.getNumberOfCharacters());
    Assertions.assertEquals(metrics.getNumberOfLines(), loadedMetrics.getNumberOfLines());
    Assertions.assertEquals(metrics.getNumberOfWords(), loadedMetrics.getNumberOfWords());
    Assertions.assertEquals(
        metrics.getTotalNumberOfErrors(), loadedMetrics.getTotalNumberOfErrors());
    Assertions.assertEquals(
        metrics.getCurrentNumberOfErrors(), loadedMetrics.getCurrentNumberOfErrors());
    Assertions.assertEquals(
        metrics.getTotalNumberOfAcceptedCorrections(),
        loadedMetrics.getTotalNumberOfAcceptedCorrections());
    Assertions.assertEquals(
        metrics.getTotalNumberOfManualCorrections(),
        loadedMetrics.getTotalNumberOfManualCorrections());
    Assertions.assertEquals(
        metrics.getNumberOfWordsInUserDictionary(),
        loadedMetrics.getNumberOfWordsInUserDictionary());
  }

  @Test
  void testToString() {
    Metrics metrics = new Metrics(1, 2, 3, 4, 5, 6, 7, 8);
    String expected =
        "Metrics{"
            + "numberOfCharacters=1, "
            + "numberOfLines=2, "
            + "numberOfWords=3, "
            + "totalNumberOfErrors=4, "
            + "currentNumberOfErrors=5, "
            + "totalNumberOfAcceptedCorrections=6, "
            + "totalNumberOfManualCorrections=7, "
            + "numberOfWordsInUserDictionary=8}";
    Assertions.assertEquals(expected, metrics.toString());
  }
}
