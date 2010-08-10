/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net._3haku.kvb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net._3haku.kvb.coursetable.CourseColumn;
import net._3haku.kvb.coursetable.CourseTable;
import net._3haku.kvb.time.SchoolTime;
import net._3haku.kvb.time.WinterSchoolTime;
import net._3haku.kvb.bean.Course;
import net._3haku.kvb.util.Parser;
import net._3haku.kvb.util.StringUtil;

/**
 *
 * @author princehaku
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException, Exception {
        Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        String sresult="error1";
        System.out.println(sresult.substring((int) (sresult.indexOf("error") + 5),sresult.length()));
        String kbhtml = "";
        FileInputStream fs = new FileInputStream("src/resource/kb.html");
        InputStreamReader read = new InputStreamReader(fs, "gb2312");
        BufferedReader in = new BufferedReader(read);
        while (in.ready()) {
            kbhtml += in.readLine();
        }
        fs.close();
        CourseTable Tb = Parser.parseTable(kbhtml);
        //System.out.println(Tb.getRowNums());
        SchoolTime st = new WinterSchoolTime();
        int classnums = 0;
        //计算总课数
        for (int i = 1; i <= Tb.getRowNums(); i++) {
            for (int o = 1; o <= Tb.getColumnNums(i); o++) {
                CourseColumn cc = Tb.getColum(i, o);
                if (cc.haveClass()) {
                    classnums++;
                }
            }

        }
        //打印周数,课程数,分段数
        System.out.print(Tb.getRowNums() + "@" + Tb.getCoursesNums() + "@" + classnums);
        //依次答应课程 {索引名}
        for (int i = 1; i <= Tb.getCoursesNums(); i++) {
            Course cc = Tb.getCourseAt(i);
            System.out.print("|" + cc.getCourseIdxName() + "@" + cc.getCourseName()+"@"+cc.getCourseType() + "@" + cc.getCoursePlace());
	}
        Date dat=new Date();
        //初始化date
        dat.setMonth(Integer.parseInt(Tb.getRowHead(1).getWeekStartDate().substring(0,Tb.getRowHead(1).getWeekStartDate().indexOf("-")))-1);
        dat.setDate(Integer.parseInt(Tb.getRowHead(1).getWeekStartDate().substring(Tb.getRowHead(1).getWeekStartDate().indexOf("-")+1,Tb.getRowHead(1).getWeekStartDate().length())));
        for (int i = 1; i <= Tb.getRowNums(); i++) {
            // 一周开始的时间
            int courest = 1;
            for (int o = 1; o <= Tb.getColumnNums(i); o++) {
                CourseColumn cc = Tb.getColum(i, o);
                if (cc.haveClass()) {
                    System.out.print("|" + cc.getCourseIdxName().replaceAll("\n", ""));
                    int date=dat.getMonth()+1;
                    int day=dat.getDate();
                    System.out.print("@"+StringUtil.plusZero((dat.getYear()+1900)+"",4)+"-"+StringUtil.plusZero(date+"",2)+"-"+StringUtil.plusZero(day+"",2));
                    System.out.print("@"
                            + StringUtil.plusZero(st.getStartTimeAt(courest).getHours()+"",2)
                            + ":"
                            + StringUtil.plusZero(st.getStartTimeAt(courest).getMinutes()+"",2));
                    courest = courest + cc.getCrossSpan();
                    System.out.print("@"
                            + StringUtil.plusZero(st.getEndTimeAt(courest - 1).getHours()+"",2)
                            + ":"
                            + StringUtil.plusZero(st.getEndTimeAt(courest - 1).getMinutes()+"",2));

                } else {
                    courest = courest + cc.getCrossSpan();
                }
                if (courest > 11) {
                    courest = 1;
                    dat=new Date(dat.getTime()+3600*24*1000);
                }
            }
        }

    }
}
