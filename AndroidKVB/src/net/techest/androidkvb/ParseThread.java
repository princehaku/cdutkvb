/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-8, 15:44:50
 */
package net.techest.androidkvb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**用于解析从服务器返回的数据
 *
 * @author princehaku
 */
public class ParseThread extends Thread {

    /**资源
     *
     */
    private static MainActivity res;
    
    ParseThread(MainActivity res) {
        ParseThread.res = res;
    }
    private boolean islock = false;

    public boolean isIslock() {
        return islock;
    }

    public void setIslock(boolean islock) {
        this.islock = islock;
    }
    public Handler statuHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            //0是暂停
            if (msg.what == 0) {
                setIslock(true);
            }
            if (msg.what == 1) {
                setIslock(false);
            }

            super.handleMessage(msg);
        }
    };

    @Override
    @SuppressWarnings("static-access")
    public void run() {
        int parNums = 0;
        String coursefullname = "";
        //CourseList cl = new CourseList();
        StringTokenizer st = new StringTokenizer(res.getResultString(), "|");
        res.tolnums = st.countTokens();
        int i = 1;
        String rspart = "";
        while (st.hasMoreTokens()) {
            rspart = st.nextToken();
            if (rspart.indexOf("end") != -1) {
                break;
            }
            //Log.v(MainActivity.ACTIVITY_SERVICE, i+"");
            if (i == 1) {
                StringTokenizer st1 = new StringTokenizer(rspart, "@");
                parNums = Integer.parseInt(st1.nextToken());
            }
            if (i > 1 ) {
                //这里面的是课程
                StringTokenizer st1 = new StringTokenizer(rspart, "@");
                Course c = new Course(st1.nextToken(), st1.nextToken(),st1.nextToken(), st1.nextToken(), st1.nextToken());
                //cl.add(c);
                //这里面的是课
                coursefullname = c.getCourseName();
                String sttime = st1.nextToken();
                String edtime = st1.nextToken();
                String stdate = st1.nextToken();
                String eddate = st1.nextToken();
                //创建msg 并发送给主线程
                Message msg = new Message();
                Bundle ble = new Bundle();
                ble.putString("title", coursefullname+"["+c.getCourseType()+"]");
                ble.putString("desp", c.getCoursePlace());
                ble.putString("location", c.getCoursePlace());
                ble.putString("sttime", sttime);
                ble.putString("edtime", edtime);
                ble.putString("stdate", stdate);
                ble.putString("eddate", eddate);
                msg.setData(ble);
                res.eventHandler.sendMessage(msg);
            }
            //总行数加1
            res.nownums = i++;
            //用于阻塞消息
            while (1 == 1) {
                try {
                    this.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ParseThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!isIslock()) {
                    res.buffspace = res.buffspace + rspart + "加入成功\n";
                    break;
                }
            }

        }
        //设置完成标志为1
        res.statu = 6;
    }
}
