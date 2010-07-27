/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-22, 21:50:29
 */
package net._3haku.kvb.bean;

import java.util.ArrayList;

/**课程表
 *
 * @author princehaku
 */
public class CourseTable {
    /**
     * @param rows
     * @param courses
     */
    public CourseTable(ArrayList<CourseRow> rows,ArrayList<Course> courses) {
        this.rows = rows;
        this.rowNums=rows.size();
        this.courses=courses;
    }
    /**所有的课程信息{课号,课名,上课教师,}
     *
     */
    private ArrayList<Course> courses;
    /**得到课程的数量
     *
     * @return
     */
    public int getCoursesNums() {
        return courses.size();
    }
    /**课表行私有
     *
     */
    private ArrayList<CourseRow> rows;
    /**行数
     *
     */
    private int rowNums;

    public int getRowNums() {
        return rowNums;
    }

    public CourseRowHead getRowHead(int rowNum) throws Exception {
        if (rowNum > rows.size() || rowNum < 1) {
            throw new Exception("Row Out of Range");
        }
        return rows.get(rowNum-1).getRowHead();
    }
    /**得到指定位置的课程单元格
     * @param rowIdx
     * @param columnIdx
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
     * @param rowIdx
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
