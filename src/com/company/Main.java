package com.company;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws IOException {
        AhoCorasick ahoCorasick = new AhoCorasick();
        int choice;
        int numberOfWords = 0;
        Path filepath = Paths.get("C:\\Users\\HP\\IdeaProjects\\Ninci-Said-AHO\\src\\com\\company\\TexteCyrano.txt");
        List<String> replaced;
        int node = 0;
        do {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter a word to search : ");
            String enteredWord = scan.nextLine().toLowerCase().trim(); //Abstraction des majuscules afin de compter les occurences du mot
            ahoCorasick.searchString(enteredWord);
            numberOfWords++;
            Scanner choix = new Scanner(System.in);
            System.out.println("Do you want to enter another word ? :  1 -> yes  2 -> no");
            choice = choix.nextInt();
            Stream<String> lines= Files.lines(filepath);
            replaced = lines.map(line -> line.replaceAll(enteredWord, "@"+enteredWord+"@")).collect(Collectors.toList()); //ajout de "@" avant et apre le mot recherché
            Files.write(filepath, replaced);
            lines.close();
        }while(choice != 2);

        final JFrame frame = new JFrame("File Display");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        // Creation de la zone d'afffichage du texte pour l'interface graphique

        final JTextComponent textpane = new JTextArea();

        // Creation de la barre defilante pour le texte

        final JScrollPane pane = new JScrollPane(textpane);
        pane.setPreferredSize(new Dimension(600, 600));

        // creation selecteur fichier

        String cwd = System.getProperty("user.dir");
        final JFileChooser jfc = new JFileChooser(cwd);

        JButton filebutton = new JButton("Choose File");
        filebutton.addActionListener(e -> {
            if (jfc.showOpenDialog(frame) !=
                    JFileChooser.APPROVE_OPTION)
                return;
            File f = jfc.getSelectedFile();

            // Lecture du fichier
            frame.setCursor(Cursor.getPredefinedCursor(
                    Cursor.WAIT_CURSOR));
            TextReader.readin(f.toString(), textpane);

        });

        JPanel buttonpanel = new JPanel();
        buttonpanel.add(filebutton);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add("North", buttonpanel);
        panel.add("Center", pane);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);



        /*
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