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


    //fonction de test la lecture du fichier dans le terminal Intellij
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

    //fonction de test d'affichage du contenue du ficher dans le terminal Intellij
    public void printText(List<String> records){
        for(String word : records) {
            System.out.println(word);
        }
    }

    //fonction de lecture du contenue du fichier pour l'interface graphique swing
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

    //fonction de test du nombre d'occurence du mot recherché dans le fichier
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