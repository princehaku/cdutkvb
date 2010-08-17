/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 02-八月-10, 下午 01:20
 */

package net._3haku.kvb.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**夏时令时间
 *
 * @author princehaku
 */
public class SummerSchoolTime implements SchoolTime{

    public SummerSchoolTime(){
        this.setTime();
    }
    /**上课时间
     *
     */
    ArrayList<String> startTimeTb=new ArrayList<String>();
    /**下课时间
     *
     */
    ArrayList<String> endTimeTb=new ArrayList<String>();

    public void setTime() {
         startTimeTb.add("8:10");
         startTimeTb.add("9:00");
         startTimeTb.add("10:15");
         startTimeTb.add("11:05");
         startTimeTb.add("14:30");
         startTimeTb.add("15:20");
         startTimeTb.add("16:10");
         startTimeTb.add("17:05");
         startTimeTb.add("19:10");
         startTimeTb.add("20:00");
         startTimeTb.add("20:50");
         endTimeTb.add("8:55");
         endTimeTb.add("9:45");
         endTimeTb.add("11:00");
         endTimeTb.add("11:50");
         endTimeTb.add("15:15");
         endTimeTb.add("16:00");
         endTimeTb.add("16:55");
         endTimeTb.add("17:50");
         endTimeTb.add("19:55");
         endTimeTb.add("20:40");
         endTimeTb.add("21:35");
    }

   public Date getStartTimeAt(int courseSt) {
        try {
            @SuppressWarnings("static-access")
            Date parse = new SimpleDateFormat("HH:mm").parse(startTimeTb.get(courseSt - 1));
            return parse;
        } catch (ParseException ex) {
            Logger.getLogger(WinterSchoolTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Date getEndTimeAt(int courseSt) {
        try {
            @SuppressWarnings("static-access")
            Date parse = new SimpleDateFormat("HH:mm").parse(endTimeTb.get(courseSt - 1));
            return parse;
        } catch (ParseException ex) {
            Logger.getLogger(WinterSchoolTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
