package ca.uwo.cs2212.group2.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class TextProcessor {
	static BufferedReader text = null;
	List<String> lines;
	private int lineCount;
	private int wordCount;
	private int charCountWithSpace;
	private int charCountNoSpace;
	List<Word> words = new ArrayList<Word>();
	
	public TextProcessor(String fileName) throws FileNotFoundException {
	
		try {
			//System.out.println("File: "+ fileName);
			
			lines = this.parse(fileName);
			
			//System.out.println("Line count: "+ lineCount+"\nWord Count: " + this.wordCount);
			
			//System.out.println("!-----------------!");
			
			//System.out.println("-----"+this.charCountWithSpace+"-----");
			
			//System.out.println("\n");
			
			//for (Word word: this.words) {
				
				
				//System.out.println(word.getContent());
			//}
			this.wordCount = lines.size();
			//System.out.println("--------------");
			wordsAfterPeriod();
			//System.out.println();
			
			//for (Word word: this.words) {
				//System.out.println("-");
				//System.out.println(word.getContent());
			//}
		}catch (Exception e) {
			System.out.println("Error: "+e);
		}
		
	}
	
	public List<String> parse(String fileName2){
		List<String> words = new ArrayList<String>();
		this.lineCount = 0;
		try (Scanner scan = new Scanner(new File(fileName2))){
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				this.lineCount++;
				this.charCountNoSpace += getNumCharNoSpace(line);
				//System.out.println("Char count = "+line.length());
				this.charCountWithSpace += line.length();
				//System.out.println("Line: "+ line);
				
				
				String[] lineWords = line.split("\\s+|,|\\;|\\(|\\)|\\{|\\}|\\:");
				
				
				
				for (String word : lineWords) {
					if (word != "") {
						
						
						words.add(word);
						Word newWord = new Word(word);
						this.words.add(newWord);
						this.wordCount++;
						
					}
					
				}
			}
			
			
		}catch(Exception e) {
			System.out.println("Error in parse: "+e);
		}
		return words;
	}
	
	private long getNumCharNoSpace(String line) {
		
		return line.chars().filter(c -> !Character.isWhitespace(c)).count();
		
	}
	
	public void wordsAfterPeriod() {
        boolean afterPeriod = false;

        for (Word word : this.words) {
            if (afterPeriod) {
            	// change words instance variable to True
                //System.out.println("Capital: "+word.getContent());
                afterPeriod = false; // Reset after printing
            }

            if (word.getContent().charAt(word.getContent().length()-1) == '.' || word.getContent().charAt(word.getContent().length()-1) == '!' || word.getContent().charAt(word.getContent().length()-1) == '?') {
            	
            	afterPeriod = true;
            	word.setContent(word.getContent().substring(0,(word.getContent().length() - 1 )));
            }
        }
    }
	
	private void setLineCount(int numLines) {
		
		this.lineCount = numLines;
		
	}
	
	private void setWordCount(int numWords) {
		
		this.wordCount = numWords;
		
	}
		
	private void setCharCountWithSpace(int numChars) {
	
		this.charCountWithSpace = numChars;
	
	}
	
	private int getCharCountWithSpace() {
		
		return this.charCountWithSpace;
		
	}
	
	private void setCharCountNoSpace(int numChars) {
		
		this.charCountNoSpace = numChars;
		
		
	}
	
	private int getCharCountNoSpace() {
		
		return this.charCountNoSpace;

	}
	
	
	private int getLineCount() {
	
		return lineCount;
	
	}
	
	private int getWordCount() {
	
		return wordCount;
	
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		TextProcessor file = new TextProcessor("test2.txt");
		System.out.println(file.getCharCountNoSpace());
		System.out.println(file.getCharCountWithSpace());
	}

}
