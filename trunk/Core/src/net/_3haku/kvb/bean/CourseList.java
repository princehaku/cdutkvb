/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-10, 21:46:37
 */

package net._3haku.kvb.bean;

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
    public String getFullName(String idxName)
    {
        for(int i=0;i<cs.size();i++)
        {
            String courseidxName=((Course)(cs.get(i+""))).getCourseIdxName();
            if(idxName.equals(courseidxName))
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
    public String getPlace(String idxName)
    {
        for(int i=0;i<cs.size();i++)
        {
            String courseidxName=((Course)(cs.get(i+""))).getCourseIdxName();
            if(idxName.equals(courseidxName))
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
    public String getType(String idxName)
    {
        for(int i=0;i<cs.size();i++)
        {
            String courseidxName=((Course)(cs.get(i+""))).getCourseIdxName();
            if(idxName.equals(courseidxName))
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
    public void reBuiltClass(String idxName,String coursePlace)
    {
        boolean found=false;
        String courseName="";
        String type="理论";
        for(int i=0;i<cs.size();i++)
        {
            //如果包含s字段..表明是实验课
            if(idxName.indexOf("s")!=-1)
            {
                idxName=idxName.substring(0,idxName.indexOf("s"));
                type="实验";
            }
            //名字和上课地点都相同才算匹配
            if(idxName.equals(((Course)(cs.get(i+""))).getCourseIdxName())&&coursePlace.equals(((Course)(cs.get(i+""))).getCoursePlace()))
            {
                //System.out.println(idxName+" - "+((Course)(cs.get(i+""))).getCourseName()+type+" - "+coursePlace);
                found=true;
                }
            else
            {
                if(idxName.equals(((Course)(cs.get(i+""))).getCourseIdxName()))
                    courseName=((Course)(cs.get(i+""))).getCourseName();
            }
        }
        if(!found)
        {
            System.out.println(idxName+""+coursePlace+"  -  "+courseName+type);
        }
    }
    /**更新某个课程的时间表信息
     * 考虑到课程有类型的区别..故连上课程的上课地点一起传值
     * @param idxName 课程索引名
     * @param coursePlace 课程上课地点
     * @param stTime
     * @param edTime
     */
    public void insertTime(String idxName,String coursePlace,String stTime,String edTime)
    {
        
        for(int i=0;i<cs.size();i++)
        {
            String courseidxName=((Course)(cs.get(i+""))).getCourseIdxName();
            
            if(idxName.equals(courseidxName))
            {
                
                ((Course)(cs.get(i+""))).addNewTime(coursePlace,stTime,edTime);
            }
        }
    }
}
