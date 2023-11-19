import ca.uwo.cs2212.group2.model.Word;
import ca.uwo.cs2212.group2.model.CorrectionSuggestions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;


public class WordTest {
    
    @Test 
    public void testWordContent(){
        Word w1 = new Word("apple");
        Assertions.assertEquals("apple",w1.getContent());
        w1.setContent("orange");
        Assertions.assertEquals("orange",w1.getContent());
    }

    @Test 
    public void testWordCorrect(){
        Word word = new Word("example");
        Assertions.assertTrue(word.isWrong());
        word.setCorrect(false);
        Assertions.assertFalse(word.isWrong());
    }

    @Test
    public void testIsBegining() {
        Word word = new Word("example");
        Assertions.assertFalse(word.isBegining());
        word.setBegining();
        Assertions.assertTrue(word.isBegining());
    }

    @Test
    public void testSetOption() {
        Word word = new Word("example");

        // Add options
        word.setOption("correction1", 1);
        word.setOption("correction2", 2);

        // Add more options (should not exceed 4)
        word.setOption("correction3", 3);
        word.setOption("correction4", 4);
        word.setOption("correction5", 5);

        // Check that only 4 options are added
        Assertions.assertEquals(4, word.getOption().length);
    }

    @Test
    public void testGetOption() {
        Word word = new Word("example");

        // Add options
        word.setOption("correction1", 1);
        word.setOption("correction2", 2);

        // Check that options are retrieved correctly
        String[] options = word.getOption();
        Assertions.assertEquals("correction1", options[0]);
        Assertions.assertEquals("correction2", options[1]);
        Assertions.assertNull(options[2]);
        Assertions.assertNull(options[3]);
    }


}