package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86152
 */
public class LeetCode1475 {
    public int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] >= prices[j]) {
                    prices[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    public int[] finalPrices1(int[] prices) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            int c = prices[i];
            while (!deque.isEmpty() && prices[deque.peekLast()] >= c) {
                int index = deque.pollLast();
                prices[index] -= c;
            }
            deque.addLast(i);
        }
        return prices;
    }

    public int[] finalPrices3(int[] price) {
        int index = -1;
        int[] prices = new int[price.length];
        for (int i = 0; i < price.length; i++) {
            int c = price[i];
            while (index != -1 && price[prices[index]] >= c) {
                int ix = prices[index--];
                price[ix] -= c;
            }
            prices[++index] = i;
        }
        return price;
    }
}
