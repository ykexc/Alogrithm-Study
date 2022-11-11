package leetcode;


public class LeetCode521 {

    public int findLUSlength(String a, String b) {
        return a.length() == b.length() ? a.equals(b) ? -1 : a.length() : Math.max(a.length(), b.length());
    }

}
