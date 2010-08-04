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
import net._3haku.kvb.CourseTable.CourseColumn;
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
                String kbhtml="";
                FileInputStream fs=new FileInputStream("src/resourse/kb.html");
                InputStreamReader read = new InputStreamReader (fs,"gb2312");
                BufferedReader   in   =   new   BufferedReader(read);
                while(in.ready())
                    kbhtml +=in.readLine();
                fs.close();
                CourseTable Tb=Parser.parseTable(kbhtml);
                //System.out.println(Tb.getRowNums());
                SchoolTime st=new WinterSchoolTime();
                for(int i=1;i<=Tb.getRowNums();i++)
                {
                   //一周开始的时间
                   System.out.println(Tb.getRowHead(i).getWeekTh());
                   int courest=1;
                   for(int o=1;o<=Tb.getColumnNums(i);o++)
                   {
                       CourseColumn cc=Tb.getColum(i, o);
                       System.out.println("--------------------------");
                       System.out.println(Tb.getColum(i, o).toString());
                       System.out.println("上课时间"+st.getStartTimeAt(courest).toString());
                       courest=courest+cc.getCrossSpan();
                       System.out.println("下课时间"+st.getEndTimeAt(courest-1).toString());
                       if(courest>11)courest=1;
                     }
                }
                 
    }

}
