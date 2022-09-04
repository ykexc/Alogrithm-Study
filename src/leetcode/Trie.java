package leetcode;

/**
 * @author 86152
 */
class Trie {
    static class TrieNode {
        boolean isEnd;
        TrieNode[] trieNodes = new TrieNode[26];
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode temp = this.root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.trieNodes[word.charAt(i) - 'a'] == null) {
                temp.trieNodes[word.charAt(i) - 'a'] = new TrieNode();
            }
            temp = temp.trieNodes[word.charAt(i) - 'a'];
        }
        temp.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode temp = this.root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.trieNodes[word.charAt(i) - 'a'] == null) {
                return false;
            }
            temp = temp.trieNodes[word.charAt(i) - 'a'];
        }
        return temp.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode temp = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            if (temp.trieNodes[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            temp = temp.trieNodes[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}
