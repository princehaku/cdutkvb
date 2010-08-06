package net._3haku.kvb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.*;

import net._3haku.kvb.CourseTable.CourseColumn;
import net._3haku.kvb.CourseTable.CourseTable;
import net._3haku.kvb.TimeFactory.SchoolTime;
import net._3haku.kvb.TimeFactory.WinterSchoolTime;
import net._3haku.kvb.util.Parser;

@SuppressWarnings("serial")
public class CdutkvbServlet extends HttpServlet {
	@SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		String kbhtml = "";
		FileInputStream fs = new FileInputStream("res/kb.html");
		InputStreamReader read = new InputStreamReader(fs, "gb2312");
		BufferedReader in = new BufferedReader(read);
		while (in.ready())
			kbhtml += in.readLine();
		fs.close();
		// 解析课表
		try {
			CourseTable Tb;
			Tb = Parser.parseTable(kbhtml);
			// System.out.println(Tb.getRowNums());
			SchoolTime st = new WinterSchoolTime();
			resp.getWriter().print(Tb.getRowNums() + "@" + Tb.getCoursesNums());
			for (int i = 1; i <= Tb.getRowNums(); i++) {
				// 一周开始的时间
				int courest = 1;
				for (int o = 1; o <= Tb.getColumnNums(i); o++) {
					CourseColumn cc = Tb.getColum(i, o);
					if (cc.haveClass()) {
						resp.getWriter().print("|");
						resp.getWriter().print(
								cc.getCourseIdxName() + "@"
										+ cc.getCoursePlace());
						resp.getWriter().print(
								"@"
										+ st.getStartTimeAt(courest).getHours()
										+ ":"
										+ st.getStartTimeAt(courest)
												.getMinutes());
						courest = courest + cc.getCrossSpan();
						resp.getWriter().print(
								"@"
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
