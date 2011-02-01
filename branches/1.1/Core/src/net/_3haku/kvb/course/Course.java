/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-24, 14:43:16
 */
package net._3haku.kvb.course;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net._3haku.kvb.time.TimeTable;

import java.util.ArrayList;
import java.util.Date;
import javax.rmi.CORBA.Util;
import net._3haku.kvb.util.Time;

/**课程
 *
 * @author princehaku
 */
public class Course {

    /**课程名
     *
     */
    private String courseName;
    /**课程id号
     *
     */
    private String courseId;
    /**课程教师
     *
     */
    private String courseTeacher;
    /**课程上课地点
     *
     */
    private String coursePlace;
    /**
     * @param courseName
     * @param courseId
     * @param courseTeacher
     * @param coursePlace
     * @param courseScoure
     * @param courseIdxName
     * @param courseType
     */
    public Course(String courseName, String courseId, String courseTeacher, String coursePlace, String courseScoure, String courseIdxName, String courseType) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseTeacher = courseTeacher;
        this.coursePlace = coursePlace;
        this.courseScoure = courseScoure;
        this.courseIdxName = courseIdxName;
        this.courseType = courseType;
        timeTables=new ArrayList<TimeTable>();
    }
    /**课程学分
     *
     */
    private String courseScoure;
    /**课程索引名
     *
     */
    private String courseIdxName;
    /**课程类型
     * 比如:实验 理论
     */
    private String courseType;
    /**时间表
     *
     */
    private ArrayList<TimeTable> timeTables;
    /**得到课程上课地点
     * 
     * @return
     */
    public String getCoursePlace() {
        return coursePlace;
    }
    /**得到课程执教老师
     * 
     * @return
     */
    public String getCourseTeacher() {
        return courseTeacher;
    }
    /**得到课程索引名
     * 
     * @return
     */
    public String getCourseIdxName() {
        return courseIdxName;
    }
    /**得到课程课号
     * 
     * @return
     */
    public String getCourseId() {
        return courseId;
    }
    /**得到课程源HTML
     * 
     * @return
     */
    public String getCourseScoure() {
        return courseScoure;
    }
    /**得到课程全名
     * 
     * @return
     */
    public String getCourseName() {
        return courseName;
    }
    /**得到课程类型
     * 
     * @return
     */
    public String getCourseType() {
        return courseType;
    }

    /**得到时间表
     * @return
     */
    public ArrayList<TimeTable> getTimeTables() {
        return timeTables;
    }

    /**设置时间表
     * @return
     */
    public void addNewTime(String coursePlace,String stTimeString,String edTimeString) {
        try {
            String stDate=stTimeString.substring(0,stTimeString.indexOf(" "));
            String edDate=edTimeString.substring(0,stTimeString.indexOf(" "));
            String stTime=stTimeString.substring(stTimeString.indexOf(" ")+1,stTimeString.length());
            String edTime=edTimeString.substring(edTimeString.indexOf(" ")+1,edTimeString.length());
            int week=Time.toDate(stDate).getDay();
            int o=-1;
            //检测时间表..得到可以更新的时间表
            for(int i=0;i<getTimeTables().size();i++)
            {
                TimeTable tt=getTimeTables().get(i);
                //System.out.println();
                //System.out.println(tt.getEdDate()+" "+tt.getStTime());
                //System.out.println(stDate+" "+stTime);
                //必须满足  开始时间 结束时间 星期相同  且当前的开始日期恰好和以前的结束日期间隔一周
                if(tt.getStTime().equals(stTime)&&tt.getEdTime().equals(edTime)&&Time.isWeek(tt.getEdDate()+" "+tt.getStTime(),stDate+" "+stTime))
                {
                    //System.out.println(coursePlace+"@"+this.courseName+this.courseType+this.coursePlace+edDate+"updated");
                    //设置重复的日期到这次
                    tt.setEdDate(edDate);
                    o=1;
                }

            }
            //如果没有检测到可以更新的时间表 则新建一个时间表并插入到课表的时间表链表内
            if(o==-1)
            {
                TimeTable tt=new TimeTable(stDate, edDate, stTime, edTime, week);
                getTimeTables().add(tt);
            }
        } catch (Exception ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**得到第 pos 个timetable
     * @throws
     * @param pos
     * @return
     */
    public TimeTable getTimeTable(int pos) throws Exception {
        if (timeTables.size() < pos) {
            throw new Exception("Out of range");
        }
        TimeTable tt = timeTables.get(pos - 1);
        return tt;
    }
}
