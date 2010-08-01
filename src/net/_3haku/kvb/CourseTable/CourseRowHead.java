/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-20, 9:03:43
 */

package net._3haku.kvb.CourseTable;

import java.util.StringTokenizer;

/**课程行标头
 *
 * @author princehaku
 */
public class CourseRowHead {
    /**构造一个新的行标头
     * 包含的信息
     * @param weekTh 
     * @param weekDate
     */
    public CourseRowHead(String weekTh, String weekDate) {
        this.weekTh = weekTh;
        //System.out.println(weekDate);
        StringTokenizer st=new StringTokenizer(weekDate, "/");
        this.weekStartDate = st.nextToken();
        this.weekEndDate = st.nextToken();
    }
    /**第?周
     * 
     */
   private String weekTh;

    public String getWeekEndDate() {
        return weekEndDate;
    }

    public String getWeekStartDate() {
        return weekStartDate;
    }

    public String getWeekTh() {
        return weekTh;
    }
    /**第?周开始的时间
     *
     */
   private String weekStartDate;
    /**第?周结束的时间
     *
     */
   private String weekEndDate;
}
