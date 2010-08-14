/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-6-1, 9:19:23
 */
package net._3haku.util;

import java.util.Calendar;
import java.util.TimeZone;

/**用来格式化时间等等
 * @see java.util.Date
 * @author princehaku
 */
public class Date extends java.util.Date {

    /**格式化时间到UNIX SPAN
     * @param time
     * Format : XXXX-XX-XX XX:XX:XX
     * Source : YYYY-MM-DD HH:MM:SS
     * @return
     */
    public static long ToTimeSpan(String time) {
        try {
            TimeZone.getTimeZone("GMT+8");
            if (time.length() != 19) {
                return 0;
            }
            Calendar cal = Calendar.getInstance();
            int Year = Integer.parseInt(time.substring(0, 4));
            cal.set(Calendar.YEAR, Year);
            //月份要减一
            int Month = Integer.parseInt(time.substring(5, 7)) - 1;
            cal.set(Calendar.MONTH, Month);
            int Day = Integer.parseInt(time.substring(8, 10));
            cal.set(Calendar.DATE, Day);
            int Hours = Integer.parseInt(time.substring(11, 13));
            cal.set(Calendar.HOUR_OF_DAY, Hours);
            int Mins = Integer.parseInt(time.substring(14, 16));
            cal.set(Calendar.MINUTE, Mins);
            int Seconds = Integer.parseInt(time.substring(17, 19));
            cal.set(Calendar.SECOND, Seconds);
            //System.out.println(cal.getTime());
            long totime = cal.getTime().getTime();
            return totime;
        } catch (NumberFormatException numberFormatException) {
            return 0;
        }
    }
}

