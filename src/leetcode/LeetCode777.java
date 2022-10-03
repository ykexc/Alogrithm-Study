package leetcode;

/**
 * @author 86152
 */
public class LeetCode777 {

    public boolean canTransform(String start, String end) {
        int i = 0, j = 0, n = start.length();
        while (i < n || j < n) {
            while ( i < n && start.charAt(i) == 'X') i++;
            while ( j < n && end.charAt(j) == 'X') j++;
            if (i == n || j == n) return i == j;
            if (start.charAt(i) != end.charAt(j)) return false;
            if (start.charAt(i) == 'L' && i < j) return false;
            if (start.charAt(i) == 'R' && i > j) return false;
            i++; j++;
        }
        return true;
    }

}
