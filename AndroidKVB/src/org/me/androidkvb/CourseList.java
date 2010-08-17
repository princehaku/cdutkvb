/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-10, 21:46:37
 */

package org.me.androidkvb;

import java.util.ArrayList;

/**课程链表
 *
 * @author princehaku
 */
public class CourseList {

    ArrayList<Course> cs;
    CourseList()
    {
         cs=new ArrayList<Course>();
    }
    /**根据索引名得到课程的全名
     *
     * @param idxname
     * @return
     */
    String getFullName(String idxname)
    {
        for(int i=0;i<cs.size();i++)
        {
            if(idxname.indexOf(cs.get(i).getCourseIdxName())!=-1)
            {
                return cs.get(i).getCourseName();
            }
        }
        return "";

    }
    /**根据索引名得到上课地点
     *
     * @param idxname
     * @return
     */
    String getPlace(String idxname)
    {
        for(int i=0;i<cs.size();i++)
        {
            if(idxname.indexOf(cs.get(i).getCourseIdxName())!=-1)
            {
                return cs.get(i).getCoursePlace();
            }
        }
        return "";
    }
    /**根据索引名得到课程类型
     *
     * @param idxname
     * @return
     */
    String getType(String idxname)
    {
        for(int i=0;i<cs.size();i++)
        {
            if(idxname.indexOf(cs.get(i).getCourseIdxName())!=-1)
            {
                return cs.get(i).getCourseType();
            }
        }
        return "";
    }
    /**加入新课程至链表
     *
     * @param csr
     */
    void add(Course csr)
    {
        cs.add(csr);
    }
}
