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
import net._3haku.kvb.CourseTable.CourseTable;
import net._3haku.kvb.TimeFactory.SchoolTime;
import net._3haku.kvb.TimeFactory.WinterSchoolTime;
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
        //        String kbhtml="";
        //        FileInputStream fs=new FileInputStream("src/resourse/kb.html");
        //        InputStreamReader read = new InputStreamReader (fs,"gb2312");
        //        BufferedReader   in   =   new   BufferedReader(read);
        //        while(in.ready())
        //            kbhtml +=in.readLine();
        //        fs.close();
        //        CourseTable Tb=Parser.parseTable(kbhtml);
        //        System.out.println(Tb.getRowNums());
        //        System.out.println(Tb.getCoursesNums());
        //        System.out.println(Tb.getRowHead(20).getWeekTh());
        SchoolTime st=new WinterSchoolTime();
        System.out.println(st.getStartTimeAt(3).toString());
    }

}
