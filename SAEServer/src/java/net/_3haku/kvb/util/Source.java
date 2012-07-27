/**
 * Copyright (c) 2010 princehaku All right reserved. Author princehaku Site http://3haku.net
 */
package net._3haku.kvb.util;

import com.sina.sae.fetchurl.SaeFetchurl;
import java.io.*;
import java.util.Map;

/**
 * 下载网页内容
 *
 * @author princehaku
 */
public class Source {

    /**
     * 存放cookie
     */
    SaeFetchurl fetchUrl = new SaeFetchurl();

    /**
     * url
     *
     * @param url 提交地址
     * @param encode 编码
     */
    public String get(String url, String encode) throws UnsupportedEncodingException {
        fetchUrl.setMethod("get");
        String result = fetchUrl.fetch(url);
        byte[] bs = result.getBytes("iso8859-1");
        String content = new String(bs, encode);
        return content;
    }

    /**
     * url
     *
     * @param url 提交的url地址
     * @param parm 参数
     * @param encode 编码
     */
    public String post(String url, Map maps, String encode) throws UnsupportedEncodingException {
        fetchUrl.setMethod("post");
        fetchUrl.setPostData(maps);
        String result = fetchUrl.fetch(url);
        byte[] bs = result.getBytes("iso8859-1");
        String content = new String(bs, encode);
        return content;
    }
}