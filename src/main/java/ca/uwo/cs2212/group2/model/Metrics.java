package ca.uwo.cs2212.group2.model;

import com.google.gson.Gson;

public class Metrics {

    // Document metrics
    private int numberOfCharacters;
    private int numberOfLines;
    private int numberOfWords;

    // Spelling metrics
    private int totalNumberOfErrors;
    private int currentNumberOfErrors;
    private int totalNumberOfAcceptedCorrections;

    private int totalNumberOfManualCorrections;

    // Dictionary metrics
    private int numberOfWordsInUserDictionary;

    /**
     * Constructor with default values of 0.
     */
    public Metrics() {
        this.numberOfCharacters = 0;
        this.numberOfLines = 0;
        this.numberOfWords = 0;
        this.totalNumberOfErrors = 0;
        this.currentNumberOfErrors = 0;
        this.totalNumberOfAcceptedCorrections = 0;
        this.totalNumberOfManualCorrections = 0;
        this.numberOfWordsInUserDictionary = 0;
    }

    /**
     * Constructor with known values for each parameter.
     *
     * @param numberOfCharacters
     * @param numberOfLines
     * @param numberOfWords
     * @param totalNumberOfErrors
     * @param currentNumberOfErrors
     * @param totalNumberOfAcceptedCorrections
     * @param totalNumberOfManualCorrections
     * @param numberOfWordsInUserDictionary
     */
    public Metrics(int numberOfCharacters, int numberOfLines, int numberOfWords, int totalNumberOfErrors,
                   int currentNumberOfErrors, int totalNumberOfAcceptedCorrections,
                   int totalNumberOfManualCorrections, int numberOfWordsInUserDictionary) {
        this.numberOfCharacters = numberOfCharacters;
        this.numberOfLines = numberOfLines;
        this.numberOfWords = numberOfWords;
        this.totalNumberOfErrors = totalNumberOfErrors;
        this.currentNumberOfErrors = currentNumberOfErrors;
        this.totalNumberOfAcceptedCorrections = totalNumberOfAcceptedCorrections;
        this.totalNumberOfManualCorrections = totalNumberOfManualCorrections;
        this.numberOfWordsInUserDictionary = numberOfWordsInUserDictionary;
    }

    /**
     * Factory method that creates a Metrics object from a JSON string.
     * 
     * @param json
     * @return the Metrics object created from the JSON string.
     */
    public static Metrics fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Metrics.class);
    }

    /**
     * Converts the Metrics object to a JSON string.
     * 
     * @return the JSON version of the Metrics object as a String.
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
    @Override
    public String toString() {
        return "Metrics{" +
                "numberOfCharacters=" + numberOfCharacters +
                ", numberOfLines=" + numberOfLines +
                ", numberOfWords=" + numberOfWords +
                ", totalNumberOfErrors=" + totalNumberOfErrors +
                ", currentNumberOfErrors=" + currentNumberOfErrors +
                ", totalNumberOfAcceptedCorrections=" + totalNumberOfAcceptedCorrections +
                ", totalNumberOfManualCorrections=" + totalNumberOfManualCorrections +
                ", numberOfWordsInUserDictionary=" + numberOfWordsInUserDictionary +
                '}';
    }

    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }

    public void setNumberOfCharacters(int numberOfCharacters) {
        this.numberOfCharacters = numberOfCharacters;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public int getTotalNumberOfErrors() {
        return totalNumberOfErrors;
    }

    public void setTotalNumberOfErrors(int totalNumberOfErrors) {
        this.totalNumberOfErrors = totalNumberOfErrors;
    }

    public int getCurrentNumberOfErrors() {
        return currentNumberOfErrors;
    }

    public void setCurrentNumberOfErrors(int currentNumberOfErrors) {
        this.currentNumberOfErrors = currentNumberOfErrors;
    }

    public int getTotalNumberOfAcceptedCorrections() {
        return totalNumberOfAcceptedCorrections;
    }

    public void setTotalNumberOfAcceptedCorrections(int totalNumberOfAcceptedCorrections) {
        this.totalNumberOfAcceptedCorrections = totalNumberOfAcceptedCorrections;
    }

    public int getTotalNumberOfManualCorrections() {
        return totalNumberOfManualCorrections;
    }

    public void setTotalNumberOfManualCorrections(int totalNumberOfManualCorrections) {
        this.totalNumberOfManualCorrections = totalNumberOfManualCorrections;
    }

    public int getNumberOfWordsInUserDictionary() {
        return numberOfWordsInUserDictionary;
    }

    public void setNumberOfWordsInUserDictionary(int numberOfWordsInUserDictionary) {
        this.numberOfWordsInUserDictionary = numberOfWordsInUserDictionary;
    }
}
