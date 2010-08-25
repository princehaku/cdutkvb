/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 24-八月-10, 下午 05:02
 */

package net._3haku.buildkvb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author princehaku
 */
public class Combiner implements Runnable{
     String key="";
     public Combiner(String key){
        this.key=key;
     }
    public void run() {
        Process p;
        String cmd = "javac -g:none "+BuilderServlet.tmpPath+"Key.java";
        try {
            p = Runtime.getRuntime().exec(cmd);
            InputStream fis = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
