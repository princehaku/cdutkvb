/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-19, 21:53:31
 */
package net._3haku.kvb.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net._3haku.kvb.course.Course;
import net._3haku.kvb.course.CourseList;
import net._3haku.kvb.coursetable.CourseColumn;
import net._3haku.kvb.coursetable.CourseRow;
import net._3haku.kvb.coursetable.CourseRowHead;
import net._3haku.kvb.coursetable.CourseTable;
import net._3haku.kvb.time.SchoolTime;
import net._3haku.kvb.time.WinterSchoolTime;

/**
 *
 * @author princehaku
 */
public class Parser {

    /**课程表行和课程信息的分割行
     */
    private static int courseidx = 99;
    /**课程信息的html
     */
    private static String CourseInfoHTMLString = "";

    /**解析课程表
     * @return CourseTable
     */
    public CourseTable parseTable(String WholeHTMLString) throws Exception {
       	courseidx = 99;
    	CourseInfoHTMLString = "";
        ArrayList<CourseRow> rows = parseRow(WholeHTMLString);
        CourseList courses = parseCourse(CourseInfoHTMLString);
        CourseTable tb = new CourseTable(rows);
        //装饰下这个类
        tb =organizeCourseTime(tb,courses);
        return tb;
    }

    /**解析出课程表的行
     * @return ArrayList<CourseRow>
     */
    private ArrayList<CourseRow> parseRow(String WholeHTMLString) {
        ArrayList<CourseRow> rows = new ArrayList<CourseRow>();
        Pattern pn = Pattern.compile("<tr>([\\s|\\S]*?)</tr>");
        Matcher mc = pn.matcher(WholeHTMLString);
        //System.out.println(stString+"([\\s|\\S]*)"+endString);
        int rowidx = 1;
        while (mc.find()) {
            //第一行是无用信息..过滤掉
            if (rowidx != 1) {
                //如果是课程单元格.解析
                if (rowidx < courseidx) {

                    CourseRow cr = parseColumn(mc.group(1), rowidx);
                    if (cr != null) {
                        rows.add(cr);
                    } else {
                        CourseInfoHTMLString = mc.group(1);
                    }
                } else {
                    CourseInfoHTMLString += mc.group(1);
                }
            }

            rowidx++;
        }
        //System.out.println(CourseInfoHTMLString);
        return rows;
    }

    /**对单元格进行详细的分析
     * @param RowHTMLString
     * @return CourseRow
     */
    private CourseRow parseColumn(String RowHTMLString, int rowidx) {
        CourseRow cr = new CourseRow();
        ArrayList<CourseColumn> cols = new ArrayList<CourseColumn>();
        //System.out.println(RowHTMLString);
        //解析出td
        Pattern pn = Pattern.compile("<td([\\s|\\S]*?)>([\\s|\\S]*?)</td>");
        Matcher mc = pn.matcher(RowHTMLString);
        int colidx = 1;
        while (mc.find()) {
            CourseColumn cc = new CourseColumn();
            //参数1..分离单元格的跨度(用于计算上课时间)
            String argv1 = mc.group(1);
            int crossSpan = 1;
            String crossSpanString = StringUtil.findMc(argv1.toLowerCase(), "colspan=[\'\"]{0,1}(\\d*)[\'\"]{0,1}", 1);
            if (!crossSpanString.equals("")) {
                crossSpan = Integer.parseInt(crossSpanString);
            }
            cc.setCrossSpan(crossSpan);
            //参数2...分离出课程索引名和上课的地点
            String argv2 = mc.group(2);
            //如果是1号单元格..则将其视为表头..分离出表头信息
            if (colidx == 1) {
                StringTokenizer st = new StringTokenizer(argv2, "<br>");
                String weekTitle = st.nextToken();
                if (weekTitle.indexOf("周") != -1) {
                    cr.setRowHead(new CourseRowHead(weekTitle, st.nextToken()));
                } else {
                    //记录当前行..从此行开始的应该解析为课程信息表
                    courseidx = rowidx;
                    //System.out.println(courseidx);
                    return null;
                }
            } else {
                int pos1 = argv2.indexOf("<br>");
                if (pos1 == -1) {
                    //不是课
                    cc.setExtClass(false);
                } else {
                    //分析为课程cell
                    String courseIdxName = argv2.substring(0, pos1);
                    String ls = argv2.substring(pos1 + 4, argv2.length());
                    String coursePlace = "";
                    coursePlace = StringUtil.findMc(ls, "<font.*?>(.*?)</font>", 1);
                    cc.setCourseIdxName(courseIdxName);
                    cc.setCoursePlace(coursePlace);
                }
                //单元格加入行
                cols.add(cc);
            }
            colidx++;
        }
        cr.setCourseColums(cols);
        return cr;
    }

    /**解析课程的信息
     * @param CourseInfoHTMLString
     * @return CourseList
     */
    private CourseList parseCourse(String CourseInfoHTMLString) throws Exception {
        CourseList courses=new CourseList();

        String courseName = "", courseidxName = "", courseScoure = "", courseId = "";
        String courseType = "", courseTeacher = "", coursePlace = "";

        Pattern pn = Pattern.compile("<td(.*?)>(.*?)</td>");

        Matcher mc = pn.matcher(CourseInfoHTMLString);

        while (mc.find()) {
            String argv = mc.group(2);
            //b标签里面的是课程名字
            courseName = StringUtil.findMc(argv, "<b(.*?)>(.*?)</b>", 2);
            //font标签里面的包含了课程索引名和学分的信息
            courseidxName = StringUtil.findMc(argv, "<font(.*?)>\\((.*?)\\).*?学分\\[(.*?)\\].*?</font>", 2);
            courseScoure = StringUtil.findMc(argv, "<font(.*?)>\\((.*?)\\).*?学分\\[(.*?)\\].*?</font>", 3);
            //按br标签进行分离
            StringTokenizer st = new StringTokenizer(argv, "<br>");
            while (st.hasMoreTokens()) {
                String lineString = st.nextToken();
                //科幻的正则得到科幻的匹配..
                Pattern pnline = Pattern.compile("(.*?)\\(\\[(.*?)\\].*?\\[.*?\\].*?\\[(.*?)\\].*?\\[(.*?)\\]\\)");
                Matcher mcline = pnline.matcher(lineString);
                //结果里面必定包含课程id 课程种类 课程教师 课程地点
                if (mcline.matches()) {
                    if (mcline.groupCount() != 4) {
                        throw new Exception("Unexpected Error");
                    }
                    courseId=mcline.group(1);
                    courseType=mcline.group(2);
                    courseTeacher=mcline.group(3);
                    coursePlace=mcline.group(4);
                    //然后把得到的信息组合成课程存入table
                    courses.add(new Course(courseName, courseId, courseTeacher, coursePlace, courseScoure, courseidxName, courseType));
                }
            }
        }
        return courses;
    }
    /**重组课程的时间信息
     *
     */
    private CourseTable organizeCourseTime(CourseTable tb,CourseList courseList) throws Exception{

        SchoolTime st = new WinterSchoolTime();
        int classnums = 0;
        //计算总课数
        for (int i = 1; i <= tb.getRowNums(); i++) {
            for (int o = 1; o <= tb.getColumnNums(i); o++) {
                CourseColumn cc = tb.getColum(i, o);
                if (cc.haveClass()) {
                    classnums++;
                }
            }

        }
        //打印周数,课程数,分段数
        //System.out.print(tb.getRowNums() + "@" + courseList.toString() + "@" + classnums);
        
        Date dat=new Date();
        //初始化date
        dat.setMonth(Integer.parseInt(tb.getRowHead(1).getWeekStartDate().substring(0,tb.getRowHead(1).getWeekStartDate().indexOf("-")))-1);
        dat.setDate(Integer.parseInt(tb.getRowHead(1).getWeekStartDate().substring(tb.getRowHead(1).getWeekStartDate().indexOf("-")+1,tb.getRowHead(1).getWeekStartDate().length())));
        for (int i = 1; i <= tb.getRowNums(); i++) {
            // 一周开始的时间
            int courest = 1;
            for (int o = 1; o <= tb.getColumnNums(i); o++) {
                CourseColumn cc = tb.getColum(i, o);
                if (cc.haveClass()) {
                    String idxName=cc.getCourseIdxName().replaceAll("\n", "");
                    int date=dat.getMonth()+1;
                    int day=dat.getDate();
                    //开始的时间  格式yy-MM-dd HH:mm
                    String stTime=StringUtil.plusZero((dat.getYear()+1900)+"",4)+"-"+StringUtil.plusZero(date+"",2)
                            +"-"+StringUtil.plusZero(day+"",2)+" "
                            +StringUtil.plusZero(st.getStartTimeAt(courest).getHours()+"",2)+ ":"
                            + StringUtil.plusZero(st.getStartTimeAt(courest).getMinutes()+"",2);
                    courest = courest + cc.getCrossSpan();
                    //结束的时间  格式yy-MM-dd HH:mm
                    String edTime=StringUtil.plusZero((dat.getYear()+1900)+"",4)+"-"+StringUtil.plusZero(date+"",2)
                            +"-"+StringUtil.plusZero(day+"",2)+" "
                            + StringUtil.plusZero(st.getEndTimeAt(courest - 1).getHours()+"",2)
                            + ":"
                            + StringUtil.plusZero(st.getEndTimeAt(courest - 1).getMinutes()+"",2);
                    //重建课程的索引 加入未被包含的课程
                    courseList.reBuiltClass(idxName,cc.getCoursePlace());
                    //将时间插入到课程列表 (考虑到课程有类型的区别..故连上课程的上课地点一起传)
                    courseList.insertTime(idxName,cc.getCoursePlace(), stTime, edTime);
                } else {
                    courest = courest + cc.getCrossSpan();
                }
                if (courest > 11) {
                    courest = 1;
                    dat=new Date(dat.getTime()+3600*24*1000);
                }
            }
        }
        //给表格加上课程信息
        tb.setCourseList(courseList);
        return tb;
    }
}
