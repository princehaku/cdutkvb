/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-17, 20:44:46
 */

package net._3haku.kvb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author princehaku
 */
public class Time {

    /**判断两个时间是不是恰好间隔一周
     *格式 yy-MM-dd HH:mm
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static boolean isWeek(String date1,String date2) throws ParseException {
        Date d1=new SimpleDateFormat("yy-MM-dd HH:mm").parse(date1);
        Date d2=new SimpleDateFormat("yy-MM-dd HH:mm").parse(date2);
        if((d2.getTime()-d1.getTime())==604800||(d1.getTime()-d2.getTime())==604800)
            return true;
        else
            return false;
    }
}
