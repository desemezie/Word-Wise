import ca.uwo.cs2212.group2.model.CorrectionSuggestions;

import java.beans.Transient;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;


public class CorrectionSuggestionsTest {
    @Test 
    public void testWord(){
        CorrectionSuggestions cs = new CorrectionSuggestions("star",3);
        Assertions.assertEquals("star",cs.getWord());
        cs.setWord("run");
        Assertions.assertEquals("run",cs.getWord());
    }

    @Test
    public void testDistance(){
        CorrectionSuggestions cs = new CorrectionSuggestions("star",3);
        Assertions.assertEquals(3,cs.getDistance());
        cs.setDistance(5);
        Assertions.assertEquals(5,cs.getDistance());
    }
}
