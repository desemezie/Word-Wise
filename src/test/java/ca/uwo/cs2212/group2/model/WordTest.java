package test.java.ca.uwo.cs2212.group2.model;


import java.beans.Transient;

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



}