package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author 86152
 */
public class LeetCode981 {

    Map<String, Map<Integer, String>> map = new HashMap<>();



    public void set(String key, String value, int timestamp) {
        var orDefault = map.getOrDefault(key, new HashMap<>());
        orDefault.put(timestamp,value);
        map.put(key, orDefault);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        var vt = map.get(key);
        while (timestamp > 0) {
            if (vt.containsKey(timestamp)) {
                return vt.get(timestamp);
            }
            timestamp --;
        }
        return "";
    }
}
