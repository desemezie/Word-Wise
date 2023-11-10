//just a test file

import java.util.*;
public class main{
    public static void main(String[] args){
        CorrectionSuggestions cs1 = new CorrectionSuggestions("happy", 4);
        CorrectionSuggestions cs2 = new CorrectionSuggestions("FROG", 2);
        System.out.println(cs1.getWord());
        System.out.println(cs2.getDistance());
        cs1.setWord("bye");
        cs2.setDistance(0);
        System.out.println(cs1.getWord());
        System.out.println(cs2.getDistance());
        Word w1 = new Word("apple");
        Word w2 = new Word("pear");
        System.out.println(w1.getContent());
        w1.setContent("dog");
        System.out.println(w1.getContent());
        w2.setOption("star", 4);
        w2.setOption("moon", 0);
        System.out.println(Arrays.toString(w2.getOption()));


    
    }

}
