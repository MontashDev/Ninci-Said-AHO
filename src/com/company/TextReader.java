package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TextReader {
    private String word;

    public TextReader(String word){
        this.word=word;
    }

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

    public void printText(List<String> records){
        for(String word : records) {
            System.out.println(word);
        }
    }

    public void occurence (String search, List<String> records){
        int count=0;
        String[] words;
        for(String searchword: records) {
            words=searchword.split(" ");
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