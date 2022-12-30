package leetcode;

import java.math.BigInteger;

/**
 * 组合数求逆元
 */
public class LeetCode2514 {

    public int countAnagrams(String s) {
        long[] dp = new long[s.length() + 1];
        long p = 1;
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = dp[i - 1] * i % 1000000007;
        }
        for (String t : s.split(" ")) {
            int[] count = new int[26];
            for (int c : t.toCharArray()) {
                count[c - 'a']++;
            }
            p = p * dp[t.length()] % 1000000007;
            for (int i : count) {
                p = p * BigInteger.valueOf(dp[i]).modInverse(BigInteger.valueOf(1000000007)).intValue() % 1000000007;
            }
        }
        return (int) p;
    }


    static final long MOD = 1000000007;
    static long[] f = new long[100001];

    static {
        f[0] = 1;
        for (int i = 1; i <= 100000; i++) {
            f[i] = f[i - 1] * i % MOD;
        }
    }

    public int countAnagrams2(String s) {
        long ans = 1;
        for (String sp : s.split(" ")) {
            int[] cnt = new int[26];
            for (char c : sp.toCharArray()) {
                cnt[c - 'a']++;
            }
            ans = ans * f[sp.length()] % MOD;
            for (int i = 0; i < 26; i++) {
                ans = ans * BigInteger.valueOf(f[cnt[i]]).modInverse(BigInteger.valueOf(MOD)).longValue() % MOD;
            }
        }
        return (int) ans;
    }

}
