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
	static String cookieString="";
	/**
	 * url
	 * 
	 * @param url
	 *            提交地址
	 * @param encode
	 *            编码
	 * @return content 页面内容
	 */
	public String get(String url, String encode) {

		//String line = "";

		String content = "";

		// System.out.println(Inc.cookieString);

		HttpConnection httpConn = null;

		try {
			// System.out.println(url);
			httpConn = (HttpConnection) Connector.open(url, Connector.READ_WRITE,true);
			//httpConn.setConnectTimeout(30000);
			//httpConn.setReadTimeout(30000);
			httpConn.setRequestProperty("Accept-Charset","utf-8;q=0.7,*;q=0.7");
			if (!(Source.cookieString.equals(""))) {
				// 晕死..
				httpConn.setRequestProperty("Cookie", "" + Source.cookieString
						+ ";");
				// System.out.print("发送cookie======="+Inc.cookieString);
			}
			//httpConn.setRequestProperty("Keep-Alive", "300");
			httpConn.setRequestProperty("Connection", "keep-alive");
			//System.out.println(httpConn.getResponseMessage());
			InputStream uurl= httpConn.openInputStream();

			if (httpConn.getHeaderField("Set-Cookie") != null) {
				String set_Cookie = httpConn.getHeaderField("Set-Cookie");
				// System.out.println("得到cookie"+set_Cookie);
				Source.cookieString = set_Cookie.substring(0, set_Cookie
						.indexOf(";"));
				//System.out.println(httpConn.getHeaderField("Timestamp"));
			}
                        //InputStreamReader br=new InputStreamReader(uurl, encode);
                   int ch;
                   ch = uurl.read();
                   do {
                        content +=(char) ch;
                        ch = uurl.read();
                        } while (ch != -1);
			uurl.close();
                        httpConn.close();
                        //转换编码
                        content = new String(content.getBytes(),encode);
		} catch (Exception e) {
			// System.out.println(e.getMessage());
                        content="error0";
                        content+=e.getMessage();
                }
                    return content;
	}
}