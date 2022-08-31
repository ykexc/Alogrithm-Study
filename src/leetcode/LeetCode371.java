package leetcode;

/**
 * @author 86152
 */
class LeetCode371 {
    public int getSum(int a, int b) {
        while (b != 0) {
            var temp = (a & b) << 1;
            a = a ^ b;
            b = temp;
        }
        return a;
    }
    public int getSum2(int a, int b) {
        if (a == 0 || b == 0) {
            return a | b;
        }
        return getSum2(a ^ b, (a & b) << 1);
    }
}
