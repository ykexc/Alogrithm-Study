package leetcode;

public class LeetCode215 {

    int[] nums;

    int qselect(int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(i, j);
        }
        if (k <= j) return qselect(l, j, k);
        else return qselect(j + 1, r, k);
    }

    void swap(int i, int j) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }

    /**
     * 模拟快排
     * @param _nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] _nums, int k) {
        nums = _nums;
        int n = nums.length;
        return qselect(0, n - 1, n - k);
    }

}
