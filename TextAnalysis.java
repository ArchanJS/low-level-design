// Text Analysis Tool: Design a program that takes a string of text as input and performs basic analysis on it. This could include calculating the number of words, characters, unique words, average word length, frequency of occurrence of certain letters or words, etc.

package lld;

import java.util.*;

public class TextAnalysis {
    private String text;
    private HashMap<String, Integer> wordFrequency;
    private HashMap<Character, Integer> letterFrequency;
    private String[] words;

    public TextAnalysis(String textMessage){
        text=textMessage;
        wordFrequency=new HashMap<>();
        letterFrequency=new HashMap<>();
        words = text.split("\\s");
        for(String word:words) wordFrequency.put(word, wordFrequency.getOrDefault(word, 0));
        for(int i=0;i<text.length();i++) letterFrequency.put(text.charAt(i), letterFrequency.getOrDefault(i, 0));
    }
    public void totalNoOfWords(){
        System.out.println("Total no. of words: "+words.length);
    }
    public void totalNoOfCharacters(){
        System.out.println("Total no. of characters: "+text.length());
    }
    public void printUniqueKeys(){
        System.out.println("Printing all the unique words:");
        for(String word:wordFrequency.keySet()){
            System.out.println(word);
        }
    }
    public void calculateAverageWordLength(){
        int totalNoOfWords=0,totalWordLength=0;
        for(Map.Entry<String, Integer> entry:wordFrequency.entrySet()) {
            totalNoOfWords+=entry.getValue();
            totalWordLength+=(entry.getValue()*entry.getKey().length());
        }
        System.out.println("Average length of word: "+Math.round(totalWordLength/totalNoOfWords));
    }
    public void calculateFrequencyOfWord(String word){
        System.out.println("Frequency of word "+word+"is: "+wordFrequency.getOrDefault(word,0));
    }
    public void calculateFrequencyOfLetter(char letter){
        System.out.println("Frequency of letter"+letter+"is: "+letterFrequency.getOrDefault(letter,0));
    }
    public void analyzeText(String operation, Optional<String> wordToBeSearched, Optional<Character> letterToBeSearched ) throws IllegalArgumentException  {
        operation=operation.toLowerCase();
        switch(operation){
            case "totalwords":
            totalNoOfWords();
            break;
            case "totalcharacters":
            totalNoOfCharacters();
            break;
            case "uniquekey":
            totalNoOfWords();
            break;
            case "averagewordlength":
            calculateAverageWordLength();
            break;
            case "frequencyofword":
            if(wordToBeSearched.isPresent() && wordToBeSearched.get().length()>0) calculateFrequencyOfWord(wordToBeSearched.get());
            else throw new IllegalArgumentException("Please enter the word you want to search");
            break;
            case "frequencyofletter":
            if(letterToBeSearched.isPresent()) calculateFrequencyOfWord(wordToBeSearched.get());
            else throw new IllegalArgumentException("Please enter the character you want to search");
            break;
            default:
            break;
            
        }
    }

}
