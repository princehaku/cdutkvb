/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-7-27, 22:32:19
 */

package net._3haku.kvb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author princehaku
 */
public class StringUtil {

    /**正则匹配,返回模式串里面第pos个模式匹配的字符串
     *
     * @param SourceString
     * @param RegxString
     * @param pos
     * @return
     */
    public static String findMc(String SourceString,String RegxString,int pos){
            Pattern pn=Pattern.compile(RegxString);
            Matcher mc=pn.matcher(SourceString);
            if(mc.find())
                return mc.group(pos);
            return "";
    }
    /**字符串前补0
     *
     */
    public static String plusZero(String s,int length)
    {
        if(s.length()<length)
            return "0"+plusZero(s,length-1);
        else
            return s;
    }
}
