/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-31, 14:22:59
 */

package net._3haku.kvb.TimeFactory;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author princehaku
 */
public class SchoolTime extends Date{
    /**夏时令时间
     *
     */
    ArrayList<String> summerTimer=new ArrayList<String>();
    /**到当天第X节课时间
     *@param courseSt
     *@param seasonType 时令
     */
    Date getTimeAt(int courseSt,SeasonType seasonType){
        this.setSeconds(0);
        if(seasonType.equals(SeasonType.WINTER))
        {
            this.setHours(8);
        }
        return null;
    }
    SchoolTime()
    {
        summerTimer.add("7:45");
        summerTimer.add("7:45");
    }
}
