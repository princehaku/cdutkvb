package virtualbroser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载网页内容
 * 
 * @author princehaku
 */
public class VB {
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

		HttpURLConnection httpConn = null;

		try {
			URL turl = new URL(url);
			// System.out.println(url);
			httpConn = (HttpURLConnection) turl.openConnection();
			httpConn.setRequestMethod("GET");
                        httpConn.connect();
			InputStream uurl=null;
			uurl = httpConn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(uurl,encode));
			while (line != null) {
				line = br.readLine();
				if (line   !=   null)
					content = content.toString() + line.toString() + "\n";
			}
			// 关闭连接
			httpConn.disconnect();
			// System.out.println(content);
			return content;

		} catch (Exception e) {
			// 关闭连接
			httpConn.disconnect();
			// System.out.println(e.getMessage());
			throw e;
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
	
	@SuppressWarnings("finally")
	public String post(String url, String parm, String encode) throws Exception{

		String line = "";
		String content = "";
		HttpURLConnection httpConn = null;
		try {
			URL turl = new URL(url);
			// System.out.println(url);
			httpConn = (HttpURLConnection) turl.openConnection();
			httpConn.setRequestMethod("POST");
			if (!(getCookieString().equals(""))) {
				// 晕死..
				httpConn.setRequestProperty("Cookie", "" + getCookieString()
						+ ";");
				// System.out.print("发送cookie======="+Inc.cookieString);
			}
			httpConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Content-Length", String.valueOf(parm.length()));
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
			// 关闭连接
			httpConn.disconnect();
			// System.out.println(content);
			return content;

		} catch (Exception e) {
			// 关闭连接
			httpConn.disconnect();
			// System.out.println(e.getMessage());
			throw e;
		} 
	} 
	void setCookieString(String cookieString) {
		this.cookieString = cookieString;
	}
	public String getCookieString() {
		return cookieString;
	}*/
}