/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-25, 19:52:06
 */

package net._3haku.buildkvb;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author princehaku
 */
public class JarBuilder {
    static final int BUFFER = 2048;
    String key="";
    JarBuilder(String key)
    {
        this.key=key;
    }
    public void compress(){
        Process p;
        String cmd = BuilderServlet.zipPath+"zip  "+BuilderServlet.tmpPath+"cdutkvb-"+key+".jar ./ -r";
        try {
            p = Runtime.getRuntime().exec(cmd,new String[]{""},new File(BuilderServlet.sourcePath+""));
            InputStream fis = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
