/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-2-04, 11:34:50
 */

package net._3haku.util;

/**用于进行替换字符串中的字符串
 *
 * @author princehaku
 */
public class StringReplacer {
    private String source;

    public StringReplacer(String string) {
        this.source=string;
    }
    /*替换字符串
     *
     */
    public String replaceAll(String from,String to){
        this.source=replaceAll(from,to,source);
        return source;
    }
    /**
     *
     * @param from
     * @param to
     * @param source
     * @return
     */
    private String replaceAll(String from, String to, String source) {
        int st=source.indexOf(from);
        if(st==-1)
            return source;
        source=source.substring(0,st)+to+source.substring(st+from.length(),source.length());
        return replaceAll(from,to,source);
    }

    public String getString() {
        return this.source;
    }
}
