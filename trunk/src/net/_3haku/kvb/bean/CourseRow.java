/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-22, 7:23:43
 */

package net._3haku.kvb.bean;

import java.util.ArrayList;

/**课程行
 *
 * @author princehaku
 */
public class CourseRow extends  TableRow{
    /**表头
     *
     */
    private CourseRowHead courseRowHead;
   
    public CourseRowHead getRowHead() {
        return courseRowHead;
    }

    public void setRowHead(CourseRowHead rowHead) {
        this.courseRowHead = rowHead;
    }

   public ArrayList<CourseColumn> getCourseColums() {
        ArrayList<CourseColumn> ccols=new ArrayList<CourseColumn>();
         for(int i=0;i<colums.size();i++)
         {
            CourseColumn d=(CourseColumn)colums.get(i);
            ccols.add(d);
            //System.out.print(d.getCourseIdxName());
         }
        return ccols;
    }
   
    public void setCourseColums(ArrayList<CourseColumn> cols) {
         ArrayList<TableColumn> ccols=new ArrayList<TableColumn>();
         for(int i=0;i<cols.size();i++)
         {
             TableColumn d=cols.get(i);
             ccols.add(d);
             //System.out.print(((CourseColumn)d).getCourseIdxName());
         }
         setColums(ccols);
    }
}
