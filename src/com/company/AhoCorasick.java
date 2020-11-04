package com.company;

public class AhoCorasick {

    //create a table of nodes and a a count for the nodes
    State[] states;
    int nodeCount;

    //initialize the structure and her root
    public AhoCorasick() {

        states = new State[1000];
        //create the root with no father
        states[0] = new State();
        states[0].suffix = 0;
        states[0].father = -1;
        nodeCount = 1;
    }

    //create the automata according to the string(s) entered
    public void createAutomata(int c, int current, char cara){
        //if the current node don't have any children, create him one, link his father and increment variables
        if (states[current].children[c] == -1) {
            states[nodeCount] = new State();
            states[nodeCount].father = current;
            states[nodeCount].fatherChar = cara;
            states[current].children[c] = nodeCount;
            nodeCount++;
        }
    }

    //add a string to search in the text
    public void searchString(String s) {

        int current = 0;
        //for each letter of the word
        for (char cara : s.toCharArray()) {
            int c = cara - 'a';

            createAutomata(c, current, cara);

            //modify the current variable
            current = states[current].children[c];
        }
        //create a final state in the automata also called "leaf"
        states[current].isLeaf = true;
    }

    //find the longest suffix to find another way in the automata
    public int findSuffix(int nodeIndex) {

        State state = states[nodeIndex]; //create a new node to compare the value and return the suffix
        if (state.suffix == -1)
            state.suffix = state.father == 0 ? 0 : findTransition(findSuffix(state.father), state.fatherChar);
        return state.suffix;
    }

    //create the "hidden transition" in the automata
    public int findTransition(int nodeIndex, char car) {

        int c = car - 'a';
        State state = states[nodeIndex]; //create a new node to compare the value and return the transitions
        if (state.transitions[c] == -1)
            state.transitions[c] = state.children[c] != -1 ? state.children[c]
                    : (nodeIndex == 0 ? 0 : findTransition(findSuffix(nodeIndex), car));
        return state.transitions[c];
    }
}