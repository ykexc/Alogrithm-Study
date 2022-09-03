package leetcode;

/**
 * @author 86152
 */
public class LeetCode12292 {
    public int maxSideLength(int[][] mat, int threshold) {
        int[][] grid = new int[mat.length + 1][mat[0].length + 1];
        for (int i = 1; i <= mat.length; i++) {
            for (int j = 1; j <= mat[0].length; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1] - grid[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int maxLen = Math.min(mat.length, mat[0].length);
        for (; maxLen >= 1 ; maxLen--) {
            for (int i = maxLen; i <= mat.length; i++) {
                for (int j = maxLen; j <= mat[0].length; j++) {
                    if (query(i,j,grid,maxLen) <= threshold) {
                        return maxLen;
                    }
                }
            }
        }
        return 0;
    }

    private int query(int i, int j, int[][] grid,int maxLen) {
        int a = i - maxLen + 1;
        int b = j - maxLen + 1;
        return grid[i][j] + grid[a - 1][b - 1] - grid[i][b - 1] - grid[a - 1][j];
    }
}
