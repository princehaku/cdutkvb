/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-22, 21:50:29
 */
package net._3haku.kvb.CourseTable;

import java.util.ArrayList;
import net._3haku.kvb.bean.Course;

/**课程表
 *
 * @author princehaku
 */
public class CourseTable {
    /**用课表行信息  和  课程信息构造一个新的课程表
     * @param rows
     * @param courses
     */
    public CourseTable(ArrayList<CourseRow> rows,ArrayList<Course> courses) {
        this.rows = rows;
        this.rowNums=rows.size();
        this.courses=courses;
    }
    /**所有的课程信息{课号,课名,上课教师,}
     */
    private ArrayList<Course> courses;
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
    /**得到课程的数量
     * @return int
     */
    public int getCoursesNums() {
        return courses.size();
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
}
