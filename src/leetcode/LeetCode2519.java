package leetcode;

/**
 * 树状数组
 */
public class LeetCode2519 {

    public int kBigIndices(int[] nums, int k) {
        int n = nums.length;
        int[] tree1 = new int[n + 1];
        int[] tree2 = new int[n + 1];
        int[] l = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            add(tree1, nums[i]);
            l[i] = query(tree1, nums[i] - 1);
        }
        for (int i = n - 1; i >= 0; i--) {
            add(tree2, nums[i]);
            r[i] = query(tree2, nums[i] - 1);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) if (l[i] >= k && r[i] >= k) ans++;
        return ans;
    }


    int lowbit(int a) {
        return a & -a;
    }

    void add(int[] tree, int a) {
        while (a < tree.length) {
            tree[a]++;
            a += lowbit(a);
        }
    }

    int query(int[] tree, int a) {
        int ans = 0;
        while (a > 0) {
            ans += tree[a];
            a -= lowbit(a);
        }
        return ans;
    }

}
