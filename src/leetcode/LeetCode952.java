package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86152
 */
public class LeetCode952 {
    static class UnionFind {
        int[] arr;
        int[] rank;

        public UnionFind(int n) {
            arr = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int n) {
            if (n == arr[n]) {
                return n;
            }
            return arr[n] = find(arr[n]);
        }

        public void union(int x, int y) {
            int xx = find(arr[x]);
            int yy = find(arr[y]);
            if (xx != yy) {
                if (rank[xx] < rank[yy]) {
                    arr[xx] = yy;
                } else if (rank[xx] > rank[yy]) {
                    arr[yy] = xx;
                } else {
                    arr[xx] = yy;
                    rank[yy]++;
                }
            }
        }
    }

    public int largestComponentSize(int[] nums) {
        int MAX = 0;
        for (int num : nums) {
            MAX = Math.max(MAX, num);
        }
        int n = MAX;
        UnionFind unionFind = new UnionFind(n + 1);
        for (int num : nums) {
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    unionFind.union(i, num);
                    unionFind.union(num / i, num);
                }
            }
        }
        int[] ans = new int[n + 1];
        int max = 0;
        for (int num : nums) {
            int t = unionFind.find(num);
            ans[t]++;
            max = Math.max(max, ans[t]);
        }
        return max;
    }

    public int largestComponentSize2(int[] nums) {
        int n = nums.length;
        List<Integer> path[] = new List[n + 5];
        for (int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (gcd(nums[i], nums[j]) > 1) {
                    path[i].add(j);
                    path[j].add(i);
                }
            }
        }
        int ans = 0;
        boolean used[] = new boolean[n + 5];
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                int count = 1;
                used[i] = true;
                List<Integer> list = new ArrayList<>();
                list.add(i);
                for (int j = 0; j < list.size(); j++) {
                    int a = list.get(j);
                    for (int k = 0; k < path[a].size(); k++) {
                        int b = path[a].get(k);
                        if (!used[b]) {
                            used[b] = true;
                            list.add(b);
                            count++;
                        }
                    }
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    int gcd(int a, int b) {
        return a <= b ? (b % a == 0 ? a : gcd(b % a, a)) : gcd(b, a);
    }

}


