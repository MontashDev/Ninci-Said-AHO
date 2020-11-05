package com.company;

import javax.swing.text.JTextComponent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextReader {
    private String word;

    public TextReader(String word){
        this.word=word;
    }


    //test if the file is read
    public List<String> readFile(String filepath){

        List<String> records = new ArrayList();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null){
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filepath);
            e.printStackTrace();
            return null;
        }
    }

    //display the file's text in the terminal
    /*public void printText(List<String> records){
        for(String word : records) {
            System.out.println(word);
        }
    }*/

    //reads file text for Swing
    static void readin(String fn, JTextComponent pane) {

        try {
            FileReader fr = new FileReader(fn);
            pane.read(fr, null);
            fr.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }

    //Test number of occurence of string search
    public void occurence (String search, List<String> records){

        int count=0;
        String[] words;
        for(String searchword: records) {
            words = searchword.split(" ");
            for(String find: words) {
                if (find.equals(search))
                    count++;
            }
        }
        if (count!=0)
            System.out.println("The word '"+search+"' is present "+count+" times.");
        else
            System.out.println("The word '"+search+"' isn't in the file.");
    }
}