/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 02-八月-10, 下午 01:27
 */

package net._3haku.kvb.TimeFactory;

import java.util.Date;

/**
 *
 * @author princehaku
 */
public class CourseTime {
    /**课开始的时间
     *
     */
    private Date classStart;

    public CourseTime(Date classStart, Date classEnd) {
        this.classStart = classStart;
        this.classEnd = classEnd;
    }
    /**课结束的时间
     *
     */
    private Date classEnd;

    public Date getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(Date classEnd) {
        this.classEnd = classEnd;
    }

    public Date getClassStart() {
        return classStart;
    }

    public void setClassStart(Date classStart) {
        this.classStart = classStart;
    }
}
