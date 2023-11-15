package ca.uwo.cs2212.group2.model.TextProcessor;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
//import java.util.Arrays;
import java.util.Scanner;
import java.util.ListIterator;


public class TextProcessor {
	static BufferedReader text = null;
	List<String> lines;
	private int lineCount;
	private int wordCount;
	private int charCountWithSpace;
	private int charCountNoSpace;
	List<String> lineWords = new ArrayList<String>();
	
	public TextProcessor(String fileName) throws FileNotFoundException {
	
		try {
			System.out.println("File: "+ fileName);
			lines = this.parse(fileName);
			System.out.println("Line count: "+ lineCount+"\nWord Count: " + this.wordCount);
			
			System.out.println("!-----------------!");
			System.out.println("-----"+this.charCountWithSpace+"-----");
			System.out.println("\n");
			for (String line: this.lineWords) {
				// make word object here
				
				System.out.println(line);
			}
			this.wordCount = lines.size();
			System.out.println("--------------");
			printWordsAfterPeriod();
			System.out.println();
			for (String line: this.lineWords) {
				// make word object here
				
				System.out.println(line);
			}
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
				System.out.println("Char count = "+line.length());
				this.charCountWithSpace += line.length();
				
				
				
				String[] lineWords = line.split("\\s+|,|\\;|\\(|\\)|\\{|\\}|\\:");
				
				
				
				for (String word : lineWords) {
					if (word != "") {
						words.add(word);
						this.lineWords.add(word);
						this.wordCount++;
						
					}
					
				}
			}
			
			
		}catch(Exception e) {
			System.out.println("Error in parse: "+e);
		}
		return words;
	}
	
	public void printWordsAfterPeriod() {
        boolean afterPeriod = false;

        for (String word : this.lineWords) {
            if (afterPeriod) {
            	// change words instance variable to True
                System.out.println("Capital: "+word);
                afterPeriod = false; // Reset after printing
            }

            if (word.charAt(word.length()-1) == '.' || word.charAt(word.length()-1) == '!' || word.charAt(word.length()-1) == '?') {
            	
            	afterPeriod = true;
            	// word.setContent(word.substring(0,(word.length -1));
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

	}

}
