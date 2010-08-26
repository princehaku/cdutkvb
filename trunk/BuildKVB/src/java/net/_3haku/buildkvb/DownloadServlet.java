/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-25, 22:18:31
 */
package net._3haku.buildkvb;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author princehaku
 */
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (request.getParameter("key") == null) {
            response.getWriter().println("some error occured");
            return;
        }
        String key = request.getParameter("key");
        //把jar文件写入输出流
        File fr = null;
        try {
            fr = new File(BuilderServlet.tmpPath + "cdutkvb-" + key + ".jar");

            //设置消息头用于下载文件
            response.setHeader("Content-Disposition:", "attachment; filename=cdutkvb.jar");
            InputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(fr)));
            byte[] buf = new byte[2048];
            // 读进 缓冲区
            int num = fis.read(buf);
            while (num != (-1)) { // 是否读完文件
                response.getOutputStream().write(buf, 0, num);// 把文件数据写入文件
                response.getOutputStream().flush();// 刷新缓冲区
                num = fis.read(buf);// 继续从文件中读取下一部分
            }
            //关闭流
            fis.close();
        } catch (Exception ex) {

            response.getWriter().println("服务器好像发生了一些小错误..  请和我联系.<br />" + ex.getMessage());
        }
    }
}
