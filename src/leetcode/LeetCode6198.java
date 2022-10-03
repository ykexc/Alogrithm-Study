package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode6198 {

    long ans;
    int di;
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        di = diff;
        int[] nums = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            nums[i] = nums1[i] - nums2[i];
        }
        mergeSort(nums, 0, nums.length - 1);
        return ans;
    }
    void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        merge(nums, start, end, mid);
    }
    void merge(int[] nums, int start, int end, int mid) {
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j] + di) {
                ans += end - j + 1;
                i++;
            } else {
                j++;
            }
        }
        Arrays.sort(nums,start,end + 1);
    }
}
