package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AhoCorasick ahoCorasick = new AhoCorasick();
        int choice;
        int numberOfWords = 0;
        do {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter a word to search : ");
            String enteredWord = scan.nextLine();
            ahoCorasick.searchString(enteredWord);
            numberOfWords++;

            Scanner choix = new Scanner(System.in);
            System.out.println("Do you want to enter another word ? :  1 -> yes  2 -> no");
            choice = choix.nextInt();

        }while(choice == 1);

        String str = null;
        TextReader text = new TextReader(null);
        text.printText(text.readFile("C:\\Users\\thoma\\IdeaProjects\\Ninci-Said-AHO\\src\\com\\company\\TexteCyrano.txt"));

        String s = "tabcbc"; //fichier doc

        int node = 0;
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) { //s.length sera la longueur du fichier
            node = ahoCorasick.findTransition(node, s.charAt(i));
            if (ahoCorasick.states[node].isLeaf)
                positions.add(i);  //autre système à mettre en place
        }
        System.out.println(positions);

        /*Scanner saisieUtilisateur = new Scanner(System.in);
        System.out.println("Enter word : ");
        String str = saisieUtilisateur.next();
        TextReader text = new TextReader(str);
        text.printText(text.readFile("C:\\Users\\HP\\Downloads\\TexteCyrano.txt"));
        text.occurence(str,text.readFile("C:\\Users\\HP\\Downloads\\TexteCyrano.txt"));
        */
    }
}