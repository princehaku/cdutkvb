/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-24, 14:43:16
 */
package net._3haku.kvb.bean;

import java.util.ArrayList;

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

    public String getCoursePlace() {
        return coursePlace;
    }
    
    public String getCourseTeacher() {
        return courseTeacher;
    }

    public String getCourseIdxName() {
        return courseIdxName;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseScoure() {
        return courseScoure;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseType() {
        return courseType;
    }
    
    public ArrayList<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTable(ArrayList<TimeTable> timeTables) {
        this.timeTables = timeTables;
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
