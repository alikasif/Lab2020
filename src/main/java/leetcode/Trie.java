package leetcode;

import java.util.Arrays;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isLast = false;
    String val;

    public TrieNode() {
        isLast = false;
        Arrays.fill(children, null);
    }
}

public class Trie {
    TrieNode root = null;
    public Trie(){
       root = new TrieNode();
    }

    public void insert(String val) {
        TrieNode tmp = root;
        for(int i = 0; i<val.length(); i++) {
            int index = val.charAt(i) - 'a';
            if(tmp.children[index] == null)
                tmp.children[index] = new TrieNode();
            tmp = tmp.children[index];
        }
        tmp.isLast = true;
    }

    public boolean search(String key) {
        TrieNode tmp = root;
        for(int i=0; i< key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if(tmp.children[index] == null) {
                return false;
            }
            tmp = tmp.children[index];
        }
        return tmp.isLast;
    }

    public static void main(String[] args) {

        String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};

        Trie trie = new Trie();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            trie.insert(keys[i]);

        // Search for different keys
        if(trie.search("the"))
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(trie.search("these"))
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(trie.search("their"))
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(trie.search("thaw"))
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);
    }
}