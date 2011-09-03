/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-24, 14:43:16
 */
package net.techest.androidkvb;



/**课程
 *
 * @author princehaku
 */
public class Course {

    public Course(String courseName, String coursePlace,  String courseType,String courseScoure,String courseTeacher) {
        this.courseName = courseName;
        this.coursePlace = coursePlace;
        this.courseType = courseType;
    }

    /**课程名
     *
     */
    private String courseName;
    /**课程上课地点
     *
     */
    private String coursePlace;
    /**课程类型
     * 比如:实验 理论
     */
    private String courseType;
    /**得到课程上课地点
     * 
     * @return
     */
    public String getCoursePlace() {
        return coursePlace;
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
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}
