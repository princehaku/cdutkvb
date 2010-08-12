/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-12, 21:54:50
 */

package net._3haku.util;

/**用于J2ME的分裂字符串函数
 *
 * @author princehaku
 */
public class StringTokenizer {
    /**源数据
     *
     */
    private String source;
    /**分离符
     *
     */
    private String sperator;
    /**当前解析到的位置
     *
     */
    private int pos;
    /**
     *
     * @param string
     * @param sperator
     */
    public StringTokenizer(String string, String sperator) {
        setSource(string);
        setSperator(sperator);
        pos=0;
    }
    /**是否还有分词
     * @return
     */
    public boolean hasMoreTokens() {
        //int pos1=getSource().indexOf(sperator,this.pos);
        if(pos>=getSource().length())
            return false;
        else
            return true;
    }
    /**下一个分词
     *
     * @return
     */
    public String nextToken() {
        int pos1=getSource().indexOf(sperator,this.pos);
        //如果找不到了..则将指针指向元素最大空间
        if(pos1==-1)
            pos1=getSource().length();
        int poslast=this.pos;
        this.pos=pos1+sperator.length();
        return getSource().substring(poslast, pos1);
    }
    private String getSource() {
        return source;
    }

    private void setSource(String source) {
        this.source = source;
    }

    private String getSperator() {
        return sperator;
    }

    private void setSperator(String sperator) {
        this.sperator = sperator;
    }


}
