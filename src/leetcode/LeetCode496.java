package leetcode;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * @author 86152
 */
public class LeetCode496 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        var map = new HashMap<Integer, Integer>();
        var deque = new ArrayDeque<Integer>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!deque.isEmpty() && deque.peekLast() < nums2[i]) {
                deque.pollLast();
            }
            if (deque.isEmpty()) {
                map.put(nums2[i], -1);
            } else {
                map.put(nums2[i], deque.peekLast());
            }
            deque.addLast(nums2[i]);
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.get(nums1[i]);
        }
        return nums1;
    }
}
