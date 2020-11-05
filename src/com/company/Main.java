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
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) throws IOException {

        AhoCorasick ahoCorasick = new AhoCorasick();
        int choice;

        String path = "C:\\Users\\thoma\\IdeaProjects\\Ninci-Said-AHO2\\TexteCyrano.txt";
        Path filepath = Paths.get(path);
        List<String> replaced;

        do {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter a word to search : ");
            String enteredWord = scan.nextLine().toLowerCase().trim(); //Make abstraction of CAPS in order to count occurences
            ahoCorasick.searchString(enteredWord);

            TextReader text = new TextReader(enteredWord);
            text.occurence(enteredWord,text.readFile(path));
            Scanner choix = new Scanner(System.in);
            System.out.println("Do you want to enter another word ? :  1 -> yes  2 -> no");
            choice = choix.nextInt();

            Stream<String> lines= Files.lines(filepath);
            replaced = lines.map(line -> line.replaceAll(enteredWord, "[WORD]"+enteredWord+"[DETECTED]")).collect(Collectors.toList()); //add balise around the word
            Files.write(filepath, replaced);
            lines.close();

        }while(choice != 2);

        final JFrame frame = new JFrame("File Display");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Set Swing Interface
        final JTextComponent textpane = new JTextArea();

        // set scroll bar
        final JScrollPane pane = new JScrollPane(textpane);
        pane.setPreferredSize(new Dimension(600, 600));

        // set file chooser
        String cwd = System.getProperty("user.dir");
        final JFileChooser jfc = new JFileChooser(cwd);

        JButton filebutton = new JButton("Choose File");
        filebutton.addActionListener(e -> {
            if (jfc.showOpenDialog(frame) !=
                    JFileChooser.APPROVE_OPTION)
                return;
            File f = jfc.getSelectedFile();

            // read of the file
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

        //String s = "test";
        //findPositions(ahoCorasick, s);
    }

    /*public static void findPositions(AhoCorasick ahoCorasick, String s){

        int node = 0;
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            node = ahoCorasick.findTransition(node, s.charAt(i));
            if (ahoCorasick.states[node].isLeaf)
                positions.add(i);
        }
        System.out.println(positions);
    }*/
}