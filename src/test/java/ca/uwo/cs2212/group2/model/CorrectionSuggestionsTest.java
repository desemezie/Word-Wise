package test.java.ca.uwo.cs2212.group2.model;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;


public class CorrectionSuggestionsTest {
    @Test 
    public void testCorrectionSuggestions(){
        CorrectionSuggestions cs = new CorrectionSuggestions("star",3);
        Assert.assertEquals("star",cs.getWord());
        Assert.assertEquals(3,cs.getDistance());



    }
}
