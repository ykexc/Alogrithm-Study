package leetcode;

/**
 * @author 86152
 */
public class LeetCode307 {

    BIT bit;
    int[] a;

    public LeetCode307(int[] nums) {
        bit = new BIT(nums);
        a = nums;
    }

    public void update(int index, int val) {
        bit.add(index + 1, val - a[index]);
        a[index] = val;
    }

    public int sumRange(int left, int right) {
        return bit.query(right + 1) - bit.query(left);
    }

}


class BIT {
    int[] tree;

    BIT(int[] arr) {
        tree = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            this.add(i + 1, arr[i]);
        }
    }

    int lowBit(int n) {
        return n & -n;
    }

    int query(int x) {
        int sum = 0;
        while (x > 0) {
            sum += tree[x];
            x -= lowBit(x);
        }
        return sum;
    }

    void add(int x, int v) {
        while (x < tree.length) {
            tree[x] += v;
            x += lowBit(x);
        }
    }
}