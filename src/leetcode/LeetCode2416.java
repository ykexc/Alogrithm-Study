package leetcode;

/**
 * @author 86152
 */
public class LeetCode2416 {

    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }
        var ans = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            ans[i] = trie.getSum(words[i]);
        }
        return ans;
    }

    static class Trie {
        static class TrieNode {
            TrieNode[] trieNodes = new TrieNode[26];
            int prefix;
        }

        TrieNode trieNode = new TrieNode();

        void insert(String s) {
            var temp = this.trieNode;
            for (int i = 0; i < s.length(); i++) {
                int x = s.charAt(i) - 'a';
                if (temp.trieNodes[x] == null) {
                    temp.trieNodes[x] = new TrieNode();
                }
                temp.trieNodes[x].prefix++;
                temp = temp.trieNodes[x];
            }
        }

        int getSum(String s) {
            int ans = 0;
            var temp = this.trieNode;
            for (int i = 0; i < s.length(); i++) {
                int x = s.charAt(i) - 'a';
                ans += temp.trieNodes[x].prefix;
                temp = temp.trieNodes[x];
            }
            return ans;
        }
    }

}
