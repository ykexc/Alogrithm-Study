package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode2426 {
        public long numberOfPairs(int[] a, int[] nums2, int diff) {
            var n = a.length;
            for (var i = 0; i < n; ++i)
                a[i] -= nums2[i];
            var b = a.clone();
            Arrays.sort(b); // 配合下面的二分，离散化

            var ans = 0L;
            var t = new BIT(n + 1);
            for (var x : a) {
                ans += t.query(lowerBound(b, x + diff + 1));
                t.add(lowerBound(b, x) + 1);
            }
            return ans;
        }

        private int lowerBound(int[] a, int x) {
            int left = 0, right = a.length;
            while (left < right) {
                var mid = left + (right - left) / 2;
                if (a[mid] < x) left = mid + 1;
                else right = mid;
            }
            return left;
        }

        static class BIT {
            private final int[] tree;

            public BIT(int n) {
                tree = new int[n];
            }

            public void add(int x) {
                while (x < tree.length) {
                    ++tree[x];
                    x += x & -x;
                }
            }

            public int query(int x) {
                var res = 0;
                while (x > 0) {
                    res += tree[x];
                    x &= x - 1;
                }
                return res;
            }
        }
    }

