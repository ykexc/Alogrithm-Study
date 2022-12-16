package leetcode;

/**
 * DP模拟
 */
public class LeetCode678 {

    public boolean checkValidString(String s) {
        // l：从左往右匹配的过程中未匹配的左括号最少是多少
        // r: 从左往右匹配的过程中未匹配的左括号最多是多少
        int l = 0, r = 0;

        for (char c : s.toCharArray()) {
            // 增加了一个未匹配的左括号，显然需要同时增加lr
            if (c == '(') {
                l++;
                r++;
            }
            // 匹配了前边一个未匹配的左括号，显然同时减少lr
            else if (c == ')') {
                l--;
                r--;
            }
            // *号如果匹配了前边的左括号，l就减少了，如果这个*视作左括号，r就增加了，所以*拓宽了lr的边界
            else {
                l--;
                r++;
            }

            // 最少不能低于0
            if (l < 0) l = 0;

            // lr在上述if else中是始终表现为l <= r的趋势的，只有l重置为0的时候，r < 0 才会出现l > r，也就是出现了不能
            // 匹配的右括号
            if (l > r) return false;
        }
        // 在上边循环逻辑中，出现了不能匹配的右括号会提前返回，循环结束后要检查是否能将剩余的未匹配左括号消耗完
        return l == 0;
    }

    /**
     *memory search
     */
    public boolean checkValidString2(String s) {
        char[] cs = s.toCharArray();
        return dfs(cs, 0, 0, new Boolean[cs.length][cs.length]);
    }
    public boolean dfs(char[] cs, int index, int leftCount, Boolean[][] memo) {
        if(index == cs.length) {
            //必须要匹配完才是true，否则false
            if(leftCount == 0){
                return true;
            }else{
                return false;
            }
        }
        //memo防超时
        if(memo[index][leftCount] != null) {
            return memo[index][leftCount];
        }
        char curr = cs[index];
        boolean flag = false;
        //三种情况
        if(curr == '(') {
            //左括号直接往前一步，并且左括号计数器+1
            flag |= dfs(cs, index + 1, leftCount + 1, memo);
        }else if(curr == ')') {
            //如果左括号没了，那么直接false，匹配失败，注意这里不会是有负数，所以memo不会超下标
            if(leftCount <= 0) {
                memo[index][leftCount] = false;
                return memo[index][leftCount];
            }
            //如果左括号还有，那么继续匹配
            flag |= dfs(cs, index + 1, leftCount - 1, memo);
        }else if(curr == '*') {
            //如果是*，那么三种都试下
            //首先是当空字符串用，直接前进一步
            flag |= dfs(cs, index + 1, leftCount, memo);
            //当左括号用，记得计数器加一
            flag |= dfs(cs, index + 1, leftCount + 1, memo);
            //如果还有多的左括号，可以尝试当右括号用
            if(leftCount > 0) {
                flag |= dfs(cs, index + 1, leftCount - 1, memo);
            }
        }
        return memo[index][leftCount] = flag;
    }

}
