package Stupifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Text { //a collection of sentences
    //collection of tokens with terminal punctuation
    //consider edge cases
    
    private ArrayList<Sentence> sentences = new ArrayList<>();
    private String name;
    private String rawText;
    private int index;
    
    public Text(String fileName){ //constructor..
        this.index = 0;
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            System.out.println("file read");
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = input.readLine()) != null ) {
                sb.append(s);
            }
            rawText = sb.toString();
            System.out.println("Rawtext: " + rawText);
            //this is rough, splits on period, exclamation, question mark
            String[] rawSentences = rawText.split("(?<=(!+|\\.+|\\?+))");
            for (int i = 0; i < rawSentences.length; i++){
                rawSentences[i] = rawSentences[i].trim(); //trim
                System.out.println(i + ": " + rawSentences[i]);
            }
            //if it is one sentence with no terminals, consider that as well
            Sentencizer sentencizer = new Sentencizer(rawSentences);
            System.out.println("Sentencizer created");
            
            while (sentencizer.hasNext()){
                System.out.println("adding sentence " + sentencizer.getIndex());
                sentences.add(sentencizer.nextSentence());
            }  
        }
        catch (IOException ex)
        {
            System.out.println("There was an error in BufferedReader, check input file(s)");
            System.exit(0);
        }  
    }
    
    public String getName(){ //get name of text
        return name;
    }
    
    public void setName(String s){
        this.name = s;
    }
    
    public int getIndex(){
        return index;
    }
    
    public void printRawSentences(){
        for (int i = 0; i < sentences.size(); i++){
            System.out.println(sentences.get(i));
        }
    }
    
    public void printTokens(){
        for (int i = 0; i < sentences.size(); i++){
            ArrayList<Token> tokens =  sentences.get(i).getTokens();
            for (int j = 0; j < tokens.size(); j++){
                System.out.println(tokens.get(j) + " ");
            }
        }
    }
    public void printNewSentence(){
        for (int i = 0; i < sentences.size(); i++){
            ArrayList<Token> tokens =  sentences.get(i).getTokens();
            for (int j = 0; j < tokens.size(); j++){
                System.out.print(tokens.get(j) + " ");
            }
        }
    }
    public Sentence getSentence(int i){
        return sentences.get(i);
    }
    
    public ArrayList<Sentence> getSentences(){
        return sentences;
    }
    public Sentence getNextSentence(){
        return sentences.get(index++);
    }
    
    public String getRawText(){
        return rawText;
    } 
}
