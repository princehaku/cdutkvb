/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-8, 15:44:50
 */
package org.me.androidkvb;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.StringTokenizer;

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
        this.res = res;
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
    public void run() {
        int weekNums = 0;
        int courseNums = 0;
        int parNums = 0;
        String coursefullname = "";
        CourseList cl = new CourseList();
        StringTokenizer st = new StringTokenizer(res.getResultString(), "|");
        res.tolnums = st.countTokens();
        int i = 1;
        String rspart = "";
        while (st.hasMoreTokens()) {
            rspart = st.nextToken();
            if (rspart.indexOf("end") != -1) {
                break;
            }
            if (i == 1) {
                StringTokenizer st1 = new StringTokenizer(rspart, "@");
                weekNums = Integer.parseInt(st1.nextToken());
                courseNums = Integer.parseInt(st1.nextToken());
                parNums = Integer.parseInt(st1.nextToken());
            }
            if (i > 1 && i - 1 <= courseNums) {
                //这里面的是课程
                StringTokenizer st1 = new StringTokenizer(rspart, "@");
                Course c = new Course(st1.nextToken(), st1.nextToken(), st1.nextToken(), st1.nextToken());
                cl.add(c);
            }
            if (i - 1 > courseNums) {
                //这里面的是课
                StringTokenizer st1 = new StringTokenizer(rspart, "@");
                String idxname=st1.nextToken();
                coursefullname = (cl.getFullName(idxname));
                String dat = st1.nextToken();
                String startdat = dat + " " + st1.nextToken();
                String enddat = dat + " " + st1.nextToken();
                //创建msg 并发送给主线程
                Message msg = new Message();
                Bundle ble = new Bundle();
                ble.putString("title", coursefullname+"["+cl.getType(idxname)+"]");
                ble.putString("desp", cl.getPlace(idxname));
                ble.putString("location", cl.getPlace(idxname));
                ble.putString("sttime", startdat);
                ble.putString("edtime", enddat);
                msg.setData(ble);
                res.eventHandler.sendMessage(msg);
            }
            //总行数加1
            res.nownums = i++;
            //用于阻塞消息
            while (1 == 1) {
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
