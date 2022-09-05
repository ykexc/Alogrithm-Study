package leetcode;

/**
 * @author 86152
 */
public class TirePro {

    static class TireNode {
        TireNode[] tireNodes = new TireNode[26];
        int countEnd;
        int countPrefix;
    }

    TireNode root = new TireNode();

    public void insert(String word) {
        var temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.tireNodes[word.charAt(i) - 'a'] == null) {
                temp.tireNodes[word.charAt(i) - 'a'] = new TireNode();
            }
            temp.tireNodes[word.charAt(i) - 'a'].countPrefix++;
            temp = temp.tireNodes[word.charAt(i) - 'a'];
        }
        temp.countEnd++;
    }

    public int countWordsEqualTo(String word) {
        var temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.tireNodes[word.charAt(i) - 'a'] == null) {
                return 0;
            }
            temp = temp.tireNodes[word.charAt(i) - 'a'];
        }
        return temp.countEnd;
    }

    public int countWordsStartingWith(String prefix) {
        var temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (temp.tireNodes[prefix.charAt(i) - 'a'] == null) {
                return 0;
            }
            temp = temp.tireNodes[prefix.charAt(i) - 'a'];
        }
        return temp.countPrefix;
    }

    public void erase(String word) {
        var counts = countWordsEqualTo(word);
        var temp = root;
        for (int i = 0; i < word.length(); i++) {
            temp.tireNodes[word.charAt(i) - 'a'].countPrefix--;
            temp = temp.tireNodes[word.charAt(i) - 'a'];
        }
        temp.countEnd--;
    }
}
