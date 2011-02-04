/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-22, 21:50:29
 */
package net._3haku.kvb.coursetable;

import java.util.ArrayList;
import java.util.Hashtable;
import net._3haku.kvb.course.Course;
import net._3haku.kvb.course.CourseList;
import net._3haku.kvb.time.TimeTable;

/**课程表
 *
 * @author princehaku
 */
public class CourseTable {
    /**用课表行信息  和  课程信息构造一个新的课程表
     * @param rows
     * @param courses
     */
    public CourseTable(ArrayList<CourseRow> rows) {
        this.rows = rows;
        this.rowNums=rows.size();
    }
    /**所有的课程信息{课号,课名,上课教师}
     */
    private CourseList courseList;

    public CourseList getCourseList() {
        return courseList;
    }
    /**设置课程信息
     *
     * @param courseList
     */
    public void setCourseList(CourseList courseList) {
        this.courseList = courseList;
    }
    /**课表行私有
     *
     */
    private ArrayList<CourseRow> rows;
    /**行数
     *
     */
    private int rowNums;
    /**得到行数
     * @return int
     */
    public int getRowNums() {
        return rowNums;
    }
    /**得到某行单元格数两
     * @param rowIdx 行号 下标1
     * @return int
     * @throws Exception
     */
    public int getColumnNums(int rowIdx) throws Exception {
        if (rowIdx > rows.size() || rowIdx < 1) {
            throw new Exception("Row Out of Range");
        }
        return rows.get(rowIdx-1).getCourseColums().size();
    }
    /**得到表头
     * @param rowIdx 行号 下标1
     * @return CourseRowHead
     * @throws Exception
     */
    public CourseRowHead getRowHead(int rowIdx) throws Exception {
        if (rowIdx > rows.size() || rowIdx < 1) {
            throw new Exception("Row Out of Range");
        }
        return rows.get(rowIdx-1).getRowHead();
    }
    /**得到指定位置的课程单元格
     * @param rowIdx 行号 下标1
     * @param columnIdx 列号 下标1
     * @return CourseColumn
     * @throws Exception
     */
    public CourseColumn getColum(int rowIdx,int columnIdx) throws Exception {
        if (rowIdx > rows.size() || rowIdx < 1) {
            throw new Exception("Row Out of Range");
        }
        if ( columnIdx > rows.get(rowIdx - 1).getCourseColums().size() ||  columnIdx < 1) {
            throw new Exception("Col Out of Range");
        }
        return rows.get(rowIdx - 1).getCourseColums().get(columnIdx-1);

    }
    /**得到某行的所有课程节点
     * @param rowIdx 行号 下标1
     * @return ArrayList<CourseColumn>
     * @throws Exception
     */
   public  ArrayList<CourseColumn> getRowColums(int rowIdx) throws Exception {
        if (rowIdx > rows.size() || rowIdx < 1) {
            throw new Exception("Out of broundary");
        }
        return rows.get(rowIdx - 1).getCourseColums();
    }
    /**得到某列的所有节点
     *@deprecated
     */
    public ArrayList<CourseColumn> getColumnColums(int columnIdx) throws Exception {
        throw new Exception("Not implements");
    }
    /**序列化成字符串
     * 节点总个数|课程全名@课程地点@课程类型@课程学分@课程老师@上课时间@下课时间@重复开始的日期@重复结束的日期|...|...|end
     */
    @Override
    public String toString()
    {
        String returnString="";
        Hashtable cs=getCourseList().getContaner();
        int count=0;
        for(int i=0;i<cs.size();i++)
        {
            Course cc=((Course)(cs.get(i+"")));
            //遍历时间表
            ArrayList<TimeTable> tta=cc.getTimeTables();
            for(int j=0;j<tta.size();j++)
            {
                count++;
                returnString +=cc.getCourseName()+"@"+cc.getCoursePlace()+"@"+cc.getCourseType()+"@"+cc.getCourseScore()+"@"
                        +cc.getCourseTeacher()+"@";
                //System.out.println("时间表"+j);
                TimeTable tt=tta.get(j);
                returnString +=tt.getStTime()+"@"+tt.getEdTime()+"@"+tt.getStDate()+"@"+tt.getEdDate();
                returnString +="|";
            }

        }
        //加上附加信息
        returnString =count+"|"+returnString+"end";
        return returnString;
    }
}
