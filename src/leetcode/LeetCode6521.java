package leetcode;

/**
 * 前缀和，乘法
 */
public class LeetCode6521 {

    int mod = (int) 1e9 + 7;

    public int countPalindromes(String s) {
        var cs = s.toCharArray();
        var n = cs.length;
        var ans = 0L;
        var cntP = new int[n + 1][10];
        var cntPos = new int[n + 1][10];
        for (var i = 1; i <= n; i++) {
            System.arraycopy(cntP[i - 1], 0, cntP[i], 0, 10);
            cntP[i][cs[i - 1] - '0']++;
        }
        for (var i = n - 2; i >= 0; i--) {
            System.arraycopy(cntPos[i + 1], 0, cntPos[i], 0, 10);
            cntPos[i][cs[i + 1] - '0']++;
        }
        var cnt1 = new int[n][10][10];
        var cnt2 = new int[n][10][10];
        for (var i = 1; i < n; i++) {
            for (var j = 0; j < 10; j++) System.arraycopy(cnt1[i - 1][j], 0, cnt1[i][j], 0, 10);
            for (var j = 0; j < 10; j++) {
                cnt1[i][j][cs[i] - '0'] = (cnt1[i][j][cs[i] - '0'] + cntP[i][j]) % mod;
            }
        }
        for (var i = n - 2; i >= 0; i--) {
            for (var j = 0; j < 10; j++) System.arraycopy(cnt2[i + 1][j], 0, cnt2[i][j], 0, 10);
            for (var j = 0; j < 10; j++) {
                cnt2[i][cs[i] - '0'][j] = (cnt2[i][cs[i] - '0'][j] + cntPos[i][j]) % mod;
            }
        }
        for (var i = 1; i < n - 1; i++) {
            for (var j = 0; j < 10; j++) {
                for (var k = 0; k < 10; k++) {
                    ans = (ans + ((long) cnt1[i - 1][j][k] * cnt2[i + 1][k][j])) % mod;
                }
            }
        }
        return (int) (ans % mod);
    }

}
