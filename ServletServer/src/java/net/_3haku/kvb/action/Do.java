/*
 *  Document   : index
 *  Created on : 17-八月-10, 下午 03:42
 *  Author     : princehaku
 */
package net._3haku.kvb.action;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net._3haku.kvb.bean.Course;
import net._3haku.kvb.coursetable.CourseColumn;
import net._3haku.kvb.coursetable.CourseTable;
import net._3haku.kvb.db.Hb;
import net._3haku.kvb.db.Productlock;
import net._3haku.kvb.time.SchoolTime;
import net._3haku.kvb.time.WinterSchoolTime;
import net._3haku.kvb.util.MD5;
import net._3haku.kvb.util.Parser;
import net._3haku.kvb.util.Source;
import net._3haku.kvb.util.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author princehaku
 */
public class Do extends HttpServlet {

    /**-------------------------------------
     * 传入的参数
     * s   用户学号
     * p   用户密码
     * k   验证密钥
     * -------------------------------------
     * 返回的错误代码
     * 0   参数无效
     * 1   登陆失败 (超时)
     * 2   登陆失败  (密码错误)
     * 3   获取课表url失败 (超时)
     * 4   获取课表HTML失败 (超时)
     * 5   服务器内部错误
     * 6   错误的客户端版本号 (J2ME only)
     * -------------------------------------
     * 返回的格式
     * 正常    {总周数}@{课程数量}@{总分段数}|{{课程索引名@课程上课地点@上课时间@下课时间}}|end
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            resp.setContentType("text/plain");
            //课表的HTML
            String kbhtml = "";
            Source sc = new Source();
            if (req.getParameter("s") == null || req.getParameter("p") == null|| req.getParameter("k") == null) {
                throw new Exception("0");
            }
            req.setCharacterEncoding("UTF-8");
            //学号
            String sid = req.getParameter("s");
            //密码
            String pwd = req.getParameter("p");

            String key = req.getParameter("k");
            
            String pString = MD5.getMD5(key.getBytes());
            //System.out.println(pString);
            Session ses = Hb.getSessionFactory().openSession();
            Query wes = ses.createQuery("from Productlock where pstring='" + pString + "'");
            if (wes.list().size() == 0) {
                Productlock pl = new Productlock(pString, sid);
                ses.save(pl);
            }
            Productlock lastPl = (Productlock) wes.list().get(0);
            ses.connection().commit();
            ses.flush();
            ses.close();
            //版本判断
            if (!key.equals("998914a777898389484faf4ed0fb607e")&&!lastPl.getUid().equals(sid)) {
                throw new Exception("5");
            }
            int classnums = 0;
            //从教务处得到课表的HTML
            //登陆
            String res = "";
            //resp.getWriter().println("尝试1");
            res = sc.post("http://202.115.139.16/login.php", "upwd=" + pwd + "&uname=" + sid + "&usertype=%D1%A7%C9%FA", "gb2312");
            if (res.length() < 10) {
                //resp.getWriter().println("尝试2");
                res = sc.post("http://202.115.139.16/login.php", "upwd=" + pwd + "&uname=" + sid + "&usertype=%D1%A7%C9%FA", "gb2312");
            }
            ////resp.getWriter().print(res);
            if (res.length() < 10) {
                throw new Exception("1");
            }
            if (res.indexOf("登录失败") != -1) {
                throw new Exception("2");
            }
            //得到课表url

            //resp.getWriter().println("尝试1");
            res = sc.get("http://202.115.139.16/sel_listsys/sel_listsys.php", "gb2312");
            if (res.length() < 10) {
                //resp.getWriter().println("尝试2");
                res = sc.get("http://202.115.139.16/sel_listsys/sel_listsys.php", "gb2312");
            }
            if (res.length() < 10) {
                throw new Exception("3");
            }
            String url = StringUtil.findMc(res, "\\\'查看所选课表总课表\\\' onclick=\\\"window.open\\(\\\'\\.\\.(.*?)\\\'\\,", 1).replaceAll(" ", "%20");

            ////resp.getWriter().println(url);
            //resp.getWriter().println("尝试1");

            kbhtml = sc.get("http://202.115.139.16" + url, "gb2312");
            if (kbhtml.length() < 10) {
                //resp.getWriter().println("尝试2");
                kbhtml = sc.get("http://202.115.139.16" + url, "gb2312");
            }
            if (kbhtml.length() < 10) {
                throw new Exception("4");
            }
            // 解析课表
            CourseTable Tb;
            SchoolTime st = new WinterSchoolTime();
            Tb = Parser.parseTable(kbhtml);
            //计算总课数
            for (int i = 1; i <= Tb.getRowNums(); i++) {
                for (int o = 1; o <= Tb.getColumnNums(i); o++) {
                    CourseColumn cc = Tb.getColum(i, o);
                    if (cc.haveClass()) {
                        classnums++;
                    }
                }

            }
            String result = "";
            //打印周数,课程数,分段数
            result = result + (Tb.getRowNums() + "@" + Tb.getCoursesNums() + "@" + classnums);
            //依次打印课程 {索引名,课程名,课程类型,上课地点}
            for (int i = 1; i <= Tb.getCoursesNums(); i++) {
                Course cc = Tb.getCourseAt(i);
                result = result + ("|" + cc.getCourseIdxName() + "@" + cc.getCourseName() + "@" + cc.getCourseType() + "@" + cc.getCoursePlace());
            }
            Date dat = new Date();
            //初始化date
            dat.setMonth(Integer.parseInt(Tb.getRowHead(1).getWeekStartDate().substring(0, Tb.getRowHead(1).getWeekStartDate().indexOf("-"))) - 1);
            dat.setDate(Integer.parseInt(Tb.getRowHead(1).getWeekStartDate().substring(Tb.getRowHead(1).getWeekStartDate().indexOf("-") + 1, Tb.getRowHead(1).getWeekStartDate().length())));
            for (int i = 1; i <= Tb.getRowNums(); i++) {
                // 一周开始的时间
                int courest = 1;
                for (int o = 1; o <= Tb.getColumnNums(i); o++) {
                    CourseColumn cc = Tb.getColum(i, o);
                    if (cc.haveClass()) {
                        result = result + ("|" + cc.getCourseIdxName().replaceAll("\n", ""));
                        int date = dat.getMonth() + 1;
                        int day = dat.getDate();
                        result = result + ("@" + StringUtil.plusZero((dat.getYear() + 1900) + "", 4) + "-" + StringUtil.plusZero(date + "", 2) + "-" + StringUtil.plusZero(day + "", 2));
                        result = result + ("@"
                                + StringUtil.plusZero(st.getStartTimeAt(courest).getHours() + "", 2)
                                + ":"
                                + StringUtil.plusZero(st.getStartTimeAt(courest).getMinutes() + "", 2));
                        courest = courest + cc.getCrossSpan();
                        result = result + ("@"
                                + StringUtil.plusZero(st.getEndTimeAt(courest - 1).getHours() + "", 2)
                                + ":"
                                + StringUtil.plusZero(st.getEndTimeAt(courest - 1).getMinutes() + "", 2));

                    } else {
                        courest = courest + cc.getCrossSpan();
                    }
                    if (courest > 11) {
                        courest = 1;
                        dat = new Date(dat.getTime() + 3600 * 24 * 1000);
                    }
                }
            }
            result = result + ("|end");
            resp.getWriter().print(result);
        } catch (Exception e) {
            String exs=e.getMessage();
            try{
                Integer.parseInt(e.getMessage().substring(0, 1));
            }
            catch(Exception ex)
            {
                exs="6"+e.getMessage().toString();
            }
            resp.getWriter().print("error" +exs);
            resp.getWriter().close();
            //e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
