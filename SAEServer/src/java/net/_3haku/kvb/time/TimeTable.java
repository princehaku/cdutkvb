/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-27, 21:56:23
 */

package net._3haku.kvb.time;

/**时间表
 *
 * @author princehaku
 */
public class TimeTable {

    public TimeTable(String stDate, String edDate, String stTime, String edTime, int week) {
        this.stDate = stDate;
        this.edDate = edDate;
        this.stTime = stTime;
        this.edTime = edTime;
        this.week = week;
    }

    /**重复开始日期
     *
     */
    String stDate;
    /**重复结束日期
     *
     */
    String edDate;
    /**每次的开始时间
     *
     */
    String stTime;
    /**每次的结束时间
     *
     */
    String edTime;
    /**星期几
     * 0-6 0,Sunday,1,Monday ...
     */
    int week;
    public String getEdDate() {
        return edDate;
    }

    public void setEdDate(String edDate) {
        this.edDate = edDate;
    }

    /**每次的结束时间
     *
     */
    public String getEdTime() {
        return edTime;
    }

    /**每次的结束时间
     *
     */
    public void setEdTime(String edTime) {
        this.edTime = edTime;
    }

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    /**每次的开始时间
     *
     */
    public String getStTime() {
        return stTime;
    }

    /**每次的开始时间
     *
     */
    public void setStTime(String stTime) {
        this.stTime = stTime;
    }
    /**星期几
     * 0-6 0,Sunday,1,Monday ...
     */
    public int getWeek() {
        return week;
    }
    /**星期几
     * 0-6 0,Sunday,1,Monday ...
     */
    public void setWeek(int week) {
        this.week = week;
    }
}
