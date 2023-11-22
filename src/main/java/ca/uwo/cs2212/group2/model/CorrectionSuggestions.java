package ca.uwo.cs2212.group2.model;
import java.util.PriorityQueue;

public class CorrectionSuggestions implements Comparable<CorrectionSuggestions>{
    private String word;
    private int distance;

    public CorrectionSuggestions(String word, int distance){
        this.word = word;
        this.distance = distance;
    }

    public String getWord(){
        return word;
    }

    public int getDistance(){
        return distance;
    }

    public void setWord(String newWord){
        this.word = newWord;
    }

    public void setDistance(int newDistance){
        this.distance = newDistance;
    }

    // Comparator for the for priority queue least to greatest
	public int compareTo(CorrectionSuggestions o) {
		if(this.getDistance() > o.getDistance()) return 1;
		if(this.getDistance() < o.getDistance()) return -1;
		return 0;
	}


}