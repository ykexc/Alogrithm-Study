package leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author 86152
 */
public class LeetCode715 {

    private final TreeMap<Integer, Integer> map = new TreeMap<>();

    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> floor;
        // 键值对: left->right
        // 返回与小于或等于给定键的最大键关联的键值映射, 如果没有这样的键, 则返回null
        // 如果存在一个区间[l:r]使得l小于等于right
        while ((floor = this.map.floorEntry(right)) != null) {
            // 如果该区间的右区间小于left, 则不需要继续合并
            // ..l..r..left...right...
            if (floor.getValue() < left) {
                break;
            }
            // 如果该[l:r]区间的右区间大于等于left, 则需要将其与当前区间合并, 但首先要移除[l:r]区间
            // ..l...left.r...right   ..l..left...right..r  ..left..l...r...right
            this.map.remove(floor.getKey());
            left = Math.min(left, floor.getKey());
            right = Math.max(right, floor.getValue());
        }
        // 将新合并的区间放入map中
        this.map.put(left, right);

    }

    public boolean queryRange(int left, int right) {
        // 获取一个区间[l:r]使得其l<=left
        Map.Entry<Integer, Integer> floor = this.map.floorEntry(left);
        // 如果存在这个区间并且r>=right, 那么说明该区间存在, 返回true
        return floor != null && floor.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> lower;
        // 返回严格小于给定键的最大键关联的键值映射, 如果没有这样的键, 则返回null
        // 找到一个区间[l:r], 其l小于right
        while ((lower = this.map.lowerEntry(right)) != null) {
            if (lower.getValue() <= left) {
                // 如果该区间[l:r]的r小于等于left, 则不需要移除
                // ..l..r/left..right..  ..l..r..left..right..
                break;
            }
            if (lower.getKey() >= left) {
                // 如果[l:r]的l大于等于left, 则需要移除
                // ..left.l..r..right  ..left/l..r..right ...left..l..right..r (第三种情况则需要在移除后新增一个[right:r]区间)
                this.map.remove(lower.getKey());
            } else {
                // 如果[l:r]的l小于left, ..l..left..r..right ..l..left..right..r
                // 则需要将[left:r]区间消除, 具体做法则是更新[l:r]区间变为[l:left]
                this.map.put(lower.getKey(), left);
            }
            // 如果出现...right..r..的情况, 则需要将right前面的区间消除, 具体做法则是生成/更新一个区间为[right:r]
            if (lower.getValue() > right) {
                this.map.put(right, lower.getValue());
            }
        }
    }

}
