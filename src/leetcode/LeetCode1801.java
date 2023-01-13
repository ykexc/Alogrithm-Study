package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode1801 {


    static int mod = (int) 1e9 + 7;

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buy = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sell = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] order : orders) {
            int price = order[0], amount = order[1] % mod, type = order[2];
            if (type == 0) {
                while (!sell.isEmpty() && amount > 0) {
                    int[] peek = sell.peek();
                    int p = peek[0], c = peek[1];
                    if (p <= price) {
                        sell.poll();
                        if (amount >= c) {
                            amount -= c;
                        } else {
                            c -= amount;
                            amount = 0;
                            sell.add(new int[]{p, c});
                            break;
                        }
                    } else break;
                }
                if (amount != 0) {
                    buy.add(new int[]{price, amount});
                }
            }
            if (type == 1) {
                System.out.println(amount);
                while (!buy.isEmpty() && amount > 0) {
                    int[] peek = buy.peek();
                    int p = peek[0], c = peek[1];
                    if (p >= price) {
                        buy.poll();
                        if (amount >= c) {
                            amount -= c;
                        } else {
                            c -= amount;
                            amount = 0;
                            buy.add(new int[]{p, c});
                            break;
                        }
                    } else break;
                }
                if (amount != 0) {
                    sell.add(new int[]{price, amount});
                }
            }
        }
        long ans = 0;
        while (!buy.isEmpty()) {
            ans += buy.poll()[1];
            ans %= mod;
        }
        while (!sell.isEmpty()) {
            ans += sell.poll()[1];
            ans %= mod;
        }
        return (int) ans;
    }

}
