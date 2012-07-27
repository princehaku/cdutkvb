/*
 *  Document   : index
 *  Created on : 17-八月-10, 下午 03:42
 *  Author     : princehaku
 */
package net._3haku.kvb.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net._3haku.kvb.coursetable.CourseTable;
import net._3haku.kvb.util.MD5;
import net._3haku.kvb.util.Parser;
import net._3haku.kvb.util.Source;
import net._3haku.kvb.util.StringUtil;

/**
 *
 * @author princehaku
 */
public class Do extends HttpServlet {

    /**
     * ------------------------------------- 传入的参数 s 用户学号 p 用户密码 k 验证密钥 ------------------------------------- 返回的错误代码 0
     * 参数无效 1 登陆失败 (超时) 2 登陆失败 (密码错误) 3 获取课表url失败 (超时) 4 获取课表HTML失败 (超时) 5 服务器内部错误 6 错误的客户端版本号 (J2ME only)
     * ------------------------------------- 返回的格式 正常 {总周数}@{课程数量}@{总分段数}|{{课程索引名@课程上课地点@课程类型@课程学分@课程老师@上课时间@下课时间}}|end
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
            if (req.getParameter("s") == null || req.getParameter("p") == null) {
                throw new Exception("0");
            }
            req.setCharacterEncoding("UTF-8");
            //学号
            String sid = req.getParameter("s");
            //密码
            String pwd = req.getParameter("p");
            //从教务处得到课表的HTML
            //登陆
            String res = "";
            System.out.println("登录尝试1");
            Map maps = new HashMap();
            maps.put("upwd", pwd);
            maps.put("uname", sid);
            maps.put("usertype", "学生");
            res = sc.post("http://202.115.139.16/login.php", maps, "gb2312");
            if (res.length() < 10) {
                System.out.println("登录尝试2");
                res = sc.post("http://202.115.139.16/login.php", maps, "gb2312");
            }
            ////resp.getWriter().print(res);
            if (res.length() < 10) {
                throw new Exception("1");
            }
            if (res.indexOf("登录失败") != -1) {
                throw new Exception("2");
            }
            //得到课表url
            System.out.println("课表url尝试1");
            res = sc.get("http://202.115.139.16/sel_listsys/sel_listsys.php", "gb2312");
            if (res.length() < 10) {
                System.out.println("课表url尝试2");
                res = sc.get("http://202.115.139.16/sel_listsys/sel_listsys.php", "gb2312");
            }
            if (res.length() < 10) {
                throw new Exception("3");
            }
            String url = StringUtil.findMc(res, "\\\'查看所选课表总课表\\\' onclick=\\\"window.open\\(\\\'\\.\\.(.*?)\\\'\\,", 1).replaceAll(" ", "%20");

            ////resp.getWriter().println(url);
            System.out.println("课表html尝试1");

            kbhtml = sc.get("http://202.115.139.16" + url, "gb2312");
            if (kbhtml.length() < 10) {
                System.out.println("课表html尝试2");
                kbhtml = sc.get("http://202.115.139.16" + url, "gb2312");
            }
            if (kbhtml.length() < 10) {
                throw new Exception("4");
            }
            // 解析课表
            CourseTable Tb;
            Parser psr = new Parser();
            Tb = psr.parseTable(kbhtml);
            String result = Tb.toString();
            resp.getWriter().print(result);
        } catch (Exception e) {
            String exs = e.getMessage();
            e.printStackTrace();
            try {
                Integer.parseInt(e.getMessage().substring(0, 1));
            } catch (Exception ex) {
                exs = "5" + e.getMessage().toString();
            }
            resp.getWriter().print("error" + exs);
            resp.getWriter().close();
            //e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
