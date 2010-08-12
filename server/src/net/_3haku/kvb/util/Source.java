/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 */
package net._3haku.kvb.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * 下载网页内容
 * 
 * @author princehaku
 */
public class Source {
    /** 存放cookie
     */
	public static String cookieString="";
	/**存放的时间 用于使session过期
	 * 
	 */
	static Long timespan;
	/**课表的url
	 * 
	 */
	public static String surl="";
	/**课表的HTML
	 * 
	 */
	public static String res="";
	
	public static String s="";
	/**Session 是否过期
	 * @return boolean
	 */
	public static boolean isSessionOutOfDate()
	{
		if((new Date().getTime()-timespan)>300000)
			return true;
		else
			return false;
	}
	/**
	 * url
	 * 
	 * @param url
	 *            提交地址
	 * @param encode
	 *            编码
	 */
	@SuppressWarnings("finally")
	public static String get(String url, String encode) {

		String line = "";

		String content = "";
		
		timespan=new Date().getTime();

		// System.out.println(Inc.cookieString);

		HttpURLConnection httpConn = null;

		try {
			URL turl = new URL(url);
			// System.out.println(url);
			httpConn = (HttpURLConnection) turl.openConnection();
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000); 
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("Host", turl.getHost());
			httpConn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5 FBSMTWB");
			httpConn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
			httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate");
			httpConn.setRequestProperty("Accept-Charset","GB2312,utf-8;q=0.7,*;q=0.7");
			if (!(Source.cookieString.equals(""))) {
				// 晕死..
				httpConn.setRequestProperty("Cookie", "" + Source.cookieString+ ";");
				// System.out.print("发送cookie======="+Inc.cookieString);
			}
			httpConn.setRequestProperty("Keep-Alive", "300");
			httpConn.setRequestProperty("Connection", "keep-alive");
			// System.out.println(httpConn.getResponseMessage());
			httpConn.setRequestProperty("Cache-Control", "no-cache");

			InputStream uurl;

			uurl = httpConn.getInputStream();

			if (httpConn.getHeaderField("Set-Cookie") != null) {
				String set_Cookie = httpConn.getHeaderField("Set-Cookie");
				// System.out.println("得到cookie"+set_Cookie);
				Source.cookieString = set_Cookie.substring(0, set_Cookie.indexOf(";"));
				// System.out.println(Inc.cookieString);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(uurl,encode));
			while (line != null) {
				line = br.readLine();
				if (line   !=   null)
					content = content.toString() + line.toString() + "\n";
			}

		} catch (Exception e) {
			// System.out.println(e.getMessage());
			System.out.println(e.getMessage());
		} finally {
			// 关闭连接
			httpConn.disconnect();
			// System.out.println(content);
			return content;
		}
	}
	/**
	 * url
	 * 
	 * @param url
	 *            提交的url地址
	 * @param parm
	 *            参数
	 * @param encode
	 *            编码
	 */
	@SuppressWarnings("finally")
	public static String post(String url, String parm, String encode) {

		String line = "";

		String content = "";
		
		timespan=new Date().getTime();

		// System.out.println(Inc.cookieString);

		HttpURLConnection httpConn = null;

		try {
			URL turl = new URL(url);
			// System.out.println(url);
			httpConn = (HttpURLConnection) turl.openConnection();
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000); 
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Host", turl.getHost());
			httpConn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5 FBSMTWB");
			httpConn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
			httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate");
			httpConn.setRequestProperty("Accept-Charset",
					"GB2312,utf-8;q=0.7,*;q=0.7");
			if (!(Source.cookieString.equals(""))) {
				// 晕死..
				httpConn.setRequestProperty("Cookie", "" + Source.cookieString
						+ ";");
				System.out.println("发送cookie======="+Source.cookieString);
			}
			httpConn.setRequestProperty("Keep-Alive", "300");
			httpConn.setRequestProperty("Connection", "keep-alive");
			// System.out.println(httpConn.getResponseMessage());
			httpConn.setRequestProperty("Cache-Control", "no-cache");
			httpConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Content-Length", String.valueOf(parm.length()));
			httpConn.setConnectTimeout(30000);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			OutputStreamWriter out = new OutputStreamWriter(httpConn
					.getOutputStream(), encode);

			out.write(parm);

			out.close();

			InputStream uurl;

			uurl = httpConn.getInputStream();

			if (httpConn.getHeaderField("Set-Cookie") != null) {
				String set_Cookie = httpConn.getHeaderField("Set-Cookie");
				System.out.println("得到cookie"+set_Cookie);
				Source.cookieString = set_Cookie.substring(0, set_Cookie
						.indexOf(";"));
				// System.out.println(Inc.cookieString);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(uurl,
					encode));
			while (line != null) {
				line = br.readLine();
				if (line != null)
					content = content.toString() + line.toString() + "\n";
			}

		} catch (Exception e) {
			// System.out.println(e.getMessage());
			System.out.println(e.getMessage());
		} finally {
			// 关闭连接
			httpConn.disconnect();
			//System.out.println(content);
			return content;
		}
	}
}