package leetcode;

public class QuickSort {



    int[] nums;
    public int[] sortArray(int[] nums) {
        this.nums = nums;
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }


    void quickSort(int q[], int l, int r) {
        if (l >= r) return;

        int i = l - 1, j = r + 1, x = q[l + r >> 1];
        while (i < j) {
            do i++; while (q[i] < x);
            do j--; while (q[j] > x);
            if (i < j) swap(q, i, j);
        }
        quickSort(q, l, j);
        quickSort(q, j + 1, r);
    }

    void swap(int[] q, int i, int j) {
        q[i] ^= q[j];
        q[j] ^= q[i];
        q[i] ^= q[j];
    }

}
