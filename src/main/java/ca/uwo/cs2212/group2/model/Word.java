package ca.uwo.cs2212.group2.model;

import java.util.*;

public class Word{
    private String content;
    private boolean correct;
    private boolean beginingOfSentence;
    private ArrayList<CorrectionSuggestions> options;

    public Word(String content){
        this.content = content;
        this.correct = true;
        this.beginingOfSentence = false;
        this.options = new ArrayList<CorrectionSuggestions>();

    }

    public boolean isBegining(){
        return beginingOfSentence;
    }

    public void setBegining(){
        this.beginingOfSentence = true;
    }

    public boolean isWrong(){
        return correct;
    }

    public void setCorrect(boolean x){
        this.correct = x;
    }

    public void setOption(String content, int distance){
        CorrectionSuggestions option = new CorrectionSuggestions(content, distance);
        if (options.size()<4){
            options.add(option);
        }
    }

    public String[] getOption(){
        String[] arr = new String[4];
        for(int i=0; i<options.size(); i++){
            arr[i]=options.get(i).getWord();
        }
        return arr;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String newContent){
        this.content = newContent;
    }

}