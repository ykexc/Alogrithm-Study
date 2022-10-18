package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode902 {

    private String[] digits;
    private char[] s;
    private int[] dp;

    public int atMostNGivenDigitSet(String[] digits, int n) {
        this.digits = digits;
        this.s = String.valueOf(n).toCharArray();
        dp = new int[s.length];
        Arrays.fill(dp, -1);//表示状态还未计算出
        return f(0, true, false);
    }

    private int f(int i, boolean isLimit, boolean isNum) {
        if (i == s.length) return isNum ? 1 : 0; //长度已经到了最后且已经填了数字
        if (!isLimit && isNum && dp[i] >= 0) return dp[i];
        int res = 0;
        if (!isNum) {//前面不填数字的话，此时可以跳过或填数字1~9，如果也不填数字的话，那么isLimit就为false,因为此时的数位
            //已经比n小了,isNum依然为false;
            res = f(i + 1, false, false);
        }
        int up = isLimit ? s[i] : '9';//根据是否收到约束,来决定可以填的数字的上限
        // 注意：对于一般的题目而言，如果此时 isNum 为 false，则必须从 1 开始枚举，由于本题 digits 没有 0，所以无需处理这种情况
        for (String s : digits) {
            if (s.charAt(0) > up) break;
            res += f(i + 1, isLimit && s.charAt(0) == up, true);//下一个数位受限制必须得是这个数位和比较数位相同且下一个与比较数位相同
        }
        if (!isLimit && isNum) dp[i] = res;
        return res;
    }

}
