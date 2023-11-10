public class CorrectionSuggestions{
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


}