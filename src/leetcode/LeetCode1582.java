package leetcode;

/**
 * @author 86152
 */
public class LeetCode1582 {
    public int numSpecial(int[][] mat) {
        int[] rows = new int[mat.length];
        int[] columns = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    rows[i] ++;
                }
            }
        }
        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[j][i] == 1) {
                    columns[i] ++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (rows[i] == 1 && columns[j] == 1 && mat[i][j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
