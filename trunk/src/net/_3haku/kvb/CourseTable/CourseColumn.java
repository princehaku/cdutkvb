/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-20, 13:14:43
 */

package net._3haku.kvb.CourseTable;

/**课程单元格
 * @author princehaku
 */
public class CourseColumn extends TableColumn implements CourseCellInfo{
    /**是否有课
     *
     */
    private boolean haveClass;
    /**课程索引名
     *
     */
    private String courseIdxName="";
   /**上课的地点
    *
    */
    private String coursePlace="";
    /**构造新课程节点
     * @deprecated
     * @param haveClass
     * @param crossSpan
     * @param courseId
     * @param courseTeacher
     */
    public CourseColumn(boolean haveClass,int crossSpan,String courseId,String courseTeacher) {
       this.haveClass = haveClass;
       setCourseIdxName(courseId);
       setCrossSpan(crossSpan);
       setCoursePlace(courseTeacher);
    }

    public CourseColumn() {
        haveClass=true;
    }
    /**是否有课
     *
     * @return boolean
     */
    public boolean haveClass() {
        return haveClass;
    }
    /**设置课程状态
     * @param havaClass
     */
    public void setExtClass(boolean havaClass) {
        haveClass=havaClass;
    }
    public String getCoursePlace() {
        if(!haveClass())
            return "No Class here";
        return coursePlace;
    }
    /**设置上课的地点
     *
     * @param coursePlace
     */
    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }
    
    public String getCourseIdxName() {
        if(!haveClass())
            return "No Class here";
        return courseIdxName;
    }
    /**设置课程的索引名
     * 对应课表节点
     * @param courseId
     */
    public void setCourseIdxName(String courseIdxName) {
        this.courseIdxName = courseIdxName;
    }


}
