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
import android.app.AlertDialog;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    String sid;
    /**密码
     *
     */
    String pwd;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(this);
    }
    /**点击按钮的事件
     * 
     */
    public void onClick(View arg0) {
        //隐藏button
        bt.setVisibility(4);
        //用户的学号
        EditText text1=(EditText)findViewById(R.id.sid);
        sid=text1.getText().toString();
        //用户的密码
        text1=(EditText)findViewById(R.id.pwd);
        pwd=text1.getText().toString();
        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Information")
            .setMessage("开始联网获取课表")
            .show();
        //显示进度圈圈
       ((ProgressBar)(findViewById(R.id.processbar))).setVisibility(0);
       VB vb=new VB();
        try {
            String result = vb.post("http://g.cn", "upwd=be98c5a516&uname=200805030326&usertype=%D1%A7%C9%FA", "gb2312");
        } catch (Exception ex) {
        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Information")
            .setMessage("获取课表信息失败")
            .show();
        }
        addEvent();
    }
    private void addEvent()
    {
          ContentValues event = new ContentValues();
          event.put("calendar_id", 1);
          event.put("title", "Event Title");
          event.put("description", "Event Desc");
          event.put("eventLocation", "Event Location");
          long startTime = 120000000;
          long endTime = 120010200;
          event.put("dtstart", startTime);
          event.put("dtend", endTime);
          Uri eventsUri = Uri.parse("content://calendar/events");
          Uri url = getContentResolver().insert(eventsUri, event);
    }

}
