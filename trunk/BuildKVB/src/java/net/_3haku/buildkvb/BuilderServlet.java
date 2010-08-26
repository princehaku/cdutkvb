package net._3haku.buildkvb;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author princehaku
 */
public class BuilderServlet extends HttpServlet {

    
    /*static String tmpPath="E:/JAVA/KVB/BuildKVB/tmp/";
    static String zipPath="E:/JAVA/KVB/BuildKVB/exec/";
    static String javaPath="";
    static String sourcePath="E:/JAVA/KVB/BuildKVB/S/";
    static String delCommand="del";*/
    static String tmpPath="/tmp/tomcat6-temp/";
    static String zipPath="/usr/bin/";
    static String javaPath="/usr/bin/";
    static String sourcePath="/var/S/";
    static String delCommand="rm";
     /**/
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String auth="";
        for(Cookie c:request.getCookies())
        {
            if(c.getName().equals("auth"))
                auth=c.getValue();
        }
        if(auth.equals(""))
        {
            response.getWriter().println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><body>sorry...you dont't have an authkey,<br />please visit <a href=http://3haku.net>http://3haku.net</a> and click download link</body>");
            return;
        }
        String key=MD5.getMD5((auth).getBytes());
        
        try{
            //创建key的java文件
            GeneralKey gk=new GeneralKey(key);
            gk.run();
            //创建key的class文件
            Combiner cb=new Combiner(key);
            cb.run();
            //创建jar文件
            JarBuilder jb=new JarBuilder(key);
            jb.run();
            String cmd = BuilderServlet.zipPath+"zip  "+BuilderServlet.tmpPath+"cdutkvb-"+key+".jar ./ -r";
            response.getWriter().println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><meta http-equiv='refresh' content='5;url=download?key="+key+
                    "'><br />please wait....<br />下载即将开始!!!!<br/><br/><br/><img src=/d.png /><br />如果您使用ie..请向上图一样操作");
        }
        catch(Exception ex)
        {
            response.getWriter().println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'><meta http-equiv='refresh' content='1;url=/getjar><br />发生了错误.."+ex.getMessage()+"<br />正在重试");
        }
} 
@Override
public String getServletInfo() {
        return "Short description";

}// </editor-fold>

}
