/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-19, 20:37:53
 */

package net._3haku.kvb.bean;
/**课程单元格接口
 *
 * @author princehaku
 */
public interface CourseCellInfo {
    /**是否有课
     *@return boolean
     */
    boolean  haveClass();
    /**设置课程状态
     * @param havaClass
     */
    void setExtClass(boolean havaClass);

    String getCoursePlace();
    /**设置上课的地点
     *
     * @param coursePlace
     */
    void setCoursePlace(String coursePlace);

    String getCourseIdxName();
    /**设置课程的索引名
     * 对应课表节点
     * @param courseId
     */
    void setCourseIdxName(String courseId) ;
}
