package leetcode;

class TrieNode {

    TrieNode[] children = new TrieNode[26];
    boolean isLast = false;
    String val;

    public TrieNode() {
        isLast = false;
        for(int i = 0; i<children.length; i++)
            children[i]= null;
    }

    public void insert(String val) {
        TrieNode tmp = this;
        for(int i = 0; i<val.length(); i++) {
            int index = val.charAt(i) - 'a';
            if(tmp.children[index] == null)
                tmp.children[index] = new TrieNode();
            tmp = tmp.children[index];
        }
        tmp.isLast = true;
    }

    public boolean search(String key) {
        TrieNode tmp = this;
        for(int i=0; i< key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if(tmp.children[index] == null) {
                return false;
            }
            tmp = tmp.children[index];
        }
        return tmp.isLast;
    }
}

public class Trie {

    public static void main(String[] args) {

        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};


        TrieNode root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            root.insert(keys[i]);

        // Search for different keys
        if(root.search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(root.search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(root.search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(root.search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);
    }
}
