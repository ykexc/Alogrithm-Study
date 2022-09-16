package leetcode;

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

}


