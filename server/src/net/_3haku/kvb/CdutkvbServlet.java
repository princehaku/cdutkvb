/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-06, 10:26:40
 */

package net._3haku.kvb;

import java.io.IOException;

import javax.servlet.http.*;

import net._3haku.kvb.CourseTable.CourseColumn;
import net._3haku.kvb.CourseTable.CourseTable;
import net._3haku.kvb.TimeFactory.SchoolTime;
import net._3haku.kvb.TimeFactory.WinterSchoolTime;
import net._3haku.kvb.util.Parser;
import net._3haku.kvb.util.Source;
import net._3haku.kvb.util.StringUtil;

@SuppressWarnings("serial")
public class CdutkvbServlet extends HttpServlet {
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		//课表的HTML
		String kbhtml = "";
		Source kvb=new Source();
		if(req.getParameter("s")==null||req.getParameter("p")==null)
		{
			resp.getWriter().print("error");
			resp.getWriter().close();
			}
		//学号
		String sid=req.getParameter("s");
		//密码
		String pwd=req.getParameter("p");
		int classnums=0;
		//从教务处得到课表的HTML
		//登陆
		kvb.post("http://202.115.139.16/login.php","uname="+sid+"&upwd="+pwd+"&usertype=%D1%A7%C9%FA","gb2312");
		//得到课表url
		String res=kvb.get("http://202.115.139.16/sel_listsys/sel_listsys.php","gb2312");
		if(res.equals(""))
		{
			resp.getWriter().print("error");
			resp.getWriter().close();
			}
		String url=StringUtil.findMc(res, "\\\'查看所选课表总课表\\\' onclick=\\\"window.open\\(\\\'\\.\\.(.*?)\\\'\\,",1).replaceAll(" ","%20");
		kbhtml=kvb.get("http://202.115.139.16"+url,"gb2312");
		// 解析课表
		try {
			CourseTable Tb;
			Tb = Parser.parseTable(kbhtml);
			//计算总课数
			SchoolTime st = new WinterSchoolTime();
			for (int i = 1; i <= Tb.getRowNums(); i++) {
				for (int o = 1; o <= Tb.getColumnNums(i); o++) {
					CourseColumn cc = Tb.getColum(i, o);
					if (cc.haveClass()) {classnums++;}
				}
				
			}
			resp.getWriter().print(Tb.getRowNums() + "@" + Tb.getCoursesNums()+"@"+classnums);
			for (int i = 1; i <= Tb.getRowNums(); i++) {
				// 一周开始的时间
				int courest = 1;
				for (int o = 1; o <= Tb.getColumnNums(i); o++) {
					CourseColumn cc = Tb.getColum(i, o);
					if (cc.haveClass()) {
						resp.getWriter().print("|"+cc.getCourseIdxName().replaceAll("\n", "") + "@"
										+ cc.getCoursePlace());
						resp.getWriter().print("@"
										+ st.getStartTimeAt(courest).getHours()
										+ ":"
										+ st.getStartTimeAt(courest)
												.getMinutes());
						courest = courest + cc.getCrossSpan();
						resp.getWriter().print("@"
										+ st.getEndTimeAt(courest - 1)
												.getHours()
										+ ":"
										+ st.getStartTimeAt(courest - 1)
												.getMinutes());
						
					} else {
						courest = courest + cc.getCrossSpan();
					}
					if (courest > 11)
						courest = 1;
				}
			}
			resp.getWriter().print("|end");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resp.getWriter().print("error");
			resp.getWriter().close();
			e.printStackTrace();
		}
	}
}
