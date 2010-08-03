/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 02-八月-10, 下午 01:20
 */

package net._3haku.kvb.TimeFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WishH
 */
public class WinterSchoolTime implements SchoolTime{

    public WinterSchoolTime(){
        this.setTime();
    }
    /**夏时令时间
     *
     */
    ArrayList<String> TimeTb=new ArrayList<String>();
    /**每节课持续的时间
     *
     */
    public void setTime() {
         TimeTb.add("8:10");
         TimeTb.add("9:00");
         TimeTb.add("10:15");
         TimeTb.add("11:05");
         TimeTb.add("2:30");
         TimeTb.add("3:20");
         TimeTb.add("4:10");
         TimeTb.add("5:05");
         TimeTb.add("7:10");
         TimeTb.add("8:00");
         TimeTb.add("8:50");
    }

   public Date getStartTimeAt(int courseSt) {
        try {
            @SuppressWarnings("static-access")
            Date parse = new SimpleDateFormat("HH:mm").parse(TimeTb.get(courseSt - 1));
            return parse;
        } catch (ParseException ex) {
            Logger.getLogger(WinterSchoolTime.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
