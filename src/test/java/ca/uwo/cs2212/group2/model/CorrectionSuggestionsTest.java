package test.java.ca.uwo.cs2212.group2.model;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;


public class CorrectionSuggestionsTest {
    @Test 
    public void testCorrectionSuggestions(){
        CorrectionSuggestions cs = new CorrectionSuggestions("star",3);
        Assertions.assertEquals("star",cs.getWord());
        Assertions.assertEquals(3,cs.getDistance());
        cs.setWord("run");
        cs.setDistance(1);
        Assertions.assertEquals("run",cs.getWord());
        Assertions.assertEquals(1,cs.getDistance());
    }
}
