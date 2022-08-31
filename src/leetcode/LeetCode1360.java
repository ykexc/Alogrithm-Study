package leetcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 86152
 */
public class LeetCode1360 {
    public int daysBetweenDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(date1);
            Date endDate = sdf.parse(date2);

            long seconds = (startDate.getTime() - endDate.getTime()) / 1000;
            return Math.abs((int) (seconds / (60 * 60 * 24)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
