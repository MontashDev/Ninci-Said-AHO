package com.company;

public class AhoCorasick {
    //create a table of nodes and a a count for the nodes
    Node[] nodes;
    int nodeCount;

    //initialize the structure and her root
    public AhoCorasick() {

        nodes = new Node[1000];
        //create the root with no father
        nodes[0] = new Node();
        nodes[0].suffix = 0;
        nodes[0].father = -1;
        nodeCount = 1;
    }

    //create the automata according to the string(s) entered
    public void createAutomata(int c, int current, char cara){
        //if the current node don't have any children, create him one, link his father and increment variables
        if (nodes[current].children[c] == -1) {
            nodes[nodeCount] = new Node();
            nodes[nodeCount].father = current;
            nodes[nodeCount].parentChar = cara;
            nodes[current].children[c] = nodeCount++;
        }
    }

    //add a string to search in the text
    public void addString(String s) {

        int current = 0;
        //for each letter of the word
        for (char cara : s.toCharArray()) {
            int c = cara - 'a';

            createAutomata(c, current, cara);

            //modify the current variable
            current = nodes[current].children[c];
        }
        //create a final state in the automata also called "leaf"
        nodes[current].isLeaf = true;
    }

    //find the longest suffix to find another way in the automata
    public int findSuffix(int nodeIndex) {

        Node node = nodes[nodeIndex]; //create a new node to compare the value and return the suffix
        if (node.suffix == -1)
            node.suffix = node.father == 0 ? 0 : findTransition(findSuffix(node.father), node.parentChar);
        return node.suffix;
    }

    //create the "hidden transition" in the automata
    public int findTransition(int nodeIndex, char car) {

        int c = car - 'a';
        Node node = nodes[nodeIndex]; //create a new node to compare the value and return the transitions
        if (node.transitions[c] == -1)
            node.transitions[c] = node.children[c] != -1 ? node.children[c]
                    : (nodeIndex == 0 ? 0 : findTransition(findSuffix(nodeIndex), car));
        return node.transitions[c];
    }
}