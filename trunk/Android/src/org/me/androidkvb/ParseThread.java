/**
 * Copyright (c) 2010 princehaku
 * All right reserved.
 * Author princehaku
 * Site http://3haku.net
 * Created on : 2010-8-8, 15:44:50
 */

package org.me.androidkvb;

import android.os.Handler;
import android.os.Message;
import java.util.StringTokenizer;

/**
 *
 * @author princehaku
 */
public class ParseThread extends Thread{

                    public Handler statuHandler = new Handler() {

                        @Override
                        public void handleMessage(Message msg) {
                            if(msg.what==0)
                                //ParseThread.wait();
                            if(msg.what==1)
                                //ParseThread.resume();

                            super.handleMessage(msg);
                        }
                    };

                    @Override
                     public void run() {
                         StringTokenizer st = new StringTokenizer(MainActivity.getResultString(),"|");
                         MainActivity.tolnums=st.countTokens();
                         int i=1;
                         while(st.hasMoreTokens())
                         {
                             String rspart=st.nextToken();
                             MainActivity.nownums=i++;
                             MainActivity.buffspace +=rspart+"\n";
                         }
                         //processbarHandler.sendEmptyMessage(20);
                         //processbarHandler.sendEmptyMessage(40);
                         // consoleHandler.sendMessage(msg);
                        // msg = new Message();
                         //ble.putString("add","wawa");
                        // msg.setData(ble);
                     }
 }