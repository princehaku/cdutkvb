/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-06, 10:26:40
 */

package net._3haku.kvb;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.*;

import net._3haku.kvb.CourseTable.CourseColumn;
import net._3haku.kvb.CourseTable.CourseTable;
import net._3haku.kvb.TimeFactory.SchoolTime;
import net._3haku.kvb.TimeFactory.WinterSchoolTime;
import net._3haku.kvb.bean.Course;
import net._3haku.kvb.util.Parser;
import net._3haku.kvb.util.Source;
import net._3haku.kvb.util.StringUtil;

@SuppressWarnings("serial")
public class CdutkvbServlet extends HttpServlet {
	
	/**-------------------------------------
	 * 返回的错误代码
	 * 0   参数无效
	 * 1   登陆失败 (超时)
	 * 2   登陆失败  (密码错误)
	 * 3   获取课表url失败 (超时)
	 * 4   获取课表HTML失败 (超时)
	 * -------------------------------------
	 * 返回的格式
	 * 正常    {总周数}@{课程数量}@{总分段数}|{{课程索引名@课程上课地点@上课时间@下课时间}}|end
	 */
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			//课表的HTML
			String kbhtml = "";
			
			if(req.getParameter("s")==null||req.getParameter("p")==null)
			{
				throw new Exception("0");
			}
			req.setCharacterEncoding("UTF-8");
			//学号
			String sid=req.getParameter("s");
			//密码
			String pwd=req.getParameter("p");
			int classnums=0;
			//从教务处得到课表的HTML
			//登陆
			String res="";
			if(!Source.s.equals(sid)||Source.cookieString.equals("")||Source.isSessionOutOfDate())
				{
				//resp.getWriter().println("尝试1");
				res=Source.post("http://202.115.139.16/login.php","upwd="+pwd+"&uname="+sid+"&usertype=%D1%A7%C9%FA","gb2312");
				if(res.length()<10)	{
					//resp.getWriter().println("尝试2");
					res=Source.post("http://202.115.139.16/login.php","upwd="+pwd+"&uname="+sid+"&usertype=%D1%A7%C9%FA","gb2312");
				}
				////resp.getWriter().print(res);
				if(res.length()<10)	{
					throw new Exception("1");
				}
				if(res.indexOf("登录失败")!=-1)	{
					throw new Exception("2");
				}
				Source.s=sid;
			}
			//得到课表url
			String url=Source.surl;
			if(!Source.s.equals(sid)||Source.surl.equals(""))
			{
				//resp.getWriter().println("尝试1");
				res=Source.get("http://202.115.139.16/sel_listsys/sel_listsys.php","gb2312");
				if(res.length()<10)	{
					//resp.getWriter().println("尝试2");
					res=Source.get("http://202.115.139.16/sel_listsys/sel_listsys.php","gb2312");
				}	
				if(res.length()<10)	{
					throw new Exception("3");
				}
				url=StringUtil.findMc(res, "\\\'查看所选课表总课表\\\' onclick=\\\"window.open\\(\\\'\\.\\.(.*?)\\\'\\,",1).replaceAll(" ","%20");
	
				Source.surl=url;
			}
			////resp.getWriter().println(url);
			//resp.getWriter().println("尝试1");
			if(!Source.s.equals(sid)||Source.res.equals(""))
			{
				kbhtml=Source.get("http://202.115.139.16"+url,"gb2312");
				if(kbhtml.length()<10){
					//resp.getWriter().println("尝试2");
					kbhtml=Source.get("http://202.115.139.16"+url,"gb2312");
				}
				if(kbhtml.length()<10){
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
		        String result="";
		        //打印周数,课程数,分段数
		        result=result+(Tb.getRowNums() + "@" + Tb.getCoursesNums() + "@" + classnums);
		        //依次打印课程 {索引名,课程名,课程类型,上课地点}
		        for (int i = 1; i <= Tb.getCoursesNums(); i++) {
		            Course cc = Tb.getCourseAt(i);
		            result=result+("|" + cc.getCourseIdxName() + "@" + cc.getCourseName()+"@"+cc.getCourseType() + "@" + cc.getCoursePlace());
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
		                	result=result+("|" + cc.getCourseIdxName().replaceAll("\n", ""));
		                    int date=dat.getMonth()+1;
		                    int day=dat.getDate();
		                    result=result+("@"+StringUtil.plusZero((dat.getYear()+1900)+"",4)+"-"+StringUtil.plusZero(date+"",2)+"-"+StringUtil.plusZero(day+"",2));
		                    result=result+("@"
		                            + StringUtil.plusZero(st.getStartTimeAt(courest).getHours()+"",2)
		                            + ":"
		                            + StringUtil.plusZero(st.getStartTimeAt(courest).getMinutes()+"",2));
		                    courest = courest + cc.getCrossSpan();
		                    result=result+("@"
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
		        result=result+("|end");
				Source.res=result;
				}
				resp.getWriter().print(Source.res);
		} catch (Exception e) {
				resp.getWriter().print("error"+e.getMessage());
				resp.getWriter().close();
				//e.printStackTrace();
		}
	}
}
