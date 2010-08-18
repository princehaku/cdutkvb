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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        if((d2.getTime()-d1.getTime())==604800000||(d1.getTime()-d2.getTime())==604800000)
            return true;
        else
            return false;
    }
    /**格式化字符串到时间
     *格式 yy-MM-dd
     */
    public static Date toDate(String date){
        Date d1=null;
        try {
            d1 = new SimpleDateFormat("yy-MM-dd").parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Time.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d1;
    }
}
