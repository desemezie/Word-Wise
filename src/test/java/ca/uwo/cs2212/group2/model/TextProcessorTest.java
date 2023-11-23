package ca.uwo.cs2212.group2.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests for TextProcessor class */
class TextProcessorTest {

  @TempDir Path tempDir;

  File testFile;

  @BeforeEach
  void setUp() throws IOException {
    // Create a temporary file for testing
    testFile = tempDir.resolve("testFile.txt").toFile();

    // Write some content to the file
    try (PrintWriter out = new PrintWriter(testFile)) {
      out.println("Hello world.");
      out.println("This is a test.");
    }
  }

  @Test
  void testFileNotFound() {
    // Test for file not found exception
    assertThrows(
        FileNotFoundException.class,
        () -> {
          new TextProcessor("nonexistentfile.txt");
        });
  }

  @Test
  void testParseFile() throws Exception {
    // Test parsing of the file
    TextProcessor processor = new TextProcessor(testFile.getAbsolutePath());

    assertEquals(2, processor.getLineCount(), "Line count should match");
    assertEquals(6, processor.getWordCount(), "Word count should match");
  }

  @Test
  void testCharacterCountWithSpace() throws Exception {
    // Test character count including spaces
    TextProcessor processor = new TextProcessor(testFile.getAbsolutePath());

    long expectedCharCountWithSpace = "Hello world.\nThis is a test.".length();
    assertEquals(
        expectedCharCountWithSpace,
        (processor.getCharCountWithSpace() + processor.getLineCount() - 1),
        "Character count with space should match");
  }

  @Test
  void testCharacterCountNoSpace() throws Exception {
    // Test character count excluding spaces
    TextProcessor processor = new TextProcessor(testFile.getAbsolutePath());

    long expectedCharCountNoSpace = "Helloworld.\nThisisatest.".length();
    assertEquals(
        expectedCharCountNoSpace,
        (processor.getCharCountNoSpace() + processor.getLineCount() - 1),
        "Character count without space should match");
  }
}
