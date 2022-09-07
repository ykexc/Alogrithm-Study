package leetcode;

import java.util.PriorityQueue;

/**
 * @author 86152
 */
public class LeetCode1705 {

    public int eatenApples(int[] apples, int[] days) {
        int ans = 0;

        // 堆中保存 [苹果数量, 过期时间]，先过期的苹果优先吃掉，已过期的苹果扔掉
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int i = 0;
        // 第 n 天之后也可以继续吃苹果，只要堆中还有可吃的苹果即可
        while (i < apples.length || !heap.isEmpty()) {
            // 把过期的扔掉
            while (!heap.isEmpty() && heap.peek()[1] <= i) {
                heap.poll();
            }

            // 把当天成熟的苹果放进队列
            if (i < apples.length && apples[i] > 0) {
                heap.offer(new int[] {apples[i], i + days[i]});
            }

            // 队首的是可以吃的，一天吃一个
            // 这里有个优化，可以把 >n 的部分单独拿出去
            // 因为 >n 之后是不需要上面判断apples数组的部分
            // 可以一次性的吃 x 天的苹果，有兴趣的可以自己改下代码
            if (!heap.isEmpty()) {
                int[] head = heap.peek();
                head[0]--;
                ans++;
                if (head[0] == 0) {
                    heap.poll();
                }
            }
            i++;
        }

        return ans;
    }
}
