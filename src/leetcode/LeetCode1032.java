package leetcode;

/**
 * @author 86152
 */
public class LeetCode1032 {

    TireNode root = new TireNode();
    StringBuilder sb = new StringBuilder();

    static class TireNode {
        boolean isEnd;
        TireNode[] tireNodes = new TireNode[26];
    }

    public void addWord(String s) {
        TireNode temp = this.root;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (temp.tireNodes[s.charAt(i) - 'a'] == null) {
                temp.tireNodes[s.charAt(i) - 'a'] = new TireNode();
            }
            temp = temp.tireNodes[s.charAt(i) - 'a'];
        }
        temp.isEnd = true;
    }

    public LeetCode1032(String[] words) {
        for (String s : words) {
            addWord(s);
        }
    }

    public boolean query(char letter) {
        var temp = this.root;
        sb.append(letter);
        var end = sb.length() <= 200 ? 0 : sb.length() - 200;
        for (int i = sb.length() - 1; i >= end; i--) {
            if (temp.tireNodes[sb.charAt(i) - 'a'] == null) {
                return false;
            } else if (temp.tireNodes[sb.charAt(i) - 'a'].isEnd) {
                return true;
            }
            temp = temp.tireNodes[sb.charAt(i) - 'a'];
        }
        return temp.isEnd;
    }
}
