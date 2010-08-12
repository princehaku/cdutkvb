/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net._3haku.kvb;

import net._3haku.course.CourseList;
import net._3haku.course.Course;
import net._3haku.util.StringTokenizer;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.pim.Event;
import javax.microedition.pim.EventList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;
import javax.microedition.pim.PIMItem;
import net._3haku.util.Date;
import org.netbeans.microedition.lcdui.LoginScreen;

/**
 * @author princehaku
 */
public class HelloMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command exitCommand;
    private Command okCommand;
    private Form form;
    private StringItem stringItem;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The HelloMIDlet constructor.
     */
    public HelloMIDlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|19-preAction
            if (command == exitCommand) {//GEN-END:|7-commandAction|1|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|19-postAction
                // write post-action user code here
            } else if (command == okCommand) {//GEN-LINE:|7-commandAction|3|24-preAction
                // write pre-action user code here
                         int weekNums=0;
                         int courseNums=0;
                         int parNums=0;
                         StringTokenizer st = new StringTokenizer("20@15@194|数@数字信号处理@理论@7404|数@数字信号处理@实验@6A502|数1@数字逻辑@理论@4301|数1@数字逻辑@实验@6B602|计@计算机组成基础@理论@4502|计@计算机组成基础@实验@6C1001|计1@计算机网络原理@理论@7404|计1@计算机网络原理@实验@6A502|编@编译原理@理论@4502|计2@计算机图形学@理论@6B201|计2@计算机图形学@实验@6A502|操@操作系统原理@理论@6A104|操@操作系统原理@实验@6A502|软@软件工程课程设计@实习@6A110|形@形势与政策@理论@6C405|操@2010-09-01@08:10@09:45|数1@2010-09-01@10:15@11:50|形@2010-09-01@14:30@16:00|计@2010-09-02@08:10@09:45|编@2010-09-02@10:15@11:50|数@2010-09-02@16:10@17:50|计2@2010-09-03@14:30@16:55|计@2010-09-06@08:10@09:45|操@2010-09-06@10:15@11:50|编@2010-09-07@14:30@16:00|数@2010-09-07@16:10@17:50|操@2010-09-08@08:10@09:45|数1@2010-09-08@10:15@11:50|计@2010-09-09@08:10@09:45|编@2010-09-09@10:15@11:50|数@2010-09-09@16:10@17:50|计2@2010-09-10@14:30@16:55|计@2010-09-13@08:10@09:45|操@2010-09-13@10:15@11:50|编@2010-09-14@14:30@16:00|数@2010-09-14@16:10@17:50|操@2010-09-15@08:10@09:45|数1@2010-09-15@10:15@11:50|形@2010-09-15@14:30@16:00|计@2010-09-16@08:10@09:45|编@2010-09-16@10:15@11:50|数@2010-09-16@16:10@17:50|计2@2010-09-17@14:30@16:55|计@2010-09-19@08:10@09:45|编@2010-09-19@10:15@11:50|数@2010-09-19@16:10@17:50|计@2010-09-20@08:10@09:45|操@2010-09-20@10:15@11:50|编@2010-09-21@14:30@16:00|数@2010-09-21@16:10@17:50|计2@2010-09-25@14:30@16:55|操@2010-09-26@08:10@09:45|数1@2010-09-26@10:15@11:50|形@2010-09-26@14:30@16:00|计@2010-09-27@08:10@09:45|操@2010-09-27@10:15@11:50|编@2010-09-28@14:30@16:00|数@2010-09-28@16:10@17:50|操@2010-09-29@08:10@09:45|数1@2010-09-29@10:15@11:50|计@2010-09-30@08:10@09:45|编@2010-09-30@10:15@11:50|数@2010-09-30@16:10@17:50|计1@2010-10-08@10:15@11:50|计2@2010-10-08@14:30@16:55|计@2010-10-09@08:10@09:45|编@2010-10-09@10:15@11:50|数@2010-10-09@16:10@17:50|计@2010-10-11@08:10@09:45|操@2010-10-11@10:15@11:50|编@2010-10-12@14:30@16:00|数@2010-10-12@16:10@17:50|操@2010-10-13@08:10@09:45|数1@2010-10-13@10:15@11:50|计@2010-10-14@08:10@09:45|编@2010-10-14@10:15@11:50|数@2010-10-14@16:10@17:50|计1@2010-10-15@10:15@11:50|计2@2010-10-15@14:30@16:55|计@2010-10-18@08:10@09:45|操@2010-10-18@16:10@17:50|编@2010-10-19@14:30@16:00|数@2010-10-19@16:10@17:50|操@2010-10-20@08:10@09:45|数1@2010-10-20@10:15@11:50|形@2010-10-20@14:30@16:00|计@2010-10-21@08:10@09:45|编@2010-10-21@10:15@11:50|数@2010-10-21@16:10@17:50|计1@2010-10-22@10:15@11:50|计2@2010-10-22@14:30@16:55|计@2010-10-25@08:10@09:45|计1@2010-10-25@10:15@11:50|操@2010-10-25@16:10@17:50|操@2010-10-25@19:10@20:40|计2s1@2010-10-26@08:10@09:45|数s1@2010-10-26@10:15@11:50|编@2010-10-26@14:30@16:00|数@2010-10-26@16:10@17:50|操@2010-10-27@08:10@09:45|数1@2010-10-27@10:15@11:50|计@2010-10-28@08:10@09:45|编@2010-10-28@10:15@11:50|数@2010-10-28@16:10@17:50|计1@2010-10-29@10:15@11:50|计2@2010-10-29@14:30@16:55|计@2010-11-01@08:10@09:45|计1@2010-11-01@10:15@11:50|操@2010-11-01@16:10@17:50|操@2010-11-01@19:10@20:40|计2s1@2010-11-02@08:10@09:45|数s1@2010-11-02@10:15@11:50|编@2010-11-02@14:30@16:00|数@2010-11-02@16:10@17:50|操@2010-11-03@08:10@09:45|数1@2010-11-03@10:15@11:50|形@2010-11-03@14:30@16:00|计@2010-11-04@08:10@09:45|编@2010-11-04@10:15@11:50|数@2010-11-04@16:10@17:50|计1@2010-11-05@10:15@11:50|计2@2010-11-05@14:30@16:55|计@2010-11-08@08:10@09:45|计1@2010-11-08@10:15@11:50|操@2010-11-08@16:10@17:50|操@2010-11-08@19:10@20:40|计2s1@2010-11-09@08:10@09:45|数s1@2010-11-09@10:15@11:50|编@2010-11-09@14:30@16:00|数@2010-11-09@16:10@17:50|操@2010-11-10@08:10@09:45|数1@2010-11-10@10:15@11:50|计@2010-11-11@08:10@09:45|编@2010-11-11@10:15@11:50|数@2010-11-11@16:10@17:50|计1@2010-11-12@10:15@11:50|计2@2010-11-12@14:30@16:55|计@2010-11-15@08:10@09:45|计1@2010-11-15@10:15@11:50|操@2010-11-15@16:10@17:50|操@2010-11-15@19:10@20:40|计2s1@2010-11-16@08:10@09:45|数s1@2010-11-16@10:15@11:50|编@2010-11-16@14:30@16:00|数1@2010-11-17@10:15@11:50|形@2010-11-17@14:30@16:00|计@2010-11-18@08:10@09:45|编@2010-11-18@10:15@11:50|计s3@2010-11-18@16:10@17:50|计1@2010-11-19@10:15@11:50|计2@2010-11-19@14:30@16:55|计@2010-11-22@08:10@09:45|计1@2010-11-22@10:15@11:50|计2s1@2010-11-23@08:10@09:45|编@2010-11-23@14:30@16:00|数1@2010-11-24@10:15@11:50|计@2010-11-25@08:10@09:45|编@2010-11-25@10:15@11:50|计s3@2010-11-25@16:10@17:50|计1@2010-11-26@10:15@11:50|计2@2010-11-26@14:30@16:55|计@2010-11-29@08:10@09:45|计1@2010-11-29@10:15@11:50|计2s1@2010-11-30@08:10@09:45|计1s1@2010-11-30@10:15@11:50|编@2010-11-30@14:30@16:00|数1@2010-12-01@10:15@11:50|形@2010-12-01@14:30@16:00|计@2010-12-02@08:10@09:45|编@2010-12-02@10:15@11:50|计s3@2010-12-02@16:10@17:50|数1s3@2010-12-03@08:10@09:45|计1@2010-12-03@10:15@11:50|计2@2010-12-03@14:30@16:00|计@2010-12-06@08:10@09:45|计1@2010-12-06@10:15@11:50|计2s1@2010-12-07@08:10@09:45|计1s1@2010-12-07@10:15@11:50|编@2010-12-07@14:30@16:00|数1@2010-12-08@10:15@11:50|计@2010-12-09@08:10@09:45|编@2010-12-09@10:15@11:50|计s3@2010-12-09@16:10@17:50|数1s3@2010-12-10@08:10@09:45|计1@2010-12-10@10:15@11:50|计2@2010-12-10@14:30@16:00|计1@2010-12-13@10:15@11:50|软s1@2010-12-13@14:30@16:00|计2s1@2010-12-14@08:10@09:45|计1s1@2010-12-14@10:15@11:50|软s1@2010-12-14@14:30@17:50|数1@2010-12-15@10:15@11:50|形@2010-12-15@14:30@16:00|软s1@2010-12-16@08:10@11:50|数1s3@2010-12-17@08:10@09:45|计1@2010-12-17@10:15@11:50|软s1@2010-12-17@14:30@17:50|计1@2010-12-20@10:15@11:50|软s1@2010-12-20@14:30@17:50|计1s1@2010-12-21@10:15@11:50|软s1@2010-12-21@14:30@17:50|数1@2010-12-22@10:15@11:50|软s1@2010-12-23@08:10@11:50|数1s3@2010-12-24@08:10@09:45|软s1@2010-12-28@08:10@11:50|形@2010-12-29@15:20@16:00|软s1@2010-12-29@19:10@21:35|软s1@2010-12-30@19:10@21:35|软s1@2010-12-31@14:30@17:50|end","|");
                         CourseList cl=new CourseList();
                         int i=1;
                         while(st.hasMoreTokens())
                         {
                             String rspart=st.nextToken();
                             if(rspart.indexOf("end")!=-1)
                             {
                                 break;
                             }
                             if(i==1)
                             {
                                StringTokenizer st1 = new StringTokenizer(rspart,"@");
                                weekNums=Integer.parseInt(st1.nextToken());
                                courseNums=Integer.parseInt(st1.nextToken());
                                parNums=Integer.parseInt(st1.nextToken());
                             }
                             if(i>1&&i-1<=courseNums)
                             {
                                 //这里面的是课程
                                 StringTokenizer st1 = new StringTokenizer(rspart,"@");
                                 Course c=new Course(st1.nextToken(),st1.nextToken(),st1.nextToken(),st1.nextToken());
                                 cl.add(c);
                             }
                             if(i-1>weekNums)
                             {
                                 //这里面的是课
                                StringTokenizer st1 = new StringTokenizer(rspart, "@");
                                String idxname=st1.nextToken();
                                String coursefullname = (cl.getFullName(idxname));
                                String dat = st1.nextToken();
                                String startdat = dat + " " + st1.nextToken();
                                String enddat = dat + " " + st1.nextToken();
                                try {
                                    EventList list;
                                    list = (EventList) PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.READ_WRITE);
                                    Event item;
                                    Event ev =list.createEvent();
                                    ev.addDate(Event.START, PIMItem.DATE,Date.ToTimeSpan(startdat+":00"));
                                    ev.addDate(Event.END, PIMItem.DATE,Date.ToTimeSpan(enddat+":00"));
                                    ev.addString(Event.SUMMARY, PIMItem.ATTR_NONE,coursefullname+"["+cl.getType(idxname)+"]"+ cl.getPlace(idxname));
                                    ev.commit();
                                    this.form.append(coursefullname+"添加成功\n");
                                } catch (PIMException ex) {
                                    this.form.append(coursefullname+"添加失败\n");
                                }
                             }
                             i++;
                         }
//GEN-LINE:|7-commandAction|4|24-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|7-postCommandAction
        }//GEN-END:|7-commandAction|5|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|6|27-postAction
    //</editor-fold>//GEN-END:|7-commandAction|6|27-postAction



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            form = new Form("Welcome", new Item[] { getStringItem() });//GEN-BEGIN:|14-getter|1|14-postInit
            form.addCommand(getExitCommand());
            form.addCommand(getOkCommand());
            form.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return form;
    }
    //</editor-fold>//GEN-END:|14-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|16-getter|0|16-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|16-getter|0|16-preInit
            // write pre-init user code here
            stringItem = new StringItem("Hello", "Hello, World!");//GEN-LINE:|16-getter|1|16-postInit
            // write post-init user code here
        }//GEN-BEGIN:|16-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|16-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|23-getter|2|



    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

}
