package VirtualBroser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载网页内容
 * 
 * @author princehaku
 */
public class VB {
    /** 存放cookie
     */
	private String cookieString="";
	/**
	 * url
	 * 
	 * @param url
	 *            提交地址
	 * @param encode
	 *            编码
	 */
	@SuppressWarnings("finally")
	public String get(String url, String encode) throws Exception {

		String line = "";

		String content = "";

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
			//httpConn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5 FBSMTWB");
			httpConn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
			httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate");
			httpConn.setRequestProperty("Accept-Charset","GB2312,utf-8;q=0.7,*;q=0.7");
			if (!(getCookieString().equals(""))) {
				// 晕死..
				httpConn.setRequestProperty("Cookie", "" + getCookieString()
						+ ";");
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
				setCookieString(set_Cookie.substring(0, set_Cookie
						.indexOf(";")));
				// System.out.println(Inc.cookieString);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(uurl,
					encode));
			while (line != null) {
				line = br.readLine();
				if (line   !=   null)
					content = content.toString() + line.toString() + "\n";
			}

		} catch (Exception e) {
			// System.out.println(e.getMessage());
			throw e;
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
	public String post(String url, String parm, String encode) throws Exception{

		String line = "";

		String content = "";

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
			//httpConn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.1.5) Gecko/20091102 Firefox/3.5.5 FBSMTWB");
			httpConn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
			httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate");
			httpConn.setRequestProperty("Accept-Charset",
					"GB2312,utf-8;q=0.7,*;q=0.7");
			if (!(getCookieString().equals(""))) {
				// 晕死..
				httpConn.setRequestProperty("Cookie", "" + getCookieString()
						+ ";");
				// System.out.print("发送cookie======="+Inc.cookieString);
			}
			httpConn.setRequestProperty("Keep-Alive", "300");
			httpConn.setRequestProperty("Connection", "keep-alive");
			// System.out.println(httpConn.getResponseMessage());
			httpConn.setRequestProperty("Cache-Control", "no-cache");
			httpConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Content-Length", String.valueOf(parm
					.length()));
			httpConn.setConnectTimeout(10000);
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
				// System.out.println("得到cookie"+set_Cookie);
				setCookieString(set_Cookie.substring(0, set_Cookie
						.indexOf(";")));
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
			throw e;
		} finally {
			// 关闭连接
			httpConn.disconnect();
			// System.out.println(content);
			return content;
		}
	}
	void setCookieString(String cookieString) {
		this.cookieString = cookieString;
	}
	public String getCookieString() {
		return cookieString;
	}
}