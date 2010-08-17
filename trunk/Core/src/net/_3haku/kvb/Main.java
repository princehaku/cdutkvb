/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net._3haku.kvb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.TimeZone;
import net._3haku.kvb.coursetable.CourseTable;
import net._3haku.kvb.util.Parser;

/**
 *
 * @author princehaku
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException, Exception {
        Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        String sresult="error1";
        //System.out.println(sresult.substring((int) (sresult.indexOf("error") + 5),sresult.length()));
        String kbhtml = "";
        FileInputStream fs = new FileInputStream("src/resource/kb.html");
        InputStreamReader read = new InputStreamReader(fs, "gb2312");
        BufferedReader in = new BufferedReader(read);
        while (in.ready()) {
            kbhtml += in.readLine();
        }
        fs.close();
        Parser psr=new Parser();
        CourseTable Tb = psr.parseTable(kbhtml);
        //System.out.println(Tb.getRowNums());
        
    }
}
