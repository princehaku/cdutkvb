/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net._3haku.kvb;

import net._3haku.util.StringTokenizer;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.pim.Event;
import javax.microedition.pim.EventList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMItem;
import javax.microedition.pim.RepeatRule;
import net._3haku.key.Key;
import net._3haku.util.Date;
import net._3haku.util.Source;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author princehaku
 */
public class MainMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">
    private Command exitCommand;
    private Command okCommand;
    private Form form;
    private TextField textField;
    private TextField textField1;
    private ChoiceGroup choiceGroup;
    private TextField textField2;
    private ImageItem imageItem;
    private WaitScreen waitScreen;
    private Alert alert;
    private Alert alert1;
    private SimpleCancellableTask task;
    //</editor-fold>

    /**
     * The MainMIDlet constructor.
     */
    public MainMIDlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {
        // write pre-initialize user code here
 
        // write post-initialize user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {
        // write pre-action user code here
        switchDisplayable(null, getForm());
        // write post-action user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {
        // write pre-action user code here
 
        // write post-action user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {
        // write pre-switch user code here
        Display display = getDisplay();
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }
        // write post-switch user code here
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {
        // write pre-action user code here
        if (displayable == form) {
            if (command == exitCommand) {
                // write pre-action user code here
                exitMIDlet();
                // write post-action user code here
            } else if (command == okCommand) {
                // write pre-action user code here
                switchDisplayable(null, getWaitScreen());
                // write post-action user code here
            }
        } else if (displayable == waitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {
                // write pre-action user code here
                switchDisplayable(getAlert1(), getForm());
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {
                // write pre-action user code here
                switchDisplayable(getAlert(), getForm());
                // write post-action user code here
            }
        }
        // write post-action user code here
    }
    //</editor-fold>

    /**解析串并加入到手机日程表
     *
     */
    private void addintophone() throws Exception {
        int parNums = 0;
        try {
            StringTokenizer st = new StringTokenizer(getResReturn(), "|");
            int i = 1;
            while (st.hasMoreTokens()) {
                String rspart = st.nextToken();
                if (rspart.indexOf("end") != -1) {
                    break;
                }
                if (i == 1) {
                    parNums = Integer.parseInt(rspart);
                }
                if (i > 1 && i <= parNums) {
                    //这里面的是课程
                    StringTokenizer st1 = new StringTokenizer(rspart, "@");
                    String coursefullname = st1.nextToken();
                    String coursePlace = st1.nextToken();
                    String courseType = st1.nextToken();
                    String courseScorce = st1.nextToken();
                    String courseTeacher = st1.nextToken();
                    String coursestTime = st1.nextToken();
                    String courseedTime = st1.nextToken();
                    String coursestDate = st1.nextToken();
                    String courseedDate = st1.nextToken();
                    int per = i * 100 / parNums;
                    //处理描述
                    this.getTextField2().getString();
                    String summary=coursefullname + "[" + courseType + "]" + coursePlace;
                    //System.out.println(coursefullname+coursePlace+courseType+coursestTime+courseedTime+coursestDate+courseedDate);
                    //System.out.println(Date.ToTimeSpan(coursestDate+" "+coursestTime + ":00")+"");
                    //System.out.println(Date.ToWeek(coursestDate+" "+coursestTime + ":00")+"");
                    try {
                        EventList list;
                        list = (EventList) PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.READ_WRITE);
                        Event ev = list.createEvent();
                        ev.addDate(Event.START, PIMItem.DATE, Date.ToTimeSpan(coursestDate + " " + coursestTime + ":00"));
                        ev.addDate(Event.END, PIMItem.DATE, Date.ToTimeSpan(coursestDate + " " + courseedTime + ":00"));
                        ev.addString(Event.SUMMARY, PIMItem.STRING, summary);
                        //如果开始和结束时间不一样则写入重复时间规则
                        if (!coursestDate.equals(courseedDate)) {
                            RepeatRule rpRule = new RepeatRule();
                            rpRule.setInt(RepeatRule.FREQUENCY, RepeatRule.WEEKLY);
                            int week = (Date.ToWeek(coursestDate + " " + coursestTime + ":00"));
                            int weekinRule = RepeatRule.SUNDAY;
                            switch (week) {
                                case 1:
                                    weekinRule = RepeatRule.MONDAY;
                                    break;
                                case 2:
                                    weekinRule = RepeatRule.TUESDAY;
                                    break;
                                case 3:
                                    weekinRule = RepeatRule.THURSDAY;
                                    break;
                                case 4:
                                    weekinRule = RepeatRule.WEDNESDAY;
                                    break;
                                case 5:
                                    weekinRule = RepeatRule.FRIDAY;
                                    break;
                                case 6:
                                    weekinRule = RepeatRule.SATURDAY;
                                    break;
                                case 7:
                                    weekinRule = RepeatRule.SUNDAY;
                                    break;
                            }
                            rpRule.setInt(RepeatRule.DAY_IN_WEEK, weekinRule);
                            rpRule.setDate(RepeatRule.END, Date.ToTimeSpan(courseedDate + " " + courseedTime + ":00"));
                            ev.setRepeat(rpRule);
                        }
                        ev.commit();
                        getWaitScreen().setText(coursefullname + "添加成功\n" + per + "%");
                    } catch (Exception ex) {
                        System.out.print(ex.getMessage());
                        getWaitScreen().setText(coursefullname + "添加失败\n" + per + "%");
                        //continue;
                        throw new Exception(ex.getMessage());
                    }
                }
                i++;
            }
        } catch (Exception ex) {
            throw ex;
        }
        setStatuCode(100);
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);
            // write post-init user code here
        }
        return exitCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">
    /**
     * Returns an initiliazed instance of form component.
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {
            // write pre-init user code here
            form = new Form("Welcome", new Item[] { getImageItem(), getTextField(), getTextField1(), getTextField2(), getChoiceGroup() });
            form.addCommand(getExitCommand());
            form.addCommand(getOkCommand());
            form.setCommandListener(this);
            // write post-init user code here
        }
        return form;
    }
    //</editor-fold>



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);
            // write post-init user code here
        }
        return okCommand;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField ">
    /**
     * Returns an initiliazed instance of textField component.
     * @return the initialized component instance
     */
    public TextField getTextField() {
        if (textField == null) {
            // write pre-init user code here
            textField = new TextField("\u5B66\u53F7", "", 32, TextField.NUMERIC);
            textField.setInitialInputMode("UCB_BASIC_LATIN");
            // write post-init user code here
        }
        return textField;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: waitScreen ">
    /**
     * Returns an initiliazed instance of waitScreen component.
     * @return the initialized component instance
     */
    public WaitScreen getWaitScreen() {
        if (waitScreen == null) {
            // write pre-init user code here
            waitScreen = new WaitScreen(getDisplay());
            waitScreen.setTitle("waitScreen");
            waitScreen.setCommandListener(this);
            waitScreen.setFullScreenMode(true);
            waitScreen.setText("\u8BF7\u7A0D\u540E");
            waitScreen.setTask(getTask());
            // write post-init user code here
        }
        return waitScreen;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">
    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {
            // write pre-init user code here
            task = new SimpleCancellableTask();
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {
                    // write task-execution user code here

                    if (getStatuCode() == 100) {
                        exitMIDlet();
                    }
                    int networktype = 1;
                    networktype = getChoiceGroup().isSelected(1) ? 1 : 2;
                    //联网登陆..结果存入statu
                    String sid = getTextField().getString();
                    String pwd = getTextField1().getString();
                    Source a = new Source();
                    getWaitScreen().setText("开始联网获取信息");
                    String res = "";
                    try {
                        res = a.get("http://tecest.com/fetch?k="+Key.key+"&s=" + sid + "&p=" + pwd, "UTF-8", networktype);
                    } catch (Exception ex) {
                        setStatuCode(0);
                        setResReturn(ex.getMessage());
                        getAlert1().setString(ex.getMessage());
                    }
                    //System.out.println(res);
                    setResReturn(res);
                    setStatuCode(99);
                    if (res.indexOf("error0") != -1 || res.length() < 200) {
                        setStatuCode(0);
                    }
                    if (res.indexOf("error1") != -1) {
                        setStatuCode(1);
                    }
                    if (res.indexOf("error2") != -1) {
                        setStatuCode(2);
                    }
                    if (res.indexOf("error3") != -1) {
                        setStatuCode(3);
                    }
                    if (res.indexOf("error4") != -1) {
                        setStatuCode(4);
                    }
                    if (res.indexOf("error5") != -1) {
                        setStatuCode(5);
                    }
                    if (res.indexOf("error6") != -1) {
                        setStatuCode(6);
                    }
                    setStatuCode(statuCode);
                    if (!HandleCode()) {
                        getWaitScreen().setText("请稍后");
                        throw new Exception("!");
                    } else {
                        try {
                            addintophone();
                        } catch (Exception ex) {
                            getAlert1().setString(ex.getMessage());
                            throw ex;
                        }
                    }
                }
            });
            // write post-init user code here
        }
        return task;
    }
    //</editor-fold>

    /**处理错误
     * 
     */
    private boolean HandleCode() {
        System.out.println("code!!!" + getStatuCode());
        switch (getStatuCode()) {
            case 0:
                getAlert1().setString("无法与服务器通信" + getResReturn());
                break;
            case 1:
                getAlert1().setString("登陆超时 请重试");
                break;
            case 2:
                getAlert1().setString("登陆失败 账号名或密码错误");
                break;
            case 3:
                getAlert1().setString("获取课表链接超时 请重试");
                break;
            case 4:
                getAlert1().setString("获取课表信息超时 请重试");
                break;
            case 5:
                getAlert1().setString("内部服务器错误 请等待服务器恢复再试");
                break;
            case 6:
                getAlert1().setString("对不起.您当前使用的版本是盗版\n..请到http://3haku.net下载正版");
                break;
            case 99:
                return true;
            default:
                getAlert1().setString("无法与服务器通信" + getResReturn());
                break;
        }
        return false;
    }
    /**从服务器返回的串
     *
     */
    private String resReturn;

    public String getResReturn() {
        return resReturn;
    }

    public void setResReturn(String resReturn) {
        this.resReturn = resReturn;
    }
    /**-------------------------------------
     * 状态信息
     * 0   无法连接服务器
     * 1   登陆失败 (超时)
     * 2   登陆失败  (密码错误)
     * 3   获取课表url失败 (超时)
     * 4   获取课表HTML失败 (超时)
     * 5   正常
     * 6   处理结束
     * -------------------------------------
     */
    private int statuCode;

    public int getStatuCode() {
        return statuCode;
    }

    public void setStatuCode(int statuCode) {
        this.statuCode = statuCode;
    }
    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField1 ">
    /**
     * Returns an initiliazed instance of textField1 component.
     * @return the initialized component instance
     */
    public TextField getTextField1() {
        if (textField1 == null) {
            // write pre-init user code here
            textField1 = new TextField("\u5BC6\u7801", "", 32, TextField.ANY);
            textField1.setInitialInputMode("UCB_BASIC_LATIN");
            // write post-init user code here
        }
        return textField1;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert ">
    /**
     * Returns an initiliazed instance of alert component.
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (alert == null) {
            // write pre-init user code here
            alert = new Alert("success", "\u64CD\u4F5C\u5B8C\u6210", null, AlertType.INFO);
            alert.setTimeout(Alert.FOREVER);
            // write post-init user code here
        }
        return alert;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert1 ">
    /**
     * Returns an initiliazed instance of alert1 component.
     * @return the initialized component instance
     */
    public Alert getAlert1() {
        if (alert1 == null) {
            // write pre-init user code here
            alert1 = new Alert("error", "\u53D1\u751F\u4E86\u9519\u8BEF..\u8BF7\u91CD\u8BD5", null, null);
            alert1.setTimeout(Alert.FOREVER);
            // write post-init user code here
        }
        return alert1;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceGroup ">
    /**
     * Returns an initiliazed instance of choiceGroup component.
     * @return the initialized component instance
     */
    public ChoiceGroup getChoiceGroup() {
        if (choiceGroup == null) {
            // write pre-init user code here
            choiceGroup = new ChoiceGroup("\u8054\u7F51\u65B9\u5F0F", Choice.EXCLUSIVE);
            choiceGroup.append("WAP", null);
            choiceGroup.append("GPRS", null);
            choiceGroup.setFitPolicy(Choice.TEXT_WRAP_ON);
            choiceGroup.setSelectedFlags(new boolean[] { true, false });
            // write post-init user code here
        }
        return choiceGroup;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: textField2 ">
    /**
     * Returns an initiliazed instance of textField2 component.
     * @return the initialized component instance
     */
    public TextField getTextField2() {
        if (textField2 == null) {
            // write pre-init user code here
            textField2 = new TextField("\u81EA\u5B9A\u4E49\u6807\u9898", "{\u8BFE\u7A0B\u540D\u79F0}[{\u8BFE\u7A0B\u7C7B\u578B}]{\u4E0A\u8BFE\u5730\u70B9}", 32, TextField.ANY);
            // write post-init user code here
        }
        return textField2;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageItem ">
    /**
     * Returns an initiliazed instance of imageItem component.
     * @return the initialized component instance
     */
    public ImageItem getImageItem() {
        if (imageItem == null) {
            // write pre-init user code here
            imageItem = new ImageItem("\u6B22\u8FCE\u4F7F\u7528CdutKVB  ", null, ImageItem.LAYOUT_DEFAULT, "");
            // write post-init user code here
        }
        return imageItem;
    }
    //</editor-fold>

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
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
