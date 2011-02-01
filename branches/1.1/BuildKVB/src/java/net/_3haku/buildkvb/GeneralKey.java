/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-24, 23:48:08
 */

package net._3haku.buildkvb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 * @author princehaku
 */
public class GeneralKey{
    String key="";
    String pattern="package net._3haku.key;\npublic class Key { public static String key=\"keys\";}";
    public GeneralKey(String key)
    {
        this.key=key;
        pattern=pattern.replaceAll("keys",key);
    }
    public void run() throws Exception  {
        try {
            File f=new File(BuilderServlet.tmpPath+"Key.java");
            //System.out.print(f.getAbsoluteFile().toString());
            f.createNewFile();
            OutputStream is = new FileOutputStream(BuilderServlet.tmpPath+"Key.java");
            is.write(pattern.getBytes());
            is.close();
        } catch (Exception e) {
            throw e;
        }
    }
    public String getFileContent()
    {
        return pattern;
    }
}
