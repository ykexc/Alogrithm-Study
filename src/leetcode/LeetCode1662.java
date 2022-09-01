package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * @author 86152
 */
public class LeetCode1662 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return Arrays.stream(word2).reduce(String::concat).orElse(null).equals(Arrays.stream(word1).reduce(String::concat).orElse(null));
    }
    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        return String.join("",word1).equals(String.join("",word2));
    }
}
