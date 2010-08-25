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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author princehaku
 */
public class JarBuilder {
    static final int BUFFER = 2048;
    JarBuilder()
    {

    }
    public void compress(){
        Process p;
        String cmd = Builder.zipPath+"zip  "+Builder.tmpPath+"cdutkvb.jar ./ -r";
        System.out.println(cmd);
        try {
            p = Runtime.getRuntime().exec(cmd,new String[]{""},new File(Builder.sourcePath+""));
            InputStream fis = p.getInputStream();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
