package net._3haku.util;

import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 * 下载网页内容
 *
 * @author princehaku
 */
public class Source {

    /** 存放cookie
     */
    static String cookieString = "";
    /**联网方式
     */
    static int ConnectType = 1;

    /**
     * url
     *
     * @param url
     *            提交地址
     * @param encode
     * @param type
     *            联网方式
     *        1-------------cmnet
     *        2-------------wap
     * @return content 页面内容
     */
    public String get(String url,String encode, int type) throws Exception {

        ConnectType = type;
        String content = "";
        HttpConnection httpConn = null;
        try {
            if (type == 1) {
                System.out.println("cmnet");
                httpConn = (HttpConnection) Connector.open(url, Connector.READ_WRITE,
                        true);
            } else {
                System.out.println("cmwap");
                httpConn = (HttpConnection) Connector.open("http://10.0.0.172/" + url.substring(url.indexOf("/", url.indexOf("//") + 2) + 1, url.length()), Connector.READ, true);
                httpConn.setRequestProperty("X-Online-Host", url.substring(url.indexOf("//") + 2, url.indexOf("/", url.indexOf("//") + 2)));
                httpConn.setRequestProperty("User-Agent", System.getProperty("microedition.profiles"));
            }
            httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
            //httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate");
            httpConn.setRequestProperty("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
            if (!(Source.cookieString.equals(""))) {
                // 晕死..
                httpConn.setRequestProperty("Cookie", "" + Source.cookieString + ";");
            }
            httpConn.setRequestProperty("Connection", "keep-alive");
            InputStream uurl = httpConn.openInputStream();

            if (httpConn.getHeaderField("Set-Cookie") != null) {
                String set_Cookie = httpConn.getHeaderField("Set-Cookie");
                Source.cookieString = set_Cookie.substring(0, set_Cookie.indexOf(";"));
            }
            int ch;
            ch = uurl.read();
            do {
                content += (char) ch;
                ch = uurl.read();
            } while (ch != -1);
            uurl.close();
            httpConn.close();
            //编码字符串
            content=new String(content.getBytes(),encode);
        } catch (Exception e) {
            content = "Error : ";
            content += e.getMessage();
            throw new Exception(e.getMessage());
         }
            return content;
    }
}