/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-24, 14:43:16
 */
package net._3haku.kvb.bean;

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
    public void addNewTime(String stTime,String edTime) {
        try {
            System.out.println(Time.isWeek(stTime, edTime));
        } catch (ParseException ex) {
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
