/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-25, 19:52:06
 */

package net._3haku.buildkvb;

import java.io.File;

/**
 *
 * @author princehaku
 */
public class JarBuilder{
    static final int BUFFER = 2048;
    String key="";
    JarBuilder(String key)
    {
        this.key=key;
    }
    public void run() throws Exception {
        //复写KEY文件
        File classFile=new File(BuilderServlet.tmpPath+"Key.class");
        File keyFile=new File(BuilderServlet.sourcePath+"net/_3haku/key/Key.class");
        keyFile.delete();
        classFile.renameTo(keyFile);
        //如果之前存在了对应key文件的jar 则返回
        File file = new File(BuilderServlet.tmpPath+"cdutkvb-"+key+".jar");
        if(file.isFile() && file.exists()){
            return;
            //file.delete();
        }
        //创建jar文件
        Process p;
        String cmd = BuilderServlet.zipPath+"zip  "+BuilderServlet.tmpPath+"cdutkvb-"+key+".jar ./ -r";
        try {
            p = Runtime.getRuntime().exec(cmd,new String[]{""},new File(BuilderServlet.sourcePath+"/"));
            p.waitFor();
        } catch (Exception e) {
            throw e;
        }
}
}
