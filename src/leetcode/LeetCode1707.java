package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode1707 {
    class Trie {
        // boolean isEnd;
        //初始化一个表示0，1的字典树
        Trie[] childs = new Trie[2];
    }

    private void insert(int x) {
        Trie cur = node;
        //对10^9，即30位加入字典树
        for (int i = 30; i >= 0; i--) {
            if (((1 << i) & x) != 0) {
                if (cur.childs[1] == null) cur.childs[1] = new Trie();
                cur = cur.childs[1];
            } else {
                if (cur.childs[0] == null) cur.childs[0] = new Trie();
                cur = cur.childs[0];
            }
        }
    }

    //从30位 -> 0位查找最符合异或结果最大的数
    private int query(int x) {
        Trie cur = node;
        int ans = 0;
        for (int i = 30; i >= 0; i--) {
            if (((1 << i) & x) != 0) {
                if (cur.childs[0] != null) {
                    cur = cur.childs[0];
                } else {
                    cur = cur.childs[1];
                    ans |= 1 << i;
                }
            } else {
                if (cur.childs[1] != null) {
                    cur = cur.childs[1];
                    ans |= 1 << i;
                } else {
                    cur = cur.childs[0];
                }
            }
        }
        return ans;
    }

    Trie node;

    public int[] maximizeXor(int[] nums, int[][] qs) {
        //先排序
        Arrays.sort(nums);
        int n = nums.length;
        int len = qs.length;
        int[] ans = new int[len];
        //用idx记录qs的原坐标
        Integer[] idx = new Integer[len];
        for (int i = 0; i < len; i++) idx[i] = i;
        //先对记录的原坐标排序，再按照m从小到大排序
        Arrays.sort(idx, (a, b) -> qs[a][1] - qs[b][1]);
        Arrays.sort(qs, (a, b) -> a[1] - b[1]);
        this.node = new Trie();
        int r = 0;
        for (int i = 0; i < len; i++) {
            int x = qs[i][0], m = qs[i][1];
            //将所有不大于m的nums[r]加入字典树
            while (r < n && nums[r] <= m) insert(nums[r++]);
            //如果字典树为空，则当前没有可进行异或的元素，返回-1
            if (r == 0) ans[idx[i]] = -1;
            else {
                //查找最佳可异或值
                int xor = query(x);
                ans[idx[i]] = xor ^ x;
            }
        }

        return ans;
    }
}
