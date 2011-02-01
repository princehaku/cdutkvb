/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 24-八月-10, 下午 05:02
 */

package net._3haku.buildkvb;

/**
 *
 * @author princehaku
 */
public class Combiner{
     String key="";
     public Combiner(String key){
        this.key=key;
     }
    public void run() throws Exception {
        Process p;
        String cmd = "javac -g:none "+BuilderServlet.tmpPath+"Key.java";
        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
        } catch (Exception e) {
            throw e;
        }

    }

}
