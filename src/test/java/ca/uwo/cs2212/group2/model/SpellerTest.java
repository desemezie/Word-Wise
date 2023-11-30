package ca.uwo.cs2212.group2.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import ca.uwo.cs2212.group2.model.Word;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpellerTest {

    private Speller speller;

    @BeforeAll
    public void setUp() {
        speller = Speller.getInstance();

    }

    @Test
    public void testGetInstance() {
        Assertions.assertNotNull(speller);
        Assertions.assertSame(speller, Speller.getInstance());
    }

    @Test
    public void testGetAllWords() {
        Assertions.assertNotNull(speller.getAllwords());
        Assertions.assertEquals(0, speller.getAllwords().size());
    }

    @Test
    public void testGetWrongWords() {
        Assertions.assertNotNull(speller.getWrongWords());
        Assertions.assertEquals(0, speller.getWrongWords().size());
    }

    @Test
    public void testGetDict() {
        Assertions.assertNotNull(speller.getDict());
    }

    @Test
    public void testGetStats() {
        int[] stats = speller.getStats();
        Assertions.assertNotNull(stats);
        Assertions.assertEquals(4, stats.length);
    }

    /**
     * 
     */
    @Test
    public void testSpellcheck() {
        speller.spellcheck("This is a tett sentence.");
        List<Word> allWords = speller.getAllwords();
        List<Word> incorrectWords = speller.getWrongWords();
        Assertions.assertEquals(1, incorrectWords.size());
        Assertions.assertNotNull(incorrectWords);
        Word word = incorrectWords.remove(0);
        String string = word.getContent();
        Assertions.assertNotNull(allWords);
        Assertions.assertEquals(5, allWords.size());
        Assertions.assertEquals("tett", string);

    }
}
