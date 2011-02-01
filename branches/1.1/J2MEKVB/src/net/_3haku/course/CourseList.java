/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-10, 21:46:37
 */

package net._3haku.course;

import java.util.Hashtable;

/**课程链表
 *
 * @author princehaku
 */
public class CourseList {

    Hashtable cs;

    int size=0;
    
    public CourseList()
    {
         cs=new Hashtable();
    }
    /**根据索引名得到课程的全名
     *
     * @param idxname
     * @return
     */
    public String getFullName(String idxname)
    {
        for(int i=0;i<cs.size();i++)
        {
            if(idxname.indexOf(((Course)(cs.get(i+""))).getCourseIdxName())!=-1)
            {
                return ((Course)(cs.get(i+""))).getCourseName();
            }
        }
        return "";
    }

    /**根据索引名得到上课地点
     *
     * @param idxname
     * @return
     */
    public String getPlace(String idxname)
    {
        for(int i=0;i<cs.size();i++)
        {
            if(idxname.indexOf(((Course)(cs.get(i+""))).getCourseIdxName())!=-1)
            {
                return ((Course)(cs.get(i+""))).getCoursePlace();
            }
        }
        return "";
    }
    /**根据索引名得到课程类型
     *
     * @param idxname
     * @return
     */
    public String getType(String idxname)
    {
        for(int i=0;i<cs.size();i++)
        {
            if(idxname.indexOf(((Course)(cs.get(i+""))).getCourseIdxName())!=-1)
            {
                return ((Course)(cs.get(i+""))).getCourseType();
            }
        }
        return "";
    }
    /**向此链表中新加一个课程
     *
     * @param csr
     */
    public void add(Course csr)
    {
        cs.put(size+++"",csr);
    }
}
