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
import net._3haku.kvb.time.SchoolTime;
import net._3haku.kvb.time.WinterSchoolTime;
import net._3haku.kvb.util.Parser;
import net._3haku.kvb.util.Source;
import net._3haku.kvb.util.StringUtil;

/**
 *
 * @author princehaku
 */
public class Do extends HttpServlet {
   

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
       req.setCharacterEncoding("utf8");
       resp.setCharacterEncoding("utf8");
       try {

			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			//课表的HTML
			String kbhtml = "";
			Source sc = new Source();
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
			res=sc.post("http://202.115.139.16/login.php", "upwd=be98c5a516&uname=200805030326&usertype=%D1%A7%C9%FA", "gb2312");

				//resp.getWriter().println("尝试1");
				res=sc.post("http://202.115.139.16/login.php","upwd="+pwd+"&uname="+sid+"&usertype=%D1%A7%C9%FA","gb2312");
				if(res.length()<10)	{
					//resp.getWriter().println("尝试2");
					res=sc.post("http://202.115.139.16/login.php","upwd="+pwd+"&uname="+sid+"&usertype=%D1%A7%C9%FA","gb2312");
				}
				////resp.getWriter().print(res);
				if(res.length()<10)	{
					throw new Exception("1");
				}
				if(res.indexOf("登录失败")!=-1)	{
					throw new Exception("2");
				}
			//得到课表url

				//resp.getWriter().println("尝试1");
				res=sc.get("http://202.115.139.16/sel_listsys/sel_listsys.php","gb2312");
				if(res.length()<10)	{
					//resp.getWriter().println("尝试2");
					res=sc.get("http://202.115.139.16/sel_listsys/sel_listsys.php","gb2312");
				}
				if(res.length()<10)	{
					throw new Exception("3");
				}
				String url=StringUtil.findMc(res, "\\\'查看所选课表总课表\\\' onclick=\\\"window.open\\(\\\'\\.\\.(.*?)\\\'\\,",1).replaceAll(" ","%20");

			////resp.getWriter().println(url);
			//resp.getWriter().println("尝试1");

				kbhtml=sc.get("http://202.115.139.16"+url,"gb2312");
				if(kbhtml.length()<10){
					//resp.getWriter().println("尝试2");
					kbhtml=sc.get("http://202.115.139.16"+url,"gb2312");
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
				resp.getWriter().print(result);
		} catch (Exception e) {
				resp.getWriter().print("error"+e.getMessage());
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
