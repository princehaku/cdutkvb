/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-1, 22:31:48
 */

package org.me.androidkvb;

import VirtualBroser.VB;
import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author princehaku
 */
public class MainActivity extends Activity implements OnClickListener{
    /**确认的按钮
     *
     */
    Button bt;
    /**学号
     *
     */
    String sid="";

    /**密码
     *
     */
    String pwd="";
    /**从GAE得到的序列化后的课表串
     * 
     */
    static String result;
    /**结果Handler
     *
     */
    public Handler resultHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setResultString(msg.getData().getString("result"));
            statu=Integer.parseInt((msg.getData().getString("statu")));
            super.handleMessage(msg);
        }
    };
    //总共需要处理的节数
    static int tolnums=0;
    //当前正在处理的节数
    static int nownums=0;
    static String buffspace="";
    ParseThread t=new ParseThread();
    public Handler processbarHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            t.statuHandler.sendEmptyMessage(0);
            int nowpos=245*msg.what/100;
            if(msg.what>100)
                 nowpos=245;
            if(msg.what<0)
                nowpos=0;
            int i=nownums*100/tolnums;
            //((Button)(findViewById(R.id.barnow))).setWidth(nowpos);
            ((Button)(findViewById(R.id.barnow))).setText(i+"%");
            //增加美观度
            if(((TextView)(findViewById(R.id.console))).getLineCount()>20){
                 ((TextView)(findViewById(R.id.console))).setText("");
            }
            ((TextView)(findViewById(R.id.console))).setText(((TextView)(findViewById(R.id.console))).getText()+buffspace);
            buffspace="";
            t.statuHandler.sendEmptyMessage(1);
            super.handleMessage(msg);
        }
    };
    public Handler consoleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.getData().getString("add")!=null)
                ((TextView)(findViewById(R.id.console))).setText(((TextView)(findViewById(R.id.console))).getText()+
                    msg.getData().getString("add")+"\n");
            super.handleMessage(msg);
        }
    };
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    //返回结果串
   public static String getResultString() {
        if(result==null)
            result="";
            return result;
    }
    public void setResultString(String result) {
        this.result = result;
    }
    /**-------------------------------------
     * 状态信息
     * 0   无法连接服务器
     * 1   登陆失败 (超时)
     * 2   登陆失败  (密码错误)
     * 3   获取课表url失败 (超时)
     * 4   获取课表HTML失败 (超时)
     * 5   正常
     * -------------------------------------
     */
    int statu=0;
    Alert alert;
    public Alert getAlert(){
        if(alert==null)alert=new Alert(this);
        return alert;
    }
    /**已经重试的次数
     * 
     */
    int retried=0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(this);
        }
    public Handler statuHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1)
            {
                //显示进度圈圈
                ((ProgressBar)(findViewById(R.id.processbar))).setVisibility(0);
                //隐藏button
                bt.setVisibility(4);
            }
            else if(msg.what==3)
            {
                //新的视图载入并开始进行课程解析
                getAlert().destory();
                setContentView(R.layout.process);
                //定时器 间隔一定时间将buff里面的存入到视图区
                Timer tr=new Timer();
                tr.schedule(new TimerTask(){
                     @Override
                     public void run() {
                     //Message msg = new Message();
                     //Bundle ble = new Bundle();
                     processbarHandler.sendEmptyMessage(1);
//                    msg = new Message();
//                    ble = new Bundle();
//                    ble.putString("add",st.nextToken());
//                    msg.setData(ble);
//                    consoleHandler.sendMessage(msg);
                    }
                },0,300);
                //进程 不做视图处理  负责后台建立进程
                 t.start();
            }
            else
            {
                //隐藏进度圈圈
                ((ProgressBar)(findViewById(R.id.processbar))).setVisibility(4);
                //显示button
                bt.setVisibility(0);
                super.handleMessage(msg);
            }
        }
    };
    /**点击按钮的事件
     * 
     */
    public void onClick(View arg0) {
        sid="";
        pwd="";
        statuHandler.sendEmptyMessage(1);
        //用户的学号
        EditText text1=(EditText)findViewById(R.id.sid);
        sid=text1.getText().toString();
        //用户的密码
        text1=(EditText)findViewById(R.id.pwd);
        pwd=text1.getText().toString();
        //启动进程获取课表
        fetchHTML();
        //定时器  检测消息
        Timer t=new Timer();
        t.schedule(new TimerTask(){
            @Override
            public void run() {
                if(!getResultString().equals(""))
                {
                    int r=0;
                    switch(statu)
                    {
                        case 1:
                            getAlert().show("Error","登陆超时 请重试");
                        break;
                        case 2:
                            getAlert().show("Error","登陆失败 账号名或密码错误");
                        break;
                        case 3:
                            getAlert().show("Error","获取课表链接超时 请重试");
                        break;
                        case 4:
                            getAlert().show("Error","获取课表信息超时 请重试");
                        break;
                        case 5:
                            r=3;
                        break;
                        default:
                              getAlert().show("Error","无法与服务器通信"+getResultString());
                        break;
                    }
                        statuHandler.sendEmptyMessage(r);
                        this.cancel();
                }
            }
        }, 0, 300);
        
    }
    private void addEvent()
    {
          ContentValues event = new ContentValues();
          event.put("title", "Event Title");
          event.put("description", "Event Desc");
          event.put("eventLocation", "Event Location");
          long startTime = 120000000;
          long endTime = 120010200;
          event.put("dtstart", startTime);
          event.put("dtend", endTime);
          Uri eventsUri = Uri.parse("content://calendar/events");
          getContentResolver().insert(eventsUri, event);
    }
    private void fetchHTML() {
       result="";
       statu=0;
       getAlert().show("Please Wait","开始联网获取课表信息");
       Thread t=new Thread(){
        @Override
        public void run() {
             Message msg = new Message();
             Bundle ble = new Bundle();
             VB vb=new VB();
             try {
                 String sresult= vb.get("http://cdutkvb.appspot.com/fetch?s="+getSid()+"&p="+getPwd(), "utf8");
                 ble.clear();
                 ble.putString("result",sresult);
                 //String sints=sresult.substring((int) (sresult.indexOf("error") + 5),sresult.length());
                 ble.putString("statu","5");
                 if(sresult.indexOf("error0")!=-1)
                 {
                    ble.remove("statu");
                    ble.putString("statu","0");
                 }
                 if(sresult.indexOf("error1")!=-1)
                 {
                    ble.remove("statu");
                    ble.putString("statu","1");
                 }
                 if(sresult.indexOf("error2")!=-1)
                 {
                    ble.remove("statu");
                    ble.putString("statu","2");
                 }
                 if(sresult.indexOf("error3")!=-1)
                 {
                    ble.remove("statu");
                    ble.putString("statu","3");
                 }
                 if(sresult.indexOf("error4")!=-1)
                 {
                    ble.remove("statu");
                    ble.putString("statu","4");
                 }
                 msg.setData(ble);
              } catch (Exception ex) {
                 ble.clear();
                 ble.putString("result",ex.getMessage());
                 //String sints=sresult.substring((int) (sresult.indexOf("error") + 5),sresult.length());
                 ble.putString("statu","0");
              }
                 resultHandler.sendMessage(msg);
            }
        };
       t.start();
    }

}