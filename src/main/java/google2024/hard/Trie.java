package google2024.hard;

import java.util.Arrays;
import java.util.LinkedList;

class TrieNode {

    String value;
    int level;
    TrieNode[] children = new TrieNode[26];

    public TrieNode(String val, int l) {
        this.value = val; this.level=l;
    }

    TrieNode get(int index) {
        return this.children[index];
    }
    void set(TrieNode node, int index) {
        this.children[index] = node;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "value='" + value + '\'' +
                ", level=" + level +
                ", children=" + Arrays.toString(children) +
                '}';
    }
}

public class Trie {
    TrieNode[] root = new TrieNode[26];

    void insert(String str) {
        int l=-1;
        TrieNode tmp = null;
        for (Character c : str.toCharArray()) {
            l++;
            String s = String.valueOf(c);
            int index = c % 26;
            if (tmp == null) {
                if (root[index] == null) {
                    root[index] = new TrieNode(s, l);
                }
                tmp = root[index];
                continue;
            }
            TrieNode trieNode = tmp.get(index);
            if (trieNode == null) {
                trieNode = new TrieNode(s, l);
                tmp.set(trieNode, index);
            }
            tmp = trieNode;
        }
    }

    void print() {
        LinkedList<TrieNode> list = new LinkedList<>();
        for(TrieNode t : root){
            if (t != null)
                list.add(t);
        }
        while (!list.isEmpty()) {
            TrieNode trieNode = list.remove(0);
            System.out.println(trieNode.value +"|"+trieNode.level);
            for(TrieNode node: trieNode.children) {
                if(node != null)
                    list.add(node);
            }
        }
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
        trie.insert("abd");
        trie.print();
    }
}
