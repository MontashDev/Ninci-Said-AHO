package com.company;

import java.util.Arrays;

public class Node {

    static final int ALPHABET_SIZE = 26;

    int father;
    char parentChar;
    boolean isLeaf; //final automata state
    int suffix = -1;

    //fill all the childrens and transitions by the value -1 for saying there are no one for now
    int[] children = new int[ALPHABET_SIZE];{
        Arrays.fill(children, -1);
    }
    int[] transitions = new int[ALPHABET_SIZE];{
        Arrays.fill(transitions, -1);
    }
}
